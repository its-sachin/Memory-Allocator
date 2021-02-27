// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        A1List newNode = new A1List(address, size, key);

        A1List temp = this.next;

        this.next = newNode;
        newNode.prev = this;

        newNode.next = temp;
        temp.prev = newNode;

        return newNode;
    }

    public boolean Delete(Dictionary d) 
    {
        A1List delNode = this.Find(d.key, true);
        if (delNode == null) {
            return false;
        } 
        else {
            if (delNode.address == d.address && delNode.size == d.size) {
                delNode.prev.next = delNode.next;
                delNode.next.prev = delNode.prev;
                return true;
            }
            else {

            }
        }
    }

    public A1List Find(int k, boolean exact)
    { 
        A1List curr = this.getFirst();

        if (curr == null) {
            return null;
        }

        else if (exact == true) {
            while(curr.key != k) {
                if (curr.key == -1) {
                    return null;
                }
                curr = curr.next;
            }
            return curr;
        }
        else {
            while(curr.key < k) {
                if (curr.key == -1) {
                    return null;
                }
                curr = curr.next;
            }
            return curr;
        }
    }

    private A1List Find(A1List node, int k, boolean exact) {
        A1List curr = node.getFirst();

        if (curr == null) {
            return null;
        }

        else if (exact == true) {
            while(curr.key != k) {
                if (curr.key == -1) {
                    return null;
                }
                curr = curr.next;
            }
            return curr;
        }
        else {
            while(curr.key < k) {
                if (curr.key == -1) {
                    return null;
                }
                curr = curr.next;
            }
            return curr;
        }
    }

    public A1List getFirst()
    {
        A1List curr = this;
        if (curr.key == -1 && curr.prev == null) {
            if (curr.next.key == -1) {
                return null;
            }
            else {
                return curr.next;
            }
        }
        else {
            while (curr.prev.key != -1) {
            curr = curr.prev;
        }
        return curr;
        }
    }
    
    public A1List getNext() 
    {
        return this.next;
    }

    public boolean sanity()
    {
        // check circularity



        // check tail and head
        A1List currL = this;
        A1List currR = this;
        if (this.key == -1) {
          if (this.next != null && this.prev != null) {
            return false;
          }  
        }

        else {
            while (currL.key != -1) {
                currL = currL.prev;
            }
            while (currR.key != -1) {
                currR = currR.next;
            }

            if (currL.prev != null || currR.next != null) {
                return false;
            }
        }

        // check node.next.prev


     }

}



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

    public int Allocate(int blockSize) {
        found = this.freeBlk.Find(blockSize, false);
        if (found != null) {
            this.freeBlk.Delete(found);


            this.freeBlk.Insert(found.address + blockSize, found.size - blockSize, found.size - blockSize);

            this.allocBlk.Insert(found.address, blockSize,found.address);
            return found.address;
        }
        else {
            return -1;
        }
    } 
    
    public int Free(int startAddr) {
        Dictionary found = this.allocBlk.Find(startAddr, true);
        if (found == null) {
            return -1;
        }
        else {
            this.allocBlk.Delete(found);
            this.freeBlk.Insert(found.address, found.size, found.size);
            return 0;
        }
    }
}






































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

    public int Allocate(int blockSize) {
        Dictionary found = this.freeBlk.Find(blockSize, true);
        if (found != null) {
            this.freeBlk.Delete(found);
            Dictionary first = this.allocBlk.getFirst();

            if (first == null) {
                this.allocBlk.Insert(found.address, found.size, found.address);
                return found.address;
            }


            first.Insert(found.address, found.size, found.address);
            this.allocBlk.Delete(first);
            this.allocBlk.getFirst().Insert(first.address, first.size, first.address);
            return found.address;
        }
        else {
            found = this.freeBlk.Find(blockSize, false);
            if (found != null) {
                this.freeBlk.Delete(found);

                Dictionary firstFree = this.freeBlk.getFirst();

                if (firstFree == null) {
                    this.freeBlk.Insert(found.address + blockSize, found.size - blockSize, found.size - blockSize);
                }

                else {

                    firstFree.Insert(found.address + blockSize, found.size - blockSize, found.size - blockSize);
                    this.freeBlk.Delete(firstFree);
                    this.freeBlk.getFirst().Insert(firstFree.address, firstFree.size, firstFree.size);
                }

                Dictionary first = this.allocBlk.getFirst();

                if (first == null) {
                    this.allocBlk.Insert(found.address, blockSize, found.address);
                    return found.address;
                }


                first.Insert(found.address, blockSize, found.address);
                this.allocBlk.Delete(first);
                this.allocBlk.getFirst().Insert(first.address, first.size, first.address);
                return found.address;
            }
            else {
                return -1;
            }
        }
    } 
    
    public int Free(int startAddr) {
        Dictionary found = this.allocBlk.Find(startAddr, true);
        if (found == null) {
            return -1;
        }
        else {
            this.allocBlk.Delete(found);
            Dictionary first = this.freeBlk.getFirst();

            if (first == null) {
                this.freeBlk.Insert(found.address, found.size, found.size);
                return 0;
            }

            first.Insert(found.address, found.size, found.size);
            this.freeBlk.Delete(first);
            this.freeBlk.getFirst().Insert(first.address, first.size, first.size);

            return 0;
        }
    }
}



























// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    // time -----> O(1) 
    // space -----> O(1)
    {
        A1List newNode = new A1List(address, size, key);

        A1List temp = this.next;

        this.next = newNode;
        newNode.prev = this;

        newNode.next = temp;
        temp.prev = newNode;

        return newNode;
    }

    public boolean Delete(Dictionary d) 
    // time -----> O(n) 
    // space -----> O(1)
    {
        if (d.key != -1) {
            A1List delNode = this.Find(d.key, true);
            if (delNode == null) {
                return false;
            } 
            else {
                if (delNode.address == d.address && delNode.size == d.size) {
                    delNode.prev.next = delNode.next;
                    delNode.next.prev = delNode.prev;
                    return true;
                }
                else {
                    while (delNode.next.key != -1) {
                        delNode = this.Find(delNode.next, d.key, true);
                        if (delNode == null) {
                            return false;
                        }
                        else if (delNode.address == d.address && delNode.size == d.size) {
                            delNode.prev.next = delNode.next;
                            delNode.next.prev = delNode.prev;
                            return true;
                        }
                    }
                    return false;

                }
            }

        }

        else {
            return false;
        }
        
    }

    public A1List Find(int k, boolean exact)
    // time -----> O(n) 
    // space -----> O(1)
    { 
        A1List curr = this.getFirst();

        if (curr == null) {
            return null;
        }

        else {
            return Find(curr, k, exact);
        }
    }

    private A1List Find(A1List curr, int k, boolean exact)
    // time -----> O(n) 
    // space -----> O(1)
    {

        if (exact == true) {
            while(curr.key != k) {
                if (curr.key == -1) {
                    return null;
                }
                curr = curr.next;
            }
            return curr;
        }
        else {
            while(curr.key < k) {
                if (curr.key == -1) {
                    return null;
                }
                curr = curr.next;
            }
            return curr;
        }
    }

    public A1List getFirst()
    // time -----> O(n) 
    // space -----> O(1)
    {
        A1List curr = this;
        if (curr.key == -1 && curr.prev == null) {
            if (curr.next.key == -1) {
                return null;
            }
            else {
                return curr.next;
            }
        }
        else {
            while (curr.prev.key != -1) {
            curr = curr.prev;
        }
        return curr;
        }
    }
    
    public A1List getNext() 
    // time -----> O(1) 
    // space -----> O(1)
    {
        if (this.key == -1) {
            if (this.next == null) {
                return null;
            }

            else if (this.prev == null && this.next.key == -1) {
                return null;
            }
        }

        else if (this.next.key == -1) {
            return null;
        }

        return this.next;
    }

    public boolean sanity()
    // time -----> O(n^2) 
    // space -----> O(1)
    {

        // check tail and head
        A1List currL = this;
        A1List currR = this;

        if (this.key == -1) {
          if (this.next != null && this.prev != null) {
            return false;
          }  
        }

        else {
            while (currL.key != -1) {
                currL = currL.prev;
            }
            while (currR.key != -1) {
                currR = currR.next;
            }

            if (currL.prev != null || currR.next != null) {
                return false;
            }
        }

        // check circularity and node.next.prev
        if (this.key == -1) {
            if (this.prev == null && this.next.key != -1) {
                return checkSanityR(this.next);
            }
            else if (this.next == null && this.prev.key != -1) {
                return checkSanityL(this.prev);
            }
        }

        else {
            if (checkSanityR(this) == false || checkSanityL(this) == false) {
                return false;
            }
        }

        return true;

    }

    private boolean checkSanityR(A1List curr) 
    // time -----> O(n^2) 
    // space -----> O(1)
    {

        while (curr.next.key != -1) {
            A1List check = curr.next;

            while (check.next.key != -1) {
                if (check == curr) {
                    return false;
                }
                check = check.next;
            }


            if (curr.next.prev != curr) {
                return false;
            }

            curr = curr.next;
        }

        return true;
    }

    
    private boolean checkSanityL(A1List curr) 
    // time -----> O(n^2) 
    // space -----> O(1)
    {

        while (curr.prev.key != -1) {
            A1List check = curr.prev;

            while (check.prev.key != -1) {
                if (check == curr) {
                    return false;
                }
                check = check.prev;
            }

            if (curr.next.prev != curr) {
                return false;
            }
            curr = curr.prev;
        }

        return true;
    }

}


