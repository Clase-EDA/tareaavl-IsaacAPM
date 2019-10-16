package tareaavl;

public class NodoBTAVL<T extends Comparable<T>>{
    private T dato;
    private NodoBTAVL<T> papa;
    private NodoBTAVL<T> der;
    private NodoBTAVL<T> izq;
    private int fe;

    public NodoBTAVL(T dato){
        this.dato = dato;
        this.papa = null;
        this.der = null;
        this.izq = null;
    }

    public void setDato(T dato){
        this.dato = dato;
    }

    public T getDato(){
        return dato;
    }

    public void setPapa(NodoBTAVL<T> papa){
        this.papa = papa;
    }

    public NodoBTAVL<T> getPapa(){
        return papa;
    }

    public void setDer(NodoBTAVL<T> der){
        this.der = der;
    }

    public NodoBTAVL<T> getDer(){
        return der;
    }

    public void setIzq(NodoBTAVL<T> izq){
        this.izq = izq;
    }

    public NodoBTAVL<T> getIzq(){
        return izq;
    }

    public void setFe(int fe){
        this.fe = fe;
    }

    public int getFe(){
        return fe;
    }

    public void cuelga(NodoBTAVL<T> nodo){
        if(nodo != null){
            nodo.papa = this;
            if(nodo.dato.compareTo(this.dato) <= 0){
                izq = nodo;
            }else{
                der = nodo;
            }
        }else{
            izq = null;
            der = null;
        }
    }

    public String toString(){
        return dato.toString() + " fe:" + fe + " ";
    }
}