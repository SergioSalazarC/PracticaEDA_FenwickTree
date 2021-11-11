
package material.tree;

import java.nio.InvalidMarkException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import material.Position;
import material.tree.binarytree.BinaryTree;
import material.tree.narytree.InvalidPositionException;
import material.tree.narytree.NAryTree;

/**
 *
 * @author mayte
 * @param <T>
 */
public class MoreFunctionality<T> {

    public MoreFunctionality() {
    }
    /**
     * This method recives a NArytree and returns a List with the elements of the 
     * tree that can be seen if the tree is viewed from the left side.
     * @param tree
     * @return 
     */
    public List<T> leftView(NAryTree<T> tree) throws InvalidPositionException {
        if(tree==null){
            throw new InvalidPositionException();
        }


        List<T> lista = new LinkedList<>();
        if(tree.isEmpty()){
            return lista;
        }
        LinkedList<Position<T>> bfs = new LinkedList<>();
        bfs.add(null);
        bfs.add(tree.root());
        boolean primero=false;
        while(bfs.size()>1){
            Position<T> top= bfs.remove();
            if(top==null){
                primero=true;
                bfs.add(null);
            }
            else{
                for(Position<T> hijo : tree.children(top)){
                    bfs.add(hijo);
                }
                if(primero==true){
                    primero=false;
                    lista.add(top.getElement());
                }
            }
        }
        return lista;

    }

   /**
     * This method recives a NArytree and returns a List with the elements of the 
     * tree that can be seen if the tree is viewed from the right side.
     * @param tree
     * @return 
     */
    public List<T> rightView(NAryTree<T> tree) throws InvalidPositionException {

        if(tree==null){
            throw new InvalidPositionException();
        }


        List<T> lista = new LinkedList<>();
        if(tree.isEmpty()){
            return lista;
        }
        LinkedList<Position<T>> bfs = new LinkedList<>();
        bfs.add(null);
        bfs.add(tree.root());
        boolean primero=false;
        while(bfs.size()>1){
            Position<T> top= bfs.remove();
            if(top==null){
                primero=true;
                bfs.add(null);
            }
            else{
                LinkedList<Position<T>> aux = new LinkedList<>();
                for(Position<T> hijo : tree.children(top)){
                    aux.addFirst(hijo);
                }
                for(Position<T> hijo : aux){
                    bfs.add(hijo);
                }
                if(primero==true){
                    primero=false;
                    lista.add(top.getElement());
                }
            }
        }
        return lista;
    }
}
