import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class actividadBingo {

    public static void main(String[] args) {
        int[] carton = generarCarton();
        System.out.println("¡Bienvenido al juego de Bingo!");
        System.out.println("Tu cartón es: " + Arrays.toString(carton));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad de dinero para la apuesta: ");
        double apuesta = scanner.nextDouble();
        System.out.print("Ingrese la cantidad de intentos para hacer el bingo: ");
        int intentosBingo = scanner.nextInt();

        jugarBingo(carton, apuesta, intentosBingo);
    }

    private static int[] generarCarton() {
        int[] carton = new int[10];
        for (int i = 0; i < 10; i++) {
            int numeroAleatorio;
            do {
                numeroAleatorio = new Random().nextInt(90) + 1;
            } while (contiene(carton, numeroAleatorio));
            carton[i] = numeroAleatorio;
        }
        Arrays.sort(carton);
        return carton;
    }

    private static boolean contiene(int[] carton, int numero) {
        for (int n : carton) {
            if (n == numero) {
                return true;
            }
        }
        return false;
    }

    private static void jugarBingo(int[] carton, double apuesta, int intentosBingo) {
        var numerosSacados = new boolean[91];
        int numerosParaBingo = 0;

        int intentosRealizados = 0;
        while (intentosRealizados < intentosBingo && !esCartonCompleto(carton)) {
            int numeroAleatorio;
            do {
                numeroAleatorio = new Random().nextInt(90) + 1;
            } while (numerosSacados[numeroAleatorio]);

            System.out.println("Número extraído: " + numeroAleatorio);
            numerosSacados[numeroAleatorio] = true;

            if (contiene(carton, numeroAleatorio)) {
                System.out.println("¡Número acertado!");
                carton = quitarNumero(carton, numeroAleatorio);

                if (carton.length == 5) {
                    System.out.println("Se han acertado 5 números del cartón.");
                }
            
            }

            numerosParaBingo++;
            intentosRealizados++;
        }

        if (esCartonCompleto(carton) || intentosRealizados == intentosBingo) {
            if (esCartonCompleto(carton)) {
                System.out.println("¡Felicidades! Todos los números han sido acertados. El bingo es correcto.");
            } else {
                System.out.println("El cartón no fue premiado con Bingo en esta partida.");
            }
            double premio = (esCartonCompleto(carton)) ? apuesta * 10 : 0;
            System.out.println((premio > 0) ? "La apuesta ha sido ganadora. Premio del jugador: $" + premio : "La apuesta no fue ganadora en este juego.");
        }

        
    }

    private static boolean esCartonCompleto(int[] carton) {
        for (int n : carton) {
            if (n != 0) {
                return false;
            }
        }
        return true;
    }

    private static int[] quitarNumero(int[] carton, int numero) {
        for (int i = 0; i < carton.length; i++) {
            if (carton[i] == numero) {
                carton[i] = 0;
                break;
            }
        }
        return carton;
    }
}