package org.arunsiddharth.Arrays;

class Node{
    int val;
    Node next;
    Node(int val){
        this.val=val;
    }
}

class LinkedList {
    Node head;

    public void add(int val){
        Node node=new Node(val);
        node.next=head;
        head=node;
    }

    public void reverse(){
        Node current, prev, next;
        prev=null;
        current=head;
        while(current!=null){
            next = current.next;
            current.next=prev;
            prev=current;
            current=next;
        }
        head=prev;
    }

    public void reverseUsingTwoPointers(){
        Node current, next;
        current=head;
        while(current.next!=null){
            next=current.next;
            current.next=next.next;
            next.next=head;
            head=next;
        }
    }

    public Node recursiveReverse(Node head){
        if(head==null || head.next==null)return head;
        Node rest = recursiveReverse(head.next);
        head.next.next=head;
        head.next=null;
        return rest;
    }

}


public class PracticeLinkedList {

    public static void main(String[] args){

    }
}
