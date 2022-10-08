
public class NodoArbol {
    private int dato;
    private NodoArbol izq, der;
    
    public NodoArbol(int dato){
        this.dato=dato;
        izq=der=null;
    }
    
   public void Insertar(int dato) {
        if(dato < this.dato){
            if(this.izq  == null){
                this.izq = new NodoArbol(dato);
            }else{
                this.izq.Insertar(dato);
            }
        }else{
            if(this.der  == null){
                this.der = new NodoArbol(dato);
            }else{
                this.der.Insertar(dato);
            }
        }
    }
   
    public int getDato() {
        return this.dato;
    }

    public NodoArbol getIzq() {
        return izq;
    }

    public void setIzq(NodoArbol izq) {
        this.izq = izq;
    }

    public NodoArbol getDer() {
        return der;
    }

    public void setDer(NodoArbol der) {
        this.der = der;
    }
    
    public int getNodosCompletos(NodoArbol nodo){
        if(nodo == null){
            return 0;
        }else{
            if(nodo.getIzq() != null && nodo.getDer() != null){
                return getNodosCompletos(nodo.getIzq()) + getNodosCompletos(nodo.getDer()) + 1;
            }
            return getNodosCompletos(nodo.getIzq()) + getNodosCompletos(nodo.getDer());
        }
    }
}