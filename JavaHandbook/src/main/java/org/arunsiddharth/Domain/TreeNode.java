package org.arunsiddharth.Domain;

public class TreeNode {
    public int val;
    public int hd,vd;//horizontal and vertical distance
    public TreeNode left,right;
    public TreeNode(int data){
        val = data;
        hd=vd=0;
        left=right=null;
    }
}
