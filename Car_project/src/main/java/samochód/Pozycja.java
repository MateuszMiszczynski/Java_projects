package samochód;

public class Pozycja {
    private double x;
    private double y;

    public Pozycja(double x, double y){
        this.x=x;
        this.y=y;
    }
    public double getPozycjaX(){
        return x;
    }
    public double getPozycjaY(){
        return y;
    }
    public void setPozycjaX(double x){
        this.x=x;
    }
    public void setPozycjaY(double y){
        this.y=y;
    }

}


