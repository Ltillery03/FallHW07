import java.util.Collection;

/**
 * Your implementation of an AVL.
 *
 * @author Lesaiah Tillery
 * @version 1.0
 * @userid ltillery6
 * @GTID 903670542
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class AVL<T extends Comparable<? super T>> {

    // Do not add new instance variables or modify existing ones.
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }
    //============IMPLEMENT THESE METHODS FIRST===============
    //Balance the nodes, update the height/balance factors, methods to handle left/right rotations
    /**
     * Right rotation method
     * @param node node to be rotated
     * @return returns updated node
     */
    private AVLNode<T> rightRotate(AVLNode<T> node) {
        AVLNode<T> c1 = node.getLeft();
        AVLNode<T> c2 = c1.getRight();
        c1.setRight(node);
        node.setLeft(c2);
        updateHeightBF(node);
        updateHeightBF(c1);
        return c1;
    }
     /**
     * Left rotation method
     * @param node Node to be rotated
     * @return returns updated parent node
     */
    private AVLNode<T> leftRotate(AVLNode<T> node) {
        AVLNode<T> c1 = node.getRight();
        AVLNode<T> c2 = c1.getLeft();
        c1.setLeft(node);
        node.setRight(c2);
        updateHeightBF(node);
        updateHeightBF(c1);
        return c1;
    }

    private void updateHeightBF(AVLNode<T> node) {
        if (node == null) {
            return;
        }
        if (node.getLeft() == null && node.getRight()== null) {
            node.setHeight(0);
            node.setBalanceFactor(0);
            return;
        }
        node.setHeight(Math.max(node.getLeft().getHeight(), node.getRight().getHeight()) + 1);
        node.setBalanceFactor(node.getLeft().getHeight() - node.getRight().getHeight());
    }
    private AVLNode<T> balance(AVLNode<T> node) {
        if (node == null) {
            return null;
        }
        updateHeightBF(node);
        if (node.getBalanceFactor() > 1) { // left heavy
            if (node.getLeft().getBalanceFactor() < 0) {
                node.setLeft(leftRotate(node.getLeft()));
            }
            node = rightRotate(node);
        } else if (node.getBalanceFactor() < -1) { // right heavy
            if (node.getRight().getBalanceFactor() > 0) {
                node.setRight(rightRotate(node.getRight()));
            }
            node = leftRotate(node);
        }
        return node;
    }
    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null");
        }
        for (T item : data) {
            if (item == null) {
                throw new java.lang.IllegalArgumentException("Element is null");
            }
            add(item);
        }
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null");
        }
        root = reAdd(root, data);
    }
    
    /**
     * Method to recursively add data to BST
     * @param curr current node
     * @param data data to be added
     */
    private AVLNode<T> reAdd(AVLNode<T> curr, T data) {
        if (curr == null) {
            size++;
            return new AVLNode<T>(data);
        } else {
            if (curr.getData().compareTo(data) > 0) {
                curr.setLeft(reAdd(curr.getLeft(), data));
            } else if (curr.getData().compareTo(data) < 0 ) {
                curr.setRight(reAdd(curr.getRight(), data));
            } 
        }
        return balance(curr);
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data, NOT successor. As a reminder, rotations can occur
     * after removing the predecessor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null");
        }
        
    }

    ==========+=============SWITCH THE TWO BELOW METHODS TO AVL STUFF=============================
    private AVLNode<T> reRem(AVLNode<T> node, T data, AVLNode<T> dummyNode) {
        if (node == null) {
            throw new java.util.NoSuchElementException("Data is not in tree");
        } else if (node.getData().compareTo(data) > 0) {
            node.setLeft(reRem(node.getLeft(), data, dummyNode));
        } else if (node.getData().compareTo(data) < 0) {
            node.setRight(reRem(node.getRight(), data, dummyNode));
        } else {
            dummyNode.setData(node.getData());
            size--;
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                AVLNode<T> dNode = new AVLNode<T>(null);
                node.setRight(findSuccessor(node.getRight(), dNode));
                node.setData(dNode.getData());
            }
        }
        return node;
    }
    /**
     * Method that finds Successor
     * @param node current node
     * @param successor node to contain successor data
     * @return node 
     */
    private AVLNode<T> findSuccessor(AVLNode<T> node, AVLNode<T> successor) {
        if (node.getLeft() == null) {
            successor.setData(node.getData());
            return node.getRight();
        }
        node.setLeft(findSuccessor(node.getLeft(), successor));
        return node;
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null");
        }
        return reGet(root, data);
    }
    private T reGet(AVLNode<T> curr, T data) {
        if (curr.getData().compareTo(data) > 0) {
            return reGet(curr.getLeft(), data);
        } else if (curr.getData().compareTo(data) < 0) {
            return reGet(curr.getRight(), data);
        } else if (curr.getData().compareTo(data) == 0) {
            return curr.getData();
        }
        throw new java.util.NoSuchElementException();
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null");
        }
        return reCont(root, data);
    }
    private boolean reCont(AVLNode<T> curr, T data) {
        if (curr.getData().compareTo(data) > 0) {
            return reCont(curr.getLeft(), data);
        } else if (curr.getData().compareTo(data) < 0) {
            return reCont(curr.getRight(), data);
        } else if (curr.getData().compareTo(data) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * Should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (size == 0) {
            return -1;
        }
        return root.getHeight();
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * The predecessor is the largest node that is smaller than the current data.
     *
     * Should be recursive.
     *
     * This method should retrieve (but not remove) the predecessor of the data
     * passed in. There are 2 cases to consider:
     * 1: The left subtree is non-empty. In this case, the predecessor is the
     * rightmost node of the left subtree.
     * 2: The left subtree is empty. In this case, the predecessor is the lowest
     * ancestor of the node containing data whose right child is also
     * an ancestor of data.
     *
     * This should NOT be used in the remove method.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *     76
     *   /    \
     * 34      90
     *  \    /
     *  40  81
     * predecessor(76) should return 40
     * predecessor(81) should return 76
     *
     * @param data the data to find the predecessor of
     * @return the predecessor of data. If there is no smaller data than the
     * one given, return null.
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T predecessor(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null");
        }
        return rePred(root, data);
    }
    private T rePred(AVLNode<T> curr, T data) {
        if (curr.getData().compareTo(data) > 0) {
            return rePred(curr.getLeft(), data);
        } else if (curr.getData().compareTo(data) < 0) {
            return rePred(curr.getRight(), data);
        } 
        if (curr.getData().compareTo(data) == 0) {
            if (curr.getLeft() != null) {
                AVLNode<T> node = curr.getLeft();
                while (node.getRight() != null) {
                    node = node.getRight();
                }
                return node.getData();
            }
            return root.getData();
            
        }
        throw new java.util.NoSuchElementException("Data is not in tree");
    }

    /**
     * Returns the data in the deepest node. If there is more than one node
     * with the same deepest depth, return the rightmost (i.e. largest) node with
     * the deepest depth.
     *
     * Should be recursive.
     *
     * Must run in O(log n) for all cases.
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      3
     *        \
     *         1
     * Max Deepest Node:
     * 1 because it is the deepest node
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      4
     *        \    /
     *         1  3
     * Max Deepest Node:
     * 3 because it is the maximum deepest node (1 has the same depth but 3 > 1)
     *
     * @return the data in the maximum deepest node or null if the tree is empty
     */
    public T maxDeepestNode() {
        if (size == 0) {
            return null;
        }
        return reDN(root);
    }
    private T reDN(AVLNode<T> curr) {
        if (curr.getLeft().getHeight() > curr.getRight().getHeight()) {
            return reDN(curr.getLeft());
        } else if (curr.getLeft().getHeight() < curr.getRight().getHeight()) {
            return reDN(curr.getRight());
        } else if (curr.getRight().getHeight() == curr.getLeft().getHeight()) {
            return reDN(curr.getRight());
        }

        return curr.getData();
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
