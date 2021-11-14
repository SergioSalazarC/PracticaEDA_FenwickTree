
package material.tree.binarytree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import material.Position;
import material.tree.BreadthFirstTreeIterator;
import material.tree.PreOrderTreeIterator;
import material.tree.narytree.InvalidPositionException;
import material.tree.narytree.LinkedTree;

/**
 *
 * @author mayte
 * @param <T>
 */
public class LinkedBinaryTree<T> implements BinaryTree<T> {
    
    private BinaryTreeNode root;


    private class BinaryTreeNode<T> implements Position<T>{
        private T element;
        private BinaryTreeNode<T> left;
        private BinaryTreeNode<T> right;
        private BinaryTreeNode<T> parent;

        @Override
        public String toString() {
            return element.toString();
        }

        public BinaryTreeNode(T element, BinaryTreeNode parent) {
            this.element = element;
            this.parent = parent;
        }
        
        @Override
        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public BinaryTreeNode<T> getLeft() {
            return left;
        }

        public void setLeft(BinaryTreeNode<T> left) {
            this.left = left;
        }

        public BinaryTreeNode<T> getRight() {
            return right;
        }

        public void setRight(BinaryTreeNode<T> right) {
            this.right = right;
        }

        public BinaryTreeNode<T> getParent() {
            return parent;
        }

        public void setParent(BinaryTreeNode<T> parent) {
            this.parent = parent;
        }
    }




