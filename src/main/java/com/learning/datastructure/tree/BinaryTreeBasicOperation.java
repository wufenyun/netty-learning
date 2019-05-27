package com.learning.datastructure.tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeBasicOperation {

    public BinaryTreeNode newFullBinaryTree(Integer[] dataArray) {
        BinaryTreeNode root = new BinaryTreeNode();

        BinaryTreeNode parent = root;
        for (Integer data:dataArray) {
            BinaryTreeNode left = new BinaryTreeNode(data);
            BinaryTreeNode right = new BinaryTreeNode(data);
            parent.setLeft(left);
            parent.setRight(right);
            parent = left;
        }

        return root;
    }

    /*  *********************  深度优先  *********************  */
    public static void preTravel(BinaryTreeNode node) {
        if(null == node) {
            return;
        }

        System.out.println(node.getData());
        preTravel(node.getLeft());
        preTravel(node.getRight());

    }

    public static void inorderTravel(BinaryTreeNode node) {
        if(null == node) {
            return;
        }

        inorderTravel(node.getLeft());
        System.out.println(node.getData());
        inorderTravel(node.getRight());

    }

    public static void postorderTravel(BinaryTreeNode node) {
        if(null == node) {
            return;
        }

        postorderTravel(node.getLeft());
        postorderTravel(node.getRight());
        System.out.println(node.getData());

    }

    /*  *********************  广度优先  *********************  */
    public static void bfs(BinaryTreeNode node) {
        Queue<BinaryTreeNode> queue = new LinkedList();
        queue.offer(node);
        while(queue.size() > 0) {
            int size = queue.size();
            for (int i=0;i<size;i++) {
                BinaryTreeNode currentNode = queue.poll();
                System.out.println(currentNode.getData());
                if(null != currentNode.getLeft()) {
                    queue.offer(currentNode.getLeft());
                }
                if(null != currentNode.getRight()) {
                    queue.offer(currentNode.getRight());
                }
            }
        }
    }


    public static class BinaryTreeBasicOperationTest {

        @Test
        public void inorderTravel() {
            BinaryTreeNode root = new BinaryTreeNode(4);
            BinaryTreeNode left = new BinaryTreeNode(1,new BinaryTreeNode(2),new BinaryTreeNode(3));
            BinaryTreeNode right = new BinaryTreeNode(5,new BinaryTreeNode(6),new BinaryTreeNode(7));
            root.setLeft(left);
            root.setRight(right);
            BinaryTreeBasicOperation.inorderTravel(root);
        }

        @Test
        public void preTravel() {
            BinaryTreeNode root = new BinaryTreeNode(4);
            BinaryTreeNode left = new BinaryTreeNode(1,new BinaryTreeNode(2),new BinaryTreeNode(3));
            BinaryTreeNode right = new BinaryTreeNode(5,new BinaryTreeNode(6),new BinaryTreeNode(7));
            root.setLeft(left);
            root.setRight(right);
            BinaryTreeBasicOperation.preTravel(root);
        }

        @Test
        public void postorderTravel() {
            BinaryTreeNode root = new BinaryTreeNode(4);
            BinaryTreeNode left = new BinaryTreeNode(1,new BinaryTreeNode(2),new BinaryTreeNode(3));
            BinaryTreeNode right = new BinaryTreeNode(5,new BinaryTreeNode(6),new BinaryTreeNode(7));
            root.setLeft(left);
            root.setRight(right);
            BinaryTreeBasicOperation.postorderTravel(root);
        }

        @Test
        public void bfs() {
            BinaryTreeNode root = new BinaryTreeNode(4);
            BinaryTreeNode left = new BinaryTreeNode(1,new BinaryTreeNode(2),new BinaryTreeNode(3));
            BinaryTreeNode right = new BinaryTreeNode(5,new BinaryTreeNode(6),new BinaryTreeNode(7));
            root.setLeft(left);
            root.setRight(right);
            BinaryTreeBasicOperation.bfs(root);
        }
    }

}
