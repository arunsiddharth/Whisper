package org.arunsiddharth.StackQueue;
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

    public static int precedence(Character ch){
        switch(ch){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '%':
                return 3;
            case '^':
                return 4;
            default:
                return -1;
        }
    }

    public static String infixToPostfix(String expression) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder postfix = new StringBuilder("");
        for(int i=0;i<expression.length();i++){
            char ch = expression.charAt(i);
            if(Character.isLetterOrDigit(ch))postfix.append(ch);
            else if(ch=='(')stack.push(ch);
            else if(ch==')'){
                while(!stack.isEmpty() && !stack.peek().equals('(')){
                    postfix.append(stack.pop());
                }
                stack.pop();
            }else{
                while(!stack.isEmpty() && precedence(stack.peek())>=precedence(ch)){
                    postfix.append(stack.pop());
                }
                stack.push(ch);
            }
        }
        while(!stack.isEmpty()){
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }
    public static String infixToPrefix(String expression){
        StringBuilder stb = new StringBuilder(expression);
        stb.reverse();
        for(int i=0;i<stb.length();i++){
            if(stb.charAt(i)=='(')stb.replace(i, i+1, ")");
            else if(stb.charAt(i)==')')stb.replace(i, i+1, "(");
        }
        System.out.println(stb.toString());
        stb = new StringBuilder(infixToPostfix(stb.toString()));
        return stb.reverse().toString();
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
        String expression = "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println(infixToPrefix(expression));
    }
    
}
