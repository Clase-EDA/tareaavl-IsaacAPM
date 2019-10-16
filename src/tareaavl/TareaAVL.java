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
        avl.add(100);
        avl.add(300);
        avl.add(400);
        avl.add(350);
        avl.add(375);
        avl.add(50);
        avl.add(200);
        avl.add(360);
        avl.add(380);
        avl.add(500);
        avl.add(390);
        //avl.remove(375);
        //avl.remove(50);
        //avl.remove(400);
        //avl.remove(380);
        avl.imprime();
        /*System.out.println(avl.raiz);
        System.out.println(avl.raiz.getIzq());
        System.out.println(avl.raiz.getDer());
        System.out.println(avl.raiz.getIzq().getIzq());
        System.out.println(avl.raiz.getIzq().getDer());
        System.out.println(avl.raiz.getDer().getIzq());
        System.out.println(avl.raiz.getDer().getDer());
        System.out.println(avl.raiz.getDer().getIzq().getDer());
        System.out.println(avl.raiz.getDer().getDer().getIzq());
        System.out.println(avl.raiz.getDer().getDer().getDer());
        System.out.println(avl.raiz.getDer().getDer().getDer());*/
       
    }
    
}
