package datastructures;

import util.BTreePrinter;

import java.io.IOException;
import java.util.Scanner;

/**
 * Tree can only contain int values and can compute sum in segment
 * This implementation does not support the same values
 *
 */
public class SplayTree {
    private TreeNode<Integer> root;

    public void put(Integer val) {

        TreeNode<Integer> newNode = new TreeNode<>(val, null, null, val);
        if (root == null) {
            root = newNode;
            return;
        } else if (contains(val)) {
            return;
        }

        TreeNode<Integer> node = root;
        while (true) {
            int compare = val.compareTo(node.getVal());
            node.setSum(node.getSum() + val);

            if (compare < 0) {
                if (node.getLeft() == null) {
                    newNode.setParent(node);
                    node.setLeft(newNode);
                    break;
                }
                node = node.getLeft();
            } else if (compare > 0){
                if (node.getRight() == null) {
                    newNode.setParent(node);
                    node.setRight(newNode);
                    break;
                }
                node = node.getRight();
            }
        }

        splay(newNode);
//        BTreePrinter.printNode(root);
    }

    public boolean contains(Integer value) {
        if (root == null) {
            return false;
        }

        TreeNode<Integer> node = root;
        TreeNode<Integer> prev = null;
        boolean found = false;

        while (node != null) {
            prev = node;
            int compare = value.compareTo(node.getVal());
            if (compare < 0) {
                node = node.getLeft();
            } else if (compare > 0) {
                node = node.getRight();
            } else {
                found = true;
                break;
            }
        }
        splay(prev);
        return found;
    }

    public void remove(Integer val) {
        if (contains(val)) {
            TreeNode<Integer> rm = root;
            TreeNode<Integer> left = rm.getLeft();
            TreeNode<Integer> right = rm.getRight();

            if (left == null && right == null) {
                root = null;
            } else if (left == null) {
                root = right;
                root.setParent(null);
            } else if (right == null) {
                root = left;
                root.setParent(null);
            } else {
                root = left;
                root.setParent(null);
                root = merge(left, right);
            }

            rm.setVal(null);
            rm.setRight(null);
            rm.setLeft(null);
        }
    }

    public long getSum(int l, int r) {
        if (l == r) {
            return l;
        } else if (root == null || l > r) {
            return 0;
        }

        long sum;

        if (contains(l - 1) && root.getRight() != null) {
            sum = root.getRight().getSum();
        } else {
            sum = root.getSum();
            if (root.getLeft() != null) {
                sum -= root.getLeft().getSum();
            }
        }
//        System.out.println(sum);

        if (contains(r)) {
            if (root.getRight() != null) {
                sum -= root.getRight().getSum();
            }
        } else if (sum != root.getSum()) {
            sum -= root.getSum();
            if (root.getLeft() != null) {
                sum += root.getLeft().getSum();
            }
        }

        return sum;
    }

    private void splay(TreeNode<Integer> node) {
//        System.out.println("splay is invoked with val " + node.getVal());
        while (node != null) {
            if (hasGrandParent(node)) {
                boolean nodeIsLeft = isLeft(node);
                boolean parentIsLeft = isLeft(node.getParent());
                boolean isGrandParentRoot = isRoot(node.getParent().getParent());

                if (nodeIsLeft && parentIsLeft) {
                    // zig zig
                    TreeNode<Integer> parent = node.getParent();
                    zig(parent, isGrandParentRoot);
                    zig(node, isGrandParentRoot);
                } else if (nodeIsLeft) {
                    // zig zag
                    zig(node, false);
                    zag(node, isGrandParentRoot);
                } else if (parentIsLeft) {
                    // zag zig
                    zag(node, false);
                    zig(node, isGrandParentRoot);
                } else {
                    // zag zag
                    TreeNode<Integer> parent = node.getParent();
                    zag(parent, isGrandParentRoot);
                    zag(node, isGrandParentRoot);
                }
            } else if (hasParent(node)) {
                if (isLeft(node)) {
                    zig(node, true);
                } else {
                    zag(node, true);
                }
                node = node.getParent();
            } else {
                break;
            }
        }
    }

