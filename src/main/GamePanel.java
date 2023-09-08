package main;

import entities.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    private Thread thread = new Thread(this);

    private Player player;

    public MListener listener;

    public static final int FPS_SET = 60;

    public GamePanel (){

        Dimension size = new Dimension(Window.WIDTH, Window.HEIGHT);

        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);

        listener = new MListener();

        addKeyListener(listener);

        thread.start();
        player = new Player();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(40, 46, 56));
        g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
        player.paint(g);
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while(true){
            now = System.nanoTime();
            if(System.nanoTime() - lastFrame >= timePerFrame){

                tick();
                lastFrame = now;
                frames ++;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }

        }
    }

    private void tick() {
        repaint();
        player.move(listener.e_pressed, listener.q_pressed);
    }
}
