package entities;

import utilz.Vector;

import java.awt.*;

public class Player {

    private final static Dimension size = new Dimension(40, 10);
    private final static double diagonale = Math.sqrt(size.width* size.width + size.height*size.height)/2;

    private Polygon vehicle = new Polygon(new int[4], new int[4], 4);

    public Dimension pos;
    public static final int WHEIGHT = 10;

    public static final double static_friction_coef = 0.02;

    private Vector velocity = new Vector(6, 0);

    private final static double maxfriction = WHEIGHT*static_friction_coef;
    private Vector friction = new Vector(0, 0);

    private final static double maxTrust = 0.2;
    private Vector trust = new Vector(0, 0);

    private int angle = 0;

    public Player(){
        pos = new Dimension(20, 20);
        System.out.println(diagonale);
    }

    public void paint(Graphics g){

        // Draw trust line:
        g.setColor(new Color(0, 255, 0));
        g.drawLine(pos.width + size.width/2, pos.height + size.height/2, (int) (pos.width + size.width/2 + trust.x*60), (int) (pos.height + size.height/2 + trust.y*60));

        //Draw velocity line:
        //g.setColor(new Color(0, 0, 255));
        //g.drawLine(pos.width + size.width/2, pos.height + size.height/2, (int) (pos.width + size.width/2 + velocity.x*2), (int) (pos.height + size.height/2 + velocity.y*2));

        //Draw vehicle:
        g.setColor(new Color(255,0 ,0));
        g.fillPolygon(vehicle);
        //g.setColor(new Color(0, 0, 255));
        //g.fillOval(pos.width - 10, pos.height - 10, 10, 10);

        //Substract friction:
        velocity.x -= friction.x;
        velocity.y -= friction.y;

        //Add trust:
        velocity.x += trust.x;
        velocity.y += trust.y;

        //modifying pos:
        pos.width += velocity.x;
        pos.height += velocity.y;



    }

    public void move(boolean e_pressed, boolean q_pressed){
        //key binding + angle:
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
        //calculate trust from angle:
        trust.x = Math.cos(angle*(Math.PI/180))*maxTrust;
        trust.y = Math.sin(angle*(Math.PI/180))*maxTrust;

        //calculate friction:
        Vector velocityUnitVector = velocity.unitVector();

        friction.x = velocityUnitVector.x * maxfriction * absoluteValue(velocity.x) * 0.1;
        friction.y = velocityUnitVector.y * maxfriction * absoluteValue(velocity.y) * 0.1;

        //calculate rect angle:
        vehicle.xpoints[0] = pos.width + size.width/2 + (int)(diagonale*Math.sin((45 + angle)*(Math.PI/180)));
        vehicle.xpoints[1] = pos.width + size.width/2 - (int)(diagonale*Math.cos((45 + angle)*(Math.PI/180)));
        vehicle.xpoints[2] = pos.width + size.width/2 - (int)(diagonale*Math.sin((45 + angle)*(Math.PI/180)));
        vehicle.xpoints[3] = pos.width + size.width/2 + (int)(diagonale*Math.cos((45 + angle)*(Math.PI/180)));

        vehicle.ypoints[0] = pos.height + size.height/2 - (int)(diagonale*Math.cos((45 + angle)*(Math.PI/180)));
        vehicle.ypoints[1] = pos.height + size.height/2 - (int)(diagonale*Math.sin((45 + angle)*(Math.PI/180)));
        vehicle.ypoints[2] = pos.height + size.height/2 + (int)(diagonale*Math.cos((45 + angle)*(Math.PI/180)));
        vehicle.ypoints[3] = pos.height + size.height/2 + (int)(diagonale*Math.sin((45 + angle)*(Math.PI/180)));

    }
    public double absoluteValue(double nbr){
        return Math.sqrt(nbr*nbr);
    }
}
