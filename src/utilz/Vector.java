package utilz;

public class Vector {

    public double x;
    public double y;


    public Vector (double x_, double y_){
        x = x_;
        y = y_;
    }
    public double norm(){
        return Math.sqrt(x*x + y*y);
    }
    public Vector unitVector(){
        return new Vector(x/norm(), y/norm());
    }
}
