package account;

import entities.Character;
import exceptions.InformationIncompleteException;

import java.util.ArrayList;

public class Account {
    public Information player;
    public ArrayList<Character> characters;
    public int gamesNumber;

    public Account() {
    }

    public Account(Information player, int gamesNumber) {
        this.player = player;
        this.characters = new ArrayList<>();
        this.gamesNumber = gamesNumber;
    }

    public Account(Information player, ArrayList<Character> characters, int gamesNumber) {
        this.player = player;
        this.characters = characters;
        this.gamesNumber = gamesNumber;
    }

    public Information getPlayer() {
        return player;
    }

    public void setPlayer(Information.Builder player) throws InformationIncompleteException {
        this.player = player.build();
    }

    public ArrayList<Character> getAllCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    public int getGamesNumber() {
        return gamesNumber;
    }

    public void setGamesNumber(int gamesNumber) {
        this.gamesNumber = gamesNumber;
    }

    public static class Information{
        private Credentials credentials;
        private ArrayList<String> favoriteGames;
        private String name;
        private String country;

        public static class Builder{
            Credentials credentials;
            ArrayList<String> favoriteGames;
            String name ;
            String country ;

            public Builder() {
                this.credentials = null;
                this.favoriteGames = null;
                this.name = null;
                this.country = null;
            }

            public Builder country(String country){
                this.country = country;
                return this;
            }

            public Builder name(String name){
                this.name = name;
                return this;
            }

            public Builder favoriteGames(ArrayList<String> favoriteGames){
                this.favoriteGames = new ArrayList<>(favoriteGames);
                return this;
            }

            public Builder credentials(Credentials credentials){
                this.credentials = credentials;
                return this;
            }

            public Information build() throws InformationIncompleteException {
                if(favoriteGames == null)
                    favoriteGames = new ArrayList<>();
                if(country == null)
                    country = "unspecified";
                if(credentials == null || name == null)
                    throw new InformationIncompleteException("InformationIncompleteException");
                return new Information(this);
            }

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder("Information {");
                sb.append("credentials=").append(credentials);
                sb.append(", favoriteGames=").append(favoriteGames);
                sb.append(", name='").append(name).append('\'');
                sb.append(", country='").append(country).append('\'');
                sb.append('}');
                return sb.toString();
            }
        }

        public Information(Builder builder){
            credentials = builder.credentials;
            favoriteGames = builder.favoriteGames;
            name = builder.name;
            country = builder.country;
        }

        public Credentials getCredentials() {
            return credentials;
        }

        public void setCredentials(Credentials credentials) {
            this.credentials = credentials;
        }

        public ArrayList<String> getFavoriteGames() {
            return favoriteGames;
        }

        public void setFavoriteGames(ArrayList<String> favoriteGames) {
            this.favoriteGames = favoriteGames;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public boolean addNewFavoriteGame(String gameName){
            if(!favoriteGames.contains(gameName)){
                return favoriteGames.add(gameName);
            }
            return false;
        }

        public boolean removeFavoriteGame(String gameName){
            return favoriteGames.remove(gameName);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Information {");
            sb.append("player_credentials=").append(credentials);
            sb.append(", favorite_games=").append(favoriteGames);
            sb.append(", name='").append(name).append('\'');
            sb.append(", country='").append(country).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account {");
        sb.append("player=").append(player);
        sb.append(", characters=").append(characters);
        sb.append(", gamesNumber=").append(gamesNumber);
        sb.append('}');
        return sb.toString();
    }
}
