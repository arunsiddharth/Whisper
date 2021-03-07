package org.arunsiddharth.LinkedList;

import org.arunsiddharth.Domain.Node;

public class DoublyLinkedList{
    private Node head;

    /**
     * Dummy tail to fetch last member easily
     */
    private Node tail;

    public DoublyLinkedList(){
        head = tail = new Node(0,0);
    }
    public void add(Node tmp){
        tmp.prev = null;
        tmp.next = head;
        head.prev = tmp;
        head=tmp;
    }
    public Node getFirst(){
        return (head!=tail)?head:null;
    }
    public Node getLast(){
        return tail.prev;
    }

    /**
     * Given a reference to node it will delete that node only
     */
    public void remove(Node tmp){
        if(tmp==head){
            head=tmp.next;
            tmp.next=null;
            head.prev=null;
        }
        else {
            Node prev = tmp.prev;
            Node next = tmp.next;
            prev.next = next;
            next.prev = prev;
            tmp.next=null;
            tmp.prev=null;
        }
    }
}
