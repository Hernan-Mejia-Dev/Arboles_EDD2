
public class ArbolBinario {

    private NodoArbol raiz;
    private int[] Recorridos;
    private int subizq;
    private int subder;
    private int i;

    public ArbolBinario() {
        this.raiz = null;
    }

    public NodoArbol getRaiz() {
        return this.raiz;
    }
    
    public boolean ArbolVacio() {
        return (this.raiz == null);
    }
    
    public void Insertar(int dato) {
        if (ArbolVacio()) {
            this.raiz = new NodoArbol(dato);
        } else {
            this.raiz.Insertar(dato);
        }
        ComprobarBalance(getRaiz());
    }

    public NodoArbol BuscarNodo(int dato) {
        NodoArbol aux = getRaiz();
        while (aux.getDato() != dato) {
            if (dato < aux.getDato()) {
                aux = aux.getIzq();
            } else {
                aux = aux.getDer();
            }
            if (aux == null) {
                return null;
            }
        }
        return aux;
    }

    private NodoArbol BuscarPadre(int dato) {
        NodoArbol aux = getRaiz();
        NodoArbol padre = aux;
        while (aux.getDato() != dato) {
            if (dato < aux.getDato()) {
                padre = aux;
                aux = aux.getIzq();
            } else {
                padre = aux;
                aux = aux.getDer();
            }
            if (aux == null) {
                return padre;
            }
        }
        return padre;
    }

    public boolean existe(int dato) {
        NodoArbol nodo = getRaiz();
        while (nodo != null) {
            if (dato == nodo.getDato()) {
                return true;
            } else if (dato > nodo.getDato()) {
                nodo = nodo.getDer();
            } else {
                nodo = nodo.getIzq();
            }
        }
        return false;
    }

    private void cantidadNodosHoja(NodoArbol nodo) {
        if (nodo != null) {
            if (nodo.getIzq() == null && nodo.getDer() == null) {
                i++;
            }
            cantidadNodosHoja(nodo.getIzq());
            cantidadNodosHoja(nodo.getDer());
        }
    }

    public int getCantidadNodosHoja() {
        i = 0;
        cantidadNodosHoja(getRaiz());
        return i;
    }

    private void Altura(NodoArbol nodo, int nivel) {
        if (nodo != null) {
            Altura(nodo.getIzq(), nivel + 1);
            if (nivel > i) {
                i = nivel;
            }
            Altura(nodo.getDer(), nivel + 1);
        }
    }

    public int getAltura() {
        i = 0;
        Altura(getRaiz(), i);
        return i;
    }

    public int getMenorValor() {
        NodoArbol nodo = getRaiz();
        while (nodo.getIzq() != null) {
            nodo = nodo.getIzq();
        }
        return nodo.getDato();
    }

    public int getMayorValor() {
        NodoArbol nodo = getRaiz();
        while (nodo.getDer() != null) {
            nodo = nodo.getDer();
        }
        return nodo.getDato();
    }

    private void Balance(NodoArbol nodo, boolean lado, int i) {
        if (nodo != null) {
            if (nodo.getDer() == null && nodo.getIzq() == null) {
                if (lado) {
                    subder = (i > subder) ? i : subder;
                } else {
                    subizq = (i > subizq) ? i : subizq;
                }
            }
            Balance(nodo.getDer(), lado, i + 1);
            if (i == 0) {
                lado = false;
            }
            Balance(nodo.getIzq(), lado, i + 1);
        }
    }

    private int getBalance(NodoArbol nodo) {
        subizq = 0;
        subder = 0;
        Balance(nodo, true, 0);
        return (subder - subizq);
    }
    
    private void RSI(NodoArbol nodo) {
        NodoArbol padre = BuscarPadre(nodo.getDato());
        NodoArbol nieto = BuscarNodo(nodo.getDer().getDato());
        if (nodo != getRaiz()) {
            if (padre.getDer() == nodo)padre.setDer(nieto);
            if (padre.getIzq() == nodo)padre.setIzq(nieto);
        } else {
            this.raiz = nieto;
        }
        nodo.setDer(nieto.getIzq());
        nieto.setIzq(nodo);
        ComprobarBalance(getRaiz());
    }

