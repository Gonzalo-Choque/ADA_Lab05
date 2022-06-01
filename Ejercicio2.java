/*	Ejercicio2: Tiempo de ejecucion de BubleSort
*	Autores: 
*	- Gonzalo Joel Choque Dongo
*	- Glenny Shindely  Choque Vilcape
*/

package Lab05;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

import java.io.IOException;

public class Ejercicio2 {
	
	public static BufferedWriter bw = null;
	public static File gnu = null;
	
	public static void main(String[] args) throws IOException{
		
		gnu = new File ("Bublesort.txt");
    	gnu.createNewFile();
    	int inicio=10, fin=1510,crecimiento=100;
    	int tam[] = crearTamaños(inicio, fin, crecimiento);
    	
    	boolean random=true;// es true si el array es random, es false si el array es peor caso
    	bucleTiempos(tam,random);
    	
    	bw.close();
		Desktop.getDesktop().open(gnu);
	 	
	}
	private static int[] crearTamaños(int inicio, int fin, int crecimiento) {//crea todos los casos a ejecutar
		int tam = ((fin-inicio)/crecimiento)+ 1;
		int resultado[]=new int[tam];
		for(int i = 0; i<resultado.length; i++) {
			resultado[i]=inicio;
			inicio+=crecimiento;
		}
		return resultado;
	}
	public static void bucleTiempos(int[] tamaños, boolean random) throws IOException {//funcion de inicializacion
		if(random==true) {
			bucleTiempos(tamaños);//para un array random
		}else {
			bucleTiemposPeorCaso(tamaños);//para un array de la forma 4,3,2,1
		}
	}
	private static void bucleTiemposPeorCaso(int[] tamaños) throws IOException {//crea y analiza en el peor caso
		long Tinicio, Tfinal;
		long tiempo;
		bw = new BufferedWriter(new FileWriter(gnu));

		int tam;
		for(int i=0; i < tamaños.length; i++) {
			//crear arreglos en peor caso
			tam = tamaños[i];
			int[] array =  crearArregloPeorCaso(tam);
			printArray(array);
			Tinicio = System.nanoTime();
			sortArray(array);
			Tfinal = System.nanoTime();
			printArray(array);
			tiempo = Tfinal-Tinicio;
			System.out.println("the task "+tam+" has taken "+tiempo+" nanoseconds");
			bw.write(tam+"\t"+tiempo+"\n");			
		}
	}
	private static void bucleTiempos(int [] tamaños) throws IOException {//crea y analiza un array random
		long Tinicio, Tfinal;
		long tiempo;
		bw = new BufferedWriter(new FileWriter(gnu));

		int tam;
		for(int i=0; i < tamaños.length; i++) {
			//crear arreglos en peor caso
			tam = tamaños[i];
			int[] array =  llenarArrayAleatorio(0,tam,tam);
			printArray(array);
			Tinicio = System.nanoTime();
			sortArray(array);
			Tfinal = System.nanoTime();
			tiempo = Tfinal-Tinicio;
			printArray(array);
			System.out.println("the task "+tam+" has taken "+tiempo+" nanoseconds");
			bw.write(tam+"\t"+tiempo+"\n");			
		}
	}
	public static void sortArray(int[] array) {//funcion BubbleSort
		for (int i = array.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (array[j] > array[j + 1]) {
					swap(array, j, j+1);
				}
			}
		}
	}

	public static void printArray(int[] array) {//imprime el array
		for (int i = 0; i < array.length; i++) {
			System.out.printf("%d \t", array[i]);
		}
		System.out.println();
	}

	private static void swap(int[] array, int a, int b) {//hace un intercambio entre a y b
		int value = array[b];
		array[b] = array[a];
		array[a] = value;
	}
	public static int [] llenarArrayAleatorio(int desde, int hasta, int tam){//llena el array aleatoriamente
        int[] numeros = new int[tam];                                                                             
        Random rnd = new Random();
        for (int i = 0; i < numeros.length; i++) {
             numeros[i] = rnd.nextInt(hasta - desde + 1) + desde;                                                 
        }
        return numeros;
	}
	public static int[] crearArregloPeorCaso(int x) {//llena el array en el peor de los casos ejemplo 4,3,2,1
		int[] a = new int[x];
		for(int i=0; i<a.length; i++) {
			a[i] = a.length-i;
		}
		return a;
	}
}
