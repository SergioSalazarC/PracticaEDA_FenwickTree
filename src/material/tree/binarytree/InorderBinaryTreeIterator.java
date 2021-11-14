
package material.tree.binarytree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import material.Position;
import material.tree.Tree;

/**
 *
 * @author jvelez
 * @param <T>
 */
public class InorderBinaryTreeIterator<T> implements Iterator<Position<T>> {
    LinkedList<Position<T>> lista = new LinkedList<>();
    HashSet<Position<T>> marcado = new HashSet<>();
    BinaryTree<T> arbol;
       
    public InorderBinaryTreeIterator(BinaryTree <T> tree) {
        this(tree,tree.root());
    }

    public InorderBinaryTreeIterator(BinaryTree <T> tree, Position<T> node) {
        arbol=tree;
        lista.add(node);
    }

         
    @Override
    public boolean hasNext() {
        return !lista.isEmpty();
    }

    /**
     * This method visits the nodes of a binary tree first left-child, then the node and at last right-child
     */
    @Override
    public Position<T> next() {
        Position<T> pos = lista.removeFirst();
        while(true){
            if(marcado.contains(pos)){
                break;
            }
            else{
                marcado.add(pos);
                if(arbol.hasRight(pos)){
                    lista.addFirst(arbol.right(pos));
                }
                lista.addFirst(pos);
                if(arbol.hasLeft(pos)){
                    lista.addFirst(arbol.left(pos));
                }
            }
            pos=lista.removeFirst();
        }
        return pos;
    }

    
}
