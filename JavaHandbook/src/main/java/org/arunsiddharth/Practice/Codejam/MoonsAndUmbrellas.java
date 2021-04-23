package org.arunsiddharth.Practice.Codejam;

import java.util.Scanner;

public class MoonsAndUmbrellas {
    public static long computeCost(String str, int cj, int jc){
        long result = 0;
        for(int i=1;i<str.length();i++){ 
            String duo = ""+str.charAt(i-1)+str.charAt(i);
            if(duo.equals("CJ"))result+=cj;
            else if(duo.equals("JC"))result+=jc;
        }
        return result;
    }
    public static String stripe(String str){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)!='?')
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int ct=1;ct<=t;ct++){
            int cj = sc.nextInt();
            int jc = sc.nextInt();
            String str = sc.next();
            if(cj>=0 && jc>=0){
                str = stripe(str);
            }else{
                str = str;
            }
            System.out.println("Case #"+ct+": "+computeCost(str, cj, jc));
        }
    }
}
