package org.arunsiddharth.StackQueue;

import java.util.*;

class MinStack{
    Deque<Long> stack;
    Integer minElement;

    MinStack(){
        stack = new ArrayDeque<>();
        minElement = Integer.MIN_VALUE;
    }


    public void push(Integer element){
        stack.push((long)2*element-minElement);
        minElement = Math.min(minElement, element);
    }

    public Integer pop(){
        if(stack.isEmpty())return -1;
        long current = stack.pop();
        if(current<minElement){
            minElement = (int)(2*minElement-current);
        }
        return (int)(current+minElement)/2;
    }

    public Integer getMin(){
        return minElement;
    }

    public Integer peek(){
        long top = stack.peek();
        if(top<minElement){
            return minElement;
        }
        return (int)(minElement+top)/2;
    }
}

public class Practice {

    public static void insertAtBottom(Deque<Integer> stack, Integer topElement){
        if(stack.isEmpty()){
            stack.push(topElement);
            return;
        }
        Integer element = stack.pop();
        insertAtBottom(stack, topElement);
        stack.push(element);
    }
    public static void reverseStack(Deque<Integer> stack){
        if(stack.isEmpty())return;
        Integer topElement = stack.pop();
        reverseStack(stack);
        insertAtBottom(stack, topElement);
    } 

    public static void insertAtSortedLocation(Deque<Integer> stack, Integer topElement){
        if(stack.isEmpty() || stack.peek()<=topElement){
            stack.push(topElement);
            return;
        }
        Integer element = stack.pop();
        insertAtSortedLocation(stack, topElement);
        stack.push(element);
    }

    public static void sortStack(Deque<Integer> stack){
        if(stack.isEmpty())return;
        Integer topElement = stack.pop();
        sortStack(stack);
        insertAtSortedLocation(stack, topElement);
    }

    public static void main(String[] args) throws Exception {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        System.out.println(stack);
        reverseStack(stack);
        System.out.println(stack);
        sortStack(stack);
        System.out.println(stack);
        MinStack stack2 = new MinStack();
        int[] array = {1,2,3,4};
        Collections.reverse(Arrays.asList(array));
        System.out.println(Arrays.toString(array));
    }
}
