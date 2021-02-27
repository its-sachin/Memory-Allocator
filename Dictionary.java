// Class: Dictionary

public abstract class Dictionary {

     // The data stored in the dictionary 
     public int key;              // Key on which the dictionary is indexed. 
     public int address, size;    // Contains the address and size of the memory block

     public Dictionary(int address, int size, int key) { // Constructor for the Dictionary element
          this.key = key;
          this.address = address;
          this.size = size;
     }
 
     public abstract Dictionary Insert(int address, int size, int k);
          // Inserts node in the dictionary and returns the corresponding Dictionary element created and inserted

     public abstract boolean Delete(Dictionary d);
          // Deletes the entry corresponding to d from the dictionary.
          // Searches for the d.key in the dictionary
          // Deletes the element it is found in the dictionary and returns true. 
          // Note there may be multiple elements with the same key value. 
          // Delete searches for the node with the same key and same d and returns true only if d as well as d.key match
          // Returns false if d not found in the dictionary.
          
     public abstract Dictionary Find(int k, boolean exact);
          // Searches for the key in the dictionary. 
          // If exact is true, then performs and exact match and returns an element of the dictionary with key = k
          // and return null if no such element exists.
          // If exact is false, performs an approximate search and
          // returns an element with key >= k in the dictionary.  Returns null in case no such element found.

     public abstract Dictionary getFirst(); 
     public abstract Dictionary getNext(); 

          // The getFirst and getNext functions are for traversal of the dictionary. 
          // The getFirst() returns the first element of the dictionary and null if the dictionary is empty
          // The getNext(d) returns the next element after d
          // The dictionary class does not define any order in which the elements of the dictionary are to be traversed. 
          // The only requirement is that using the following loop, getFirst() and getNext() should be able to 
          // traverse all the elements in the dictionary. 
          // count = 0; for (d = dict.getFirst(); d != null; d = d.getNext()) count = count + 1;
          // After the above loop, count should contain the total number of elements in the dictionary.

     public abstract boolean sanity();
          // Checks the sanity of the dictionary and returns true if sane, false otherwise
}