
package material.tree.narytree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import material.Position;
import material.tree.BreadthFirstTreeIterator;


/**
 *
 * @author mayte
 * @param <T>
 */
public class LCRSTree<T> implements NAryTree<T> {

    private LCRSNode root;

    private class LCRSNode<T> implements Position<T>{
        private T element;
        private LCRSNode<T> hijo;
        private LCRSNode<T> hermano;
        private LCRSNode<T> parent;

        public LCRSNode(T element, LCRSNode<T> hijo, LCRSNode<T> hermano, LCRSNode<T> parent) {
            this.element = element;
            this.hijo = hijo;
            this.hermano = hermano;
            this.parent = parent;
        }

        @Override
        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public LCRSNode<T> getHijo() {
            return hijo;
        }

        public void setHijo(LCRSNode<T> hijo) {
            this.hijo = hijo;
        }

        public LCRSNode<T> getHermano() {
            return hermano;
        }

        public void setHermano(LCRSNode<T> hermano) {
            this.hermano = hermano;
        }

        public LCRSNode<T> getParent() {
            return parent;
        }

        public void setParent(LCRSNode<T> parent) {
            this.parent = parent;
        }
    }

    @Override
    public Position<T> addRoot(T e) {
        this.root=new LCRSNode(e,null,null,null);
        return this.root;
    }

    @Override
    public Position<T> add(T element, Position<T> p) {
        LCRSNode<T> padre=null;
        try {
            padre = checkPosition(p);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        LCRSNode<T> nodo = new LCRSNode<T>(element,null,null,padre);
        LCRSNode<T> hermano = padre.hijo;
        if(hermano==null){
            padre.hijo=nodo;
        }
        else{
            while(hermano.hermano!=null){
                hermano=hermano.hermano;
            }
            hermano.hermano=nodo;
        }
        return nodo;
    }

    @Override
    public Position<T> add(T element, Position<T> p, int n) {
        LCRSNode<T> padre=null;
        try {
            padre = checkPosition(p);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        LCRSNode<T> nodo = new LCRSNode<T>(element,null,null,padre);
        LCRSNode<T> hermano = padre.hijo;
        if(hermano==null){
            padre.hijo=nodo;
        }
        else{
            int i=0;
            while(hermano!=null && i<n){
                hermano=hermano.hermano;
                i++;
            }
            if(hermano.hermano!=null){
                nodo.hermano=hermano.hermano;
                hermano.hermano=nodo;
            }
            else{
                hermano.hermano=nodo;
            }
        }
        return nodo;
    }

    @Override
    public void swapElements(Position<T> p1, Position<T> p2) {
        LCRSNode<T> nodo1=null;
        LCRSNode<T> nodo2=null;
        try {
            nodo1=checkPosition(p1);
            nodo2=checkPosition(p2);
            T e1 = nodo1.element;
            nodo1.element= nodo2.element;
            nodo2.element=e1;
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public T replace(Position<T> p, T e) {
        LCRSNode<T> nodo = null;
        T elm = null;
        try {
            nodo=checkPosition(p);
            nodo.element=e;
            elm= nodo.element;
        } catch (InvalidPositionException ex) {
            ex.printStackTrace();
        }
        return elm;
    }

    @Override
    public void remove(Position<T> p) {
        if(this.isRoot(p)){
            this.root=null;
        }
        else{
            LCRSNode<T> nodo=null;
            LCRSNode<T> pad=null;
            try {
                nodo=checkPosition(p);
                pad= checkPosition(nodo.parent);
                if(pad.hijo==nodo){
                    pad.hijo=nodo.hermano;
                }
                else{
                    LCRSNode hermanoant = pad.hijo;
                    while(hermanoant.hermano!=nodo){
                        hermanoant=hermanoant.hermano;
                    }
                    hermanoant.hermano=nodo.hermano;
                    nodo.parent=null;
                    nodo.hermano=null;
                }
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public NAryTree<T> subTree(Position<T> v) {
        LCRSNode<T> nodo=null;
        LCRSNode<T> pad=null;
        try {
            nodo=checkPosition(v);
            pad= checkPosition(nodo.parent);
            if(pad.hijo==nodo){
                pad.hijo=nodo.hermano;
            }
            else{
                LCRSNode hermanoant = pad.hijo;
                while(hermanoant.hermano!=nodo){
                    hermanoant=hermanoant.hermano;
                }
                hermanoant.hermano=nodo.hermano;
                nodo.parent=null;
                nodo.hermano=null;
            }
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        LCRSTree<T> arbol = new LCRSTree<>();
        arbol.root=nodo;
        return arbol;
    }

    @Override
    public void attach(Position<T> p, NAryTree<T> t) {
        LCRSNode nodo =null;
        try {
            nodo = checkPosition(p);
            LCRSNode raiz =checkPosition(t.root());
            LCRSNode hijo = nodo.hijo;
            nodo.hijo= raiz;
            raiz.hermano=hijo;
            raiz.parent=nodo;
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isEmpty() {
        return this.root==null;
    }

    @Override
    public Position<T> root() {
        return this.root;
    }

    @Override
    public Position<T> parent(Position<T> v) {
        LCRSNode<T> nodo = null;
        Position<T> padre=null;
        try {
            nodo=checkPosition(v);
            padre = nodo.parent;
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return padre;
    }

    @Override
    public Iterable<? extends Position<T>> children(Position<T> v) {
        LinkedList<Position<T>> lista = new LinkedList<>();
        LCRSNode nodo = null;
        try {
            nodo = checkPosition(v);
            LCRSNode hijo = nodo.hijo;
            while(hijo!=null){
                lista.addLast(hijo);
                hijo=hijo.hermano;
            }
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean isInternal(Position<T> v) {
        return this.parent(v)!=null;
    }

    @Override
    public boolean isLeaf(Position<T> v) {
        boolean x = false;
        LCRSNode<T> nodo = null;
        try {
            nodo = checkPosition(v);
            if(nodo.hijo==null) x=true;
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return x;
    }

    @Override
    public boolean isRoot(Position<T> v) {
        return this.parent(v)==null;
    }

    @Override
    public Iterator<Position<T>> iterator() {
        return new BreadthFirstTreeIterator(this);
    }

    public int size() {
        LinkedList<Position<T>> l = new LinkedList<>();
        int s=0;
        if(this.isEmpty()){
            return s;
        }
        else{
            l.add(this.root);
        }
        while(!l.isEmpty()){
            Position<T> pop = l.remove();
            s++;
            for(Position<T> k : this.children(pop)){
                l.add(k);
            }
        }
        return s;
    }

    private LCRSNode<T> checkPosition(Position<T> p)throws InvalidPositionException{
        if(p==null || !(p instanceof LCRSNode)){
            throw new InvalidPositionException("The position is invalid");
        }
        LCRSNode<T> n = (LCRSNode<T>) p;
        return n;
    }

    
}
