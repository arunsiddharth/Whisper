package org.arunsiddharth.Strings;

public class KMP {

    public static int[] calculateLPS(String needle){
        int n = needle.length();
        int[] lps = new int[n];
        int j=0;
        lps[0] = 0;
        for(int i=1;i<n;){
            if(needle.charAt(i)==needle.charAt(j)){
                lps[i] = j+1;
                i++;
                j++;
            }else{
                if(j!=0){
                    j = lps[j-1];
                }else{
                    lps[i]= 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static int strstr(String hayStack, String needle){
        int m = hayStack.length();
        int n = needle.length();
        int[] lps = calculateLPS(needle);
        int len = 0;
        for(int i=0;i<m;){
            if(hayStack.charAt(i)==needle.charAt(len)){
                i++;
                len++;
            }else{
                if(len!=0){
                    len = lps[len-1];
                }else{
                    i++;
                }
            }
            if(len==n){
                return i-len;
            }
        }
        return -1;
    }
    public static void main(String[] args){
        String a = "abcdabcd";
        String b = "cda";
        System.out.println(strstr(a, b));
    }
}
