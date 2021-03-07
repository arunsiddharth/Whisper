package org.arunsiddharth.Domain;

/**
 * Node for single and doubly linked list
 */
public class Node {
    public int key,val;
    public Node prev, next;
    public Node(int key, int val){
        this.key = key;
        this.val = val;
        prev=next=null;
    }
    public Node(int val){
        this.val = val;
        next = null;
    }
}