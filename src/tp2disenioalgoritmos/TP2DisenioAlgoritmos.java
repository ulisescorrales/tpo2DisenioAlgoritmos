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
    public static void simularDatos(int preferencias_H[][],int preferencias_M[][],boolean forbidden[][],int n){
        Random ran = new Random();
        int aleatorio;
        LinkedList numerosUsados;
        for (int i = 0; i < n; i++) {
            numerosUsados=new LinkedList();
            for (int j = 0; j < n; j++) {
                do {
                    aleatorio = ran.nextInt(n);
                } while (numerosUsados.contains(aleatorio));
                numerosUsados.addFirst(aleatorio);
                preferencias_H[i][j] = aleatorio;
            }
        }
        for (int i = 0; i < n; i++) {
            numerosUsados=new LinkedList();
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
            aleatorio=ran.nextInt(n);
            forbidden[i][aleatorio]=true;
        }
        
        //Imprimir datos simulados
        System.out.println("Preferencia de hombres");
        for (int i = 0; i < n; i++) {
            System.out.println("Hombre "+i+":");
            for (int j = 0; j < n; j++) {
                System.out.print(preferencias_H[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("Preferencia de mujeres");
        for (int i = 0; i < n; i++) {
            System.out.println("Mujer "+i+":");
            for (int j = 0; j < n; j++) {
                System.out.print(preferencias_M[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("Prohibidos: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(forbidden[i][j]){
                    System.out.printf("(%d-%d)%n",i,j);
                }
            }
        }
    }
    public static boolean mujerTienePareja(int parejas_H[],int nro_mujer){
        boolean tienePareja=false;
        int i=0;
        int n=parejas_H.length;
        while(i<n && !tienePareja){
            if(parejas_H[i]==nro_mujer){
                tienePareja=true;
            }
        }
        return tienePareja;
    }
    public static boolean mujerPrefiereA(int preferencias_M[][],int mujerActual,int hombreActual,int hombre2){
        boolean preferieAHombre=true;
        int posHombre2=-1;
        int posHombreActual=-1;
        int n=preferencias_M.length;
        
        for (int i = 0; i < n; i++) {
            if(preferencias_M[mujerActual][i]==hombreActual){
                posHombreActual=1;
            }else if(preferencias_M[mujerActual][i]==hombre2){
                posHombre2=i;
            }
        }
        
        return posHombreActual< posHombre2;
    }
    public static void main(String[] args) {
        //Versión H-proponen
        //n es la cantidad personas de cada género

        int n = 6;
        int preferencias_H[][] = new int[n][n];
        int preferencias_M[][] = new int[n][n];
        
        LinkedList propuestos_H[]=new LinkedList[n];
        for (int i = 0; i < n; i++) {
            propuestos_H[i]=new LinkedList();
        }

        //Parejas de prohibidos. Para el hombre i y la mujer j, si forbiden[i][j]=1 entonces es una pareja prohibida.
        boolean forbidden[][] = new boolean[n][n];

        simularDatos(preferencias_H,preferencias_M,forbidden,n);
        
        int parejas_H[]=new int[n];
        for (int i = 0; i < n; i++) {
            //-1: no tiene pareja
            parejas_H[i]=-1;
        }
        
        Queue hombresLibres=new LinkedList();
        for (int i = 0; i < n; i++) {
            hombresLibres.add(i);
        }
        while(!hombresLibres.isEmpty()){
            int hombreActual=(int)hombresLibres.poll();
            int contador_mujer=0;
            int mejorMujer;
            //Buscar a la mejor mujer que todavía no ha sido propuesta
            do{
                mejorMujer=preferencias_H[hombreActual][contador_mujer];
                contador_mujer++;
            }while(contador_mujer<n && propuestos_H[hombreActual].contains(mejorMujer));
            //Si la mujer no está en pareja con otro
            if(!mujerTienePareja(parejas_H,mejorMujer)){
                //Emparejar
                parejas_H[hombreActual]=mejorMujer;
            }else{
                if(mujerPrefiereA(preferencias_M,mujerActual,hombreActual,pareja_mujer_actual)){
                    
                }
            }
        }
    }
}