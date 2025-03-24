package org.example;

public class TreeMap
{
    private SplayTree<KeyValuePair> tree;

    TreeMap()
    {
        tree = new SplayTree<KeyValuePair>();
    }

    public void insert(String key, String value)
    {
        KeyValuePair pair = new KeyValuePair(key, value);
        //System.out.println("Inserting: " + key + " = " + value);
        tree.insert(pair);
    }

    public String get(String key)
    {
        KeyValuePair search = new KeyValuePair(key, null);
        //System.out.println("Getting value for key: " + key);

        SplayTree<KeyValuePair>.Node foundNode = tree.searchNode(search);

        if(foundNode != null){
            //System.out.println("Found node with key: " + key);
            tree.splay(foundNode);
            return foundNode.data.getValue();
        } else {
            //System.out.println("Key not found: " + key);
            return null;
        }
    }

    public boolean delete(String key)
    {
        KeyValuePair search = new KeyValuePair(key, null);
        //System.out.println("Trying to delete key: " + key);

        SplayTree<KeyValuePair>.Node foundNode = tree.searchNode(search);

        if(foundNode != null){
            //System.out.println("Node found, deleting: " + key);
            return tree.delete(foundNode.data);
        } else{
            //System.out.println("Node not found, cannot delete: " + key);
            return false;
        }
        
    }
}