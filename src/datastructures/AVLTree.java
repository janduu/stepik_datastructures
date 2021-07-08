package datastructures;

public class AVLTree<V> {
    private static class TreeNode<T> {
        T val;
        TreeNode<T> parent;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(T val, TreeNode<T> parent, TreeNode<T> left, TreeNode<T> right) {
            this.val = val;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }
}
