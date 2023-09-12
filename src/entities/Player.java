package entities;

import utilz.Vector;

import java.awt.*;

public class Player {

    private final static Dimension size = new Dimension(10, 10);

    public Dimension pos;
    public static final int WHEIGHT = 50;

    public static final double static_friction_coef = 0.02;

    private Vector velocity = new Vector(6, 0);

    private final static double maxfriction = WHEIGHT*static_friction_coef;
    private Vector friction = new Vector(0, 0);

    private final static double maxTrust = 1;
    private Vector trust = new Vector(0, 0);

    private int angle = 0;

    public Player(){
         pos = new Dimension(0, 20);

    }

    public void paint(Graphics g){
        g.setColor(new Color(0, 255, 0));
        g.drawLine(pos.width + size.width/2, pos.height + size.height/2, (int) (pos.width + size.width/2 + trust.x*10), (int) (pos.height + size.height/2 + trust.y*10));
        //g.setColor(new Color(0, 0, 255));
        //g.drawLine(pos.width + size.width/2, pos.height + size.height/2, (int) (pos.width + size.width/2 + velocity.x*2), (int) (pos.height + size.height/2 + velocity.y*2));
        g.setColor(new Color(255,0 ,0));
        g.fillRect(pos.width, pos.height, size.width, size.height);

        velocity.x -= friction.x;
        velocity.y -= friction.y;

        velocity.x += trust.x;
        velocity.y += trust.y;

        pos.width += velocity.x;
        pos.height += velocity.y;


    }

    public void move(boolean e_pressed, boolean q_pressed){
        if(e_pressed){
            if(angle < 360){
                angle += 4;
            }else{
                angle = 0;
            }

        }else if(q_pressed){
            if(angle > 0){
                angle -= 4;
            }else{
                angle = 360;
            }
        }
        trust.x = Math.cos(angle*(Math.PI/180))*maxTrust;
        trust.y = Math.sin(angle*(Math.PI/180))*maxTrust;

        Vector velocityUnitVector = velocity.unitVector();

        System.out.println(velocityUnitVector.x + " | " + velocityUnitVector.y);

        friction.x = velocityUnitVector.x * maxfriction * absoluteValue(velocity.x) * 0.1;
        friction.y = velocityUnitVector.y * maxfriction * absoluteValue(velocity.y) * 0.1;

    }
    public double absoluteValue(double nbr){
        return Math.sqrt(nbr*nbr);
    }

}
