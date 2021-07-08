package solutions.module_4;

import datastructures.TreeNode;

public class BinaryTreeValidator<T extends Comparable<T>> {
    private T prevVal;
    private final TreeNode<T> parent;

    public BinaryTreeValidator(TreeNode<T> parent) {
        this.parent = parent;
    }

    public boolean validate() {
        prevVal = null;
        return validate(parent);
    }

    private boolean validate(TreeNode<T> node) {
        if (node == null) {
            return true;
        }

        if (!validate(node.getLeft())) {
            return false;
        }

        if (prevVal != null) {
            int cmp = node.getVal().compareTo(prevVal);
            if (cmp < 0) {
                return false;
            } else if (node.getLeft() != null && cmp == 0) {
                return false;
            }
        }
        prevVal = node.getVal();

        return validate(node.getRight());
    }

    public static void main(String[] args) {
        TreeNode<Integer> parent = TreeSearch.readAndBuild();
        BinaryTreeValidator<Integer> validator = new BinaryTreeValidator<>(parent);

        if (validator.validate()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
