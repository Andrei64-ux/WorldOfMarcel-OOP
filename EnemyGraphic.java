package graphic;

import entities.Enemy;
import utils.RNG;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EnemyGraphic {

    public BufferedImage image;
    public Point position;
    public Enemy newEnemy;

    public EnemyGraphic(int x, int y) {
        loadImage();
        position = new Point(x, y);
        newEnemy = new Enemy();
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

    public void loadImage() {
        try {
            image = ImageIO.read(new File("C:\\Users\\user\\OneDrive - Universitatea Politehnica Bucuresti\\Desktop\\TemaPOO\\src\\image\\bomb.gif"));
            image = scale(image, 50, 50);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(image, position.x * Board.TILE_SIZE, position.y * Board.TILE_SIZE, observer);
    }

    public Point getPos() {
        return position;
    }

}
