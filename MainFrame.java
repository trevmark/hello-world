package com.trev.View;

/**
 * Created by trevor on 06/04/17.
 */
import com.trev.Controller.Game;
import com.trev.Models.Hand;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import static java.lang.System.exit;

public class MainFrame extends JFrame {

    private boolean initialized = false;

    public MainFrame(){
 //     JPanel p1=new JPanel();
        JPanel p2=new JPanel();
        JLabel label1 = new JLabel("Click Here to Start Game\n");
        JTextField textField = new JTextField("Enter Player Name", 15);
        JButton button=new JButton("Start");
        JButton button2=new JButton("Again!");
        button.setBorderPainted(false);
//      p1.add(label1);
        p2.add(button);
        p2.add(textField);
        p2.add(button2);
        p2.setToolTipText("Lets Play Baccarat!");

        Actions listenForButton = new Actions();
        button.addActionListener(listenForButton);

        Actions2 listenForButton2 = new Actions2();
        button2.addActionListener(listenForButton2);

        this.setSize(500,500);

        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("/home/trevor/IdeaProjects/HouseOfCards/test.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.pack();
 //     this.add(p1);
        this.add(p2);
        Toolkit tk = Toolkit.getDefaultToolkit();
 //     Dimension dim = tk.getScreenSize();
        this.setTitle("Baccarat");
        this.setVisible(true);

    }

    public void initialize() {
        initializeGui();
        initializeEvents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initializeGui() {
        if (initialized)
            return;
        initialized = true;
        this.setSize(500, 600);
        Dimension windowSize = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width/2 - windowSize.width/2, screenSize.height/2 - windowSize.height/2);
        Container pane = this.getContentPane();
        pane.setLayout(new GridBagLayout());
        // TODO: Add UI widgets
    }

    private void initializeEvents() {
        // TODO: Add action listeners, etc
    }

    public class Actions implements ActionListener {

        public void actionPerformed(ActionEvent e) {

           Game game = new Game();
           game.startGame();
           game.playGame();

        }
    }
    public class Actions2 implements ActionListener {

        public void actionPerformed(ActionEvent e) {
          //should move to controller***
            Hand hand1 = Game.instance.getPlayer();
            hand1.resetPlayerHand();
            Game.instance.setPlayer(hand1);

            Hand hand2 = Game.instance.getBanker();
            hand2.resetPlayerHand();
            Game.instance.setBanker(hand2);

            Game.instance.playGame();;
        }
    }

    public void dispose() {
        // TODO: Save settings
        //super.dispose();
        exit(0);
    }

    public void setVisible(boolean b) {
        initialize();
        super.setVisible(b);
    }

}
