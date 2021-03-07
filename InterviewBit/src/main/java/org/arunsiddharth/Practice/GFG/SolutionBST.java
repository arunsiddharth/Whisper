package org.arunsiddharth.Practice.GFG;

import org.arunsiddharth.Domain.TreeNode;
import java.util.*;
import org.arunsiddharth.Tree.BinarySearchTree;

public class SolutionBST {

    private boolean isBSTUtil(TreeNode root, int min, int max){
        if(root==null)return true;
        if(root.val>max || root.val<min)return false;
        return isBSTUtil(root.left, min, root.val-1)&&isBSTUtil(root.right, root.val+1, max);
    }
    
    public boolean isBST(TreeNode root){
        return isBSTUtil(root,Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    void leftView(TreeNode root){
        if(root==null)return;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            System.out.print(q.peek().val+" ");
            while(size--!=0){
                TreeNode node = q.poll();
                if(node.left!=null)q.offer(node.left);
                if(node.right!=null)q.offer(node.right);
            }
        }
     }


     public ArrayList <Integer> bottomView(TreeNode root){
        TreeMap<Integer,Integer> bv = new TreeMap<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        root.hd=0;
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            bv.put(node.hd, node.val);
            if(node.left!=null){
                node.left.hd = node.hd-1;
                q.offer(node.left);
            }
            if(node.right!=null){
                node.right.hd = node.hd+1;
                q.offer(node.right);
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        for(Integer e:bv.values()){
            result.add(e);
        }
        return result;
    }


    ArrayList <Integer> verticalOrder(TreeNode root){
        Map<Integer, List<Integer> > vv = new TreeMap<Integer, List<Integer>>();
        Deque<TreeNode> q= new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode p = q.poll();
            vv.putIfAbsent(p.hd, new ArrayList<Integer>());
            vv.get(p.hd).add(p.val);
            if(p.left!=null){
                p.left.hd = p.hd-1;
                q.add(p.left);
            }
            if(p.right!=null){
                p.right.hd = p.hd+1;
                q.add(p.right);
            }
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(List<Integer> l:vv.values()){
            result.addAll(l);
        }
        return result;
    }


    void printSpiral(TreeNode node){
        if(node==null)return;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(node);
        boolean flag=true;
        while(!q.isEmpty()){
            int size = q.size();
            while(size--!=0){
                TreeNode p = null;
                if(!flag){
                    p = q.removeFirst();
                    if(p.left!=null)q.addLast(p.left);
                    if(p.right!=null)q.addLast(p.right);
                }else{
                    p = q.removeLast();
                    if(p.right!=null)q.addFirst(p.right);
                    if(p.left!=null)q.addFirst(p.left);
                }
                System.out.print(p.val+" ");
            }
            flag=!flag;
        }
   }


    public static void main(String[] args){
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.inOrder();
        tree.delete(2);
        tree.delete(1);
        tree.inOrder();
        
    }
}