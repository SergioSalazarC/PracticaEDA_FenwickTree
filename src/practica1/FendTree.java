
package practica1;

/**
 *
 * @author mayte
 */
public class FendTree {

    int[] Ftree;
    
    /**
     * Builds a Fendwick Tree with the array that receives
     * 
     * @param array
     */


    public FendTree(int [] array){
      Ftree=new int[array.length+1];
      for(int i=0; i<array.length+1;i++){
          if(i==0) continue;
          int x=i;
          int y= x-(x & (-x));
          for(int j=y;j<x;j++){
              Ftree[x]+=array[j];
          }
      }


    }

    public int getNum(int i){
        return this.Ftree[i];
    }
    
    /**
     * Receives an index and returns the partial sum from zero to that index.
     * An exception is thrown if the index is less than zero or greater than or equal to n.
     * @param index
     * @return 
     */
    public int getSum(int index){
        int value=0;
        int x=index+1;
        while(x>0){
            value+=this.Ftree[x];
            x=x-(x&(-x));
        }
        return value;
    }
 
    /**
     * Update the value of the position index in the array.
     * 
     * @param index
     * @param val
     */
    public void upDate (int index, int val){
        if(index<0){
            throw new IndexOutOfBoundsException();
        }
        int x=index+1;
        while(x<=this.Ftree.length){
            this.Ftree[x]=this.Ftree[x]+val;
            x=x+(x&(-x));
        }
    }
    
}
