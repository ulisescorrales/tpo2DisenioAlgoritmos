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
public static void empresasProponen(int n, int[][] prefEmp, int[][] prefEst) {
        int rankEst[][] = new int[n][n];
        for (int est = 0; est < n; est++) {
            for (int orden = 0; orden < n; orden++) {
                rankEst[est][prefEst[est][orden]] = orden;
            }
        }

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
                System.out.print(prefEst[i][j] + " ");
            }
            System.out.println("");
        }
        
        System.out.println("\n--- INICIANDO ASIGNACIONES (EMPRESAS PROPONEN) ---");

        int asignEmp[] = new int[n];
        int asignEst[] = new int[n];
        int nextEmp[] = new int[n];
        Queue<Integer> empresasLibres = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            asignEmp[i] = -1;
            asignEst[i] = -1;
            empresasLibres.add(i);
        }

        int e;
        int mejorEst;

        while (!empresasLibres.isEmpty()) {
            e = empresasLibres.peek();
            mejorEst = prefEmp[e][nextEmp[e]];
            nextEmp[e]++;
            
            System.out.println("-> La Empresa " + e + " propone al Estudiante " + mejorEst);

            if (asignEst[mejorEst] == -1) {
                System.out.println("   [NUEVA PAREJA] El Estudiante " + mejorEst + " está libre. Se forma la pareja provisional (Empresa " + e + " - Estudiante " + mejorEst + ")");
                asignEmp[e] = mejorEst;
                asignEst[mejorEst] = e;
                empresasLibres.poll();
            } else {
                int e2 = asignEst[mejorEst];
                if (rankEst[mejorEst][e] < rankEst[mejorEst][e2]) {
                    System.out.println("   [BORRA PAREJA] El Estudiante " + mejorEst + " prefiere a la Empresa " + e + " sobre la Empresa " + e2 + ". Se borra la pareja (Empresa " + e2 + " - Estudiante " + mejorEst + ")");
                    System.out.println("   [NUEVA PAREJA] Se forma la nueva pareja provisional (Empresa " + e + " - Estudiante " + mejorEst + ")");
                    
                    asignEst[mejorEst] = e;
                    asignEmp[e] = mejorEst;
                    asignEmp[e2] = -1;
                    empresasLibres.poll();
                    empresasLibres.add(e2);
                } else {
                    System.out.println("   [RECHAZO] El Estudiante " + mejorEst + " prefiere quedarse con su actual Empresa " + e2 + ". Rechaza a la Empresa " + e);
                }
            }
            System.out.println();
        }

        System.out.println("Parejas finales formadas (Empresa - Estudiante):");
        for (int i = 0; i < n; i++) {
            System.out.printf("( Empresa %d - Estudiante %d )%n", i, asignEmp[i]);
        }
    }

    public static void estudiantesProponen(int n, int[][] prefEmp, int[][] prefEst) {
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
        
        System.out.println("\n--- INICIANDO ASIGNACIONES (ESTUDIANTES PROPONEN) ---");

        int asignEst[] = new int[n];
        int asignEmp[] = new int[n];
        int nextEst[] = new int[n];
        Queue<Integer> estudiantesLibres = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            asignEst[i] = -1;
            asignEmp[i] = -1;
            nextEst[i] = 0;
            estudiantesLibres.add(i);
        }

        while (!estudiantesLibres.isEmpty()) {
            int s = estudiantesLibres.peek();
            int mejorEmp = prefEst[s][nextEst[s]];
            nextEst[s]++;
            
            System.out.println("-> El Estudiante " + s + " propone a la Empresa " + mejorEmp);

            if (asignEmp[mejorEmp] == -1) {
                System.out.println("   [NUEVA PAREJA] La Empresa " + mejorEmp + " está libre. Se forma la pareja provisional (Estudiante " + s + " - Empresa " + mejorEmp + ")");
                asignEmp[mejorEmp] = s;
                asignEst[s] = mejorEmp;
                estudiantesLibres.poll();
            } else {
                int s2 = asignEmp[mejorEmp];

                if (rankEmp[mejorEmp][s] < rankEmp[mejorEmp][s2]) {
                    System.out.println("   [BORRA PAREJA] La Empresa " + mejorEmp + " prefiere al Estudiante " + s + " sobre el Estudiante " + s2 + ". Se borra la pareja (Estudiante " + s2 + " - Empresa " + mejorEmp + ")");
                    System.out.println("   [NUEVA PAREJA] Se forma la nueva pareja provisional (Estudiante " + s + " - Empresa " + mejorEmp + ")");
                    
                    asignEmp[mejorEmp] = s;
                    asignEst[s] = mejorEmp;
                    asignEst[s2] = -1;

                    estudiantesLibres.poll();
                    estudiantesLibres.add(s2);
                } else {
                    System.out.println("   [RECHAZO] La Empresa " + mejorEmp + " prefiere quedarse con su actual Estudiante " + s2 + ". Rechaza al Estudiante " + s);
                }
            }
            System.out.println();
        }

        System.out.println("Parejas finales formadas (Estudiante - Empresa):");
        for (int i = 0; i < n; i++) {
            System.out.printf("( Estudiante %d - Empresa %d )%n", i, asignEst[i]);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        
        int prefEmp[][] = {
            {0, 1, 2, 3},
            {3, 2, 1, 0},
            {1, 3, 0, 2},
            {2, 0, 3, 1}
        };
        
        int prefEst[][] = {
            {1, 2, 3, 0},
            {2, 3, 0, 1},
            {3, 0, 1, 2},
            {0, 1, 2, 3}
        };

        System.out.println("============= EJECUCIÓN 1 =============");
        empresasProponen(n, prefEmp, prefEst);
        
        System.out.println("\n\n============= EJECUCIÓN 2 =============");
        estudiantesProponen(n, prefEmp, prefEst);
    }
}