    private void zig(TreeNode<Integer> node, boolean makeRoot) {
        TreeNode<Integer> parent = node.getParent();


        if (makeRoot) {
            node.setParent(null);
            root = node;
        } else {
            node.setParent(parent.getParent());
            TreeNode<Integer> grandParent = parent.getParent();
            node.setParent(grandParent);
            if (isLeft(parent)){
                grandParent.setLeft(node);
            } else {
                grandParent.setRight(node);
            }
        }

        TreeNode<Integer> r = node.getRight();
        if (r != null) {
            r.setParent(parent);
        }
        parent.setLeft(r);

        node.setRight(parent);
        parent.setParent(node);

        long nodeSum = node.getSum();
        long parentSum = parent.getSum();

        node.setSum(parentSum);
        parent.setSum(parentSum - nodeSum + (r != null ? r.getSum() : 0));
    }

    private void zag(TreeNode<Integer> node, boolean makeRoot) {
        TreeNode<Integer> parent = node.getParent();

        if (makeRoot) {
            node.setParent(null);
            root = node;
        } else {
            TreeNode<Integer> grandParent = parent.getParent();
            node.setParent(grandParent);
            if (isLeft(parent)){
                grandParent.setLeft(node);
            } else {
                grandParent.setRight(node);
            }
        }

        TreeNode<Integer> l = node.getLeft();
        if (l != null) {
            l.setParent(parent);
        }

        parent.setRight(l);

        node.setLeft(parent);
        parent.setParent(node);

        long nodeSum = node.getSum();
        long parentSum = parent.getSum();

        node.setSum(parentSum);
        parent.setSum(parentSum - nodeSum + (l != null ? l.getSum() : 0));
    }

    private TreeNode<Integer> merge(TreeNode<Integer> l, TreeNode<Integer> r) {


        TreeNode<Integer> newRoot = max(l);
        splay(newRoot);
        newRoot.setRight(r);
        newRoot.setSum(newRoot.getSum() + r.getSum());
        r.setParent(newRoot);
        return newRoot;
    }

    private TreeNode<Integer> max(TreeNode<Integer> inNode) {
        TreeNode<Integer> max = inNode;
        while (inNode.getRight() != null) {
            max = inNode = inNode.getRight();
        }
        return max;
    }

    private boolean hasParent(TreeNode<Integer> node) {
        return node.getParent() != null;
    }

    private boolean hasGrandParent(TreeNode<Integer> node) {
        return hasParent(node) && node.getParent().getParent() != null;
    }

    private boolean isLeft(TreeNode<Integer> child) {
        TreeNode<Integer> parent = child.getParent();
        return child.getVal().compareTo(parent.getVal()) < 0;
    }

    private boolean isRoot(TreeNode<Integer> node) {
        return root.getVal().equals(node.getVal());
    }

    public static void main(String[] args) throws IOException {
        SplayTree tree = new SplayTree();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1 - add");
            System.out.println("2 - find");
            System.out.println("3 - delete");
            System.out.println("4 - clear");
            System.out.println("5 - sum(l, r)");
            System.out.println("0 - quit");
            int op = sc.nextInt();
            if (op == 1) {
                System.out.println("Enter value to add:");
                tree.put(sc.nextInt());
            } else if (op == 2) {
                System.out.println("Enter value to find:");
                System.out.println(tree.contains(sc.nextInt()));
            } else if (op == 3) {
                System.out.println("Enter value to delete:");
                tree.remove(sc.nextInt());
            } else if (op == 4) {
                Runtime.getRuntime().exec("clear");
            } else if (op == 5) {
                System.out.println("Enter l and r");
                System.out.println(tree.getSum(sc.nextInt(), sc.nextInt()));
            } else if (op == 0) {
                System.out.println("Bye");
                break;
            }
            BTreePrinter.printNode(tree.root);
        } while (true);
    }
}

