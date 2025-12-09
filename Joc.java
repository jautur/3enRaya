import java.util.Random;
import java.util.Scanner;

public class Joc { 

    public static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Benvingut al joc del 3 en Raya!");
        System.out.println("Tria entre aquestes modalitats:");
        System.out.println("(1) 1 Jugador | (2) 2 Jugadors");
        
        int opcio = entrada.nextInt();
        
        char[][] graella = new char[3][3];
        inicialitzarGraella(graella); 

        if (opcio == 1) {
            System.out.println("Has triat la modalitat de 1 jugador.");
            jugaVsCPU(graella);
        } else if (opcio == 2) {
            System.out.println("Has triat la modalitat de 2 jugadors.");
            jugaVsPersona(graella);
        } else {
            System.out.println("Opció no vàlida.");
        }
    }

    public static void inicialitzarGraella(char[][] graella) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                graella[i][j] = ' ';
            }
        }
    }

    public static void jugaVsCPU(char[][] graella) {
        boolean fiPartida = false;
        while (!fiPartida) {
            printarGraella(graella);
            
            demanarJugada(graella, 'X');
            if (verificarFiJoc(graella)) break;
            entrada.nextLine();
            System.out.println("La CPU està pensant...");
            generarJugadaCPU(graella);
            if (verificarFiJoc(graella)) break;
        }
    }

    public static void jugaVsPersona(char[][] graella) {
        boolean fiPartida = false;
        while (!fiPartida) {
            printarGraella(graella);
            
            System.out.println("Torn del Jugador 1 (X)");
            demanarJugada(graella, 'X');
            if (verificarFiJoc(graella)) break;

            printarGraella(graella); 

            System.out.println("Torn del Jugador 2 (O)");
            demanarJugada(graella, 'O');
            if (verificarFiJoc(graella)) break;
        }
    }

    public static void demanarJugada(char[][] graella, char fitxa) {
        boolean movimentValid = false;
        while (!movimentValid) {
            System.out.println("Introdueix la casella a plenar (1-9): ");
            int casella = entrada.nextInt();
            int fila = (casella - 1) / 3;
            int columna = (casella - 1) % 3;

            if (fila >= 0 && fila < 3 && columna >= 0 && columna < 3) {
                if (graella[fila][columna] == ' ') {
                    graella[fila][columna] = fitxa;
                    movimentValid = true; 
                } else {
                    System.out.println("La casella ja està ocupada. Torna-ho a provar.");
                }
            } else {
                System.out.println("Casella no vàlida (1-9).");
            }
        }
    }

    public static void generarJugadaCPU(char[][] graella) {
        Random aleatori = new Random();
        boolean movimentValid = false;
        
        while (!movimentValid) {
            int casella = aleatori.nextInt(9) + 1;
            int fila = (casella - 1) / 3;
            int columna = (casella - 1) % 3;

            if (graella[fila][columna] == ' ') {
                graella[fila][columna] = 'O';
                movimentValid = true;
            }
        }
    }

    public static boolean verificarFiJoc(char[][] graella) {
        if (comprovarVictoria(graella)) {
            printarGraella(graella); 
            return true;
        }
        if (comprovarEmpat(graella)) {
            printarGraella(graella);
            System.out.println("La partida ha acabat en empat!");
            return true;
        }
        return false;
    }

    public static boolean comprovarVictoria(char[][] graella) {
        for (int i = 0; i < 3; i++) {
            if (graella[i][0] == graella[i][1] && graella[i][1] == graella[i][2] && graella[i][0] != ' ') {
                System.out.println("Ha guanyat el jugador amb " + graella[i][0]);
                return true;
            }
            if (graella[0][i] == graella[1][i] && graella[1][i] == graella[2][i] && graella[0][i] != ' ') {
                System.out.println("Ha guanyat el jugador amb " + graella[0][i]);
                return true;
            }
        }
        if (graella[0][0] == graella[1][1] && graella[1][1] == graella[2][2] && graella[0][0] != ' ') {
            System.out.println("Ha guanyat el jugador amb " + graella[0][0]);
            return true;
        }
        if (graella[0][2] == graella[1][1] && graella[1][1] == graella[2][0] && graella[0][2] != ' ') {
            System.out.println("Ha guanyat el jugador amb " + graella[0][2]);
            return true;
        }
        return false;
    }

    public static boolean comprovarEmpat(char[][] graella) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (graella[i][j] == ' ') {
                    return false; 
                }
            }
        }
        return true; 
    }

    public static void printarGraella(char[][] graella) {
        System.out.println("\n-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(graella[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }
}