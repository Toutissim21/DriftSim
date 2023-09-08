package utilz;

public class Vector {

    public double x;
    public double y;


    public Vector (double x_, double y_){
        x = x_;
        y = y_;
    }

    public void subXY(double amount){

        if(x > 0){
            x -= amount;
        }else if(x < 0){
            x += amount;
        }
        if(y > 0){
            y -= amount;
        }else if(y < 0){
            y += amount;
        }
    }
    public void add(Vector vector){
            x += vector.x;
            y += vector.y;
    }
}
