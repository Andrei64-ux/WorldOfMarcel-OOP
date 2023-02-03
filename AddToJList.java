package graphic;

import account.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddToJList extends JFrame {
    JList<String> lst;
    DefaultListModel<String> el;
    Account selectedAccount;

    public AddToJList(Account newAccount) {
        super("SELECT A CHARACTER:");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 200));
        setBounds(600, 300, 370, 600);
        getContentPane().setBackground(new Color(241, 215, 215));
        setLayout(new FlowLayout());

        selectedAccount = newAccount;

        el = new DefaultListModel<>();
        JScrollPane scrollPane = new JScrollPane();

        for(int i = 0; i < selectedAccount.characters.size() ; i++){
            el.addElement(selectedAccount.characters.get(i).currentCharacter);
        }

        lst = new JList<>(el);
        scrollPane.setViewportView(lst);
        lst.setLayoutOrientation(JList.VERTICAL);
        add(scrollPane);
        lst.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    JList target = (JList)me.getSource();
                    int index = target.locationToIndex(me.getPoint());
                    System.out.println("The selected character is in the index -> " + index);
                    System.out.println("The selected character is -> " + selectedAccount.characters.get(index).currentCharacter);
                    if (index >= 0) {
                        Object item = target.getModel().getElementAt(index);
                        JOptionPane.showMessageDialog(null, item.toString());
                        JOptionPane.showMessageDialog(null, "The game is ready to start!");
                        SecondPage.initWindow(selectedAccount.characters.get(index));

                    }
                }
            }
        });

        JPanel p = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new BorderLayout());
        JPanel p3 = new JPanel(new BorderLayout());

        p.add(p2, BorderLayout.LINE_START);
        p.add(p3, BorderLayout.LINE_END);
        add(p);

        show();
        pack();
    }
}