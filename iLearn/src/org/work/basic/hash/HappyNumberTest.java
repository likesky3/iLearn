package org.work.basic.hash;

import java.util.HashSet;

import junit.framework.Assert;

import org.junit.Test;

public class HappyNumberTest {
    @Test
    public void testIsHappy() {
        HappyNumber obj = new HappyNumber();
        // Assert.assertEquals(true, obj.isHappy(19));
//        Assert.assertEquals(true, obj.isHappy(40));
        Assert.assertEquals(true, obj.isHappy(7));
    }
}

class HappyNumber {
    public boolean isHappy(int n) {
        HashSet<Integer> values = new HashSet<Integer>();
        int newVal = 0;
        int mod = 0;
        while (n != 1) {
            while (n > 0) {
                mod = n % 10;
                n = n / 10;
                newVal += mod * mod;
            }
            System.out.println(newVal);
            if (values.contains(newVal))
                return false;
            values.add(newVal);
            n = newVal;
            newVal = 0;
        }
        return true;
    }
}