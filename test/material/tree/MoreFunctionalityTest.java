/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package material.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import material.Position;
import material.tree.narytree.LinkedTree;
import material.tree.narytree.NAryTree;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mayte
 */
public class MoreFunctionalityTest {
    
    public MoreFunctionalityTest() {
    }
    
    private NAryTree<Character> general(){
        NAryTree<Character> tree = new LinkedTree<>();
        
        Position<Character> r = tree.addRoot('A');
        tree.add('B', r);
        tree.add('C', r);
        Position<Character> hD = tree.add('D', r);
        Position<Character> hE = tree.add('E', r);
        tree.add('F', hD);
        Position<Character> hG = tree.add('G', hE);
        Position<Character> hH = tree.add('H', hE);
        tree.add('I', hE);
        Position<Character> hJ = tree.add('J', hG);
        tree.add('K', hH);
        tree.add('L', hJ);
        
        return tree;
        
    }

    /**
     * Test of leftView method, of class MoreFunctionality.
     */
    @Test
    public void testLeftView() {
        System.out.println("leftView");
        MoreFunctionality<Character> instance = new MoreFunctionality<Character>();
        try{
            instance.leftView(null);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        List<Character> l = new LinkedList<>();
        NAryTree<Character> t = new LinkedTree<>();
        assertEquals(instance.leftView(t).toString(), l.toString());
        l = Arrays.asList('A','B','F','J','L');
        t = general();
        assertEquals(instance.leftView(t).toString(),l.toString());
    }

    /**
     * Test of rightView method, of class MoreFunctionality.
     */
    @Test
    public void testRightView() {
        System.out.println("rightView");
        MoreFunctionality<Character> instance = new MoreFunctionality<>();
        try{
            instance.rightView(null);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        List<Character> l = new LinkedList<>();
        NAryTree<Character> t = new LinkedTree<>();
        assertEquals(instance.rightView(t).toString(), l.toString());
        l = Arrays.asList('A','E','I','K','L');
        t = general();
        assertEquals(instance.rightView(t).toString(),l.toString());
    }
    
}
