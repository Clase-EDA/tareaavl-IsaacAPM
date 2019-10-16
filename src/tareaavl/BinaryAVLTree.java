package tareaavl;

import java.lang.NullPointerException;

public class BinaryAVLTree<T extends Comparable<T>> implements BinaryAVLTreeADT<T>{
    public NodoBTAVL<T> raiz;
    private int cont;

    public BinaryAVLTree(){
        raiz = null;
        cont = 0;
    }

    public boolean isEmpty(){
        return cont == 0;
    }

    public int size(){
        return cont;
    }

    public T find(T dato){
        T resp = null;
        if(dato != null){
            NodoBTAVL<T> aux = buscaNodo(dato, raiz);
            if(aux != null){
                resp = aux.getDato();
            }
        }
        return resp;
    }

    private NodoBTAVL<T> buscaNodo(T dato, NodoBTAVL<T> act){
        if(act == null){
            return null;
        }
        
        if (act.getDato().equals(dato)){
            return act;
        }

        NodoBTAVL<T> der = buscaNodo(dato, act.getDer());
        NodoBTAVL<T> izq = buscaNodo(dato, act.getIzq());

        if(der != null){
            return der;
        }

        if(izq != null){
            return izq;
        }

        return null;
    }

    public void add(T elem){
        if(elem != null){
            NodoBTAVL<T> nuevo = new NodoBTAVL<T>(elem);
            if(!isEmpty()){
                NodoBTAVL<T> act = raiz;
                boolean band = false;
    
                while(!band){
                    if(elem.compareTo(act.getDato()) <= 0){
                        if(act.getIzq() != null){
                            act = act.getIzq();
                        }else{
                            act.setIzq(nuevo);
                            nuevo.setPapa(act);
                            band = true;
                        }
                    }else{
                        if(act.getDer() != null){
                            act = act.getDer();
                        }else{
                            act.setDer(nuevo);
                            nuevo.setPapa(act);
                            band = true;
                        }
                    }
                }
                reacomodaFEAgrega(nuevo);
            }else{
                raiz = nuevo;
            }
            cont++;
        }else{
            throw new NullPointerException("Elemento nulo");
        }
    }//add

    public T remove(T elem){
        if(elem != null){
            T resp = null;
            NodoBTAVL<T> aux = buscaNodo(elem,raiz);
            if(aux != null){
                resp = aux.getDato();
                if(aux.getDer() == null && aux.getIzq() == null){
                    if(aux == raiz){
                        raiz = null;          
                    }else if(aux.getPapa().getDer() == aux){
                        aux.getPapa().setDer(null);
                        reacomodaFEBorra(aux.getPapa(), true);
                        aux.setPapa(null);
                    }else{
                        aux.getPapa().setIzq(null);
                        reacomodaFEBorra(aux.getPapa(), false);
                        aux.setPapa(null);
                    }          
                }else if(aux.getDer() == null && aux.getIzq() != null){
                    if(raiz == aux){
                        raiz = aux.getIzq();
                    }else{
                        aux.getPapa().cuelga(aux.getIzq());
                        reacomodaFEBorra(aux.getPapa(), (aux.getPapa().getDer() == aux));
                        aux.setPapa(null);
                        aux.setIzq(null);
                    }
                }else if(aux.getDer() != null && aux.getIzq() == null){
                    if(raiz == aux){
                        raiz = aux.getDer();
                    }else{
                        aux.getPapa().cuelga(aux.getDer());
                        reacomodaFEBorra(aux.getPapa(), (aux.getPapa().getDer() == aux));
                        aux.setPapa(null);
                        aux.setDer(null);
                    }
                }else{
                    NodoBTAVL<T> suin = buscaSucesorInOrden(aux);
                    NodoBTAVL<T> aux2;
                    if(aux == raiz){
                        raiz = suin;
                        if(aux.getDer() == suin){                           
                            suin.setDer(aux.getDer());   
                            suin.setFe(suin.getFe()-1);
                        }else{
                            aux2 = suin.getPapa();
                            suin.getPapa().setIzq(null);
                            suin.setPapa(null);
                            suin.setDer(aux.getDer());
                            suin.setIzq(aux.getIzq());
                            aux.setDer(null);
                            aux.setIzq(null);
                            reacomodaFEBorra(aux2, false);
                        }
                    }else{  
                        boolean der;
                        if(aux.getDer() == suin){
                            aux2 = aux.getPapa();
                            suin.setIzq(aux.getIzq());
                            if(aux.getPapa().getDer() == aux){                      
                                aux.getPapa().setDer(suin);
                                der = true;
                            }else{
                                aux.getPapa().setIzq(suin);
                                der = false;
                            }
                            aux.setPapa(null);
                            aux.setDer(null);
                            aux.setIzq(null);
                        }else{
                            aux2 = suin.getPapa();
                            suin.getPapa().setIzq(null);
                            der = false;
                            
                            if(aux.getPapa().getDer() == aux){
                                aux.getPapa().setDer(suin);
                            }else{
                                aux.getPapa().setIzq(suin);
                            }
                            suin.setPapa(aux.getPapa());
                            suin.setDer(aux.getDer());
                            suin.setIzq(aux.getIzq());
                            aux.setPapa(null);
                            aux.setDer(null);
                            aux.setIzq(null);
                        }
                        reacomodaFEBorra(aux2, der);
                    }                 
                }
            }
            return resp;
        }else{
            throw new NullPointerException("Elemento nulo");
        }
    }//remove
    
