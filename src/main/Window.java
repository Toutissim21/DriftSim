package main;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    static final int HEIGHT = 1200;
    static final int WIDTH = 2400;

    private GamePanel gamePanel = new GamePanel();

    public Window (){

        setTitle("DriftSim");
        setResizable(false);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(gamePanel);
        pack();

        setVisible(true);
        setLocationRelativeTo(null);

        gamePanel.requestFocus();
    }
}
