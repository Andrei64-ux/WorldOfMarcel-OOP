package graphic;

import entities.Character;
import entities.Mage;
import entities.Rogue;
import entities.Warrior;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {
    public BufferedImage image;
    public final Point position;
    public int score;
    public Character newCharacter;

    public Player(Character myCharacter) {
        newCharacter = myCharacter;
        loadImage(newCharacter);
        position = new Point(0, 0);
        score = newCharacter.inventory.coins;
    }

    public static BufferedImage scale(BufferedImage src, int width, int height)
    {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int x, y;
        int widthImage = src.getWidth();
        int heightImage = src.getHeight();
        int[] ys = new int[height];
        for (y = 0; y < height; y++)
            ys[y] = y * heightImage / height;
        for (x = 0; x < width; x++) {
            int newX = x * widthImage / width;
            for (y = 0; y < height; y++) {
                int col = src.getRGB(newX, ys[y]);
                img.setRGB(x, y, col);
            }
        }
        return img;
    }

    private void loadImage(Character character) {
        try {
            if(character instanceof Warrior) {
                image = ImageIO.read(new File("C:\\Users\\user\\OneDrive - Universitatea Politehnica Bucuresti\\Desktop\\TemaPOO\\src\\image\\warrior.jpg"));
                image = scale(image , 50 , 50);
            }
            if(character instanceof Mage){
                image = ImageIO.read(new File("C:\\Users\\user\\OneDrive - Universitatea Politehnica Bucuresti\\Desktop\\TemaPOO\\src\\image\\mage.jpg"));
                image = scale(image , 50 , 50);
            }
            if(character instanceof Rogue){
                image = ImageIO.read(new File("C:\\Users\\user\\OneDrive - Universitatea Politehnica Bucuresti\\Desktop\\TemaPOO\\src\\image\\rogue.jpg"));
                image = scale(image , 50 , 50);
            }
        } catch (IOException exc) {
            System.out.println("I couldn't find the file" + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(image, position.x * Board.TILE_SIZE, position.y * Board.TILE_SIZE, observer);
    }

    public void keyPressed(KeyEvent e) {
        int keyCommand = e.getKeyCode();

        if (keyCommand == KeyEvent.VK_UP) {
            position.translate(0, -1);
        }
        if (keyCommand == KeyEvent.VK_RIGHT) {
            position.translate(1, 0);
        }
        if (keyCommand == KeyEvent.VK_DOWN) {
            position.translate(0, 1);
        }
        if (keyCommand == KeyEvent.VK_LEFT) {
            position.translate(-1, 0);
        }
    }

    public void check() {
        if (position.x < 0) {
            position.x = 0;
        } else if (position.x >= Board.COLUMNS) {
            position.x = Board.COLUMNS - 1;
        }
        if (position.y < 0) {
            position.y = 0;
        } else if (position.y >= Board.ROWS) {
            position.y = Board.ROWS - 1;
        }
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public void addScore(int amount) {
        score += amount;
    }

    public Point getPos() {
        return position;
    }

    public String getHealth(){
        return String.valueOf(newCharacter.currentHealth);
    }

    public String getChakra(){
        return String.valueOf(newCharacter.currentChakra);
    }
}