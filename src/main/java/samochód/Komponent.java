package samochód;

public class Komponent {
    private String nazwa;
    private int waga;
    private int cena;

    public void setNazwa(String nazwaa){
        nazwa=nazwaa;
    }

    public String getNazwa(){
        return nazwa;
    }

    public void setWaga(int masa){
        waga=masa;
    }

    public int getWaga() {
        return waga;
    }
    public int getCena() {
        return cena;
    }

    public void setCena(int cenaa){
        cena=cenaa;
    }

}

