package material.tree;

import material.Position;

import java.util.*;

/**
 *
 * @author jvelez
 * @param <T>
 */
public class PosOrderTreeIterator<T> implements Iterator<Position<T>> {

    List<Position<T>> pila = new LinkedList<>();
    HashSet<Position<T>> conj = new HashSet<>();
    Tree<T> arbol;

       
    public PosOrderTreeIterator(Tree<T> tree) {
        this(tree,tree.root());
    }

    public PosOrderTreeIterator(Tree<T> tree, Position<T> root) {
        arbol=tree;
        pila.add(root);
    }

    @Override
    public boolean hasNext() {
        return !pila.isEmpty();
    }

    /**
     * This method visits the nodes of a tree by following a pos-order
     */
    @Override
    public Position<T> next() {
        Position<T> pos = pila.remove(0);
        while(true){
            if(conj.contains(pos)){
                break;
            }
            conj.add(pos);
            pila.add(0,pos);
            LinkedList<Position<T>> aux = new LinkedList<>();
            for(Position<T> p: arbol.children(pos)){
                aux.addFirst(p);
            }
            for(Position<T> p:aux){
                pila.add(0,p);
            }
            pos=pila.remove(0);
        }
        return pos;

    }

}
