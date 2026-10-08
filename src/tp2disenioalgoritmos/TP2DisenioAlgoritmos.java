/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tp2disenioalgoritmos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author ulisescorrales
 */
public class TP2DisenioAlgoritmos {

    /**
     * @param args the command line arguments
     */
    public static void simularDatos(int preferencias_H[][], int preferencias_M[][], boolean forbidden[][], int n){
        Random ran = new Random();
        int aleatorio;
        LinkedList numerosUsados;
        for (int i = 0; i < n; i++) {
            numerosUsados = new LinkedList();
            for (int j = 0; j < n; j++) {
                do {
                    aleatorio = ran.nextInt(n);
                } while (numerosUsados.contains(aleatorio));
                numerosUsados.addFirst(aleatorio);
                preferencias_H[i][j] = aleatorio;
            }
        }
        for (int i = 0; i < n; i++) {
            numerosUsados = new LinkedList();
            for (int j = 0; j < n; j++) {
                do {
                    aleatorio = ran.nextInt(n);
                } while (numerosUsados.contains(aleatorio));
                numerosUsados.addFirst(aleatorio);
                preferencias_M[i][j] = aleatorio;
            }
        }
        //Por cada persona hay una pareja prohibida
        for (int i = 0; i < n; i++) {
            aleatorio = ran.nextInt(n);
            forbidden[i][aleatorio] = true;
        }

        //Imprimir datos simulados
        System.out.println("Preferencia de hombres");
        for (int i = 0; i < n; i++) {
            System.out.println("Hombre " + i + ":");
            for (int j = 0; j < n; j++) {
                System.out.print(preferencias_H[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("Preferencia de mujeres");
        for (int i = 0; i < n; i++) {
            System.out.println("Mujer " + i + ":");
            for (int j = 0; j < n; j++) {
                System.out.print(preferencias_M[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("Prohibidos: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (forbidden[i][j]) {
                    System.out.printf("(%d-%d)%n", i, j);
                }
            }
        }
    }

    public static void cargarDatosFijos(int preferencias_H[][], int preferencias_M[][], boolean forbidden[][], int n) {
    // Valores fijos solicitados (n = 3)
    int[][] datosH = {
        {1, 0, 2},
        {2, 1, 0},
        {1, 0, 2}
    };

    int[][] datosM = {
        {2, 1, 0},
        {2, 1, 0},
        {0, 1, 2}
    };

    // Rellenar preferencias de hombres
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            preferencias_H[i][j] = datosH[i][j];
        }
    }

    // Rellenar preferencias de mujeres
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            preferencias_M[i][j] = datosM[i][j];
        }
    }

    // Reiniciar matriz de prohibidos y asignar (0-1), (1-2), (2-0)
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            forbidden[i][j] = false;
        }
    }
    forbidden[0][1] = true;
    forbidden[1][2] = true;
    forbidden[2][0] = true;

    // Imprimir datos
    System.out.println("Preferencia de hombres");
    for (int i = 0; i < n; i++) {
        System.out.println("Hombre " + i + ":");
        for (int j = 0; j < n; j++) {
            System.out.print(preferencias_H[i][j] + " ");
        }
        System.out.println("");
    }

    System.out.println("Preferencia de mujeres");
    for (int i = 0; i < n; i++) {
        System.out.println("Mujer " + i + ":");
        for (int j = 0; j < n; j++) {
            System.out.print(preferencias_M[i][j] + " ");
        }
        System.out.println("");
    }

    System.out.println("Prohibidos: ");
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (forbidden[i][j]) {
                System.out.printf("(%d-%d)%n", i, j);
            }
        }
    }
}
    public static boolean mujerTienePareja(int parejas_H[], int nro_mujer) {
        boolean tienePareja = false;
        int i = 0;
        int n = parejas_H.length;
        while (i < n && !tienePareja) {
            if (parejas_H[i] == nro_mujer) {
                tienePareja = true;
            }
        }
        return tienePareja;
    }

    public static boolean mujerPrefiereA(int preferencias_M[][], int mujerActual, int hombreActual, int hombre2) {
        int posHombre2 = -1;
        int posHombreActual = -1;
        int n = preferencias_M.length;

        for (int i = 0; i < n; i++) {
            if (preferencias_M[mujerActual][i] == hombreActual) {
                posHombreActual = i;
            } else if (preferencias_M[mujerActual][i] == hombre2) {
                posHombre2 = i;
            }
        }

        return posHombreActual < posHombre2;
    }

    public static int mujer_pareja_actual(int parejas_H[], int mujer) {
        int parejaActual = -1;
        int n = parejas_H.length;
        int i = 0;
        while (parejaActual == -1 && i < n) {
            if (parejas_H[i] == mujer) {
                parejaActual = i;
            } else {
                i++;
            }
        }
        return parejaActual;
    }

    public static void main(String[] args) {
        //Versión H-proponen
        //n es la cantidad personas de cada género

        int n = 3;
        int preferencias_H[][] = new int[n][n];
        int preferencias_M[][] = new int[n][n];

        int propuestos_H[] = new int[n];
        for (int i = 0; i < n; i++) {
            propuestos_H[i] = 0;
        }

        //Parejas de prohibidos. Para el hombre i y la mujer j, si forbiden[i][j]=1 entonces es una pareja prohibida.
        boolean forbidden[][] = new boolean[n][n];

        simularDatos(preferencias_H, preferencias_M, forbidden, n);
        //cargarDatosFijos(preferencias_H, preferencias_M, forbidden, n);

        int parejas_H[] = new int[n];
        for (int i = 0; i < n; i++) {
            //-1: no tiene pareja
            parejas_H[i] = -1;
        }

        Queue hombresLibres = new LinkedList();
        for (int i = 0; i < n; i++) {
            hombresLibres.add(i);
        }
        while (!hombresLibres.isEmpty()) {
            int hombreActual = (int) hombresLibres.poll();
            System.out.println("Busca el hombre: "+hombreActual);
            int contador_mujer = propuestos_H[hombreActual];
            int mejorMujer = -1;
            //Buscar a la mejor mujer que todavía no ha sido propuesta y que no esté prohibida
             while (contador_mujer < n && mejorMujer == -1){
                mejorMujer = preferencias_H[hombreActual][contador_mujer];
                if (forbidden[hombreActual][mejorMujer]) {
                    mejorMujer = -1;
                }
                contador_mujer++;
            }
            propuestos_H[hombreActual]=contador_mujer;
            //Si se encontró una potencial pareja
            if (mejorMujer != -1) {
                System.out.println("Ficha a la mujer: "+mejorMujer);
                int parejaMujerActual = mujer_pareja_actual(parejas_H, mejorMujer);
                //Si la mujer no está en pareja con otro
                if (parejaMujerActual == -1) {
                    //No tiene pareja, emparejar
                    parejas_H[hombreActual] = mejorMujer;
                    System.out.printf("Pareja: %d - %d%n",hombreActual,mejorMujer);
                } else if (mujerPrefiereA(preferencias_M, mejorMujer, hombreActual, parejaMujerActual)) {
                        //Prefiere al hombre entrante
                        parejas_H[hombreActual] = mejorMujer;
                        parejas_H[parejaMujerActual] = -1;
                        hombresLibres.add(parejaMujerActual);
                        System.out.printf("Borrado: %d - %d%n",parejaMujerActual,mejorMujer);
                        System.out.printf("Pareja: %d - %d%n",hombreActual,mejorMujer);
                }else{
                    //Lo rechaza, sigue en lista de espera
                    hombresLibres.add(hombreActual);
                }
            }
        }
        
        //Imprimir resultados
        System.out.println("Parejas formadas:");
        for (int i = 0; i < parejas_H.length; i++) {
            System.out.printf("(%d - %d)%n",i,parejas_H[i]);
        }
    }
}
