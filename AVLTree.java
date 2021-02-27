// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the AVLTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    public AVLTree Insert(int address, int size, int key) 
    // time ------> O(logn)
    // space ------> O(1)
    { 
        if (size <= 0) {
            return null;
        }

        else {
            AVLTree newNode = new AVLTree(address,size,key);
            newNode.height += 1;
            AVLTree curr = this;


            if (this.parent == null) {
                if (this.right == null) {
                    this.right = newNode;
                    newNode.parent = this;
                    return newNode;
                }

                else { 
                    curr = this.right;
                }

            }
            boolean leftGo = false;

            while (true) {
                leftGo = key < curr.key || (key == curr.key && address <= curr.address);
                if (leftGo) {
                    if (curr.left == null) {
                        break;
                    }
                    curr = curr.left;
                }  
                else {
                    if (curr.right == null) {
                        break;
                    }
                    curr = curr.right;
                } 
            }

            newNode.parent = curr;

            if (leftGo) {
                curr.left = newNode;
            }
            else {
                curr.right = newNode;
            }

            checkBalance(curr);
            return newNode;
        }
    }

    // height function that gives 0 for empty tree and this.height parameter for any non empty tree "this". 
    // time ------> O(1)
    // space ------> O(1)
    private int height(AVLTree node) {
        if (node == null) {
            return 0;
        }
        else {
            return node.height;
        }
    }

    // CheckBalance function maintains the height balanced property and perform desired rotations.
    // time ------> O(logn)
    // space ------> O(1)
    private void checkBalance(AVLTree node) {

        while (node.parent != null) {
            int lh = height(node.left);
            int rh = height(node.right);
            node.height = 1 + Math.max(lh,rh);
            int llh = 0;
            int lrh = 0;
            int rlh = 0;
            int rrh = 0;
            if (lh != 0) {
                llh = height(node.left.left);
                lrh = height(node.left.right);
            }
            if (rh != 0) {
                rlh = height(node.right.left);
                rrh = height(node.right.right);
            }


            int hDiff = lh-rh;

            if (hDiff > 1) {
                int hDiffL = llh - lrh; 
                if (hDiffL >= 0){
                    node = rightRotate(node);
                }
                else {
                    node = leftRightRotate(node);
                }
            }

            else if (hDiff < -1) {
                int hDiffR = rrh - rlh;
                if (hDiffR >= 0){
                    node = leftRotate(node);
                }
                else {
                    node = rightLeftRotate(node);
                }
            }
            node = node.parent;
        }
    }

    // 4 rotation functions performing rotations as per their names.
    // time ------> O(1)
    // space ------> O(1)
    private AVLTree leftRotate(AVLTree node) {
        AVLTree right = node.right;
        AVLTree t1 = right.left;

        node.right = t1;
        if (t1!= null) {
            t1.parent = node;
        }

        right.left = node;
        right.parent = node.parent;
        if (node.parent.left == node) {
            node.parent.left = right;
        }
        else {
            node.parent.right = right;
        }
        node.parent = right;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        right.height = 1 + Math.max(height(right.left), height(right.right));


        return right;
    }

    private AVLTree rightRotate(AVLTree node) {
        AVLTree left = node.left;
        AVLTree t1 = left.right;


        node.left = t1;
        if (t1 != null ){
            t1.parent = node;
        }

        left.right = node;
        left.parent = node.parent;
        if (node.parent.left == node) {
            node.parent.left = left;
        }
        else {
            node.parent.right = left;
        }
        node.parent = left;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        left.height = 1 + Math.max(height(left.left), height(left.right));

        return left;

    }

    private AVLTree rightLeftRotate(AVLTree node) {
        node.right = rightRotate(node.right);

        return leftRotate(node);
    }

    private AVLTree leftRightRotate(AVLTree node){
        node.left = leftRotate(node.left);

        return rightRotate(node);
    }
