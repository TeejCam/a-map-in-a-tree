package org.example;

public class SplayTree<T extends Comparable<T>>
{
    class Node
    {
        Node left, right, parent;
        T data;

        public Node(T data, Node parent)
        {
            this.data = data;
            left = right = null;
            this.parent = parent;
        }
    }

    Node root; 

    public Node searchNode(T value)
    {
        Node current = root;
        while (current != null){
            int cmp = value.compareTo(current.data);
            if(cmp < 0){
                current = current.left;
            } else if(cmp > 0){
                current = current.right;
            } else {
                splay(current);
                return current;
            }
        }
        return null;
    }

    public boolean contains(T value)
    {
        return contains(root, value);
    }

    public boolean contains(Node node, T value)
    {
        if(node == null) return false;

        int cmp = value.compareTo(node.data);
        if(cmp < 0) return contains(node.left, value);
        if(cmp > 0) return contains(node.right, value);

        return true;
    }

    public boolean insert(T value)
    {
        if(value == null) return false;
        if(!contains(root, value)){
            root = insert(root, value, null);
            splay(root);
            return true;
        }
        return false;
    }

    private Node insert(Node currentLocation, T value, Node parent)
    {
        if(currentLocation == null) return new Node(value, parent);

        int cmp = value.compareTo(currentLocation.data);

        if(cmp < 0){
            currentLocation.left = insert(currentLocation.left, value, currentLocation);
        } else{
            currentLocation.right = insert(currentLocation.right, value, currentLocation);
        }

        return currentLocation;
    }

    public boolean delete(T elem)
    {
        if(elem == null) return false;

        if(contains(root, elem)){
            root = delete(root, elem);
            if(root != null && root.parent == null){
                splay(root);
            }
            return true;
        }
        return false;
    }

    private Node delete(Node node, T elem)
    {
        if(node == null) return null;

        int cmp = elem.compareTo(node.data);

        if(cmp < 0){
            node.left = delete(node.left, elem);
        } else if(cmp > 0){
            node.right = delete(node.right, elem);
        } else{
            if(node.left == null){
                return node.right;
            } else if(node.right == null){
                return node.left;
            } else{
                Node successor = findMin(node.right);
                node.data = successor.data;
                node.right = delete(node.right, successor.data);
            }
        }
        return node;
    }

    private Node findMin(Node node)
    {
        while(node.left != null){
            node = node.left;
        }
        return node;
    }

    public void splay(Node node)
    {
        while(node.parent != null){
            if(node.parent == root){
                if(node == node.parent.left) rightRotation(node.parent);
                else leftRotation(node.parent);
            } else{
                Node parent = node.parent;
                Node grand = parent.parent;

                if(grand != null){
                    if((node == parent.left) && (parent == grand.left)){
                        rightRotation(grand);
                        rightRotation(parent);
                    } else if((node == parent.right) && (parent == grand.right)){
                        leftRotation(grand);
                        leftRotation(parent);
                    } else if((node == parent.left) && (parent == grand.right)){
                        rightRotation(parent);
                        leftRotation(grand);
                    } else{
                        leftRotation(parent);
                        rightRotation(grand);
                    }
                }
            }
        }
    }

    private void rightRotation(Node node)
    {
        Node leftChild = node.left;
        node.left = leftChild.right;
        if(leftChild.right != null) leftChild.right.parent = null;
        leftChild.parent = node.parent;

        if(node.parent == null) root = leftChild;
        else if(node == node.parent.right) node.parent.right = leftChild;
        else node.parent.left = leftChild;

        leftChild.right = node;
        node.parent = leftChild;
    }

    private void leftRotation(Node node)
    {
        Node rightChild = node.right;
        node.right = rightChild.left;
        if(rightChild.left != null) rightChild.left.parent = node;
        rightChild.parent = node.parent;

        if(node.parent == null) root = rightChild;
        else if(node == node.parent.left) node.parent.left = rightChild;
        else node.parent.right = rightChild;

        rightChild.left = node;
        node.parent = rightChild;
    }

}