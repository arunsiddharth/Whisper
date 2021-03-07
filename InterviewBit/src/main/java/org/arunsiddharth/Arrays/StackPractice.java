package org.arunsiddharth.Arrays;
import java.util.*;

public class StackPractice {

    public static void insertElementAtBottom(Deque<Integer> stack, int elementToInsert){
        if(stack.isEmpty()){
            stack.push(elementToInsert);
            return;
        }
        int elementAtTop = stack.pop();
        insertElementAtBottom(stack, elementToInsert);
        stack.push(elementAtTop);
    }
    public static void reverseStack(Deque<Integer> stack){
        if(stack.isEmpty())return;
        int elementAtTop = stack.pop();
        reverseStack(stack);
        insertElementAtBottom(stack, elementAtTop);
    }

    public static void insertElementAtSortedPosition(Deque<Integer> stack, int elementToInsert){
        if(stack.isEmpty() || stack.peek()<=elementToInsert){
            stack.push(elementToInsert);
            return;
        }
        int elementAtTop = stack.pop();
        insertElementAtSortedPosition(stack, elementToInsert);
        stack.push(elementAtTop);
    }


    public static void sortStack(Deque<Integer> stack){
        if(stack.isEmpty())return;
        int elementAtTop = stack.pop();
        sortStack(stack);
        insertElementAtSortedPosition(stack, elementAtTop);
    }

    public static void main(String[] args){
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        sortStack(stack);
        System.out.println(stack);
    }
    
}
