package datastructures;

public class TreeNode<T extends Comparable<T>> {
    private T val;
    private TreeNode<T> left;
    private TreeNode<T> right;
    private TreeNode<T> parent;
    private long sum;

    public TreeNode(T val, TreeNode<T> left, TreeNode<T> right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(T val, TreeNode<T> left, TreeNode<T> right, long sum) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.sum = sum;
    }

    public TreeNode(T val, TreeNode<T> left, TreeNode<T> right, TreeNode<T> parent) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public TreeNode(T val, TreeNode<T> left, TreeNode<T> right, TreeNode<T> parent, long sum) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.sum = sum;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }
}