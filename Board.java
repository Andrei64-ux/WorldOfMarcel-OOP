package graphic;

import entities.Character;
import utils.RNG;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener, KeyListener {

    public static final int TILE_SIZE = 50;
    public static final int ROWS = RNG.getRandomInterval(8, 12);
    public static final int COLUMNS = RNG.getRandomInterval(14, 18);
    public static final int NUM_COINS = RNG.getRandomInterval(3, 5);
    public static final int NUM_ENEMY = RNG.getRandomInterval(2, 5);
    public static final int NUM_SHOP = RNG.getRandomInterval(1, 3);
    public static final int NUM_FINISH = 1;
    public static final int NUM_EMPTY = ROWS*COLUMNS*ROWS;
    public final Player player;
    public final ArrayList<CoinGraphic> coins;
    public final ArrayList<EnemyGraphic> enemyGraphics;
    public final ArrayList<ShopGraphic> shopGraphics;
    public final ArrayList<FinishGraphic> finishGraphics;
    public final ArrayList<EmptyGraphic> emptyGraphics;

    public Board(Character myCharacter) {
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
        setBackground(new Color(183, 170, 170));

        player = new Player(myCharacter);
        coins = populateCoins();
        enemyGraphics = populateEnemy();
        shopGraphics = populateShop();
        finishGraphics = populateFinish();
        emptyGraphics = populateEmpty();

        int DELAY = 25;
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.check();
        collectCoins();
        interactEnemy();
        interactShop();
        interactFinish();
        collectEmpty();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBackground(g);
        drawCoins(g);
        drawHealth(g);
        drawChakra(g);

        for (CoinGraphic coin : coins) {
            coin.draw(g, this);
        }

        for (EnemyGraphic enemy : enemyGraphics) {
            enemy.draw(g, this);
        }

        for (ShopGraphic shop : shopGraphics) {
            shop.draw(g, this);
        }

        for (FinishGraphic finish : finishGraphics) {
            finish.draw(g, this);
        }

        for (EmptyGraphic empty : emptyGraphics) {
            empty.draw(g, this);
        }

        player.draw(g, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void drawBackground(Graphics g) {
        g.setColor(new Color(252, 251, 251, 255));
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if ((row + col) % 2 == 1) {
                    g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    public void drawCoins(Graphics g) {
        String text = "$" + player.getScore();
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(16, 16, 16));
        g2d.setFont(new Font("Calibri", Font.BOLD, 25));

        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());

        Rectangle rect = new Rectangle(0, TILE_SIZE * (ROWS - 1), TILE_SIZE * COLUMNS, TILE_SIZE);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.drawString(text, x, y);
    }

    public void drawHealth(Graphics g) {
        String text = "HEALTH:" + player.getHealth();
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(0, 0, 0));
        g2d.setFont(new Font("Calibri", Font.BOLD, 25));

        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());

        Rectangle rect = new Rectangle(0, TILE_SIZE * (ROWS - 1), TILE_SIZE * COLUMNS, TILE_SIZE);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2 - 250;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.drawString(text, x, y);
    }

    public void drawChakra(Graphics g) {
        String text = "Chakra:" + player.getChakra();
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(0, 0, 0));
        g2d.setFont(new Font("Calibri", Font.BOLD, 25));

        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());

        Rectangle rect = new Rectangle(0, TILE_SIZE * (ROWS - 1), TILE_SIZE * COLUMNS, TILE_SIZE);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2 + 250;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.drawString(text, x, y);
    }

    public ArrayList<CoinGraphic> populateCoins() {
        ArrayList<CoinGraphic> coinList = new ArrayList<>();

        for (int i = 0; i < NUM_COINS; i++) {
            int coinX = RNG.getRandomInterval(0, COLUMNS - 1);
            int coinY = RNG.getRandomInterval(0, ROWS - 1);
            coinList.add(new CoinGraphic(coinX, coinY));
        }

        return coinList;
    }

    public ArrayList<EmptyGraphic> populateEmpty() {
        ArrayList<EmptyGraphic> emptyList = new ArrayList<>();

        for (int i = 0; i < NUM_EMPTY; i++) {
            int coinX = RNG.getRandomInterval(0, COLUMNS - 1);
            int coinY = RNG.getRandomInterval(0, ROWS - 1);
            emptyList.add(new EmptyGraphic(coinX, coinY));
        }

        return emptyList;
    }

    public ArrayList<EnemyGraphic> populateEnemy() {
        ArrayList<EnemyGraphic> enemyList = new ArrayList<>();

        for (int i = 0; i < NUM_ENEMY; i++) {
            int enemyX = RNG.getRandomInterval(0,COLUMNS - 1);
            int enemyY = RNG.getRandomInterval(0,ROWS - 1);
            enemyList.add(new EnemyGraphic(enemyX, enemyY));
        }

        return enemyList;
    }

    public ArrayList<ShopGraphic> populateShop() {
        ArrayList<ShopGraphic> shopList = new ArrayList<>();

        for (int i = 0; i < NUM_SHOP; i++) {
            int shopX = RNG.getRandomInterval(0, COLUMNS - 1);
            int shopY = RNG.getRandomInterval(0 , ROWS - 1);
            shopList.add(new ShopGraphic(shopX, shopY));
        }

        return shopList;
    }

    public ArrayList<FinishGraphic> populateFinish() {
        ArrayList<FinishGraphic> finishList = new ArrayList<>();

        for (int i = 0; i < NUM_FINISH; i++) {
            int finishX = RNG.getRandomInterval(0, COLUMNS - 1);
            int finishY = RNG.getRandomInterval(0, ROWS - 1);
            finishList.add(new FinishGraphic(finishX, finishY));
        }

        return finishList;
    }

    public void collectCoins() {
        ArrayList<CoinGraphic> collectedCoins = new ArrayList<>();
        for (CoinGraphic coin : coins) {
            if (player.getPos().equals(coin.getPos())) {
                player.addScore(3);
                player.newCharacter.inventory.coins += 3;
                collectedCoins.add(coin);
                System.out.println("Player-ul are -> " + player.newCharacter.inventory.coins);
            }
        }
        coins.removeAll(collectedCoins);
    }

    public void collectEmpty() {
        ArrayList<EmptyGraphic> collectedEmpty = new ArrayList<>();
        for (EmptyGraphic empty : emptyGraphics) {
            if (player.getPos().equals(empty.getPos())) {
                collectedEmpty.add(empty);
            }
        }
        emptyGraphics.removeAll(collectedEmpty);
    }

    public void interactEnemy() {
        for (EnemyGraphic enemy : enemyGraphics) {
            if (player.getPos().equals(enemy.getPos())) {
                System.out.println("YOU FOUND AN ENEMY!");
                while (enemy.newEnemy.currentHealth > 0) {
                    enemy.newEnemy.interract(player.newCharacter);
                    if (player.newCharacter.currentHealth <= 0) {
                        JOptionPane.showMessageDialog(this, "GAME OVER!");
                    }
                }

                player.newCharacter.currentXp += RNG.getRandomInterval(1, 7);
                if (player.newCharacter.currentXp >= 100) {
                    player.newCharacter.currentLvl++;
                    player.newCharacter.currentXp = player.newCharacter.currentXp - 100;
                    return;
                }

                if(enemy.newEnemy.currentHealth <= 0){
                    //System.out.println("YOU KILLED THE ENEMY!");
                    player.newCharacter.setEnemyCount();
                    player.newCharacter.enemyCount++;
                }

                try {
                    enemy.image = ImageIO.read(new File("C:\\Users\\user\\OneDrive - Universitatea Politehnica Bucuresti" +
                            "\\Desktop\\TemaPOO\\src\\image\\defeat.png"));
                    enemy.image = EnemyGraphic.scale(enemy.image, 50, 50);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void interactShop() {
        for (ShopGraphic shop : shopGraphics) {
            if (player.getPos().equals(shop.getPos())) {
                System.out.println("YOU FOUND A SHOP!");
                while (player.newCharacter.inventory.coins % 2 == 0) {
                    shop.newShop.interract(player.newCharacter);
                }
                try {
                    shop.image = ImageIO.read(new File("C:\\Users\\user\\OneDrive - Universitatea Politehnica Bucuresti\\" +
                            "Desktop\\TemaPOO\\src\\image\\shopClosed.jpg"));
                    shop.image = ShopGraphic.scale(shop.image, 50, 50);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void interactFinish() {
        ArrayList<FinishGraphic> collectedFinish = new ArrayList<>();
        for (FinishGraphic finish : finishGraphics) {
            if (player.getPos().equals(finish.getPos())) {

                collectedFinish.add(finish);
                ImageIcon icon = new ImageIcon("C:\\Users\\user\\OneDrive - Universitatea Politehnica Bucuresti\\Desktop\\TemaPOO\\src\\image\\victory.jpg");
                int scale = 1;
                BufferedImage newIcon = new BufferedImage(
                        scale*icon.getIconWidth(),
                        scale*icon.getIconHeight(),
                        BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = newIcon.createGraphics();
                g.scale(scale,scale);
                icon.paintIcon(null,g,0,0);
                g.dispose();

                JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(newIcon)));
                LastPage last = new LastPage(player.newCharacter);
            }
        }
        finishGraphics.removeAll(collectedFinish);
    }
}
