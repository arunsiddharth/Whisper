package org.arunsiddharth.Advanced;

import org.arunsiddharth.Domain.TrieNode;

public class Trie {
    TrieNode head;

    public Trie(){
        head = new TrieNode();
    }

    public void add(String str){
        int n = str.length();
        TrieNode p = head;
        for(int i=0;i<n;i++){
            char ch = str.charAt(i);
            if(p.arr[ch]==null){
                p.arr[ch]=new TrieNode();
            }
            p=p.arr[ch];
        }
        p.isFinal=true;
    }

    public boolean contains(String str){
        int n = str.length();
        TrieNode p = head;
        for(int i=0;i<n;i++){
            char ch = str.charAt(i);
            if(p.arr[ch]==null){
                return false;
            }
            p=p.arr[ch];
        }
        return p.isFinal;
    }

    public boolean isPrefix(String str){
        int n = str.length();
        TrieNode p = head;
        for(int i=0;i<n;i++){
            char ch = str.charAt(i);
            if(p.arr[ch]==null){
                return false;
            }
            p=p.arr[ch];
        }
        return true;
    }
    
    public void printHelper(String tmp, TrieNode p){
        if(p.isFinal)System.out.println(tmp);
        for(int i=0;i<256;i++){
            if(p.arr[i]!=null){
                tmp+=(char)i;
                printHelper(tmp, p.arr[i]);
                tmp=tmp.substring(0,tmp.length()-1);
            }
        }
    }
    public void print(){
        String tmp = "";
        printHelper(tmp, head);
    }
}