    private void RSD(NodoArbol nodo) {
        NodoArbol padre = BuscarPadre(nodo.getDato());
        NodoArbol nieto = BuscarNodo(nodo.getIzq().getDato());
        if (nodo != getRaiz()) {
            if (padre.getDer() == nodo)padre.setDer(nieto);
            if (padre.getIzq() == nodo)padre.setIzq(nieto);
        } else {
            this.raiz = nieto;
        }
        nodo.setIzq(nieto.getDer());
        nieto.setDer(nodo);
        ComprobarBalance(getRaiz());
    }

    private void RDI(NodoArbol nodo) {
        NodoArbol padre = BuscarPadre(nodo.getDato());
        NodoArbol nieto = BuscarNodo(nodo.getIzq().getDato());
        NodoArbol bisnieto = BuscarNodo(nieto.getDer().getDato());
        if (nodo != getRaiz()) {
            if (padre.getDer() == nodo)padre.setDer(bisnieto);
            if (padre.getIzq() == nodo)padre.setIzq(bisnieto);
        } else {
            this.raiz = bisnieto;
        }
        nodo.setIzq(null);
        nieto.setDer(null);
        bisnieto.setIzq(nieto);
        bisnieto.setDer(nodo);
        ComprobarBalance(getRaiz());
    }

    private void RDD(NodoArbol nodo) {
        NodoArbol padre = BuscarPadre(nodo.getDato());
        NodoArbol nieto = BuscarNodo(nodo.getDer().getDato());
        NodoArbol bisnieto = BuscarNodo(nieto.getIzq().getDato());
        if (nodo != getRaiz()) {
            if (padre.getDer() == nodo)padre.setDer(bisnieto);
            if (padre.getIzq() == nodo)padre.setIzq(bisnieto);
        } else {
            this.raiz = bisnieto;
        }
        nodo.setDer(null);
        nieto.setIzq(null);
        bisnieto.setIzq(nodo);
        bisnieto.setDer(nieto);
        ComprobarBalance(getRaiz());
    }
    
    private void ComprobarBalance(NodoArbol nodo) {
        if (nodo != null) {
            ComprobarBalance(nodo.getIzq());
            ComprobarBalance(nodo.getDer());
            NodoArbol hijo;
            int balance = getBalance(nodo);
            if (balance == 2) {
                if (nodo.getDer().getIzq() == null && nodo.getDer().getDer() != null) {
                    RSI(nodo);
                } else if (nodo.getDer().getIzq() != null && nodo.getDer().getDer() != null) {
                    RSI(nodo);
                } else if (nodo.getDer().getIzq() != null && nodo.getDer().getDer() == null) {
                    RDD(nodo);
                }
            } else if (balance == -2) {
                if (nodo.getIzq().getDer() == null && nodo.getIzq().getIzq() != null) {
                    RSD(nodo);
                } else if (nodo.getIzq().getDer() != null && nodo.getIzq().getIzq() != null) {
                    RSD(nodo);
                } else if (nodo.getIzq().getDer() != null && nodo.getIzq().getIzq() == null) {
                    RDI(nodo);
                }
            }
        }
    }

