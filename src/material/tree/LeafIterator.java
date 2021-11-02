
package material.tree;

import java.util.Iterator;
import material.Position;

/**
 *
 * @author mayte
 * @param <T>
 */
public class LeafIterator<T> implements Iterator<Position<T>>  {
    
    

    public LeafIterator(Tree<T> tree, Position<T> root){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public LeafIterator(Tree<T> tree){
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean hasNext() {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method only visits the leaf nodes 
     */
    @Override
    public Position<T> next() {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
