
package material.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
            if(tree.isLeaf(pos)){
                lista.addFirst(pos);
            }
            LinkedList<Position<T>> auxlist = new LinkedList<>();
            for(Position<T> position : tree.children(pos)){
                auxlist.add(0,position);
            }
            for (Position<T> child: auxlist){
                aux.addFirst(child);
            }
        }
        Position<T> leaf = lista.removeFirst();
        return leaf;
    }

    
    
}
