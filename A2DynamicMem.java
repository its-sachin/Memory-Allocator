// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 

// time ------> O(nlogn)
// space ------> O(n)
public void Defragment() {

        if (this.type == 2 ) {

            BSTree newFree = new BSTree();

            Dictionary curr = this.freeBlk.getFirst();
            if (curr != null) {

                while (curr != null) {
                    newFree.Insert(curr.address, curr.size, curr.address);
                    // System.out.println(curr.address + " de " + curr.size + " de " + curr.key);
                    curr = curr.getNext();
                }

                curr = newFree.getFirst();

                while (curr != null && curr.getNext() != null) {
                    Dictionary next = curr.getNext();
                    if (next.address == curr.address + curr.size){
                        newFree.Delete(curr);
                        newFree.Delete(next);

                        this.freeBlk.Delete(new BSTree(curr.address, curr.size, curr.size));
                        this.freeBlk.Delete(new BSTree(next.address, next.size, next.size));

                        curr = newFree.Insert(curr.address, curr.size + next.size, curr.address);
                        this.freeBlk.Insert(curr.address, curr.size + next.size, curr.size + next.size);
                    }

                    else {
                        curr = curr.getNext();
                    }
                }

                newFree = null;

            }

        }

        else {
            AVLTree newFree = new AVLTree();

            Dictionary curr = this.freeBlk.getFirst();
            if (curr != null) {

                while (curr != null) {
                    newFree.Insert(curr.address, curr.size, curr.address);
                    // System.out.println(curr.address + " de " + curr.size + " de " + curr.key);
                    curr = curr.getNext();
                }

                curr = newFree.getFirst();

                while (curr != null && curr.getNext() != null) {
                    Dictionary next = curr.getNext();
                    if (next.address == curr.address + curr.size){
                        newFree.Delete(curr);
                        newFree.Delete(next);

                        this.freeBlk.Delete(new AVLTree(curr.address, curr.size, curr.size));
                        this.freeBlk.Delete(new AVLTree(next.address, next.size, next.size));

                        this.freeBlk.Insert(curr.address, curr.size + next.size, curr.size + next.size);
                        curr = newFree.Insert(curr.address, curr.size + next.size, curr.address);
                    }

                    else {
                        curr = curr.getNext();
                    }
                }

                newFree = null;

            }
        }

    }
}