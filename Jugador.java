import java.util.Random;

public class Jugador implements Runnable{
    private Random generador = new Random();
    private int dinero;
    private String nombre;
    private Banca b;
    private int tipoJuego;
    private int apuesta;

    public Jugador(String nombre,int tipoJuego,Banca b){
        this.nombre = nombre;
        this.tipoJuego = tipoJuego;
        this.b = b;
        dinero = 100;
        this.apuesta = 10;
    }


    @Override
    public void run(){  
        while (dinero > 1 && b.getBancaTotal() > 0) {
            int numRuleta = croupier();      
            if(numRuleta == 0){
                System.out.println("El numero es..0");
                dinero = 0;
                break;
            }
            System.out.println("El numero es.." + numRuleta);
            switch (tipoJuego) {
                case 0:
                    numeroConcreto(numRuleta);
                    break;
                case 1:
                    numeroParImpar(numRuleta);
                    break;
                case 2:
                    numeroMartilanga(numRuleta);
                    break;
                default:
                    break;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    private int croupier() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return generador.nextInt(36);
    }

    public void numeroConcreto(int num){
        if(dinero > apuesta){

            apostar(apuesta);
            int resultado = 1+generador.nextInt(36);
            System.out.println(nombre + " Tiene el resultado" + resultado);
            
            if(resultado == num){
                ganar(apuesta * 36);
                System.out.println(nombre + " GANO! Ahora tiene: " + dinero);
                
            }
            System.out.println(nombre + " Tiene " + dinero);
        }
    }

    public void numeroParImpar(int num){
        if(dinero > apuesta){
            apostar(apuesta);
            int resultado = 1+generador.nextInt(36);
            System.out.println(nombre + " Tiene el resultado" + resultado);

            if(resultado/2 == num/2){
                ganar(20);
                System.out.println(nombre + " GANO! Ahora tiene: " + dinero);
            }
            System.out.println(nombre + " Tiene " + dinero);
        }
    }

    public void numeroMartilanga(int num){
        if(dinero > apuesta){
            apostar(apuesta);
            int resultado = 1+generador.nextInt(36);
            System.out.println(nombre + " Tiene el resultado" + resultado);

            if(resultado == num){
                ganar(apuesta * 36);
                System.out.println(nombre + " GANO! Ahora tiene: " + dinero);
                apuesta = 10;
            }else{
                apuesta = apuesta * 2;
                apostar(apuesta);
            }
            System.out.println(nombre + " Tiene " + dinero);
        }

    }

    private synchronized  void apostar(int cantidad) {
        if (dinero >= cantidad) {
            dinero -= cantidad;
            b.sumarBanca(cantidad);
        }
    }
    
    private synchronized  void ganar(int cantidad) {
        dinero += cantidad;
        b.restarBanca(cantidad);
    }

    public int getDinero() {
        return dinero;
    }


    public void setDinero(int dinero) {
        this.dinero = dinero;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getApuesta() {
        return apuesta;
    }


    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    

        
}
