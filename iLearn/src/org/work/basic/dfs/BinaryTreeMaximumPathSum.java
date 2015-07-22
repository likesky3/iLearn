package org.work.basic.dfs;

import org.work.leetcode.TreeNode;

public class BinaryTreeMaximumPathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        root.left = left;
        BinaryTreeMaximumPathSum obj = new BinaryTreeMaximumPathSum();
        System.out.println(obj.maxPathSum(root));
    }

    int globalMax = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;
        dfs(root);
        return globalMax;
    }
    public int dfs(TreeNode root) {
        int left = Integer.MIN_VALUE, right = Integer.MIN_VALUE; // 不能初始化为0
        if (root.left != null)
            left = dfs(root.left);
        if (root.right != null)
            right = dfs(root.right);
        int largeSub = left > right ? left : right;
        int root2Leaf = root.val + (largeSub > 0 ? largeSub : 0);
        int localMax = Math.max(root.val + (left > 0 ? left : 0) + (right > 0 ? right : 0), largeSub);
        globalMax = Math.max(globalMax, localMax);
        return root2Leaf;
    }
}
