package samochód;

public class SkrzyniaBiegow extends Komponent {

    private int AktualnyBieg;
    private int IloscBiegow;
    private int Przelozenie;

    public SkrzyniaBiegow(int iloscBiegow, String nazwa, int cena, int waga) {
        AktualnyBieg = 1;
        IloscBiegow = iloscBiegow;
        Przelozenie = 1;
        setNazwa(nazwa);
        setWaga(waga);
        setCena(cena);
    }


    public void ZwiekszBieg() {
        if (AktualnyBieg + 1 <= IloscBiegow) {
            AktualnyBieg++;
            Przelozenie = 1 * AktualnyBieg;
        } else {
            throw new SkrzyniaBiegowException("blad");
        }
    }

    public void ZmniejszBieg() {
        if (AktualnyBieg - 1 >= 1) {
            AktualnyBieg--;
            Przelozenie = 1 * AktualnyBieg;
        } else {
            throw new SkrzyniaBiegowException("blad");
        }
    }

    public int GetAktualnyBieg() {
        return AktualnyBieg;
    }

    public int GetAktualnePrzelozenie() {
        return Przelozenie;
    }

    @Override
    public String toString() {
        return getNazwa();
    }

}