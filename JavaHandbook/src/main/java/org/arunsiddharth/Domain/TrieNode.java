package org.arunsiddharth.Domain;

public class TrieNode {
    public TrieNode[] arr;
    public boolean isFinal;
    public TrieNode(){
        arr = new TrieNode[256];
        isFinal = false;
    }
}
