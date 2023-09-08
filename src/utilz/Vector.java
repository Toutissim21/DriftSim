package utilz;

public class Vector {

    public double x;
    public double y;


    public Vector (double x_, double y_){
        x = x_;
        y = y_;
    }

    public void subXY(double amount){

        long longx = Math.round(x);

        long longy = Math.round(y);

        if(longx > 0){
            x -= amount;
        }else if(longx < 0){
            x += amount;
        }
        if(longy > 0){
            y -= amount;
        }else if(longy < 0){
            y += amount;
        }
    }
    public void add(Vector vector){
        x += vector.x;
        y += vector.y;
    }
}
