package samochód;

public class Sprzeglo extends Komponent{

    private boolean StanSprzegla;

    public Sprzeglo(){
        StanSprzegla =false;
        setWaga(50);
        setNazwa("sprzeglo");
        setCena(300);
    }

    public void wcisnij(){
        StanSprzegla =true;
    }
    public void zwolnij(){
        StanSprzegla =false;
    }
    public boolean getStan() {
        return StanSprzegla;
    }

}
