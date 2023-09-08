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

    private final static double maxTrust = maxfriction;
    private Vector trust = new Vector(0, 0);

    private int angle = 0;

    double temp_x = 0;
    double temp_y = 0;

    public Player(){
         pos = new Dimension(0, 20);

    }

    public void paint(Graphics g){
        g.setColor(new Color(0, 255, 0));
        g.drawLine(pos.width + size.width/2, pos.height + size.height/2, (int) (pos.width + size.width/2 + trust.x), (int) (pos.height + size.height/2 + trust.y));
        g.setColor(new Color(255,0 ,0));
        g.fillRect(pos.width, pos.height, size.width, size.height);

        double total_speed = velocity.x + velocity.y;
        double x_coef = velocity.x/total_speed;
        double y_coef = 1 - x_coef;

        friction.x = maxfriction*x_coef;
        friction.y = maxfriction*y_coef;

        if(velocity.x != 0) {
            velocity.x -= maxfriction;
        }
        if(velocity.y != 0) {
            velocity.y -= maxfriction;
        }

        velocity.add(trust);


        temp_x = velocity.x - (int)velocity.x;
        temp_y = velocity.y - (int)velocity.y;

        pos.width += (int)velocity.x;
        pos.height += (int)velocity.y;

        if(temp_x > 1){
            pos.width += 1;
            temp_x = 0;
        }
        if(temp_y > 1){
            pos.height += 1;
            temp_y = 0;
        }

        System.out.println(velocity.y);


    }

    public void move(boolean e_pressed, boolean q_pressed){
        if(e_pressed){
            if(angle < 360){
                angle += 2;
            }else{
                angle = 0;
            }

        }else if(q_pressed){
            if(angle > 0){
                angle -= 2;
            }else{
                angle = 360;
            }
        }
        trust.x = Math.cos(angle*(Math.PI/180))*maxTrust;
        trust.y = Math.sin(angle*(Math.PI/180))*maxTrust;

        friction.x = Math.cos(angle*(Math.PI/180))*maxfriction;
        friction.y = Math.sin(angle*(Math.PI/180))*maxfriction;

    }

}
