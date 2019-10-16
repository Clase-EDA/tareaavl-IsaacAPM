/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaavl;

/**
 *
 * @author ipimentem
 */
public class TareaAVL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BinaryAVLTree<Integer> avl = new BinaryAVLTree<Integer>();
        avl.add(5);
        avl.add(7);
        avl.add(10);
        avl.add(4);
        System.out.println(avl.raiz);
        //avl.remove(4);
        System.out.println(avl.raiz.getDer());
        System.out.println(avl.raiz.getIzq());
        System.out.println(avl.raiz.getIzq().getIzq());
    }
    
}
