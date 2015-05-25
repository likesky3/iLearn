package org.work.basic.tree;

import java.util.ArrayList;
import java.util.LinkedList;

import org.work.leetcode.TreeNode;

public class BinaryTreeRightSideView {
    public ArrayList<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> mostRight = new ArrayList<Integer>();
        if (root == null)
            return mostRight;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int currLevelNum = 1;
        int nextLevelNum = 0;
        TreeNode curr = null;
        while (!queue.isEmpty()) {
            while (currLevelNum > 0) {
                curr = queue.poll();
                currLevelNum--;
                if (curr.left != null) {
                    queue.offer(curr.left);
                    nextLevelNum++;
                }
                if (curr.right != null) {
                    queue.offer(curr.right);                    
                    nextLevelNum++;
                }
                if (currLevelNum == 0) {
                    mostRight.add(curr.val);
                    break;
                }
            }
            currLevelNum = nextLevelNum;
            nextLevelNum = 0;
        }
        return mostRight;
    }
}
