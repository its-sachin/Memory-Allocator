// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) 
    // time -----> O(n) 
    // space -----> O(1)
    {
        if (blockSize <= 0) {
            return -1;
        }
        else {
            Dictionary found = this.freeBlk.Find(blockSize, false);

            if (found == null) {
                return -1;
            }

            else {
                this.freeBlk.Delete(found);

                if (this.freeBlk.key == -1) {
                    this.freeBlk.Insert(found.address + blockSize, found.size - blockSize, found.size - blockSize);
                }

                else {
                    Dictionary firstFree = this.freeBlk.getFirst();

                    firstFree.Insert(found.address + blockSize, found.size - blockSize, found.size - blockSize);
                    this.freeBlk.Delete(firstFree);
                    this.freeBlk.getFirst().Insert(firstFree.address, firstFree.size, firstFree.size);
                }

                if (this.allocBlk.key == -1) {
                    this.allocBlk.Insert(found.address, blockSize,found.address);
                    // this.print();
                    return found.address;
                }

                else {
                    Dictionary first = this.allocBlk.getFirst();

                    first.Insert(found.address, blockSize, found.address);
                    this.allocBlk.Delete(first);
                    this.allocBlk.getFirst().Insert(first.address, first.size, first.address);
                    // this.print();
                    return found.address;
                }
            }

        }
        
        
    } 
    
    public int Free(int startAddr) 
    // time -----> O(n) 
    // space -----> O(1)
    {
        if (startAddr < 0) {
            return -1;
        } 

        else  {
            Dictionary found = this.allocBlk.Find(startAddr, true);
            if (found == null) {
                return -1;
            }
            else {

                this.allocBlk.Delete(found);

                if (this.freeBlk.key == -1) {
                    // System.out.println(found.address);
                    this.freeBlk.Insert(found.address, found.size, found.size);
                }

                else {
                    Dictionary first = this.freeBlk.getFirst();

                    first.Insert(found.address, found.size, found.size);
                    this.freeBlk.Delete(first);
                    this.freeBlk.getFirst().Insert(first.address, first.size, first.size);
                }


                // this.print();

                return 0;
            }

        }
        
    }

    private void print(){

        Dictionary curr = this.freeBlk.getFirst();

        while (curr != null) {
            System.out.println(curr.address + " fr " + curr.size + " fr " + curr.key);
            curr = curr.getNext();
        }

    }
}