package tareaavl;

public interface BinaryAVLTreeADT<T extends Comparable<T>>{
    public boolean isEmpty();
    public int size();
    public T find(T dato);
    public void add(T elem);
    public T remove(T elem);
    public String toString(); //por niveles
}