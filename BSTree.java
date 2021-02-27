// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    private boolean isEmpty() {
        if (this.parent == null && this.left == null && this.right == null) {
            return true;
        }

        return false;
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        if (size <= 0){
            return null;
        }

        BSTree newNode = new BSTree(address, size, key);

        // empty tree
        if (this.isEmpty()) {
            this.right = newNode;
            newNode.parent = this;
            return newNode;
        }


        // not empty
        else {

            // at sentinal node
            if (this.parent == null) {
                return this.right.Insert(address,size,key);
            }

            // right insert acc to key
            else if (key > this.key){
                if (this.right == null) {
                    this.right = newNode;
                    newNode.parent = this;
                    return newNode;
                }
                else {
                    return this.right.Insert(address,size,key);
                }
            }


            // keys are same
            else if (this.key == key) {

                // right insert acc to address
                if (this.address < address) {
                    if (this.right == null) {
                        this.right = newNode;
                        newNode.parent = this;
                        return newNode;
                    }
                    else if(this.address == address) {
                        return this;
                    }
                    else {
                        return this.right.Insert(address,size,key);
                    }

                }

                // left insert acc to address
                else {
                    if (this.left == null) {
                        this.left = newNode;
                        newNode.parent = this;
                        return newNode;
                    }
                    else {
                        return this.left.Insert(address,size,key);
                    }

                }
            }

            // left insert acc to key
            else {
                if (this.left == null) {
                    this.left = newNode;
                    newNode.parent = this;
                    return newNode;
                }
                else {
                    return this.left.Insert(address,size,key);
                }
            }

        }
        
    }

    public boolean Delete(Dictionary e)
    { 
        BSTree delNode = this.findDel(e.key);
        if (delNode == null) {
            return false;
        }

        else {
            if (delNode.parent == null) {
                return false;
            }

            else {
                if (delNode.address == e.address && delNode.size == e.size) {
                    BSTree temp = delNode;
                    delNode.Delete();
                    delNode.address = temp.address;
                    delNode.size = temp.size;
                    delNode.key = temp.key;
                    return true;
                }

                else {

                    if (delNode.left == null) {
                        if (delNode.right == null) {
                            return false;
                        }
                        return delNode.right.Delete(e);
                    }
                    else if (delNode.right == null) {
                        return delNode.left.Delete(e);    
                    }

                    else {
                        boolean r = delNode.right.Delete(e);
                        boolean l = delNode.left.Delete(e);

                        return r || l;
                    }
                }
            }
        }
    }

    private void Delete() {

        BSTree parent = this.parent;


        if (this.left == null) {

            // both left right null
            if (this.right == null){
                if (parent.right == this) {
                    parent.right = null;
                }
                else {
                    parent.left = null;
                }
            }

            // only left null
            else {
                this.right.parent = parent;
                if (parent.right == this) {
                    parent.right = this.right;
                }
                else {
                    parent.left = this.right;
                }
            }
            
        }


        else {

            // only right null
            if (this.right == null) {
                this.left.parent = parent;
                if (parent.right == this) {
                    parent.right = this.left;
                }
                else {
                    parent.left = this.left;
                }
            }

            // no child null
            else {
                BSTree succ = this.getNext();
                BSTree temp = new BSTree(succ.address,succ.size,succ.key);
                temp.parent = this.parent;
                temp.left = this.left;
                temp.right = this.right; 
                // this.address = succ.address;
                // this.size = succ.size;
                // this.key = succ.key;

                succ.Delete();
            }

        }
    }

    private BSTree findDel(int key) {
        if (this.parent == null) {
            if (this.right == null) {
                return null;
            }

            else {
                return FindRec(this.right, key,true);
            }
        }

        else {
            return FindRec(this,key,true);
        }

    }
        
    public BSTree Find(int key, boolean exact)
    { 
        if (exact == true) {

            // this is sentinal
            if (this.parent == null){

                // empty tree
                if (this.right == null) {
                    return null;
                }

                // not empty
                else {
                    BSTree root = FindRec(this.right,key,true); 
                    if (root == null) {
                        return null;
                    }

                    if (root.left == null) {
                        return root;
                    }

                    else if (FindRec(root.left,key,true) == null) {
                        return root;
                    }

                    else {
                        return FindRec(root.left,key,true);
                    }
                }
            }

            // not sentinal
            else {

                BSTree curr = this;
                while (curr.parent.parent != null) {
                    if (curr.key == key) {
                        return curr;
                    }
                    curr = curr.parent;
                }

                BSTree root = FindRec(curr,key,true); 
                if (root == null) {
                    return null;
                }

                if (root.left == null) {
                    return root;
                }

                else if (FindRec(root.left,key,true) == null) {
                    return root;
                }

                else {
                    return FindRec(root.left,key,true);
                }
            }
        }

        else {
            // this is sentinal
            if (this.parent == null){

                // empty tree
                if (this.right == null) {
                    return null;
                }

                // not empty
                else {
                    return FindRec(this.right,key,false); 
                }
            }

            // not sentinal
            else {

                BSTree curr = this;
                while (curr.parent.parent != null) {
                    curr = curr.parent;
                }

                return FindRec(curr,key,false);
            }

        }
    }

    private BSTree FindRec(BSTree root, int key, boolean exact) {
        if (exact == true) {
            if (root.key == key) {
                return root;                
            }

            else if (root.key > key) {
                if (root.left == null) {
                    return null;
                }

                else {
                    return FindRec(root.left,key,true);
                }
            }

            else {
                if (root.right == null) {
                    return null;
                }

                else {
                    return FindRec(root.right,key,true);
                }
            }
        }

        else {
            if (root.key >= key) {
                if (root.left == null) {
                    return root;
                }

                else {
                    if (root.left.key >= key) {
                        return FindRec(root.left,key,false);
                    }

                    else {
                        return root;
                    }
                }
            }

            else {
                if (root.right == null) {
                    return null;
                }

                else {
                    return FindRec(root.right,key,false);
                }
            }
        }
    }

    public BSTree getFirst()
    { 
        if (this.parent == null) {
            if (this.right == null) {
                return null;
            }
            return this.right.getFirst();
        }

        else if (this.parent.parent == null) {
            BSTree curr = this;
            while (curr.left != null) {
                curr = curr.left;
            }

            return curr;
        }

        else {
            BSTree curr = this;
            while (curr.parent != null) {
                curr = curr.parent;
            }
            return curr.getFirst();
        }
    }

    public BSTree getNext()
    { 
        if (this.parent == null) {
            return null;
        }

        else if (this.right == null) {
            
            if (this.parent.parent == null) {
                return null;
            }

            else {
                BSTree curr = this;
                BSTree parent = this.parent;
                while (curr == parent.right) {
                    curr = parent;
                    parent = parent.parent;

                    if (parent.parent == null) {
                        return null;
                    }
                }

                return parent;
            }
        }

        else {
            BSTree curr = this.right;
            while (curr.left != null) {
                curr = curr.left;
            }

            return curr;
        }
    }

    public boolean sanity()
    { 

        // reaching to sentinal node

        BSTree curr = this;
        if (this.key != -1) {
            while (curr.parent.key != -1) {
                if (curr.left != null && curr.left.parent != curr) {
                    return false;
                }
                if (curr.right != null && curr.right.parent != curr) {
                    return false;
                }
                curr = curr.parent;
            }
        }

        // checking parent and left child of sentinal is null

        if (curr.parent != null || curr.left != null) {
            // System.out.println("hey1");
            return false;
        }

        // checking both child of every node have itself as parent

        if (curr.sanityRec() == false) {
            // System.out.println("hey2");
            return false;
        } 

        curr = curr.getFirst();
        while (curr.getNext() != null) {
            if (curr.key > curr.getNext().key || (curr.key == curr.getNext().key && curr.address > curr.getNext().address)) {
                return false;
            }

            curr = curr.getNext();
        }

        return true;
    }

    private boolean sanityRec(){

        if (this.left == null) {
            if (this.right == null) {
                return true;
            }

            else if (this.right.parent != this) {
                return false;
            }
            else if (this.right.key < this.key || (this.right.key == this.key && this.right.address < this.address)) {
                return false;
            }

            else {
                return this.right.sanityRec();
            }
        }

        else {
            if (this.right == null) {
                if (this.left.parent != this) {
                    return false;
                }

                else if (this.left.key > this.key || (this.left.key == this.key && this.left.address > this.address)) {
                    return false;
                }

                else {
                    return this.left.sanityRec();
                }
            }

            else {
                if (this.left.parent != this || this.right.parent != this) {
                    return false;
                }

                else if (this.left.key > this.key || (this.left.key == this.key && this.left.address > this.address) || this.right.key < this.key || (this.right.key == this.key && this.right.address < this.address)){
                    return false;
                }

                else {
                    return this.left.sanityRec() && this.right.sanityRec();
                }

            }
        }


    }

}


 


