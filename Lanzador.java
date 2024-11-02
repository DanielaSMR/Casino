
public class Lanzador{
    public static void main(String[] args) {
        Jugador[] jugadoresNumC = new Jugador[12];
        Banca b = new Banca();

        for (int i = 0; i < jugadoresNumC.length; i++) {
            int contador = 0;
            if(i/4 == 0 && i != 0){
                contador++;
            }
            jugadoresNumC[i] = new Jugador("Hilo: " + (i),contador, b); // Tipo 0 para NumC
            
            /*jugadoresParImpar[i] = new Jugador(1); // Tipo 1 para ParImpar
            jugadoresMartingala[i] = new Jugador(2); // Tipo 2 para Martingala*/
        }

        Thread[] hilos = new Thread[jugadoresNumC.length];

        for (int i = 0; i < jugadoresNumC.length; i++) {
            hilos[i] = new Thread(jugadoresNumC[i]);
            hilos[i].start();
        }

        /*for (Thread hilo : hilos) {
            hilo.interrupt();
        }*/

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Simulación terminada.");
        System.out.println("Banca final: " + b.getBancaTotal());
        for (Jugador jugador : jugadoresNumC) {
            System.out.println(jugador.getNombre() + " finalizó con: " + jugador.getDinero() + " euros.");
        }

    } 
}