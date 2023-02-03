package grid;

import cell.Cell;
import cell.CellType;
import entities.*;
import entities.Character;
import shop.Shop;
import utils.RNG;

import java.util.ArrayList;

public class Grid extends ArrayList<ArrayList<Cell>> {
    public int height;
    public int width;
    public Character player;
    public Cell currentCell;

    private Grid(int height, int width, Character player) {
        this.height = height;
        this.width = width;
        this.player = player;
        this.currentCell = null;
    }

    public static Grid getMap(int height , int width , Character player , int shopCount , int enemyCount){
        Grid newMap = new Grid(height , width , player);
        for(int i=0 ; i < height ; i++){
            newMap.add(new ArrayList<>());
            for(int j=0 ; j < width ; j++){
                newMap.get(i).add(new Cell(i , j , CellType.EMPTY , false , null));
            }
        }

        newMap.currentCell = newMap.get(player.currentOx).get(player.currentOy);
        newMap.get(player.currentOx).get(player.currentOy).isVisited = true;

        for(int i = 1; i<=shopCount; i++){
            int x = RNG.getRandomInterval(0,height-1);
            int y = RNG.getRandomInterval(0,width-1);

            while(newMap.get(x).get(y).type != CellType.EMPTY){
                x = RNG.getRandomInterval(0,height-1);
                y = RNG.getRandomInterval(0,width-1);
            }

            newMap.get(x).get(y).type = CellType.SHOP;
            newMap.get(x).get(y).element = new Shop();
        }

        for(int i = 1; i<=enemyCount; i++){
            int x = RNG.getRandomInterval(0,height-1);
            int y = RNG.getRandomInterval(0,width-1);

            while(newMap.get(x).get(y).type != CellType.EMPTY){
                x = RNG.getRandomInterval(0,height-1);
                y = RNG.getRandomInterval(0,width-1);
            }

            newMap.get(x).get(y).type = CellType.ENEMY;
            newMap.get(x).get(y).element = new Enemy();
        }

        int x = RNG.getRandomInterval(0,height-1);
        int y = RNG.getRandomInterval(0,width-1);

        while(newMap.get(x).get(y).type != CellType.EMPTY){
            x = RNG.getRandomInterval(0,height-1);
            y = RNG.getRandomInterval(0,width-1);
        }

        newMap.get(x).get(y).type = CellType.FINISH;

        return newMap;
    }

    public static Grid getMapHardcoded(Character player){
        Grid newMap = new Grid(5 , 5 , player);
        for(int i=0 ; i < 5 ; i++){
            newMap.add(new ArrayList<>());
            for(int j=0 ; j < 5 ; j++){
                newMap.get(i).add(new Cell(i , j , CellType.EMPTY , false , null));
            }
        }

        newMap.currentCell = newMap.get(player.currentOx).get(player.currentOy);
        newMap.get(player.currentOx).get(player.currentOy).isVisited = true;

        newMap.get(0).get(1).type = CellType.EMPTY;
        newMap.get(0).get(2).type = CellType.EMPTY;
        newMap.get(0).get(3).type = CellType.SHOP;
        newMap.get(0).get(3).element = new Shop();
        newMap.get(0).get(4).type = CellType.EMPTY;

        newMap.get(1).get(0).type = CellType.EMPTY;
        newMap.get(1).get(1).type = CellType.EMPTY;
        newMap.get(1).get(2).type = CellType.EMPTY;
        newMap.get(1).get(3).type = CellType.SHOP;
        newMap.get(1).get(3).element = new Shop();
        newMap.get(1).get(4).type = CellType.EMPTY;

        newMap.get(2).get(0).type = CellType.SHOP;
        newMap.get(2).get(0).element = new Shop();
        newMap.get(2).get(1).type = CellType.EMPTY;
        newMap.get(2).get(2).type = CellType.EMPTY;
        newMap.get(2).get(3).type = CellType.EMPTY;
        newMap.get(2).get(4).type = CellType.EMPTY;

        newMap.get(3).get(0).type = CellType.EMPTY;
        newMap.get(3).get(1).type = CellType.EMPTY;
        newMap.get(3).get(2).type = CellType.EMPTY;
        newMap.get(3).get(3).type = CellType.EMPTY;
        newMap.get(3).get(4).type = CellType.ENEMY;
        newMap.get(3).get(4).element = new Enemy();

        newMap.get(4).get(0).type = CellType.EMPTY;
        newMap.get(4).get(1).type = CellType.EMPTY;
        newMap.get(4).get(2).type = CellType.EMPTY;
        newMap.get(4).get(3).type = CellType.EMPTY;
        newMap.get(4).get(4).type = CellType.FINISH;

        return newMap;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(this.get(i).get(j) == currentCell){
                    sb.append("P").append(" ");
                }
                else {
                    sb.append(this.get(i).get(j).toString()).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean goNorth(){
        if(player.currentOx == 0){
            System.out.println("Player at board edges");
            return false;
        }
        player.currentOx--;
        currentCell = this.get(player.currentOx).get(player.currentOy);
        if(!currentCell.isVisited) {
            currentCell.isVisited = true;
            int chance = RNG.getRandomInterval(0,99);
            if(chance < 20){
                System.out.println("Wow! You are very lucky! You just found some coins");
                player.inventory.coins += RNG.getRandomInterval(4,10);
                System.out.println("Player coins -> " + player.inventory.coins);
            }
            return true;
        }
        return false;
    }

    public boolean goSouth(){
        if(player.currentOx == height-1){
            System.out.println("Player at board edges");
            return false;
        }
        player.currentOx++;
        currentCell = this.get(player.currentOx).get(player.currentOy);
        if(!currentCell.isVisited) {
            currentCell.isVisited = true;
            int chance = RNG.getRandomInterval(0,99);
            if(chance < 20){
                System.out.println("Wow! You are very lucky! You just found some coins");
                player.inventory.coins += RNG.getRandomInterval(4,10);
                System.out.println("Player coins -> " + player.inventory.coins);
            }
            return true;
        }
        return false;
    }

    public boolean goEast(){
        if(player.currentOy == width-1){
            System.out.println("Player at board edges");
            return false;
        }
        player.currentOy++;
        currentCell = this.get(player.currentOx).get(player.currentOy);
        if(!currentCell.isVisited) {
            currentCell.isVisited = true;
            int chance = RNG.getRandomInterval(0,99);
            if(chance < 20){
                System.out.println("Wow! You are very lucky! You just found some coins");
                player.inventory.coins += RNG.getRandomInterval(4,10);
                System.out.println("Player coins -> " + player.inventory.coins);
            }
            return true;
        }
        return false;
    }

    public boolean goWest(){
        if(player.currentOy == 0){
            System.out.println("Player at board edges");
            return false;
        }
        player.currentOy--;
        currentCell = this.get(player.currentOx).get(player.currentOy);
        if(!currentCell.isVisited) {
            currentCell.isVisited = true;
            int chance = RNG.getRandomInterval(0,99);
            if(chance < 20){
                System.out.println("Wow! You are very lucky! You just found some coins");
                player.inventory.coins += RNG.getRandomInterval(4,10);
                System.out.println("Player coins -> " + player.inventory.coins);
            }
            return true;
        }
        return false;
    }
}


