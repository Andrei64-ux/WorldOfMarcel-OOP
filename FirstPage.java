package graphic;

import game.Game;
import utils.RNG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FirstPage extends JFrame implements ActionListener
{
    public JButton b1;
    public JLabel l1;
    public Game g = Game.getInstance();

    public FirstPage()
    {
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("C:\\Users\\user\\OneDrive - Universitatea Politehnica Bucuresti\\Desktop\\TemaPOO\\src\\image\\animation.gif")));
        setLayout(new FlowLayout());
        setTitle("WORLD OF MARCEL");
        setSize(600,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        l1=new JLabel("Please start the game!");
        b1=new JButton("START");
        b1.addActionListener(this);
        add(l1);
        add(b1);
        setSize(399,399);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton)
        {
            Login frame = new Login(g.playerAccount.get(RNG.getRandomInterval(0,g.playerAccount.size()-1)));
            frame.setTitle("PLEASE LOGIN");
            frame.setVisible(true);
            frame.setBounds(600, 300, 370, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            this.setVisible(false);
        }
    }
}