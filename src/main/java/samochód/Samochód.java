package samochód;

import java.util.ArrayList;

public class Samochód extends Thread {
    public Silnik silnik;
    public SkrzyniaBiegow skrzyniaBiegow;
    public Sprzeglo sprzeglo;
    public Pozycja pozycja;
    private Pozycja cel;
    public String nrRejestracyjny;
    public String model;

    private final Object obiektSynchronizujący = new Object();

    private ArrayList<Listener> listeners = new ArrayList<>();

    public Samochód(String model, String nrrejestracyjny, Silnik silnik, SkrzyniaBiegow skrzyniaBiegow) {
        this.silnik = silnik;
        this.skrzyniaBiegow = skrzyniaBiegow;
        this.model = model;
        nrRejestracyjny = nrrejestracyjny;
        sprzeglo = new Sprzeglo();
        notifyListeners();
        this.start();
    }


    public interface Listener{
        void update();
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }
    private void notifyListeners() {
        for (Listener listener : listeners) {
            listener.update();
        }
    }




    public double pozycjax() {
        return pozycja.getPozycjaX();
    }

    public double pozycjay() {
        return pozycja.getPozycjaY();
    }

    public void setPozycja(Pozycja pozycja) {
        this.pozycja = pozycja;
        notifyListeners();
    }

    public void jedzDo(double x, double y) {
        synchronized (obiektSynchronizujący) {
            this.cel = new Pozycja(x, y);
            notifyListeners();

        }
    }


    @Override
    public String toString() {
        return model;
    }


    public void wlacz() {
        silnik.uruchom();
        notifyListeners();
    }

    public void wylacz() {
        silnik.zatrzymaj();
        notifyListeners();
    }


    public int aktualna_predkosc() {
        if (silnik.UruchomionyBoolean()) {
            return skrzyniaBiegow.GetAktualnePrzelozenie() * silnik.GetObroty() / 100;
        } else {
            return 0;
        }
    }


    public void ZwiekszBieg() {
        if (sprzeglo.getStan())
            skrzyniaBiegow.ZwiekszBieg();
        notifyListeners();
    }

    public void ZmniejszBieg() {
        if (sprzeglo.getStan())
            skrzyniaBiegow.ZmniejszBieg();
        notifyListeners();
    }

    public String getModel() {
        return model;
    }

    
    @Override
    public void run() {
        double deltat = 0.02;
        while (true) {
            synchronized (obiektSynchronizujący) {
                if (cel != null) {
                    double odleglosc = Math.sqrt(Math.pow(cel.getPozycjaX() - pozycja.getPozycjaX(), 2) + Math.pow(cel.getPozycjaY() - pozycja.getPozycjaY(), 2));
                    if (odleglosc < 1) {
                        cel = null;
                    } else {
                        double dx = aktualna_predkosc() * deltat * (cel.getPozycjaX() - pozycja.getPozycjaX()) / odleglosc;
                        double dy = aktualna_predkosc() * deltat * (cel.getPozycjaY() - pozycja.getPozycjaY()) / odleglosc;
                        synchronized (obiektSynchronizujący) {
                            pozycja.setPozycjaX(pozycja.getPozycjaX() + dx);
                            pozycja.setPozycjaY(pozycja.getPozycjaY() + dy);
                        }
                    }
                }
            }
            notifyListeners();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}


