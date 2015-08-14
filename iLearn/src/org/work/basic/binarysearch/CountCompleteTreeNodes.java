package org.work.basic.binarysearch;

import org.work.leetcode.TreeNode;

public class CountCompleteTreeNodes {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        node1.left = new TreeNode(2);
        node1.right = new TreeNode(3);
        node1.left.left = new TreeNode(4);
        node1.left.right = new TreeNode(5);
        CountCompleteTreeNodes obj = new CountCompleteTreeNodes();
        int ret = obj.countNodes(node1);
        System.out.println(ret);
    }
    
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        int height = 0;
        TreeNode curr = root;
        while (curr.left != null) {
            height++;
            curr = curr.left;
        }
        int left = 0, right = (1 << height) - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (existsNode(root, mid, height - 1)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (1 << height) + right;
    }
    public boolean existsNode(TreeNode root, int path, int height) {
        while (height >= 0 && root != null) {
            if (((path >> height) & 1) == 1) {
                root = root.right;
            } else {
                root = root.left;
            }
            height--;
        }
        return root != null;
    }
}
