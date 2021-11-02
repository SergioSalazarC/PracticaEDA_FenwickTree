/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package practica1;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mayte
 */
public class FendTreeTest {
    
    public FendTreeTest() {
    }

    /**
     * Test of getSum method, of class FendTree.
     */
    @Test
    public void testGetSum() {
        System.out.println("getSum");
        int[] array = {2,1,1,3,2,3,4,5,6,7,8,9};
        
        FendTree instance = new FendTree(array);
        assertEquals(instance.getSum(0),2);
        assertEquals(instance.getSum(1),3);
        assertEquals(instance.getSum(2),4);
        assertEquals(instance.getSum(3),7);
        assertEquals(instance.getSum(4),9);
        assertEquals(instance.getSum(5),12);
        assertEquals(instance.getSum(6),16);
        assertEquals(instance.getSum(7),21);
        assertEquals(instance.getSum(8),27);
        assertEquals(instance.getSum(9),34);
        assertEquals(instance.getSum(10),42);
        assertEquals(instance.getSum(11),51);
        
        try{
            int v = instance.getSum(-2);
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
        
        try{
            int v = instance.getSum(array.length);
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of upDate method, of class FendTree.
     */
    @Test
    public void testUpDate() {
        System.out.println("upDate");
        int[] array = {2,1,1,3,2,3,4,5,6,7,8,9};
        
        FendTree instance = new FendTree(array);
        assertEquals(instance.getSum(5),12);
        instance.upDate(5, 3);
        assertEquals(instance.getSum(5),15);
        
         try{
            instance.upDate(-1, 3);
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
        
        try{
            instance.upDate(array.length, 3);
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
    
}
