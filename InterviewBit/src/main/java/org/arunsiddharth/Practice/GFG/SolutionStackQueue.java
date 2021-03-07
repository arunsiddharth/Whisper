package org.arunsiddharth.Practice.GFG;

import java.util.*;
import org.arunsiddharth.LinkedList.DoublyLinkedList;
import org.arunsiddharth.Domain.Node;
import org.arunsiddharth.Domain.Pair;

public class SolutionStackQueue {
    public int[] nextGreaterElement(int[] arr){
        int n = arr.length;
        int[] result = new int[n];
		Deque<Integer> st = new ArrayDeque<>();
		for(int i=0;i<n;i++){
		    int val = arr[i];
		    while(!st.isEmpty() && arr[st.peek()]<val){
		        result[st.peek()]=val;
		        st.pop();
		    }
		    st.push(i);
		}
		while(!st.isEmpty()){
		    result[st.peek()]=-1;
		    st.pop();
        }
        return result;
    }

    /**
     * Keep track of count(array traversed), td and tp 
     * while td<=tp keep increasing count and j(array traversed for given i)
     * if td>tp increament i(starting point)
     * else return starting point(i)
     */
    int tour(int petrol[], int distance[]){
	    int i,j,n=petrol.length,tp,td,count=0;
	    i=j=tp=td=0;
	    for(i=0;i<n;i++){
	        while(count<n && td<=tp){
	            td+=distance[j];
	            tp+=petrol[j];
	            j=(j+1)%n;
	            count++;
	        }
	        if(td>tp){
	            td-=distance[i];
	            tp-=petrol[i];
	            count--;
	        } else {
	            break;
	        }
	    }
	    return (i==n)?-1:i;
    }

    /**
     * Traverse arr and for element i
     *  keep popping elements from deque end which are less than i
     *  push i
     * Keep track of elements to remove from deque front for next window
     * @param arr
     * @param k
     * @return
     */
    public int[] maximumOfAllSubarraysOfSize(int[] arr, int k){
        int n=arr.length,i;
        k=Math.min(k,n);
        
        int[] result = new int[n-k+1];
        if(k==0)return result;//throw Exception here
        Deque<Integer> deque = new ArrayDeque<>();
        
        for(i=0;i<k;i++){
            while(!deque.isEmpty() && arr[deque.peekLast()]<=arr[i])deque.removeLast();
            deque.addLast(i);
        }
        for(;i<n;i++){
            result[i-k]=arr[deque.peekFirst()];
            while(!deque.isEmpty() && deque.peekFirst()<=i-k)deque.removeFirst();
            while(!deque.isEmpty() && arr[deque.peekLast()]<=arr[i])deque.removeLast();
            deque.addLast(i);
        }
        result[i-k]=arr[deque.peekFirst()];
        return result;
    }
    /**
     * 2->Rot, 1->Fresh, 0->Empty
     * 
     */
	public static int timeToRotOranges(int[][] grid){
	    int m,n;
	    m=grid.length;
	    n=grid[0].length;
	    int time=0;
	    int size=0;
	    Deque<Pair> deque = new ArrayDeque<>();
	    Set<Pair> visited = new HashSet<>();
	    for(int i=0;i<m;i++){
	        for(int j=0;j<n;j++){
	            if(grid[i][j]==2){
	                deque.add(Pair.of(i,j));
	                visited.add(Pair.of(i,j));
	            }
	        }
	    }
	    while(!deque.isEmpty()){
	        size = deque.size();
	        while(size--!=0){
	            Pair p = deque.removeFirst();
	            int x=p.first,y=p.second;
	            if(x-1>=0 && grid[x-1][y]==1 && !visited.contains(Pair.of(x-1,y))){
	                Pair node = Pair.of(x-1,y);
	                visited.add(node);
	                deque.addLast(node);
	            } 
	            if(x+1<m && grid[x+1][y]==1 && !visited.contains(Pair.of(x+1,y))){
	                Pair node = Pair.of(x+1,y);
	                visited.add(node);
	                deque.addLast(node);
	            } 
	            if(y-1>=0 && grid[x][y-1]==1 && !visited.contains(Pair.of(x,y-1))){
	                Pair node = Pair.of(x,y-1);
	                visited.add(node);
	                deque.addLast(node);
	            }
	            if(y+1<n && grid[x][y+1]==1 && !visited.contains(Pair.of(x,y+1))){
	                Pair node = Pair.of(x,y+1);
	                visited.add(node);
	                deque.addLast(node);
	            } 
	        }
	        time++;
	    }
	    for(int i=0;i<m;i++)for(int j=0;j<n;j++)if(grid[i][j]==1 && !visited.contains(Pair.of(i,j)))return -1;
	    return time-1;
	}
}


