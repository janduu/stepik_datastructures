package solutions.module_4;

import datastructures.TreeNode;

import java.util.Scanner;

public class TreeSearch {

    public static void preOrder(TreeNode<?> node) {
        if (node == null) {
            return;
        }

        System.out.print(node.getVal() + " ");
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    public static void postOrder(TreeNode<?> node) {
        if (node == null) {
            return;
        }

        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.print(node.getVal() + " ");
    }

    public static void inOrder(TreeNode<?> node) {
        if (node == null) {
            return;
        }

        inOrder(node.getLeft());
        System.out.print(node.getVal() + " ");
        inOrder(node.getRight());
    }

    public static TreeNode<Integer> readAndBuild() {
        Scanner sc = new Scanner(System.in);
        int nodesNum = sc.nextInt();
        if (nodesNum == 0) {
            return null;
        }

        int[] keys = new int[nodesNum];
        int[] keysLeft = new int[nodesNum];
        int[] keysRight = new int[nodesNum];

        for (int i = 0; i < nodesNum; i++) {
            keys[i] = sc.nextInt();
            keysLeft[i] = sc.nextInt();
            keysRight[i] = sc.nextInt();
        }

        return buildTree(0, keys, keysLeft, keysRight);
    }

    public static TreeNode<Integer> buildTree(int kIndex, int[] keys, int[] keysLeft, int[] keysRight) {
        if (kIndex == -1) {
            return null;
        }

        return new TreeNode<>(
                keys[kIndex],
                buildTree(keysLeft[kIndex], keys, keysLeft, keysRight),
                buildTree(keysRight[kIndex], keys, keysLeft, keysRight));
    }

    public static void main(String[] args) {
        TreeNode<Integer> parent = readAndBuild();

        inOrder(parent);
        System.out.println();

        preOrder(parent);
        System.out.println();

        postOrder(parent);
    }
}
