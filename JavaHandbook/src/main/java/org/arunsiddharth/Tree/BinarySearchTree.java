package org.arunsiddharth.Tree;

import org.arunsiddharth.Domain.TreeNode;

/**
 * Binary Search Tree with no duplicates
 */
public class BinarySearchTree {
    
    TreeNode root;
    
    public BinarySearchTree(){
        root=null;
    }

    public void insert(int key){
        root=insertUtil(root, key);
    }

    public TreeNode insertUtil(TreeNode node, int key){
        if(node == null){
            node = new TreeNode(key);
            return node;
        }
        if(node.val>key){
            node.left = insertUtil(node.left, key);
        }
        else if(node.val<key){
            node.right = insertUtil(node.right, key);
        }
        return node;
    }

    public void delete(int key){
        root = deleteUtil(root,key);
    }

    private TreeNode deleteUtil(TreeNode node, int key){
        if(node==null){
            return null;
        } else if(node.val<key){
            node.right = deleteUtil(node.right, key);
        } else if(node.val>key){
            node.left = deleteUtil(node.left, key);
        } else {
            if(node.left==null)return node.right;
            else if(node.right==null)return node.left;
            else{
                node.val = minValue(node.right);
                node.right = deleteUtil(node.right, node.val);
            }
        }
        return node;
    }

    public int minValue(TreeNode node){
        int minVal = -1;
        while(node!=null){
            minVal=node.val;
            node = node.left;
        }
        return minVal;
    }

    private void inorderUtil(TreeNode node){
        if(node==null)return;
        inorderUtil(node.left);
        System.out.print(node.val+" ");
        inorderUtil(node.right);
    }

    public void inOrder(){
        inorderUtil(root);
        System.out.println();
    }

    public boolean containsUtil(TreeNode node, int val){
        if(node==null)return false;
        if(node.val<val)return containsUtil(node.right, val);
        if(node.val>val)return containsUtil(node.left, val);
        return true;
    }

    public boolean contains(int val){
        return containsUtil(root,val);
    }

    private TreeNode LCAUtil(TreeNode root, int n1, int n2){
        if(root==null)return null;
        if(root.val > n1 && root.val>n2)return LCAUtil(root.left, n1, n2);
        if(root.val < n1 && root.val<n2)return LCAUtil(root.right, n1, n2);
        return root;
    }

    public TreeNode LCA(int n1, int n2){
        TreeNode node = LCAUtil(root, n1, n2);
        if(node==null)return null;
        if(this.containsUtil(node, n1) && this.containsUtil(node,n2))return node;
        return null;
    }
}