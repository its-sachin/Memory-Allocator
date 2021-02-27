// Implements Dictionary using Doubly Linked List (DLL)

public abstract class List extends Dictionary {
        
    private List next; // Next Node
    private List prev;  // Previous Node 
        
    public List() { 
        super(0, 0, 0); 
        this.prev = null;
        this.next = null;
     }

    public List(int address, int size, int key) { 
        super(address, size, key);
        this.prev = null;
        this.next = null;
    }

    public abstract List Insert(int address, int size, int key);
        // Should insert the element in the DLL after the current node.
        // Should return the corresponding element created and inserted.

    public abstract boolean Delete(Dictionary d);
        // Deletes the entry corresponding to d from the DLL.
        // Searches for the d.key in the DLL
        // Can be called with any node in the DLL. So this function should search forward as well as backwards.
        // Deletes the element it is found in the DLL and returns true. 
        // Note there may be multiple elements with the same key value. 
        // Delete searches for the node with the same key and same d and returns true only if d as well as d.key match
        // Returns false if d not found in the DLL.
    
    public abstract List Find(int k, boolean exact);
        // Searches for the key k in the DLL. 
        // If exact is true, then performs and exact match and returns an element of the dictionary with key = k
        // and returns null if no such element exists.

        // If exact is false, performs an approximate search and
        // returns an element with key >= k in the dictionary.  Returns null in case no such element found.

        // Can be used to implement the First Split Fit strategy with exact = false
         

    public abstract List getFirst(); 
    public abstract List getNext(); 

        // The getFirst and getNext functions are for traversal of the dictionary implemented using List. 
        // In this implementation getFirst() returns the first element of the list and null if the List is empty.
        // In this implementation  getNext() returns the next element of the list.

        // Remember this class implements dictionary using List Data Structure, so semantics of these functions should be preserved.
        // The dictionary class does not define any order in which the elements of the dictionary are to be traversed. 
        // The only requirement is that using the following loop, getFirst() and getNext() should be able to traverse all the elements in the dictionary. 
        // count = 0; for (d = dict.getFirst(); d != null; d = d.getNext()) count = count + 1;
        // After the above loop, count should contain the total number of elements in the dictionary.

    public abstract boolean sanity();
        // Checks the sanity of the dictionary and returns true if sane, false otherwise
}


