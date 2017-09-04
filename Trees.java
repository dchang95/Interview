// TREES.

// Check to see if this is a valid BST.
public boolean checkBST(Node root) {
        return checkValid(root, 0, 10000);
}

public boolean checkValid(Node root, int min, int max) {
        if (root == null) {
                return true;
        }
        if (root.val < min || root.val > max) {
                return false;
        }
        boolean left = checkValid(root.left, min, root.value - 1);
        boolean right = checkValid(root.right, root.value + 1, max);
        return x && y;
}

/*
    1
   / \
   2   2
   / \ / \
   3  4 4  3

 */
// checks to see if a BST is symmetric.
public int isSymmetric(TreeNode a) {
        if (a == null) {
                return 1;
        }
        if (a.right == null && a.left == null) {
                return 1;
        }
        if (a.right == null && a.left != null || (a.left == null && a.right != null)) {
                return 0;
        }
        return isEqual(a.left, a.right);
}

public int isEqual(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
                return 1;
        } else if (left == null || right == null) {
                return 1;
        }
        int checkLeft = isEqual(left.left, right.right);
        int checkRight = isEqual(left.right, right.left);
        int val = left.val == right.val ? 1 : 0;
        if (checkLeft == 1 && checkRight == 1 && val == 1) {
                return 1;
        }
        return 0;
}

// Inorder Tree Traversal NON RECURSIVE.
public ArrayList<Integer> inorderTraversal(TreeNode a) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        stack.push(a);
        TreeNode left = a.left;
        while (left != null) {
                stack.push(left);
                left = left.left;
        }
        while (!stack.isEmpty()) {
                TreeNode curr = stack.pop();
                result.add(curr.val);
                curr = curr.right;
                while (curr != null) {
                        stack.push(curr);
                        curr = curr.left;
                }
        }
        return result;
}

// Preorder Tree Traversal NON RECURSIVE.
public ArrayList<Integer> preorderTraversal(TreeNode a) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(a.val);
        stack.push(a);
        TreeNode left = a.left;
        while (left != null) {
                result.add(left.val);
                stack.push(left);
                left = left.left;
        }

        while (!stack.isEmpty()) {
                TreeNode curr = stack.pop();
                curr = curr.right;
                while (curr != null) {
                        result.add(curr.val);
                        stack.push(curr);
                        curr = curr.left;
                }
        }
        return result;

}

// PostOrder Tree Traversal NON RECURSIVE.
public ArrayList<Integer> postorderTraversal(TreeNode a) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        stack.push(a);
        while (!stack.isEmpty()) {
                TreeNode temp = stack.pop();
                stack2.push(temp);

                if (temp.left != null) {
                        stack.push(temp.left);
                }
                if (temp.right != null) {
                        stack.push(temp.right);
                }
        }
        while (!stack2.isEmpty()) {
                TreeNode temp = stack2.pop();
                result.add(temp.val);
        }

        return result;
}

// find least common ancestor in a BST
Node lca (Node node, int n1, int n2) {
        if (node == null) {
                return null;
        }
        // if both n1 and n2 are less than root, check the left.
        if (node.data > n1 && node.data > n2) {
                return lca(node.left, n1, n2);
        }
        // if both n1 and n2 are greater than root, check right.
        if (node.data < n1 && node.data < n2) {
                return lca(node.right, n1, n2);
        }

        return node;
}
