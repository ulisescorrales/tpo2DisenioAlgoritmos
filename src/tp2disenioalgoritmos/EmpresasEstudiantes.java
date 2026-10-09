/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp2disenioalgoritmos;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ulisescorrales
 */
public class EmpresasEstudiantes {

    public static void empresasProponen() {
        int n = 4;
        int prefEmp[][] = {
            {0, 1, 2, 3},
            {3, 2, 1, 0},
            {1, 3, 0, 2},
            {2, 0, 3, 1}
        };
        int rankEst[][] = {
            {1, 2, 3, 0},
            {2, 3, 0, 1},
            {3, 0, 1, 2},
            {0, 1, 2, 3}
        };
        System.out.println("Preferencias empresas: ");
        for (int i = 0; i < n; i++) {
            System.out.println("Empresa " + i + ":");
            for (int j = 0; j < n; j++) {
                System.out.print(prefEmp[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("Preferencias estudiantes: ");
        for (int i = 0; i < n; i++) {
            System.out.println("Estudiante " + i + ":");
            for (int j = 0; j < n; j++) {
                System.out.print(rankEst[i][j] + " ");
            }
            System.out.println("");
        }

        int asignEmp[] = new int[n];
        int asignEst[] = new int[n];
        int nextEmp[] = new int[n];
        Queue empresasLibres = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            asignEmp[i] = -1;
            asignEst[i] = -1;
            empresasLibres.add(i);
        }

        int e;
        int mejorEst;

        while (!empresasLibres.isEmpty()) {
            e = (int) empresasLibres.peek();
            mejorEst = prefEmp[e][nextEmp[e]];
            nextEmp[e]++;
            if (asignEst[mejorEst] == -1) {
                asignEmp[e] = mejorEst;
                asignEst[mejorEst] = e;
                empresasLibres.poll();
            } else {
                int e2 = asignEst[mejorEst];
                if (rankEst[mejorEst][e] < rankEst[mejorEst][e2]) {
                    asignEst[mejorEst] = e;
                    asignEmp[e] = mejorEst;
                    asignEmp[e2] = -1;
                    empresasLibres.poll();
                    empresasLibres.add(e2);
                }
            }

        }

        System.out.println("Parejas formadas:");
        for (int i = 0; i < n; i++) {
            System.out.printf("( %d - %d ) %n", i, asignEmp[i]);
        }
    }

    public static void estudiantesProponen() {
        int n = 4;

        // Lista de preferencias de las empresas ordenadas de mayor a menor preferencia
        int prefEmp[][] = {
            {0, 1, 2, 3},
            {3, 2, 1, 0},
            {1, 3, 0, 2},
            {2, 0, 3, 1}
        };

        // Lista de preferencias de los estudiantes ordenadas de mayor a menor preferencia
        int prefEst[][] = {
            {1, 2, 3, 0},
            {2, 3, 0, 1},
            {3, 0, 1, 2},
            {0, 1, 2, 3}
        };

        // Matriz de rangos para las empresas: permite comparar en O(1)
        // rankEmp[empresa][estudiante] = posición/prioridad (menor valor = mayor preferencia)
        int rankEmp[][] = new int[n][n];
        for (int emp = 0; emp < n; emp++) {
            for (int orden = 0; orden < n; orden++) {
                rankEmp[emp][prefEmp[emp][orden]] = orden;
            }
        }

        System.out.println("Preferencias empresas: ");
        for (int i = 0; i < n; i++) {
            System.out.println("Empresa " + i + ":");
            for (int j = 0; j < n; j++) {
                System.out.print(prefEmp[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Preferencias estudiantes: ");
        for (int i = 0; i < n; i++) {
            System.out.println("Estudiante " + i + ":");
            for (int j = 0; j < n; j++) {
                System.out.print(prefEst[i][j] + " ");
            }
            System.out.println();
        }

        int asignEst[] = new int[n];       // asignEst[est] = empresa asignada
        int asignEmp[] = new int[n];       // asignEmp[emp] = estudiante asignado
        int nextEst[] = new int[n];        // Siguiente empresa a la que el estudiante propondrá
        Queue<Integer> estudiantesLibres = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            asignEst[i] = -1;
            asignEmp[i] = -1;
            nextEst[i] = 0;
            estudiantesLibres.add(i);
        }

        while (!estudiantesLibres.isEmpty()) {
            int s = estudiantesLibres.peek();          // Estudiante actual
            int mejorEmp = prefEst[s][nextEst[s]];     // Empresa favorita a la que no ha propuesto
            nextEst[s]++;                              // Avanza en su lista para la próxima vez

            if (asignEmp[mejorEmp] == -1) {
                // La empresa está libre: acepta provisionalmente la propuesta
                asignEmp[mejorEmp] = s;
                asignEst[s] = mejorEmp;
                estudiantesLibres.poll();
            } else {
                // La empresa ya tiene un estudiante asignado (s2)
                int s2 = asignEmp[mejorEmp];

                // Si la empresa prefiere al nuevo estudiante 's' sobre 's2'
                if (rankEmp[mejorEmp][s] < rankEmp[mejorEmp][s2]) {
                    asignEmp[mejorEmp] = s;
                    asignEst[s] = mejorEmp;
                    asignEst[s2] = -1;

                    estudiantesLibres.poll();
                    estudiantesLibres.add(s2); // El estudiante desplazado vuelve a estar libre
                }
                // Si la empresa prefiere quedarse con 's2', 's' sigue en la cola para la próxima iteración
            }
        }

        System.out.println("\nParejas formadas (Estudiante - Empresa):");
        for (int i = 0; i < n; i++) {
            System.out.printf("( Estudiante %d - Empresa %d )%n", i, asignEst[i]);
        }
    }

    public static void main(String[] args) {
        estudiantesProponen();
    }
}
