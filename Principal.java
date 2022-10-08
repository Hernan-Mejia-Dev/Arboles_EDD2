
import javax.swing.JOptionPane;

public class Principal {

    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        ArbolBinarioGrafico grafica = new ArbolBinarioGrafico(arbol);
        int dato;
        int resp = 0;
        while (resp != 4) {
            resp = Integer.valueOf(JOptionPane.showInputDialog(null, "1.Añadir nodo al arbol \n"
                    + "2. Eliminar nodo del arbol \n"
                    + "3. Imprimir estadisticas del arbol \n"
                    + "4. Salir del programa"));
            switch (resp) {
                case 1:
                    grafica.setVisible(false);
                    dato = Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese el dato que desea añadir al arbol:"));
                    if (!arbol.existe(dato)) {
                        arbol.Insertar(dato);
                        grafica = new ArbolBinarioGrafico(arbol);
                    } else {
                        JOptionPane.showMessageDialog(null, "El dato ya existe dentro del arbol!");
                    }
                    grafica.setVisible(true);
                    break;

                case 2:
                    grafica.setVisible(false);
                    dato = Integer.valueOf(JOptionPane.showInputDialog(null, "Ingrese el dato que desea eliminar del arbol:"));
                    if (arbol.existe(dato)) {
                        arbol.Eliminar(dato);
                        grafica = new ArbolBinarioGrafico(arbol);
                    } else {
                        JOptionPane.showMessageDialog(null, "El dato no existe dentro del arbol!");
                    }
                    grafica.setVisible(true);
                    break;

                case 3:
                    MostrarEstadisticas(arbol);
                    break;

                case 4:
                    System.exit(0);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "escoja una opcion valida!");
                    break;
            }
        }
    }

    public static void MostrarEstadisticas(ArbolBinario arbol) {
        if (!arbol.ArbolVacio()) {
            System.out.println("------------------------------------------");
            System.out.println("LA RAIZ ES: " + arbol.getRaiz().getDato());
            System.out.println("la cantidad de nodos es " + arbol.getCantidadNodos());
            System.out.println("cantidad de hojas = " + arbol.getCantidadNodosHoja());
            System.out.println("la altura del arbol es = " + arbol.getAltura());
            System.out.println("\nMenor valor del árbol: " + arbol.getMenorValor());
            System.out.println("Mayor valor del árbol:" + arbol.getMayorValor());
            System.out.println("\nPreOrden: ");
            System.out.println("[" + arbol.getPreOrden() + "]");
            System.out.println("\n PostOrden: ");
            System.out.println("[" + arbol.getPostOrden() + "]");
            System.out.println("\n InOrden: ");
            System.out.println("[" + arbol.getInOrden() + "]\n");
            System.out.println("el nivel maximo en el arbol es: " + arbol.getAltura());
        } else {
            JOptionPane.showMessageDialog(null, "ARBOL VACIO!");
        }
    }
}
