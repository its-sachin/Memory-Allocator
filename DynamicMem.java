// Class: DynamicMem
// Dynamic memory allocator using the dictionaries
// Can use three implementation of dictionaries
//   1. Based on doubly linked lists (List.java --> A1List.java)
//   2. Based on binary search trees (Tree.java --> BSTree.java)
//   3. Based on AVL trees (AVLTree.java)

public abstract class DynamicMem {
        
    public final static int M = 1000000; // Total number of Memory addresses
    public byte Memory[];  //Memory Array, initailized from data segment

    public Dictionary freeBlk;             // Free blocks dictionary
    public Dictionary  allocBlk;           // Allocated blocks dictionary
    int type;                              // Type of dictionary: 1 -- DL List; 2 -- Binary Search Tree; 3 -- AVL Tree
            
    public DynamicMem() {                  // Constructor function should create a memory of size M if no size specified
        this(M, 1);                        // Default dictionary using doubly linked lists
   }
    public DynamicMem(int size) {          // Default dictionary uses doubly linked lists
                                        
        this(size, 1);
    }

    public DynamicMem(int size, int dict_type) {
 
        // Constructor function. Allocates memory for the allocator. 
        // Initializes the free blocks and allocates blocks dictionaries
        // If dist_type == 1 then it uses Lists (A1List)
        // If dist_type == 2 then it uses Binary Search Trees (BSTree)
        // If dist_type == 3 then it uses AVL Trees (AVLTree)

        // Index the dictionary by size to find the best fit in case of BSTs
        // Initially, there is only one block in the free block list

        Memory = new byte[size];
        
        type = dict_type;

        if (type == 1){
            freeBlk = new A1List();   // Initiates the sentinel nodes. (Tail and head)
            allocBlk = new A1List();   
            freeBlk.Insert(0, size, size);
            // Initially free list has only one block with all the memory
        }
        else if (type == 2){
            freeBlk = new BSTree(); // Initiates the sentinel root node.
            allocBlk = new BSTree();
            freeBlk.Insert(0, size, size);
            // Initially free list has only one block with all the memory
        }
        else{
            freeBlk = new AVLTree();
            allocBlk = new AVLTree();
            freeBlk.Insert(0, size, size);
            // Initially free list has only one block with all the memory
        } 
    }

   public abstract int Allocate(int blockSize);

       // This function should allocate a contiguous array of size blockSize and return the first address. 
       // It should return -1 if memory is not avaiable. 
       // The free blocks list and the allocated blocks list should be suitably modified
       // Algorithm: 
       //     1. Search in the free block dictionary to find a block of size >= blockSize using the find function
       //     2. If found, check if splitting of the block is needed. 
       //     3. If yes, split the block and insert the two blocks into the free and allocated blocks dictionary
       //     3.1. Delete the block from the free block dictionary
       //     4 If no, insert the block into allocated blocks dictionary and remove it from free blocks dictionary
                                                                                
    public abstract int Free(int startAddr);
       // This function should free the memory block starting at the startAddr
       // It should return -1 in case block not found
       // Algorithm: 
       //    1. Add the block to free blocks list (dictionary)
       //    2. Delete the bock from the allocated blocks list (dictionary)

    public abstract void Defragment();
       // This function defragments the free block list (dictionary)
       // All the contiguous free blocks are merged into a single large free block
       // Algorithm:
       //     1. Create a new BST/AVT Tree indexed by address. Use AVL/BST depending on the type.
       //     2. Traverse all the free blocks and add them to the tree indexed by address 
       //        Note that the free blocks tree will be indexed by size, therefore a new tree indexed by address needs to be created
       //     3. Find the first block in the new tree (indexed by address) and then find the next block
       //     4. If the two blocks are contiguous, then 
       //     4.1 Merge them into a single block
       //     4.2 Remove the free blocks from the free list and the new dictionary
       //     4.3 Add the merged block into the free list and the new dictionary
       //     5. Continue traversing the new dictionary
       //     6. Once the traversal is complete, delete the new dictionary
}
