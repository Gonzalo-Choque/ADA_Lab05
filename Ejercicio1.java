/*	Ejercicio1: Pares de números de 5 dígitos diferentes entre 0 y 9, que al dividirlos 2<=cociente<=79 
 * 				Ejemplo: 79546 / 01283 = 62
 * 				Utilizando un metodo de Fuerza Bruta.
*	Autores: 
*	- Gonzalo Joel Choque Dongo
*	- Glenny Shindely  Choque Vilcape
*/

package Lab05;

import java.util.ArrayList;

public class Ejercicio1 {
	public static void main(String args[]) {
		int numeros[]= {0,1,2,3,4,5,6,7,8,9};
		ArrayList<int[][]> resultado=paresDe5Entre2y79(numeros);//llamada a la funcion, retorna un ArrayList de Arrays
		imprimirPares(resultado);//funcion que imprime el resultado
	}
	public static void imprimirPares(ArrayList<int[][]> resultado) {//imprime todos los pares que cumplan con la divicion entera 2<=N<=79
		int parA,parB,divisor;
		for(int i = 0; i <resultado.size(); i++) {
			int par[][] = resultado.get(i);
			parA = par[0][0];
			parB = par[0][1];
			divisor = parA/parB;
			System.out.print(parA+" / ");
			if(parB/10000==0){
				System.out.println("0"+parB+" = "+divisor);
			}
			else {
				System.out.println(parB+" = "+divisor);
			}
		}
	}
	private static int permutacionSinRepeticion(int n) {//no se usa, solo calcula el número de iteraciones; se pensaba usar para llenar un array de ese tamaño con todos las permutaciones.
		if(n==1) return 1;
		return n*permutacionSinRepeticion(n-1);
	}
	public static ArrayList<int[][]> paresDe5Entre2y79(int[] numeros){//retorna los pares que cumplan con la condicion requerida
		int tamaño = numeros.length;
		int count = permutacionSinRepeticion(tamaño);
		System.out.println("Cantidad de Iteraciones = "+count);
		ArrayList<int[][]> resultado = rellenarPermutacionesYvalidar(numeros);
		return resultado;
	}

	private static ArrayList<int[][]> rellenarPermutacionesYvalidar(int[] numeros){//itera todas las conbinaciones posibles y añade a un ArrayList solo los pares que cumplen con la condicion requerida
		ArrayList<int[][]> pareja = new ArrayList();//ArrayList que guarda una lista de Arrays
		int iter1, iter2;//iter1 sera el primer numero, iter2 sera el 2ndo numero
		int size = numeros.length;
		int count = 1;
		int respA,respB,respC,respD,respE,respF,respG,respH,respI;//sirven como respaldo despues de haber realizado una iteracion de un bucle for; porque sino se siguen sumando.
		for(int a = 0; a<size; a++) {//recorre todos los 10 números
			iter1=numeros[a]*10000;
			respA=iter1;
			int sina[] = eliminarNumero(numeros[a], numeros);//retorna un array con los 9 numeros diferentes a numeros[a]
			for(int b = 0; b<size-1; b++) {//recorre todos los 9 números complementarios
				iter1+=sina[b]*1000;
				respB=iter1;
				int sinb[] = eliminarNumero(sina[b],sina);
				for(int c = 0; c<size-2; c++) {//recorre todos los 8 números complementarios
					iter1+=sinb[c]*100;
					respC=iter1;
					int sinc[] = eliminarNumero(sinb[c],sinb);
					for(int d = 0; d<size-3; d++) {//recorre todos los 7 números complementarios
						iter1+=sinc[d]*10;
						respD=iter1;
						int sind[] = eliminarNumero(sinc[d],sinc);
						for(int e = 0; e<size-4; e++) {//recorre todos los 6 números complementarios
							iter1+=sind[e]*1;
							respE=iter1;
							int sine[] = eliminarNumero(sind[e],sind);
							//iter 2
							for(int f = 0; f<size-5; f++) {//recorre todos los 5 números complementarios
								iter2=sine[f]*10000;
								respF=iter2;
								int sinf[] = eliminarNumero(sine[f], sine);
								for(int g = 0; g<size-6; g++) {//recorre todos los 4 números complementarios
									iter2+=sinf[g]*1000;
									respG=iter2;
									int sing[] = eliminarNumero(sinf[g],sinf);
									for(int h = 0; h<size-7; h++) {//recorre todos los 3 números complementarios
										iter2+=sing[h]*100;
										respH=iter2;
										int sinh[] = eliminarNumero(sing[h],sing);
										for(int i = 0; i<size-8; i++) {//recorre todos los 2 números complementarios
											iter2+=sinh[i]*10;
											respI=iter2;
											int sini[] = eliminarNumero(sinh[i],sinh);
											for(int j = 0; j<size-9; j++) {//recorre el último número complementario
												iter2+=sini[j]*1;
												if(iter1%iter2==0) {//verifica si la division es entera
													int division=iter1/iter2;
													if(division>=2 ||division<=79)  {//verifica que el cociente este entre 2<=N<=79
														int par[][]=new int[1][2];//crea un nuevo array que sera insertado en pareja
														par[0][0]=iter1;
														par[0][1]=iter2;
														pareja.add(par);
													}
												}
												System.out.println("Iter: "+count+"->"+iter1+" ; "+iter2);//imprime todas las iteraciones
												count++;
												iter2=respI;//Recupera el valor con el que entro al bucle
											}
											iter2=respH;//Recupera el valor con el que entro al bucle
										}
										iter2=respG;//Recupera el valor con el que entro al bucle
									}	
									iter2=respF;//Recupera el valor con el que entro al bucle
								}
								iter2=0;
								iter1=respE;//Recupera el valor con el que entro al bucle
							}
							iter1=respD;//Recupera el valor con el que entro al bucle
						}
						iter1=respC;//Recupera el valor con el que entro al bucle
					}
					iter1=respB;//Recupera el valor con el que entro al bucle
				}
				iter1=respA;//Recupera el valor con el que entro al bucle
			}
			iter1=0;
			
		}
		return pareja;
	}
	private static int[] eliminarNumero(int n, int [] arr) {//retorna un array sin el numero n y con un tamaño-1
		int resultado[]=new int[arr.length-1];
		int count = 0;
		for(int i = 0; i<arr.length; i++) {
			if(arr[i]!=n) {
				resultado[count]=arr[i];
				count++;
			}
		}
		return resultado;
	}
}
