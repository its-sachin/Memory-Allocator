// Class: Implementation of Dictionary using Binary Search Tree
// The Dictionary now becomes an ordered dictionary, ordered by key

// NOTE: A sentinel root node is used to define the tree.

public abstract class Tree extends Dictionary {
        
    private Tree left, right;     // Children. Null if leaf node
    private Tree parent;          // Parent pointer. Null for sentinel root node.

    public Tree() { 
        super(-1, -1, -1);
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public Tree(int address, int size, int key) { 
        super(address, size, key);
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public abstract Tree Insert(int address, int size, int key);
        // Inserts a node in the subtree and returns the corresponding dictionary element created and inserted
        // Assumes that subtree is a BST sorted with respect to the key.

    public abstract boolean Delete(Dictionary e);
        // Deletes the entry corresponding to e from the subtree.
        // Assumes that the subtree is BST sorted in non-decreasing order of key
        // Searches for the e.key in the subtree
        // Deletes the element it is found in the subtree and returns true. 
        // Note there may be multiple elements with the same key value. 
        // Delete searches for the node with the same key and same e and returns true only if e as well as e.key match
        // Returns false if e not found in the subtree.
        
    public abstract Tree Find(int k, boolean exact);
        // If exact is true, then performs and exact match and returns an element of the dictionary with key = k
        // and returns null if no such element exists.

        // If exact is false, performs an approximate search and
        // returns the element with SMALLEST key such that key >= k in the subtree.  Returns null in case no such element found.

        // Can be used to implement the Best Split Fit strategy when called on sentinel node or root node with exact = false


    public abstract Tree getFirst(); 
    public abstract Tree getNext(); 

        // The getFirst and getNext functions are for inorder traversal of the BST. 
        // The getFirst() returns the first (smallest) element of the BST subtree and null if the sbutree is empty
        // The getNext() returns the next element after in the inorder traversal of the BST
        // The Unlike the abstract Dictionary class, Tree class traverses the tree according to the inorder traversal, ordered by key
        
        // Remember this class implements dictionary using List Data Structure, so semantics of these functions should be preserved.
        // The dictionary class does not define any order in which the elements of the dictionary are to be traversed, but in the tree implementation, the traversal should be inorder
        // The additional requirement is that using the following loop, getFirst() and getNext() should be able to traverse all the elements in the dictionary (even in the presence of duplicate keys). 
        // count = 0; for (d = dict.getFirst(); d != null; d = d.getNext()) count = count + 1;
        // After the above loop, count should contain the total number of elements in the dictionary.

    public abstract boolean sanity();
        // Checks the sanity of the BST subtree and returns true if sane, false otherwise
}


 
