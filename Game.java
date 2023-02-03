package game;

import account.Account;
import account.Credentials;
import cell.CellType;
import entities.Character;
import entities.CharacterFactory;
import entities.Enemy;
import entities.Inventory;
import exceptions.InformationIncompleteException;
import exceptions.InvalidCommandException;
import graphic.FirstPage;
import grid.Grid;
import org.json.JSONArray;
import org.json.JSONObject;
import spells.*;
import utils.RNG;
import utils.TheBeginningOfTheGame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Game {
    public static Game newInstance;
    public ArrayList<Account> playerAccount;
    public Map<CellType , ArrayList<String>> stories;
    public Scanner scanner;
    public Grid map;

    public static Game getInstance(){
        if(newInstance == null)
            newInstance = new Game();
        return newInstance;
    }

    private Game() {
        try {
            this.playerAccount = readAccounts();
        } catch (InformationIncompleteException e) {
            e.printStackTrace();
        }
        try {
            this.stories = readStories();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.scanner = new Scanner(System.in);
        map = null;
    }

    public int getMenu() throws InvalidCommandException {
        System.out.println("0. Exit game");
        System.out.println("1. Go North");
        System.out.println("2. Go South");
        System.out.println("3. Go East");
        System.out.println("4. Go West");
        if(map.currentCell.type == CellType.ENEMY){
            System.out.println("5. Attack");
        }
        if(map.currentCell.type == CellType.SHOP){
            System.out.println("6. Enter Shop");
        }

        System.out.println("7. Inventory");
        int option = -1;

        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e){
            String invalidInput = scanner.next();
            throw new InvalidCommandException("InvalidCommandException");
        }

        if(option == 5 && map.currentCell.type != CellType.ENEMY) {
            option = -1;
        }

        if(option == 6 && map.currentCell.type != CellType.SHOP) {
            option = -1;
        }

        return option;
    }

    private static ArrayList<Account> readAccounts() throws InformationIncompleteException {
        String path = "C:\\Users\\user\\OneDrive - Universitatea Politehnica Bucuresti\\" +
                "Desktop\\Anul 2\\TemaPOO\\src\\accounts.json";
        String contents = null;
        try {
            contents = new String((Files.readAllBytes(Paths.get(path))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert contents != null;
        JSONObject o = new JSONObject(contents);
        JSONArray accounts = o.getJSONArray("accounts");

        ArrayList<Account> newAccounts = new ArrayList<>();
        for(int i=0; i < accounts.length(); i++){
            JSONObject account = (JSONObject) accounts.get(i);

            Account.Information.Builder infoAccount = new Account.Information.Builder();

            String name = (String) account.get("name");
            String country = (String) account.get("country");
            String gamesNumber = (String) account.get("maps_completed");
            infoAccount.country(country);
            infoAccount.name(name);

            JSONObject credential = (JSONObject) account.get("credentials");

            String email = (String) credential.get("email");
            String password = (String) credential.get("password");
            infoAccount.credentials(new Credentials(email , password));

            ArrayList<String> favoriteGames = new ArrayList<>();
            JSONArray games = (JSONArray) account.get("favorite_games");

            for(int j=0; j < games.length(); j++){
                favoriteGames.add((String) games.get(j));
            }

            Collections.sort(favoriteGames);

            infoAccount.favoriteGames(favoriteGames);

            ArrayList<Character> characters = new ArrayList<>();
            JSONArray character = (JSONArray) account.get("characters");

            for(int k = 0 ; k < character.length(); k++){
                JSONObject heros = (JSONObject) character.get(k);
                String characterName = (String) heros.get("name");
                Integer characterCurrentXp = (Integer) heros.get("experience");
                String characterCurrentLevel = (String) heros.get("level");
                String characterProfession = (String) heros.get("profession");

                ArrayList<Spell> abilities = new ArrayList<>();
                AttackSpell attack = new AttackSpell(RNG.getRandomInterval(1,10));
                FireSpell fire = new FireSpell(RNG.getRandomInterval(1,10) , RNG.getRandomInterval(1,10));
                EarthSpell earth = new EarthSpell(RNG.getRandomInterval(1,10) , RNG.getRandomInterval(1,10));
                IceSpell ice = new IceSpell(RNG.getRandomInterval(1,10) , RNG.getRandomInterval(1,10));
                abilities.add(attack);
                abilities.add(fire);
                abilities.add(ice);
                abilities.add(earth);
                Inventory inventory = new Inventory(RNG.getRandomInterval(80,100));

                Character newCharacter = null;
                CharacterFactory factory = new CharacterFactory(abilities , 0 , 0 ,
                        inventory , characterCurrentXp , Integer.parseInt(characterCurrentLevel) ,
                        RNG.getRandomInterval(10,20) , RNG.getRandomInterval(10,20) , RNG.getRandomInterval(10,20));
                newCharacter = factory.getCharacater(characterProfession , characterName);
                characters.add(newCharacter);
            }
            newAccounts.add(new Account(infoAccount.build() , characters , Integer.parseInt(gamesNumber)));
        }
        return newAccounts;
    }

    private static Map<CellType , ArrayList<String>> readStories() throws IOException {
        String path = "C:\\Users\\user\\OneDrive - Universitatea Politehnica Bucuresti\\Desktop\\Anul 2\\TemaPOO\\src\\stories.json";
        String contents = new String((Files.readAllBytes(Paths.get(path))));
        JSONObject o = new JSONObject(contents);
        JSONArray stories = o.getJSONArray("stories");

        ArrayList<String> emptyStories = new ArrayList<>();
        ArrayList<String> shopStories = new ArrayList<>();
        ArrayList<String> finishStories = new ArrayList<>();
        ArrayList<String> enemyStories = new ArrayList<>();

        for (int i = 0; i < stories.length(); i++) {
            JSONObject fields = (JSONObject) stories.get(i);
            String type = (String) fields.get("type");
            String value = (String) fields.get("value");

            switch (type) {
                case "EMPTY" -> emptyStories.add(value);
                case "SHOP" -> shopStories.add(value);
                case "FINISH" -> finishStories.add(value);
                default -> enemyStories.add(value);
            }
        }

        Map<CellType, ArrayList<String>> newStories = new HashMap<>();

        newStories.put(CellType.ENEMY , enemyStories);
        newStories.put(CellType.EMPTY , emptyStories);
        newStories.put(CellType.SHOP , shopStories);
        newStories.put(CellType.FINISH , finishStories);

        return newStories;
    }

    public void run() {
        System.out.println();
        System.out.println("Please choose the game mode: Terminal or GUI.");
        String mode = scanner.next();
        System.out.println();
        if(mode.equals("Terminal")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please choose the account for terminal game!");
            for(int i = 0; i < playerAccount.size() ; i++){
                System.out.println(i + ". " + playerAccount.get(i).player.getName());
            }

            int index = scanner.nextInt();
            if(index < 0 || index > playerAccount.size()) {
                try {
                    throw new InvalidCommandException("InvalidCommandException");
                } catch (InvalidCommandException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Account: " + playerAccount.get(index).player.getName());
            System.out.println();
            Account account = playerAccount.get(index);

            System.out.println("Please choose the character!");
            for(int i = 0; i < account.characters.size() ; i++){
                System.out.println(i + ". " + account.characters.get(i).currentCharacter);
            }

            index = scanner.nextInt();
            if(index < 0 || index > playerAccount.size()-1) {
                try {
                    throw new InvalidCommandException("InvalidCommandException");
                } catch (InvalidCommandException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Character: " + account.characters.get(index).currentCharacter);
            System.out.println();
            Character character = account.characters.get(index);

            TheBeginningOfTheGame tutorial = new TheBeginningOfTheGame();
            tutorial.tutorial();
            //map = Grid.getMap(5, 5 , character , 2 , 4);
            map = Grid.getMapHardcoded(character);

            while (true){
                String p = scanner.next();

                while(!p.equals("P")){
                    p = scanner.next();
                }
                System.out.println(map);
                if(map.currentCell.type == CellType.FINISH){
                    System.out.println("Congratulation! You won!");
                    //LastPage last = new LastPage(character);
                    return;
                }
                try {
                    int option = -1;
                    option = getMenu();
                    switch (option) {
                        case 0:
                            System.out.println("Good bye!");
                            return;
                        case 1:
                            if (map.goNorth()) {
                                ArrayList<String> story = stories.get(map.currentCell.type);
                                System.out.println(story.get(RNG.getRandomInterval(0, story.size() - 1)));
                            }
                            break;
                        case 2:
                            if (map.goSouth()) {
                                ArrayList<String> story = stories.get(map.currentCell.type);
                                System.out.println(story.get(RNG.getRandomInterval(0, story.size() - 1)));
                            }
                            break;
                        case 3:
                            if (map.goEast()) {
                                ArrayList<String> story = stories.get(map.currentCell.type);
                                System.out.println(story.get(RNG.getRandomInterval(0, story.size() - 1)));
                            }
                            break;
                        case 4:
                            if (map.goWest()) {
                                ArrayList<String> story = stories.get(map.currentCell.type);
                                System.out.println(story.get(RNG.getRandomInterval(0, story.size() - 1)));
                            }
                            break;
                        case 5:
                            map.currentCell.element.interract(map.player);
                            if (map.currentCell.type == CellType.ENEMY && ((Enemy) map.currentCell.element).currentHealth <= 0) {
                                map.currentCell.type = CellType.EMPTY;
                                map.currentCell.element = null;
                                map.player.enemyCount++;
                                map.player.currentXp += RNG.getRandomInterval(30,50);
                                if(map.player.currentXp >= 100){
                                    map.player.currentLvl++;
                                    map.player.currentXp = map.player.currentXp - 100;
                                    System.out.println("Level player -> " + map.player.currentLvl);
                                }
                                map.player.setAttribute();
                                System.out.println("Enemy defeated");
                                int chance = RNG.getRandomInterval(0, 99);
                                if (chance < 80) {
                                    map.player.inventory.coins += 5;
                                }
                            }
                            if (map.player.currentHealth <= 0) {
                                System.out.println("Game over");
                                return;
                            }
                            break;
                        case 6:
                            map.currentCell.element.interract(map.player);
                            break;
                        case 7:
                            System.out.println(map.player.inventory);
                            break;
                        default:
                            throw new InvalidCommandException("InvalidCommandException");

                    }
                }catch (InvalidCommandException e){
                    System.out.println("InvalidCommandException");
                }
            }
        }

        if(mode.equals("GUI")){
            System.out.println("If you choose GUI, an account will be randomly generated and displayed in the terminal.");
            new FirstPage();
        }
    }

    public void setNewStories(){
        if(stories.isEmpty()) {
            return;
        }
        else if(stories.containsKey(CellType.ENEMY) && !map.currentCell.isVisited){
            System.out.println(stories.get(CellType.ENEMY).get(RNG.getRandomInterval(0,stories.size()-1)));
        }
        else if(stories.containsKey(CellType.EMPTY) && !map.currentCell.isVisited){
            System.out.println(stories.get(CellType.EMPTY).get(RNG.getRandomInterval(0,stories.size()-1)));
        }
        else if(stories.containsKey(CellType.SHOP) && !map.currentCell.isVisited){
            System.out.println(stories.get(CellType.SHOP).get(RNG.getRandomInterval(0,stories.size()-1)));
        }
        else{
            System.out.println(stories.get(CellType.FINISH));
        }
    }
}