// --------------------------------------------------------------------------------------------------


    public boolean Delete(Dictionary e)
    // time ------> O(logn)
    // space ------> O(logn)
    {
        AVLTree delNode = this.findDel(e.key);
        if (delNode == null) {
            return false;
        }

        else {
            if (delNode.parent == null) {
                return false;
            }

            else {
                if (delNode.address == e.address && delNode.size == e.size) {
                    delNode.Delete();
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
                        if (r == true) {return true;}
                        return delNode.left.Delete(e);
                    }
                }
            }
        }
    }

    // helper function that delete the given node maitaining BST and height properties.
    // time ------> O(logn)
    // space ------> O(1)
    private void Delete() {

        AVLTree parent = this.parent;

        // one or both child null
        if (this.left == null || this.right == null) {

            if (parent.right == this) {
                if (this.left == null) {
                    parent.right = this.right;
                    if (this.right != null){this.right.parent = parent;}
                }
                else {
                    parent.right = this.left;
                    this.left.parent = parent;

                }
            }
            else {
                if (this.left == null) {
                    parent.left = this.right;
                    if (this.right != null){this.right.parent = parent;}
                }
                else {
                    parent.left = this.left;
                    this.left.parent = parent;
                }
            }

            parent.height = 1 + Math.max(height(this.parent.left),height(this.parent.right));
            checkBalance(parent);

        }

        // no child null-- delete the successor
        else {
            AVLTree succ = this.getNext();
            AVLTree temp = new AVLTree(succ.address,succ.size,succ.key);
            if (this.parent.left == this) {
                this.parent.left = temp;
            }

            else {
                this.parent.right = temp;
            }
            temp.parent = this.parent;
            if (this.left != null) {this.left.parent = temp;}
            temp.left = this.left;
            if (this.right != null) {this.right.parent = temp;}
            temp.right = this.right; 
            temp.height = this.height;

            succ.Delete();
        }
    }

    // helper find function that gives the first fit key.
    // time ------> O(logn)
    // space ------> O(logn)
    private AVLTree findDel(int key) {
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

// --------------------------------------------------------------------------------
        
    public AVLTree Find(int key, boolean exact)
    // time ------> O(logn)
    // space ------> O(logn)
    { 
        if (exact == true) {
            AVLTree curr = this;

            // this is sentinal
            if (this.parent == null){

                // empty tree
                if (this.right == null) {
                    return null;
                }
            }

            // not sentinal
            else {
                while (curr.parent.parent != null) {
                    if (curr.key == key) {
                        return curr;
                    }
                    curr = curr.parent;
                }
            }

            AVLTree root = FindRec(curr,key,true); 
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

                AVLTree curr = this;
                while (curr.parent.parent != null) {
                    curr = curr.parent;
                }

                return FindRec(curr,key,false);
            }

        }
    }

    // helper function that find the node with key = "key" (for exact = true) or smallest key >= "key" (for exact = false) in subtree rooted at root.
    // time ------> O(logn)
    // space ------> O(logn)
    private AVLTree FindRec(AVLTree root, int key, boolean exact) {
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
            AVLTree curr = root;
            AVLTree found = null;
            while (true) {
                if (curr.key == key) {
                    found = curr;
                    if (curr.left == null) {break;}
                    curr = curr.left;
                }
                else if (curr.key > key) {
                    if (curr.left == null) {break;}
                    curr = curr.left;
                }
                else {
                    if (curr.right == null) {break;}
                    curr = curr.right;
                }
            }
            if (found != null) {
                return found;
            }
            else {
                if (curr.key > key) {
                    return curr;
                }
                else {
                    while (curr != null && curr.key < key) {
                        curr = curr.parent;
                    }
                    return curr;
                }
            }
        }
    }

// --------------------------------------------------------------------------------------

    public AVLTree getFirst()
    // time ------> O(logn)
    // space ------> O(1)
    { 
        AVLTree curr = this;
        if (curr.parent == null) {
            if (curr.right == null) {
                return null;
            }
            curr =  curr.right;
        }

        else if (curr.parent.parent != null) {
            while (curr.parent.parent != null) {
                curr = curr.parent;
            }
        }

        while (curr.left != null) {
            curr = curr.left;
        }

        return curr;
    }

// ------------------------------------------------------------------------------------------------------

    public AVLTree getNext()
    // time ------> O(logn)
    // space ------> O(1)
    {
        if (this.parent == null) {
            return null;
        }

        else if (this.right == null) {
            
            if (this.parent.parent == null) {
                return null;
            }

            else {
                AVLTree curr = this;
                AVLTree parent = this.parent;
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
            AVLTree curr = this.right;
            while (curr.left != null) {
                curr = curr.left;
            }

            return curr;
        }
    }

// -------------------------------------------------------------------------------------------------

    public boolean sanity()
    // time ------> O(n)
    // space ------> O(n)
    { 
        // reaching to sentinal node

        AVLTree curr = this;
        if (this.parent != null) {
            while (curr.parent != null) {
                // cycle formation
                if ((curr.left != null && curr.left.parent != curr) || (curr.right != null && curr.right.parent != curr)) {
                    return false;
                }
                // height imbalance
                if (Math.abs(height(curr.right) - height(curr.left)) > 1 || curr.height != (Math.max(height(curr.right), height(curr.left)) + 1)) {
                    return false;
                }
                curr = curr.parent;
            }
        }

        // checking parent and left child of sentinal is null

        if (curr.key != -1 || curr.parent != null || curr.left != null) {
            return false;
        }

        // checking both child of every node have itself as parent and BST and height property

        if (sanityRec(curr, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE) == false) {
            return false;
        } 

        return true;
    }

    // helper function that checks sanity for certain conditions recursively (properties that are required to be checked reursively)
    // time ------> O(n)
    // space ------> O(n)
    private boolean sanityRec(AVLTree curr, int minK, int maxK, int minA, int maxA){

        if (curr == null) {
            return true;
        }

        // height imbalance
        if (curr.parent != null && (Math.abs(height(curr.right) - height(curr.left)) > 1 || curr.height != (Math.max(height(curr.right), height(curr.left)) + 1))) {
            return false;
        }

        // parent child relation faulty
        if ((curr.left != null && curr.left.parent != curr) || (curr.right != null && curr.right.parent != curr) ) {
            return false;
        }

        // BST property violation
        if (curr.key > maxK || curr.key < minK ) {
            return false;
        }
        if ((curr.key == minK && curr.address <= minA) || (curr.key == maxK && curr.address > maxA)) {
            return false;
        }

        return sanityRec(curr.left, minK, curr.key, minA, curr.address) && sanityRec(curr.right, curr.key, maxK, curr.address, maxA);

    }
}


