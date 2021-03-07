package org.arunsiddharth.Tree;

import java.util.Deque;
import java.util.ArrayDeque;
import org.arunsiddharth.Domain.TreeNode;

public class BinaryTree {
    TreeNode root;
    BinaryTree(){
        root=null;
    }
    public void insert(int key){
        TreeNode nodeToInsert = new TreeNode(key);
        if(root==null){
            root = nodeToInsert;
        }else{
            Deque<TreeNode> q = new ArrayDeque<>();
            q.add(root);
            while(!q.isEmpty()){
                TreeNode p = q.poll();
                if(p.left==null){
                    p.left=nodeToInsert;
                    break;
                }
                if(p.right==null){
                    p.right=nodeToInsert;
                    break;
                }
                q.add(p.left);
                q.add(p.right);
            }
        }
    }
    public boolean delete(int key){
        if(root==null)return false;
        Deque<TreeNode> q = new ArrayDeque<>();
        TreeNode nodeToDelete = null;
        TreeNode brNode = root;
        q.add(root);
        while(!q.isEmpty()){
            brNode = q.poll();
            if(brNode.val==key){
                nodeToDelete=brNode;
            }
            if(brNode.left!=null)q.add(brNode.left);
            if(brNode.right!=null)q.add(brNode.right);
        }
        if(nodeToDelete==null)return false;
        nodeToDelete.val=brNode.val;
        return deleteLastNode(brNode);
    }
    public boolean deleteLastNode(TreeNode lastNode){
        if(root==null)return false;
        Deque<TreeNode> q = new ArrayDeque<>();
        TreeNode brNode = root;
        q.add(root);
        while(!q.isEmpty()){
            brNode = q.poll();
            if(brNode.left!=null && brNode.left==lastNode){
                brNode.left=null;
                return true;
            }
            if(brNode.right!=null && brNode.right==lastNode){
                brNode.right=null;
                return true;
            }
            if(brNode.left!=null)q.add(brNode.left);
            if(brNode.right!=null)q.add(brNode.right);
        }
        return false;
    }

    public TreeNode LCAUtil(TreeNode root, int n1, int n2){
        if(root==null || root.val==n1 || root.val==n2)return root;
        TreeNode l = LCAUtil(root.left, n1, n2);
        TreeNode r = LCAUtil(root.right, n1, n2);
        if(l!=null && r!=null)return root;
        return (l==null)?r:l;
    }


    public TreeNode LCA(int n1, int n2){
        TreeNode node = LCAUtil(root, n1, n2);
        if(node!=null && containsUtil(node, n1) && containsUtil(node, n2))return node;
        return null;
    }

    public boolean containsUtil(TreeNode root, int val){
        if(root==null)return false;
        if(root.val==val)return true;
        return containsUtil(root.left, val)||containsUtil(root.right, val);
    }
}