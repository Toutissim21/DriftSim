package entities;

import utilz.Vector;

import java.awt.*;

public class Player {

    private final static Dimension size = new Dimension(10, 10);

    public Dimension pos;

    private Vector velocity = new Vector(10, 0);
    private final static double friction = 8;
    private final static int maxTrust = 8;
    private Vector trust = new Vector(8, 0);

    private double angle = 0;

    public Player(){
         pos = new Dimension(0, 20);

    }

    public void paint(Graphics g){
        g.setColor(new Color(0, 255, 0));
        g.drawLine(pos.width + size.width/2, pos.height + size.height/2, (int) (pos.width + size.width/2 + trust.x), (int) (pos.height + size.height/2 + trust.y));
        g.setColor(new Color(255,0 ,0));
        g.fillRect(pos.width, pos.height, size.width, size.height);


        velocity.subXY(friction);

        velocity.add(trust);

        pos.width += velocity.x;
        pos.height += velocity.y;

    }

    public void move(boolean e_pressed, boolean q_pressed){
        if(e_pressed){
            if(angle < 360){
                angle += 1;
            }else{
                angle = 0;
            }

            System.out.println(angle);
        }
        trust.x = Math.cos(angle*(Math.PI/180))*maxTrust;
        trust.y = Math.sin(angle*(Math.PI/180))*maxTrust;

    }

}
