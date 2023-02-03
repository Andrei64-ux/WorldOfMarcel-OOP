package graphic;

import entities.Character;
import entities.Mage;
import entities.Rogue;
import entities.Warrior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LastPage extends JFrame implements ActionListener
{
    public JLabel lblExperience;
    public JLabel lblName;
    public JLabel lblHealth;
    public JLabel lblChakra;
    public JLabel lblCoins;
    public JLabel lblEnemyCount;
    public JLabel lblLevel;
    public JButton txtExperience;
    public JButton txtHealth;
    public JButton txtName;
    public JButton txtChakra;
    public JButton txtCoins;
    public JButton txtEnemyCount;
    public JButton txtLevel;
    public Character myPlayer;

    public LastPage(Character myPlayer)
    {
        this.myPlayer = myPlayer;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 600));
        getContentPane().setBackground(new Color(0xD8F3EC));
        setLayout(new FlowLayout(10000000));

        if(myPlayer instanceof Warrior){
            ImageIcon icon = new ImageIcon("C:\\Users\\user\\OneDrive - Universitatea Politehnica Bucuresti\\Desktop\\TemaPOO\\src\\image\\warrior.jpg");
            add(new JLabel(icon));
        }

        else if(myPlayer instanceof Rogue){
            ImageIcon icon = new ImageIcon("C:\\Users\\user\\OneDrive - Universitatea Politehnica Bucuresti\\Desktop\\TemaPOO\\src\\image\\rogue.jpg");
            add(new JLabel(icon));
        }

        else if(myPlayer instanceof Mage){
            ImageIcon icon = new ImageIcon("C:\\Users\\user\\OneDrive - Universitatea Politehnica Bucuresti\\Desktop\\TemaPOO\\src\\image\\mage.jpg");
            add(new JLabel(icon));
        }

        else {
            ImageIcon icon = new ImageIcon("C:\\Users\\user\\OneDrive - Universitatea Politehnica Bucuresti\\Desktop\\TemaPOO\\src\\image\\mario.jpg");
            add(new JLabel(icon));
        }

        lblExperience = new JLabel("Experience gained: ");
        txtExperience = new JButton("Click to see the experience");
        txtExperience.setBackground(new Color(0x01FFD9));
        txtExperience.setPreferredSize(new Dimension(300,30));

        lblName = new JLabel("Name: ");
        txtName = new JButton("Click to see the name");
        txtName.setBackground(new Color(0x01FFEA));
        txtName.setPreferredSize(new Dimension(300,30));

        lblHealth = new JLabel("Health:         ");
        txtHealth = new JButton("Click to see the final health");
        txtHealth.setBackground(new Color(0x01FFFF));
        txtHealth.setPreferredSize(new Dimension(400,30));

        lblChakra = new JLabel("Chakra:         ");
        txtChakra = new JButton("Click to see the final Chakra");
        txtChakra.setBackground(new Color(0x01D9FF));
        txtChakra.setPreferredSize(new Dimension(400,30));

        lblCoins = new JLabel("Coins: ");
        txtCoins = new JButton("Click to see the final coins");
        txtCoins.setBackground(new Color(0x01FFB3));
        txtCoins.setPreferredSize(new Dimension(330,30));

        lblEnemyCount = new JLabel("Enemy Count: ");
        txtEnemyCount = new JButton("Click to see the final enemy count");
        txtEnemyCount.setBackground(new Color(0x01FFB3));
        txtEnemyCount.setPreferredSize(new Dimension(500,30));

        lblLevel = new JLabel("Level: ");
        txtLevel = new JButton("Click to see the final level:");
        txtLevel.setBackground(new Color(0x01FFB3));
        txtLevel.setPreferredSize(new Dimension(400,30));

        txtExperience.addActionListener(this);
        txtName.addActionListener(this);
        txtHealth.addActionListener(this);
        txtChakra.addActionListener(this);
        txtCoins.addActionListener(this);
        txtEnemyCount.addActionListener(this);
        txtLevel.addActionListener(this);

        add(lblExperience); add(txtExperience);
        add(lblName); add(txtName);
        add(lblHealth); add(txtHealth);
        add(lblChakra); add(txtChakra);
        add(lblCoins); add(txtCoins);
        add(lblEnemyCount); add(txtEnemyCount);
        add(lblLevel); add(txtLevel);

        show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == txtChakra) {
            txtChakra.setText(String.valueOf(myPlayer.currentChakra));
            txtChakra.setForeground(new Color(0, 0, 0));
            txtChakra.setFont(new Font("Calibri", Font.PLAIN, 14));
        }

        if(e.getSource() == txtCoins) {
            txtCoins.setText(String.valueOf(myPlayer.inventory.coins));
            txtCoins.setForeground(new Color(0,0,0));
            txtCoins.setFont(new Font("Calibri", Font.PLAIN, 14));
        }

        if(e.getSource() == txtEnemyCount) {
            txtEnemyCount.setText(String.valueOf(myPlayer.getEnemyCount()));
            txtEnemyCount.setForeground(new Color(0,0,0));
            txtEnemyCount.setFont(new Font("Calibri", Font.PLAIN, 14));
        }

        if(e.getSource() == txtHealth){
            txtHealth.setText(String.valueOf(myPlayer.currentHealth));
            txtHealth.setForeground(new Color(0,0,0));
            txtHealth.setFont(new Font("Calibri", Font.PLAIN, 14));
        }

        if(e.getSource() == txtExperience){
            txtExperience.setText(String.valueOf(myPlayer.currentXp));
            txtExperience.setForeground(new Color(0,0,0));
            txtExperience.setFont(new Font("Calibri", Font.PLAIN, 14));
        }

        if(e.getSource() == txtName){
            txtName.setText(String.valueOf(myPlayer.currentCharacter));
            txtName.setForeground(new Color(0,0,0));
            txtName.setFont(new Font("Calibri", Font.PLAIN, 14));
        }

        if(e.getSource() == txtLevel){
            txtLevel.setText(String.valueOf(myPlayer.currentLvl));
            txtLevel.setForeground(new Color(0,0,0));
            txtLevel.setFont(new Font("Calibri", Font.PLAIN, 14));
        }
    }
}
