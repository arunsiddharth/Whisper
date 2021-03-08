package org.arunsiddharth.Practice.CSES.IntroductoryProblems;

import java.util.Scanner;

public class Repetitions {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int maxCounter = 1;
        int counter = 1;
        for(int i=1;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch=='A'){
                if(str.charAt(i-1)=='A')counter++;
                else counter=1;
            } else if(ch=='T') {
                if(str.charAt(i-1)=='T')counter++;
                else counter=1;
            } else if(ch=='C') {
                if(str.charAt(i-1)=='C')counter++;
                else counter=1;
            } else {
                if(str.charAt(i-1)=='G')counter++;
                else counter=1;
            }
            maxCounter = Math.max(maxCounter, counter);
        }
        System.out.println(maxCounter);
    }
}
