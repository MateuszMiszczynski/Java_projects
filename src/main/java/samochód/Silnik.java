package samochód;

public class Silnik extends Komponent {
    private int MaksymalneObroty;
    private int Obroty;
    private int MinimalneObroty;

    public Silnik(int maxObroty, int minObroty, String nazwa, int cena, int masa) {
        MaksymalneObroty = maxObroty;
        Obroty = 0;
        MinimalneObroty = minObroty;
        setNazwa(nazwa);
        setCena(cena);
        setWaga(masa);
    }

    public void uruchom() {
        Obroty = MinimalneObroty;
    }

    public void zatrzymaj() {
        Obroty = 0;
    }


    public void zwiekszObroty() {
        if (Obroty > 0) {
            if (Obroty + 100 <= MaksymalneObroty) {
                Obroty = Obroty + 100;
            } else {
                Obroty = MaksymalneObroty;
            }
            Obroty = Obroty + 100;
        }
    }

    public void zmiejszObroty() {
        if (Obroty > 0) {
            if ((Obroty - 100) >= MinimalneObroty) {
                Obroty = Obroty - 100;
            } else if ((Obroty - 100) < MinimalneObroty) {
                Obroty = MinimalneObroty;
            }
        }
    }

    public boolean UruchomionyBoolean() {
        if (Obroty > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int GetObroty() {
        return Obroty;
    }

    public int getMaksymalneObroty() {
        return MaksymalneObroty;
    }

    public int getMinObroty() {
        return MinimalneObroty;
    }

    @Override
    public String toString() {
        return getNazwa();
    }

}

