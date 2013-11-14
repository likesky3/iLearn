package org.work.leetcode;

public class E027_IsValidBST {

	public static void main(String[] args) {
//		TreeNode root = new TreeNode(3);
//		root.left = new TreeNode(1);
//		root.left.left = new TreeNode(0);
//		root.left.right = new TreeNode(2);
//		root.left.right.right = new TreeNode(3);
//		root.right = new TreeNode(5);
//		root.right.left = new TreeNode(4);
//		root.right.right = new TreeNode(6);
		
		E027_IsValidBST obj = new E027_IsValidBST();
//		System.out.println(obj.isValidBST(root));
		
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(1);
		System.out.println(obj.isValidBST3(root));
		
	}
	
	public boolean isValidBST(TreeNode root) {
        int[] mm = new int[2];
        return check(root, mm);
    }
    
	//思路1：mm[0]是root左子树中的最大值，mm[1]是root右子树中的最小值
	//通过判断mm[0] < root.val < mm[1]确认root代表的tree是否为BST
	
	//点评：这个思路不正确，mm[0] < root.val < mm[1]是tree为BST的必要非充分条件
	//设想root为 root0的左子树，检查root0时采用的是root带上来的mm[1], 
	//而这个mm[1]并非root0左子树中的最大值，因此不足以判断root0是否满足条件
    private boolean check(TreeNode root, int[] mm){
        if(root == null)
            return true;
        if((root.left == null) && (root.right == null)){
            mm[0] = root.val;
            mm[1] = root.val;
            return true;
        }
        int[] res = new int[2];
        
        if(root.left == null)
            res[0] = Integer.MIN_VALUE;
        else if(check(root.left, mm))
            res[0] = mm[1];
        else
            return false;
            
        if(root.right == null)    
            res[1] = Integer.MAX_VALUE;
        else if(!check(root.right, mm))
            return false;
        else
            res[1] = mm[0];
        
        if((res[0] < root.val) && (res[1] > root.val)){
            mm[0] = res[0] == Integer.MIN_VALUE ? root.val : res[0];
            mm[1] = res[1] == Integer.MAX_VALUE ? root.val : res[1];
            
            //for debug
            System.out.println("-----" + root.val);
            System.out.println(mm[0] + " " + mm[1]);
            return true;
        }else
            return false;
    }
    
    //思路2，up - bottom
    public boolean isValidBST2(TreeNode root) {
       return check(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private boolean check(TreeNode root, int low, int high){
        if(root == null)
            return true;
            
        if((root.val > low) && (root.val < high) && check(root.left, low, root.val) && check(root.right, root.val, high))
            return true;
        else
            return false;
    }
    
    //思路3, in order
    public boolean isValidBST3(TreeNode root) {
        return inorderChecker2(root, new TreeNode(Integer.MIN_VALUE));
     }
    
    //has bug
    private boolean inorderChecker(TreeNode root, TreeNode prev){
    	if(inorderChecker(root.left, prev) && (prev.val < root.val ) && (inorderChecker(root.right, root))) 
    			return true;
    	return false;
    }
    
    //fail @[1, 1]
    private boolean inorderChecker2(TreeNode root, TreeNode prev){
        if(root == null)
            return true;
        System.out.println(root.val + prev.val);
    	if(inorderChecker2(root.left, prev) && (prev.val < root.val )){
    	    prev = root; //java传引用是传引用的值，这里修改没用
    	    if(inorderChecker2(root.right, prev)){
    	        return true;
    	    }
    	    else 
    	        return false;
    	    
    	} 
    	return false;
    }
    
    //correct
    private boolean inorderChecker3(TreeNode root, TreeNode prev){
        if(root == null)
            return true;
    	if(inorderChecker3(root.left, prev) && (prev.val < root.val )){
    	    prev.val = root.val;
    	    if(inorderChecker3(root.right, prev))
    	        return true;
    	    else 
    	        return false;
    	    
    	} 
    	return false;
    }
}
