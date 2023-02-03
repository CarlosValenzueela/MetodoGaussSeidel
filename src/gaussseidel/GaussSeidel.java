/**
 * Paquete gaussseidel
 *
 */
package gaussseidel;

/**
 * Imports utilizados
 */
import java.util.Arrays;
import java.util.Scanner;

/*Nombre del archivo: asignacion10b_00000207256
Nombre del proyecto: gaussSeidel
Nombre de alumno: Carlos Antonio Valenzuela Valdez
Matricula: 00000207256
Fecha de creación: 08/10/2020 12:11 a.m. */
/**
 * Es un programa que resuelva un sistema de ecuaciones lineales simultáneas por
 * el método de Gauss – Seidel
 */
/**
 * Clase main GaussSeidel donde se tienen los métodos y se llaman a ejecutar
 *
 * @author Carlos Antonio Valenzuela Valdez
 */
public class GaussSeidel {

    //Atributos de clase
    static double[][] arregloEcuaciones;
    static double[] arregloAux;
    static int cantidadEcuaciones = 0, aux = 100;

    public static void main(String[] args) {
        //Que hace
        System.out.println("Es un programa que resuelva un sistema de ecuaciones lineales simultáneas por el método de "
                + "Gauss – Seidel\n");

        GaussSeidel.leeEcuaciones();
        GaussSeidel.gaussSeidel();
        GaussSeidel.despliegaSolucion();
    }

    /**
     * Lee el número de ecuaciones (máximo 10) y después los valores de los
     * coeficientes y término independiente para cada ecuación y los almacena en
     * un arreglo bidimensional
     *
     */
    public static void leeEcuaciones() {
        //Declaración de Scanner para ingresar datos de entrada
        Scanner teclado = new Scanner(System.in);

        boolean pasar = true;

        //Si las instrucciones no se cumplen, el ciclo se reinicia
        while (pasar) {
            System.out.println("---Solicitud de datos");
            System.out.print("\nIngrese el número de ecuaciones(Máximo 10): ");
            cantidadEcuaciones = teclado.nextInt();

            if (cantidadEcuaciones <= 0 || cantidadEcuaciones > 10) {
                System.out.println("La cantidad de ecucaciones es en un rango de 0 - 10 . \n");
            } else {
                pasar = false;
            }
        }

        //Declaración de arreglo bidimensional
        arregloEcuaciones = new double[cantidadEcuaciones][cantidadEcuaciones + 1];

        System.out.println("\nSolicitud de coeficientes y terminos independientes para cada ecuación: ");
        for (int i = 0; i < cantidadEcuaciones; i++) {
            System.out.print("\nEcuación número " + (i + 1) + "\n");
            for (int j = 0; j < (cantidadEcuaciones + 1); j++) {
                if (j < cantidadEcuaciones) {
                    System.out.print("Ingrese incognita " + (j + 1) + ": ");
                } else {
                    System.out.print("Ingrese termino independiente: ");

                }
                arregloEcuaciones[i][j] = teclado.nextDouble();

            }

        }
    }

    /**
     * Resuelve el sistema de ecuaciones cuyos coeficientes y términos
     * independientes se encuentran en un arreglo bidimensional y guarda la
     * solución en un arreglo
     */
    public static void gaussSeidel() {
        //Variables
        int longitud = arregloEcuaciones.length;
        double errorAproximado = 0.001;
        arregloAux = new double[longitud];
        double[] arregloAux2 = new double[longitud];
        cantidadEcuaciones = 0;
        Arrays.fill(arregloAux, 0);

        //Ciclo para hacer las conversioens de los arrelogS
        while (true) {
            for (int i = 0; i < longitud; i++) {
                double sum = arregloEcuaciones[i][longitud];

                for (int j = 0; j < longitud; j++) {
                    if (j != i) {
                        sum -= arregloEcuaciones[i][j] * arregloAux[j];
                    }
                }

                arregloAux[i] = 1 / arregloEcuaciones[i][i] * sum;
            }

            //Imprimir anunciado
            if (cantidadEcuaciones == 0) {
                System.out.println("\n   X1\t\tX2\t  X3");
            }
            for (int i = 0; i < longitud; i++) {
                System.out.printf("%.6f   ", arregloAux[i]);
            }
            System.out.println(" ");
            cantidadEcuaciones++;
            if (cantidadEcuaciones == 1) {
                continue;
            }

            //Metodo para saber cuando parar según el error aproximado
            boolean parar = true;
            for (int i = 0; i < longitud && parar; i++) {
                if (Math.abs(arregloAux[i] - arregloAux2[i]) > errorAproximado) {
                    parar = false;
                }
            }

            if (parar) {
                break;
            }
            arregloAux2 = (double[]) arregloAux.clone();
        }
    }

    /**
     * Despliega la solución del sistema de ecuaciones que se encuentra en un
     * arreglo
     */
    public static void despliegaSolucion() {
        System.out.println("\n\nValor de la solución ");
        for (int i = 0; i < arregloAux.length; i++) {
            System.out.printf("X%d: %.6f\n", (i + 1), arregloAux[i]);
        }

        System.out.println("\nNúmero de iteraciones = " + cantidadEcuaciones);

    }

}