    public String toString(){
        return "";
    }
    
    public NodoBTAVL buscaSucesorInOrden(NodoBTAVL<T> nodo){
        NodoBTAVL<T> ant = nodo.getDer();
        NodoBTAVL<T> act = ant.getIzq();
        while(act != null){
            ant = act;
            act = act.getIzq();
        }
        return ant;
    }

    private void reacomodaFEAgrega(NodoBTAVL<T> act){
        if(act != null){
            NodoBTAVL<T> papa = act.getPapa();
        
            if(papa.getDer() == act){
                papa.setFe(papa.getFe()+1);
            }else{
                papa.setFe(papa.getFe()-1);
            }

            if(papa.getFe() != 0){
                if(Math.abs(papa.getFe()) == 1){
                    reacomodaFEAgrega(papa);
                }else{
                    rotacion(papa);
                }
            }
        }    
    }

    private void reacomodaFEBorra(NodoBTAVL<T> act, boolean derecha){
        if(act != null){
            if(derecha){
                act.setFe(act.getFe()-1);
            }else{
                act.setFe(act.getFe()+1);
            }
            
            if(Math.abs(act.getFe()) != 1){
                if(act.getFe() == 0){
                    if(act.getPapa().getDer() == act){
                        reacomodaFEBorra(act.getPapa(), true);
                    }else{
                        reacomodaFEBorra(act.getPapa(), false);
                    }
                }else{
                    rotacion(act);
                }
            }
            
        }
    }
    
    
    private NodoBTAVL<T> rotacion(NodoBTAVL<T> N){
            //izq - izq
        NodoBTAVL<T> alfa,papa,beta,gamma,A,B,C,D;
        if(N.getFe() == -2 && N.getIzq().getFe() <= 0){
            alfa = N;
            papa = N.getPapa();
            beta = alfa.getIzq();
            gamma = beta.getIzq();
            A = gamma.getIzq();
            B = gamma.getDer();
            C = beta.getDer();
            D = alfa.getDer();
            gamma.cuelga(A);
            gamma.cuelga(B);
            alfa.cuelga(C);
            alfa.cuelga(D);
            beta.cuelga(alfa);
            beta.cuelga(gamma);

            if(papa != null){
                papa.cuelga(beta);
            }else{
                beta.setPapa(null);
                raiz = beta;
            }

            //reacomodar los factores de equilibrio

            if(alfa.getFe() == -2){
                alfa.setFe(0);
                beta.setFe(0);
            }else{
                alfa.setFe(-1);
                beta.setFe(1);
            }

            return beta;
        }else if(N.getFe() == -2 && N.getIzq().getFe() == 1){
            alfa = N;
            papa = N.getPapa();
            beta = alfa.getIzq();
            gamma = beta.getDer();
            A = beta.getIzq();
            B = gamma.getDer();
            C = gamma.getDer();
            D = alfa.getDer();
            gamma.cuelga(beta);
            gamma.cuelga(alfa);
            alfa.cuelga(C);
            alfa.cuelga(D);
            beta.cuelga(A);
            beta.cuelga(B);
            
            if(papa != null){
                papa.cuelga(gamma);
            }else{
                gamma.setPapa(null);
                raiz = gamma;
            }

            beta.setFe(-1);
            alfa.setFe(0);
            gamma.setFe(0);

            return gamma;
        }else if(N.getFe() == 2 && N.getIzq().getFe() >= 0){
            alfa = N;
            papa = N.getPapa();
            beta = alfa.getDer();
            gamma = beta.getDer();
            A = alfa.getIzq();
            B = beta.getIzq();
            C = gamma.getIzq();
            D = gamma.getDer();
            
            alfa.cuelga(A);
            alfa.cuelga(B);
            gamma.cuelga(C);
            gamma.cuelga(D);
            beta.cuelga(alfa);
            beta.cuelga(gamma);
            
            if(papa != null){
                papa.cuelga(beta);
            }else{
                beta.setPapa(null);
                raiz = beta;
            }
            
            //factores
            
            return beta;
        }else{
            alfa = N;
            papa = N.getPapa();
            beta = alfa.getDer();
            gamma = beta.getIzq();
            A = alfa.getIzq();
            B = gamma.getIzq();
            C = gamma.getDer();
            D = beta.getDer();
            
            alfa.cuelga(A);
            alfa.cuelga(B);
            beta.cuelga(C);
            beta.cuelga(D);
            gamma.cuelga(alfa);
            gamma.cuelga(beta);
            
            if(papa != null){
                papa.cuelga(gamma);
            }else{
                gamma.setPapa(null);
                raiz = gamma;
            }
            
            return gamma;
        }
    }

}