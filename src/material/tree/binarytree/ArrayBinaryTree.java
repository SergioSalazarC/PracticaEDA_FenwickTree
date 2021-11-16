
package material.tree.binarytree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import material.Position;
import material.tree.narytree.InvalidPositionException;

/**
 *
 * @author mayte
 * @param <E>
 */
public class ArrayBinaryTree<E> implements BinaryTree<E> {

    Position<E>[] tree;


    private class BTPos<E> implements Position<E>{

        private int pos;
        private E elem;

        public BTPos(int pos, E elem) {
            this.pos = pos;
            this.elem = elem;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        @Override
        public E getElement() {
            return elem;
        }

        public void setElem(E elem) {
            this.elem = elem;
        }
    }

    public ArrayBinaryTree() {
        this.tree= new Position[100];
    }

    public ArrayBinaryTree(int size){
        this.tree = new Position[size];
    }

    @Override
    public Position<E> left(Position<E> v) {
        if(!this.hasLeft(v)) throw new RuntimeException();
        BTPos<E> pos = null;
        Position<E> sol = null;
        int i;
        try {
            pos=checkPosition(v);
            i=pos.getPos();
            if(2*i+1>=tree.length){
                return null;
            }
            sol = this.tree[2*i+1];
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return sol;
    }

    @Override
    public Position<E> right(Position<E> v) {
        if(!this.hasRight(v)) throw new RuntimeException();
        BTPos<E> pos = null;
        Position<E> sol = null;
        int i;
        try {
            pos=checkPosition(v);
            i=pos.getPos();
            if(2*i+2>=tree.length){
                return null;
            }
            sol = this.tree[2*i+2];
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return sol;
    }

    @Override
    public boolean hasLeft(Position<E> v) {
        BTPos<E> pos = null;
        Position<E> sol = null;
        int i=0;
        try {
            pos=checkPosition(v);
            i=pos.getPos();
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return (2*i+1 < tree.length && tree[2*i+1]!=null);
    }

    @Override
    public boolean hasRight(Position<E> v) {
        BTPos<E> pos = null;
        Position<E> sol = null;
        int i=0;
        try {
            pos=checkPosition(v);
            i=pos.getPos();
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return (2*i+1 < tree.length && tree[2*i+1]!=null);
    }

    @Override
    public boolean isInternal(Position<E> v) {
        return (!isLeaf(v));
    }

    @Override
    public boolean isLeaf(Position<E> p) {
        return (!this.hasLeft(p) && !this.hasRight(p));
    }

    @Override
    public boolean isRoot(Position<E> p) {
        BTPos<E> pos = null;
        boolean a = false;
        try {
            pos = checkPosition(p);
            a = pos.getPos()==0;
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public Position<E> root() {
        return tree[0];
    }

    @Override
    public E replace(Position<E> p, E e) {
        BTPos<E> pos = null;
        try {
            pos = checkPosition(p);
            pos.elem=e;
            return e;
        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Position<E> sibling(Position<E> p) {
        BTPos<E> pos = null;
        try {
            pos = checkPosition(p);
            if(pos.pos%2==1){
                return this.tree[pos.pos+1];
            }
            else{
                return this.tree[pos.pos-1];
            }
        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Position<E> addRoot(E e) {
        this.tree[0] = new BTPos<E>(0,e);
        return this.tree[0];
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) {
        BTPos<E> pos = null;
        try {
            pos = checkPosition(p);
            if(pos.pos*2+1<this.tree.length){
                this.tree[pos.pos*2+1] = new BTPos<>(pos.pos*2+1,e);
                return this.tree[pos.pos*2+1];
            }
            else{
                Position<E>[] nuevo = new Position[(pos.pos*2+1)*2];
                for(int i=0;i<tree.length;i++){
                    nuevo[i]=tree[i];
                }
                tree=nuevo;
                this.tree[pos.pos*2+1] = new BTPos<>(pos.pos*2+1,e);
                return this.tree[pos.pos*2+1];
            }
        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) {
        BTPos<E> pos = null;
        try {
            pos = checkPosition(p);
            if(pos.pos*2+2<this.tree.length){
                this.tree[pos.pos*2+2] = new BTPos<>(pos.pos*2+2,e);
                return this.tree[pos.pos*2+2];
            }
            else{
                Position<E>[] nuevo = new Position[(pos.pos*2+2)*2];
                for(int i=0;i<tree.length;i++){
                    nuevo[i]=tree[i];
                }
                tree=nuevo;
                this.tree[pos.pos*2+2] = new BTPos<>(pos.pos*2+2,e);
                return this.tree[pos.pos*2+2];
            }
        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public E remove(Position<E> p) {
        if(this.isLeaf(p)){
            try {
                BTPos<E> q = checkPosition(p);
                this.tree[q.pos]=null;
                return q.elem;
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }

        }
        else{
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public void swap(Position<E> p1, Position<E> p2) {
        BTPos<E> n1 =null;
        BTPos<E> n2 = null;
        try {
            n1=checkPosition(p1);
            n2=checkPosition(p2);
            E aux = p1.getElement();
            n1.setElem(n2.elem);
            n2.setElem(aux);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void attachLeft(Position<E> h, BinaryTree<E> t1) {
        throw new UnsupportedOperationException("Not supported yet.");
        /*
        try {
            BTPos<E> pos = checkPosition(h);
            int indexLeft = pos.pos+2+1;
            if (indexLeft>= tree.length){
                Position<E>[] nuevo = new Position[(pos.pos*2+1)*2];
                for(int i=0;i<tree.length;i++){
                    nuevo[i]=tree[i];
                }
                tree=nuevo;
            }
            LinkedList<E> l = new LinkedList<E>();
            BTPos<E> rt = checkPosition(t1.root());



        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }

         */
    }

    @Override
    public void attachRight(Position<E> h, BinaryTree<E> t1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BinaryTree<E> subTree(Position<E> h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        return this.tree[0]==null;
    }

    @Override
    public Position<E> parent(Position<E> v) {
        if(this.isRoot(v)) throw new RuntimeException();
        try {
            BTPos<E> p = checkPosition(v);
            return this.tree[p.pos/2];
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        LinkedList<Position<E>> lista = new LinkedList<>();
        if(this.hasLeft(v)){
            lista.add(this.left(v));
        }
        if(this.hasRight(v)){
            lista.add(this.right(v));
        }
        return lista;

    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new InorderBinaryTreeIterator<>(this);
    }

    private BTPos<E> checkPosition(Position<E> p)throws InvalidPositionException {
        if(p==null || !(p instanceof BTPos)){
            throw new InvalidPositionException("The position is invalid");
        }
        BTPos<E> n = (BTPos<E>) p;
        return n;
    }
}
