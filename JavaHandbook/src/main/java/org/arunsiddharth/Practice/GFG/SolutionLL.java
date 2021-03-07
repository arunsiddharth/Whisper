package org.arunsiddharth.Practice.GFG;

class Node {
    int data;
    Node next;
    Node(int data){
        this.data = data;
        this.next = null;
    }
}

class LinkedList {

    //Important to track head as global here as can't pass and modify it within function itself
    Node head;
    
    public void add(int data) {
        Node node = new Node(data);
        
        if(head==null){
            head=node;
        } else {
            Node p = head;
            while(p.next!=null){
                p=p.next;
            }
            p.next=head;
        }
    }

    public void addFirst(int data){
        Node node = new Node(data);
        node.next = head;
        head=node;
    }

    public void printList(){
        Node p = head;
        while(p!=null){
            System.out.print(p.data + " ");
            p=p.next;
        }
    }

    public void reverse(){
        Node current = head;
        Node prev = null;
        Node next = null;
        while(current!=null){
            next = current.next;
            current.next = prev;

            prev = current;
            current = next;
        }
        head = prev;
    }

    public Node reverseRecursive(Node head) {
        if(head == null || head.next == null){
            return head;
        }
        Node rest = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return rest;
    }

    public void reverseUsingTwoPointers(){
        Node current = head;
        Node next = null;
        while(current.next!=null){
            next = current.next;
            current.next = next.next;
            next.next = head;
            head = next;
        }
    }

    public void rotateCounterClockwise(int k){
        Node current,kthNode;
        current = head;
        
        k = k%this.length();
        if(k==0)return;

        int count=1;
        while(count < k){
            count++;
            current = current.next;
        }
        kthNode = current;
        while(current.next!=null){
            current = current.next;
        }
        current.next=head;
        head = kthNode.next;
        kthNode.next=null;
    }

    public int length() { 
        int result=0;
        Node p = head;
        while(p!=null){
            p=p.next;
            result++;
        }
        return result;
    }
    public static Node reverseInGroupK(Node head, int k)
    {
        Node current, next;
        current = head;
        int i=1;
        while(current.next!=null && i<k){
            next = current.next;
            current.next=next.next;
            next.next=head;
            head=next;
            i++;
        }
        if(current.next!=null)current.next=reverseInGroupK(current.next, k);
        return head;
    }
}
public class SolutionLL {
    
    Node segregate(Node head)
    {
       // add your code here
       Node one,two,zero;
       zero=one=two=null;
       while(head!=null){
           Node node = new Node(head.data);
           if(head.data==0){
               node.next=zero;
               zero=node;
           } else if(head.data==1){
               node.next=one;
               one=node;
           } else {
               node.next=two;
               two=node;
           }
           head=head.next;
       }
       Node tmp=zero;
       while(tmp!=null && tmp.next!=null)tmp=tmp.next;
       if(tmp!=null)tmp.next=one;
       if(one!=null)tmp=one;
       while(tmp!=null && tmp.next!=null)tmp=tmp.next;
       if(tmp!=null)tmp.next=two;
       if(two!=null)tmp=two;
       return zero!=null?zero:one!=null?one:two;
    }

    public static Node reverse(Node head, int k)
    {
        Node current, next;
        current = head;
        int i=1;
        while(current.next!=null && i<k){
            next = current.next;
            current.next=next.next;
            next.next=head;
            head=next;
            i++;
        }
        if(current.next!=null)current.next=reverse(current.next, k);
        return head;
    }
    static Node reverseList(Node head){
        Node curr, next;
        curr = head;
        while(curr.next!=null){
            next = curr.next;
            curr.next = next.next;
            next.next = head;
            head = next;
        }
        return head;
    }

    static Node addLists(Node first, Node second){
        // code here
        // return head of sum list
        int sum,carry;
        carry=0;
        Node result = null;
        Node tmp=null;
        first = reverseList(first);
        second = reverseList(second);
        while(first!=null||second!=null){
            sum = (first!=null?first.data:0) + (second!=null?second.data:0) + carry;
            carry = sum/10;
            sum%=10;
            Node node = new Node(sum);
            if(result==null){
                result=node;
            }
            else{
                tmp.next=node;
            }
            tmp=node;
            if(first!=null)first = first.next;
            if(second!=null)second= second.next;
        }
        if(carry != 0){
            Node node = new Node(carry);
            tmp.next=node;
        }
        return reverseList(result);
    }

    int intersectPoint(Node headA, Node headB)
	{
         Node h1,h2;
         h1=headA;
         h2=headB;

         //Exhaust smaller list
         while(h1!=null && h2!=null){
             h1=h1.next;
             h2=h2.next;
         }

         //Put longer list in exhausted list
         if(h1==null){
             h1=headB;
         } else{
             h2=headA;
         }

         //Exhaust remaining elements of large list
         while(h1!=null&&h2!=null){
             h1=h1.next;
             h2=h2.next;
         }

         //Set smaller list in previous exhausted list
         if(h1==null){
             h1=headB;
         } else{
             h2=headA;
         }

         //traverse both list
         while(h1!=null&&h2!=null){
             if(h1==h2)return h1.data;
             h1=h1.next;
             h2=h2.next;
         }
         return -1;
    }
    

    public static boolean detectLoop(Node head){
        // Add code here
        Node fp,sp;
        fp=sp=head;
        while(fp!=null && fp.next!=null){
            fp=fp.next.next;
            sp=sp.next;
            if(fp==sp)return true;
        }
        return false;
    }

    public static void removeLoop(Node head){
        Node fp,sp;
        fp=sp=head;
        while(fp!=null && fp.next!=null){
            fp=fp.next.next;
            sp=sp.next;
            if(fp==sp)break;
        }
        if(fp!=sp)return;
        sp=head;
        while(sp!=fp){
            sp=sp.next;
            fp=fp.next;
        }
        //Important corner case: Whole LL is loop
        Node p = fp.next;
        while(p.next!=fp){
            p=p.next;
        }
        p.next=null;
    }

    int getNthFromLast(Node head, int n)
    {
    	// Your code here
    	int i=0;
    	Node p=head, result=head;
    	while(p!=null && i<n){
    	    p=p.next;
    	    i++;
    	}
    	if(p==null && i!=n)return -1;
    	while(p!=null){
    	    p=p.next;
    	    result=result.next;
    	}
    	return result.data;
    }

    Node sortedMerge(Node headA, Node headB) {
        // This is a "method-only" submission. 
        // You only need to complete this method
           Node result;
           if(headA==null)return headB;
           if(headB==null)return headA;
           if(headA.data<=headB.data){
               result=headA;
               result.next = sortedMerge(headA.next, headB);
           } else {
               result=headB;
               result.next = sortedMerge(headA, headB.next);
           }
           return result;
    }

    public Node pairwiseSwap(Node head)
    {
        // code here
        Node curr=head;
        if(curr==null || curr.next==null)return curr;
        Node next = curr.next;
        curr.next = pairwiseSwap(next.next);
        next.next = curr;
        return next;
    }

}