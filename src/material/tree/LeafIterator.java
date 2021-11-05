
package material.tree;

import java.util.Iterator;
import java.util.LinkedList;

import material.Position;

/**
 *
 * @author mayte
 * @param <T>
 */
public class LeafIterator<T> implements Iterator<Position<T>>  {

    LinkedList<Position<T>> lista = new LinkedList<>();
    LinkedList<Position<T>> aux = new LinkedList<>();
    Tree<T> tree;
    
    

    public LeafIterator(Tree<T> tree, Position<T> root){
        this.tree=tree;
        if(root!=null) aux.add(root);
    }
    
    public LeafIterator(Tree<T> tree){
        this(tree,tree.root());
    }
    
    @Override
    public boolean hasNext() {
        return !lista.isEmpty() || !aux.isEmpty();
    }

    /**
     * This method only visits the leaf nodes 
     */

    @Override
    public Position<T> next() {
        while(lista.isEmpty() && !aux.isEmpty()){
            Position<T> pos = aux.removeFirst();

            for (Position<T> child: tree.children(pos)){
                if(tree.isLeaf(child)){
                    lista.addLast(child);
                }else{
                    aux.addFirst(child);
                }
            }
        }
        Position<T> leaf = lista.removeLast();
        return leaf;
    }

    
    
}
