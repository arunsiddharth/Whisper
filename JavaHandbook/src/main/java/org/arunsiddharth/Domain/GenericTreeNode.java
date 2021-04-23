package org.arunsiddharth.Domain;

import java.util.ArrayList;

public class GenericTreeNode {
    public int id;
    public GenericTreeNode parent;
    public ArrayList<GenericTreeNode> children;
    public static GenericTreeNode of(int id, GenericTreeNode parent){
        GenericTreeNode node = new GenericTreeNode();
        node.id = id;
        node.parent = parent;
        node.children = new ArrayList<>();
        return node;
    }
}