    @Override
    public Position<T> left(Position<T> v) {
        if(!this.hasLeft(v)){
            throw new RuntimeException();
        }
        BinaryTreeNode k = null;
        try {
             k = checkPosition(v);
             k=k.getLeft();
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return k;
    }

    @Override
    public Position<T> right(Position<T> v) {
        if(!this.hasRight(v)){
            throw new RuntimeException();
        }
        BinaryTreeNode k = null;
        try {
            k = checkPosition(v).getRight();
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return k;
    }

    @Override
    public boolean hasLeft(Position<T> v) {
        BinaryTreeNode k = null;
        try {
            k = checkPosition(v).getLeft();
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return k!=null;
    }

    @Override
    public boolean hasRight(Position<T> v) {
        BinaryTreeNode k = null;
        try {
            k = checkPosition(v).getRight();
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return k!=null;
    }

    @Override
    public boolean isInternal(Position<T> v) {
        return(!this.isLeaf(v));
    }

    @Override
    public boolean isLeaf(Position<T> p) {
        Boolean sol = false;
        BinaryTreeNode<T> node = null;
        try {
            node = checkPosition(p);
            sol = node.getLeft()==null && node.getRight()==null;
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return sol;

    }

    @Override
    public boolean isRoot(Position<T> p) {
        Boolean sol = false;
        BinaryTreeNode<T> node = null;
        try {
            node = checkPosition(p);
            sol = node.getParent()==null;
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return sol;
    }

    @Override
    public Position<T> root() {
        return this.root;
    }

    @Override
    public T replace(Position<T> p, T e) {

        try {
            BinaryTreeNode<T> nodo = checkPosition(p);
            nodo.setElement(e);
        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
        }
        return e;
    }

    @Override
    public Position<T> sibling(Position<T> p) {
        Position<T> sib;
        if(this.isRoot(p)){
            throw new RuntimeException();
        }
        Position<T> par = this.parent(p);
        if(!this.hasLeft(par) || !this.hasRight(par)) return null;
        if(this.right(par).equals(p)) sib=this.left(par);
        else sib=this.right(par);
        return sib;
    }

    @Override
    public Position<T> addRoot(T e) {
        this.root= new BinaryTreeNode(e,null);
        return this.root;
    }

    @Override
    public Position<T> insertLeft(Position<T> p, T e) {
        BinaryTreeNode<T> hijo=null;
        try {
            BinaryTreeNode<T> nodo=checkPosition(p);
            nodo.setLeft(new BinaryTreeNode<>(e,nodo));
            hijo=nodo.getLeft();
        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
        }
        return hijo;
    }

    @Override
    public Position<T> insertRight(Position<T> p, T e) {
        BinaryTreeNode<T> hijo=null;
        try {
            BinaryTreeNode<T> nodo=checkPosition(p);
            nodo.setRight(new BinaryTreeNode<>(e,nodo));
            hijo=nodo.getRight();
        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
        }
        return hijo;
    }

    @Override
    public T remove(Position<T> p) {
        if(this.hasLeft(p) && this.hasRight(p)){
            throw new RuntimeException();
        }
        BinaryTreeNode<T> nodo=null;
        T elem=null;
        try {
            nodo=checkPosition(p);
            elem= nodo.getElement();
            if(nodo.getRight()==null && nodo.getLeft()==null){
                if(nodo.getParent().getLeft()==nodo){
                    nodo.getParent().setLeft(null);
                    nodo.setParent(null);
                }
                else{
                    nodo.getParent().setRight(null);
                    nodo.setParent(null);
                }
            }
            else if(nodo.getRight()==null){
                if(nodo.getParent().getLeft()==nodo){
                    nodo.getParent().setLeft(nodo.getLeft());
                    nodo.setParent(null);
                }
                else{
                    nodo.getParent().setRight(nodo.getLeft());
                    nodo.setParent(null);
                }
            }
            else if(nodo.getLeft()==null){
                if(nodo.getParent().getLeft()==nodo){
                    nodo.getParent().setLeft(nodo.getRight());
                    nodo.setParent(null);
                }
                else{
                    nodo.getParent().setRight(nodo.getRight());
                    nodo.setParent(null);
                }
            }
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }

        return elem;
    }

    @Override
    public void swap(Position<T> p1, Position<T> p2) {
        try {
            BinaryTreeNode<T> n1 = checkPosition(p1);
            BinaryTreeNode<T> n2 = checkPosition(p2);
            T element1 = n1.getElement();
            T element2 = n2.getElement();
            n1.setElement(element2);
            n2.setElement(element1);

        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public Position<T> parent(Position<T> v) {
        if (this.isRoot(v)) throw new RuntimeException();
        BinaryTreeNode<T> pa = null;
        try {
            BinaryTreeNode<T> node = checkPosition(v);
            pa=node.getParent();
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return pa;
    }

    @Override
    public Iterable<? extends Position<T>> children(Position<T> v) {
        LinkedList<Position<T>> aux= new LinkedList<>();
        if(this.left(v)!=null)
            aux.add(this.left(v));
        if(this.right(v)!=null)
            aux.add(this.right(v));

        return aux;
    }

    @Override
    public Iterator<Position<T>> iterator() {
        return new InorderBinaryTreeIterator<>(this);
    }

    @Override
    public void attachLeft(Position<T> h, BinaryTree<T> t1) {
        if(this.hasLeft(h)){
            throw new RuntimeException();
        }
        else{
            try {
                BinaryTreeNode<T> nodo = checkPosition(h);
                BinaryTreeNode<T> nodoraix = checkPosition(t1.root());
                nodoraix.setParent(nodo);
                nodo.setLeft(nodoraix);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void attachRight(Position<T> h, BinaryTree<T> t1) {
        if(this.hasRight(h)){
            throw new RuntimeException();
        }
        else{
            try {
                BinaryTreeNode<T> nodo = checkPosition(h);
                BinaryTreeNode<T> nodoraix = checkPosition(t1.root());
                nodoraix.setParent(nodo);
                nodo.setRight(nodoraix);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public BinaryTree<T> subTree(Position<T> h) {
        BinaryTreeNode<T> node =null;
        if(this.isRoot(h)) return this;
        Position<T> padre = this.parent(h);
        if (this.hasLeft(padre) && this.left(padre).equals(h)) {
            try {
                BinaryTreeNode nodo = checkPosition(padre);
                nodo.setLeft(null);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
        }
        if (this.hasRight(padre) && this.right(padre).equals(h)) {
            try {
                BinaryTreeNode nodo = checkPosition(padre);
                nodo.setRight(null);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
        }

        try {
            node=checkPosition(h);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        LinkedBinaryTree<T> arbol = new LinkedBinaryTree<>();
        arbol.root=node;
        return arbol;

    }

    private BinaryTreeNode<T> checkPosition(Position<T> p)throws InvalidPositionException {
        if(p==null || !(p instanceof BinaryTreeNode)){
            throw new InvalidPositionException("The position is invalid");
        }
        BinaryTreeNode n = (BinaryTreeNode <T>) p;
        return n;
    }
    
}
