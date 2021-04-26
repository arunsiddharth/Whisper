package org.arunsiddharth.DynamicProgramming;


import org.arunsiddharth.Domain.Pair;

public class BooleanParenthesization {
    static Pair[][] memo;
    public static Pair numberOfWays(String expression, int start, int end){
        if(start>end)return Pair.of(0,0);
        if(memo[start][end]!=null)return memo[start][end];
        if(start==end){
            return Pair.of(expression.charAt(start)=='T'?1:0,expression.charAt(start)=='F'?1:0);
        }
        Pair p = Pair.of(0,0);
        for(int i=start+1;i<=end-1;i+=2){
            Pair node1 = numberOfWays(expression, start, i-1);
            Pair node2 = numberOfWays(expression, i+1, end);
            int total1 = node1.first+node1.second;
            int total2 = node2.first+node2.second;
            switch(expression.charAt(i)){
                case '&':
                    p.first+=node1.first*node2.first;
                    p.second+=total1*total2-node1.first*node2.first;
                    break;
                case '|':
                    p.first+=total1*total2-node1.second*node2.second;
                    p.second+=node1.second*node2.second;
                    break;
                case '^':
                    p.first+=node1.first*node2.second+node1.second*node2.first;
                    p.second+=node1.first*node2.first+node1.second*node2.second;
                    break;
                default:
                    System.out.println("Shouldn't print");
                    break;
            }
        }
        memo[start][end] = p;
        return p;
    }
    public static void main(String[] args){
        String expression = "T^F|F";
        memo = new Pair[expression.length()][expression.length()];
        System.out.println(numberOfWays(expression, 0, expression.length()-1).first);
    }
}