    public void Eliminar(int dato) {
        NodoArbol nodo = BuscarNodo(dato);
        NodoArbol padre = BuscarPadre(dato);
        NodoArbol Reemplazo;
        if (nodo != getRaiz()) {
            if (nodo.getDer() == null && nodo.getIzq() == null) {
                if (padre.getDer() == nodo) {
                    padre.setDer(null);
                } else if (padre.getIzq() == nodo) {
                    padre.setIzq(null);
                }
            } else {
                if (nodo.getIzq() != null) {
                    Reemplazo = nodo.getIzq();
                    NodoArbol PadreReemplazo = nodo;
                    while (Reemplazo.getDer() != null) {
                        PadreReemplazo = Reemplazo;
                        Reemplazo = Reemplazo.getDer();
                    }
                    if(Reemplazo != nodo.getIzq()){
                        if(padre.getDer() == nodo){
                            padre.setDer(Reemplazo);
                        }else if(padre.getIzq() == nodo){
                            padre.setIzq(Reemplazo);
                        }
                        PadreReemplazo.setDer(Reemplazo.getIzq());
                        Reemplazo.setDer(nodo.getDer());
                        Reemplazo.setIzq(nodo.getIzq());
                    }else{
                        if(padre.getDer() == nodo){
                            padre.setDer(nodo.getIzq());
                        }else if(padre.getIzq() == nodo){
                            padre.setIzq(nodo.getIzq());
                        }
                    }
                } else if (nodo.getDer() != null) {
                    Reemplazo = nodo.getDer();
                    NodoArbol PadreReemplazo = nodo;
                    while (Reemplazo.getIzq() != null) {
                        PadreReemplazo = Reemplazo;
                        Reemplazo = Reemplazo.getIzq();
                    }
                    if(Reemplazo != nodo.getDer()){
                        if(padre.getDer() == nodo){
                            padre.setDer(Reemplazo);
                        }else if(padre.getIzq() == nodo){
                            padre.setIzq(Reemplazo);
                        }
                        PadreReemplazo.setIzq(Reemplazo.getDer());
                        Reemplazo.setDer(nodo.getDer());
                        Reemplazo.setIzq(nodo.getIzq());
                    }else{
                        if(padre.getDer() == nodo){
                            padre.setDer(nodo.getDer());
                        }else if(padre.getIzq() == nodo){
                            padre.setIzq(nodo.getDer());
                        }
                    }
                }
            }
        } else {
            if(nodo.getDer() == null && nodo.getIzq() == null){
                this.raiz = null;
            }else if (nodo.getIzq() != null) {
                Reemplazo = nodo.getIzq();
                NodoArbol PadreReemplazo = nodo;
                while (Reemplazo.getDer() != null) {
                    PadreReemplazo = Reemplazo;
                    Reemplazo = Reemplazo.getDer();
                }
                if(Reemplazo != nodo.getIzq()){
                    this.raiz = Reemplazo;
                    PadreReemplazo.setDer(Reemplazo.getIzq());
                    Reemplazo.setDer(nodo.getDer());
                    Reemplazo.setIzq(nodo.getIzq());
                }else{
                    this.raiz = nodo.getIzq();
                    this.raiz.setDer(nodo.getDer());
                }
            } else if (nodo.getDer() != null) {
                Reemplazo = nodo.getDer();
                NodoArbol PadreReemplazo = nodo;
                while (Reemplazo.getIzq() != null) {
                    PadreReemplazo = Reemplazo;
                    Reemplazo = Reemplazo.getIzq();
                }
                if(Reemplazo != nodo.getDer()){
                    this.raiz = Reemplazo;
                    PadreReemplazo.setIzq(Reemplazo.getDer());
                    Reemplazo.setDer(nodo.getDer());
                    Reemplazo.setIzq(nodo.getIzq());
                }else{
                    this.raiz = nodo.getDer();
                }
            }
        }
        ComprobarBalance(getRaiz());
    }

    private void cantidadNodos(NodoArbol nodo) {
        if (nodo != null) {
            i++;
            cantidadNodos(nodo.getIzq());
            cantidadNodos(nodo.getDer());
        }
    }

    public int getCantidadNodos() {
        i = 0;
        cantidadNodos(getRaiz());
        return i;
    }

    private void RecorrerPreOrden(NodoArbol nodo, int[] vector) {
        if (nodo != null) {
            vector[i] = nodo.getDato();
            i++;
            RecorrerPreOrden(nodo.getIzq(), vector);
            RecorrerPreOrden(nodo.getDer(), vector);
        }
    }

    private void RecorrerPostOrden(NodoArbol nodo, int[] vector) {
        if (nodo != null) {
            RecorrerPostOrden(nodo.getIzq(), vector);
            RecorrerPostOrden(nodo.getDer(), vector);
            vector[i] = nodo.getDato();
            i++;
        }
    }

    private void RecorrerInOrden(NodoArbol nodo, int[] vector) {
        if (nodo != null) {
            RecorrerInOrden(nodo.getIzq(), vector);
            vector[i] = nodo.getDato();
            i++;
            RecorrerInOrden(nodo.getDer(), vector);
        }
    }

    public int[] getPreOrden() {
        Recorridos = new int[getCantidadNodos()];
        i = 0;
        RecorrerPreOrden(getRaiz(), Recorridos);
        return Recorridos;
    }

    public int[] getPostOrden() {
        Recorridos = new int[getCantidadNodos()];
        i = 0;
        RecorrerPostOrden(getRaiz(), Recorridos);
        return Recorridos;
    }

    public int[] getInOrden() {
        Recorridos = new int[getCantidadNodos()];
        i = 0;
        RecorrerInOrden(getRaiz(), Recorridos);
        return Recorridos;
    }
}