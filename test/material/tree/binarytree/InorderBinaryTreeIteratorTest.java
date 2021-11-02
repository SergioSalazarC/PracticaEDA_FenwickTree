/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package material.tree.binarytree;

import material.Position;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mayte
 */
public class InorderBinaryTreeIteratorTest {
    
    public InorderBinaryTreeIteratorTest() {
    }

    /**
     * Test of hasNext method, of class InorderBinaryTreeIterator.
     */
    @Test
    public void testIterator() {
        System.out.println("testIterator");
        LinkedBinaryTree<Integer> t = new LinkedBinaryTree<>();
        Position<Integer> a = t.addRoot(4);
        Position<Integer> b = t.insertLeft(a,2);
        Position<Integer> c = t.insertRight(a,8);
        Position<Integer> d = t.insertLeft(b,1);
        Position<Integer> e = t.insertRight(b,3);
        Position<Integer> f = t.insertLeft(c,6);
        Position<Integer> g = t.insertRight(c,9);
        Position<Integer> h = t.insertLeft(f,5);
        Position<Integer> i = t.insertRight(f,7);
        

        String salida = "";
        InorderBinaryTreeIterator <Integer> it = new InorderBinaryTreeIterator<>(t);
        while (it.hasNext()) {
            salida += it.next().getElement().toString();
        }
        assertEquals("123456789",salida);
    }
 
}
