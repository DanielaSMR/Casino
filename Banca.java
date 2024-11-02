public class Banca {
    int bancaTotal;
 
    public Banca(){
        bancaTotal = 50000;
    }

    public synchronized  void sumarBanca(int dineroJugador){
        bancaTotal = bancaTotal + dineroJugador;
    }

    public synchronized  void restarBanca(int dineroJugador){
        bancaTotal = bancaTotal - dineroJugador;
    }

    public synchronized  int getBancaTotal() {
        return bancaTotal;
    }

    public synchronized  void setBancaTotal(int bancaTotal) {
        this.bancaTotal = bancaTotal;
    }

    
}