/**
 * Queue using two stack
 */
class StackQueue
{
    Deque<Integer> s1 = new ArrayDeque<Integer>();
    Deque<Integer> s2 = new ArrayDeque<Integer>();

    /**
     * Push directly to s1
     * @param x
     */
    void Push(int x)
    {
	   s1.push(x);
    }
    
    /**
     * If s2 is empty pop all elements from s1 to s2
     * return -1 if s2 still empty
     */
    int Pop()
    {
	   if(s2.isEmpty()){
	       while(!s1.isEmpty()){
	           s2.push(s1.pop());
	       }
	   }
	   return s2.isEmpty()?-1:s2.pop();
    }
}

class QueueStack
{
    Deque<Integer> q1 = new ArrayDeque<Integer>();
    Deque<Integer> q2 = new ArrayDeque<Integer>();
    
    /**
     * Push to q1
     * @param x
     */
    void push(int x)
    {
	    q1.offer(x);
    }
    
    /**
     * Dequeue elements from q1 to q2 till 1 element left in q1
     * Swap q1 and q2
     * Dequeue and return from q2
     */
    int pop()
    {
	    while(q1.size()>1){
            q2.offer(q1.poll());
        }
        Deque<Integer> tmp = q1;
        q1 = q2;
        q2 = tmp;
        return (q2.isEmpty()?-1:q2.poll());	
    }
}



class MinStack
{
    int minEle;
    Deque<Integer> s;
    {
        s = new ArrayDeque<>();
    }

    /*returns min element from stack*/
    int getMin(){
	    return (s.isEmpty()?-1:minEle);
    }
    
    /**
     * If popped element is less than minEle(we have change in minEle at this point)
     * Set new minEle = 2*minEle-0popped element(2*prev minEle - new minEle) and return prev minEle
     * else return (popped element(2*x-minEle) + minEle)/2 => x;
     * @return
     */
    int pop(){
        if(s.isEmpty())return -1;
	    int ele = s.pop();
	    if(ele<minEle){
	        int tmp = minEle;
	        minEle = 2*minEle-ele;
	        return tmp;
	    }else{
	        return (ele+minEle)/2;
	    }
    }

    /**
     * Push 2*x-minEle into stack
     * calculate new minEle
     * @param x
     */
    void push(int x){	
        if(s.isEmpty()){
            minEle=x;
        }
	    s.push(2*x-minEle);
	    minEle = Math.min(minEle, x);
    }	
}



class LRUCache
{
    static DoublyLinkedList dll;
    static Map<Integer, Node> cache;
    static int capacity;
    LRUCache(int cap)
    {
        // Intialize the cache capacity with the given
        // cap
        dll = new DoublyLinkedList();
        cache = new HashMap<>();
        capacity=cap;
    }

    // This method works in O(1)
    public static int get(int key)
    {
        // your code here
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            dll.remove(node);
            dll.add(node);
            return node.val;
        }
        return -1;
    }

    // This method works in O(1)
    public static void set(int key, int value)
    {
        // your code here
        if(capacity==0)return;
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            dll.remove(node);
            node.val=value;
            dll.add(node);
        } else {
            Node tmp = new Node(key,value);
            if(cache.size()>=capacity){
                Node node= dll.getLast();
                cache.remove(node.key);
                dll.remove(node);
            } 
            dll.add(tmp);
            cache.put(key,tmp);
        }
    }
}
