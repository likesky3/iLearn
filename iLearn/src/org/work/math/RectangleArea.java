package org.work.math;

import junit.framework.Assert;

import org.junit.Test;

public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        // area of rectangle 1
        int height1 = D - B;
        int width1 = C - A;
        int area1 = height1 * width1;
        // area of rectangel 2
        int height2 = H - F;
        int width2 = G - E;
        int area2 = height2 * width2;
        int total = area1 + area2;
        // compute intersection area
        if (C <= E || G <= A || D <= F || H <= B) {// no intersection
            return total;
        } else {
            // compute width of intersection
            int deltaWidth = 0, deltaHeight = 0;
            if (E >= A && C <= G) {
                deltaWidth = C - E;
            } else if (A >= E && C <= G) {
                deltaWidth = C - A;
            } else if (A >= E && G < C) {
                deltaWidth = G - A;
            } else if (E >= A && G <= C){
                deltaWidth = G - E;
            } 
            //compute heigth of intersection
            if (F <= B && H <= D) {
                deltaHeight = H - B;
            } else if (B <= F && D <= H) {
                deltaHeight = D - F;
            } else if (B <= F && D <= H) {
                deltaHeight = H - F;
            } else if (F <= B && D <= H) {
                deltaHeight = D - B;
            }
            return total - deltaWidth * deltaHeight;
        }
    }
    
    @Test 
    public void testComputeArea() {
        RectangleArea obj = new RectangleArea();
        Assert.assertEquals(4, obj.computeArea(0, 0, 0, 0, -1, -1, 1, 1));
    }
}
