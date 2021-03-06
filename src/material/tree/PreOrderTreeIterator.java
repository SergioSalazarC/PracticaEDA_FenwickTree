
package material.tree;

import material.Position;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jvelez
 * @param <E>
 */
public class PreOrderTreeIterator<E> implements Iterator<Position<E>> {

    List<Position<E>> list = new LinkedList<>();
    Tree<E> tree;

    public PreOrderTreeIterator(Tree<E> tree){
        this(tree, tree.root());
    }
    public PreOrderTreeIterator(Tree<E> tree, Position<E> root){
        this.tree= tree;
        if(root!=null) list.add(root);
    }

    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }

    @Override
    public Position<E> next() {
        Position<E> first = list.remove(0);
        List<Position<E>> aux = new LinkedList<>();
        for(Position<E> position : tree.children(first)){
            aux.add(0,position);
        }
        for(Position<E> pos:aux){
            list.add(0,pos);
        }
        return first;
    }
}