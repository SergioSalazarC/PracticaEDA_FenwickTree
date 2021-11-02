
package material.tree.narytree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import material.Position;
import material.tree.PreOrderTreeIterator;

import javax.swing.tree.TreeNode;

/**
 *
 * @author mayte
 * @param <E>
 */
public class LinkedTree<E> implements NAryTree<E> {

    private TreeNode<E> root;

    private class TreeNode<T> implements Position<T>{
        private T element;
        private List<TreeNode<T>> children;
        private TreeNode<T> parent;

        public TreeNode(T element,  TreeNode<T> parent) {
            this.element = element;
            this.children = new LinkedList<>();
            this.parent = parent;
        }


        @Override
        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public List<TreeNode<T>> getChildren() {
            return children;
        }

        public void setChildren(List<TreeNode<T>> children) {
            this.children = children;
        }

        public TreeNode<T> getParent() {
            return parent;
        }

        public void setParent(TreeNode<T> parent) {
            this.parent = parent;
        }

    }

    public LinkedTree(){
        root=null;
    }

    @Override
    public Position<E> addRoot(E e) {
        root = new TreeNode<>(e, null);
        return root;
    }

    @Override
    public Position<E> add(E element, Position<E> p) {
        TreeNode<E> parent = null;
        try {
            parent = checkPosition(p);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        TreeNode<E> node = new TreeNode<>(element, parent);
        parent.getChildren().add(node);
        return node;
    }

    @Override
    public Position<E> add(E element, Position<E> p, int n) {
        TreeNode<E> parent = null;
        try {
            parent = checkPosition(p);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        TreeNode<E> node = new TreeNode<>(element, parent);
        parent.getChildren().add(n, node);
        return node;
    }

    @Override
    public void swapElements(Position<E> p1, Position<E> p2) {
        TreeNode<E> n1 = null;
        try {
            n1 = checkPosition(p1);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        TreeNode<E> n2 = null;
        try {
            n2 = checkPosition(p2);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        E e1 = n1.getElement();
        n1.setElement(n2.getElement());
        n2.setElement(e1);
    }

    @Override
    public E replace(Position<E> p, E e) {
        TreeNode<E> n = null;
        try {
            n = checkPosition(p);
        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
        }
        E element = n.getElement();
        n.setElement(e);
        return element;
    }

    @Override
    public void remove(Position<E> p) {
        if(isRoot(p)){
            root=null;
        }
        else {
            TreeNode<E> node = null;
            try {
                node = checkPosition(p);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }

            TreeNode<E> parent = node.getParent();
            parent.getChildren().remove(node);
        }

    }

    @Override
    public NAryTree<E> subTree(Position<E> v) {
        TreeNode<E> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        TreeNode<E> parent = node.getParent();
        parent.getChildren().remove(node);
        node.setParent(null);
        LinkedTree<E> tree = new LinkedTree<>();
        tree.root=node;
        return tree;
    }

    @Override
    public void attach(Position<E> p, NAryTree<E> t) {
        TreeNode<E> node = null;
        try {
            node = checkPosition(t.root());
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        if(p==null){
            root = node;
        }
        else{
            TreeNode<E> parent = null;
            try {
                parent = checkPosition(p);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
            node.setParent(parent);
            parent.getChildren().add(node);
        }
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> v) {
        TreeNode<E> n = null;
        try {
            n = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return n.getParent();
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        TreeNode<E> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return node.getChildren();
    }

    @Override
    public boolean isInternal(Position<E> v) {
        return !(isLeaf(v)||isRoot(v));
    }

    @Override
    public boolean isLeaf(Position<E> v) {
        TreeNode<E> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return node.getChildren().isEmpty();
    }

    @Override
    public boolean isRoot(Position<E> v) {
        TreeNode<E> node = null;
        try {
            node = checkPosition(v);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return node.getParent()==null;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new PreOrderTreeIterator<>(this);
    }

    public int size(){
        Iterator<Position<E>> it = iterator();
        int size = 0;
        while (it.hasNext()){
            it.next();
            size++;
        }
        return size;
    }

    private TreeNode<E> checkPosition(Position<E> p)throws InvalidPositionException{
        if(p==null || !(p instanceof TreeNode)){
            throw new InvalidPositionException("The position is invalid");
        }
        TreeNode<E> n = (TreeNode<E>) p;
        return n;
    }
}
