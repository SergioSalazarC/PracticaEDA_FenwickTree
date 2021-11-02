
package material.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import material.Position;

/**
 *
 * @author jvelez
 * @param <T>
 */
public class BreadthFirstTreeIterator<T> implements Iterator<Position<T>> {

    LinkedList<Position<T>> cola = new LinkedList<>();
    Tree<T> tree;

    public BreadthFirstTreeIterator(Tree<T> tree, Position<T> root){
        this.tree=tree;
        if(root!=null) cola.add(root);
    }

    public BreadthFirstTreeIterator(Tree<T> tree){
        this(tree,tree.root());
    }

    
    @Override
    public boolean hasNext() {
        return !cola.isEmpty();
    }

    /**
     * This method visits the nodes of a tree by following a breath-first order
     */
    @Override
    public Position<T> next() {
        Position<T> pos = cola.removeLast();
        for (Position<T> child: tree.children(pos)){
            cola.addFirst(child);
        }
        return pos;

    }

   
}
