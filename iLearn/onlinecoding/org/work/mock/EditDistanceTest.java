package org.work.mock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EditDistanceTest {
    @Test
    public void testGetMinEditDistance() {
        EditDistance ed = new EditDistance();
        assertEquals(true, ed.runEditOperation("abc", "edf", 0, 0, 0));
        assertEquals(true, ed.runEditOperation("", "", 1, 1, 1));
        assertEquals(true, ed.runEditOperation("abc", "abc", 1, 1, 1));
        assertEquals(true, ed.runEditOperation("abcd", "abc", 1, 0, 1));
        assertEquals(true, ed.runEditOperation("abc", "adc", 2, 2, 1));
        assertEquals(true, ed.runEditOperation("abc", "dabc", 1, 0, 0));
        assertEquals(5, ed.getMinEditDistance("kitten", "sitting", 1, 1, 10));
        assertEquals(true, ed.runEditOperation("ab", "ed", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals(true, ed.runEditOperation("ab", "", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals(true, ed.runEditOperation("", "ab", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));

        assertEquals(true, ed.runEditOperation("", null, 1, 2, 3));
        assertEquals(true, ed.runEditOperation("", "a", 1, 2, 3));
        assertEquals(true, ed.runEditOperation("a", "", 1, 2, 3));
        assertEquals(3, ed.getMinEditDistance("b", "a", 1, 2, 3));
        assertEquals(true, ed.runEditOperation("ac", "ad", 1, 1, 1));
        assertEquals(true, ed.runEditOperation("ab", null, 1, 1, 1));
        assertEquals(true, ed.runEditOperation(null, "ab", 1, 1, 1));
    }
    
    @Test
    public void testRunEditOperation() {
        EditDistance ed = new EditDistance();
        assertEquals(true, ed.runEditOperation("abc", "edf", 0, 0, 0));
        assertEquals(true, ed.runEditOperation("", "", 1, 1, 1));
        assertEquals(true, ed.runEditOperation("abc", "abc", 1, 1, 1));
        assertEquals(true, ed.runEditOperation("abcd", "abc", 1, 0, 1));
        assertEquals(true, ed.runEditOperation("abc", "adc", 2, 2, 1));
        assertEquals(true, ed.runEditOperation("abc", "dabc", 1, 0, 0));
        assertEquals(true, ed.runEditOperation("kitten", "sitting", 1, 1, 10));
        assertEquals(true, ed.runEditOperation("ab", "ed", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals(true, ed.runEditOperation("ab", "", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals(true, ed.runEditOperation("", "ab", Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));

        assertEquals(true, ed.runEditOperation("", null, 1, 2, 3));
        assertEquals(true, ed.runEditOperation("", "a", 1, 2, 3));
        assertEquals(true, ed.runEditOperation("a", "", 1, 2, 3));
        assertEquals(true, ed.runEditOperation("b", "a", 1, 2, 3));
        assertEquals(true, ed.runEditOperation("ac", "ad", 1, 1, 1));
        assertEquals(true, ed.runEditOperation("ab", null, 1, 1, 1));
        assertEquals(true, ed.runEditOperation(null, "ab", 1, 1, 1));
    }
    
    @Test
    public void testMatchWithMinEditDistance() {
        EditDistance ed = new EditDistance();
        assertEquals(0, ed.matchWithMinEditDistance("", "", 1, 1, 1));
        assertEquals(3, ed.matchWithMinEditDistance("abc", "", 1, 1, 1));
        assertEquals(0, ed.matchWithMinEditDistance("abc", "eabcd", 1, 1, 1));
        assertEquals(3, ed.matchWithMinEditDistance("abc", "edf", 1, 1, 1));
    }
    
    @Test
    public void testMatchWithMinEditDistance2() {
        EditDistance ed = new EditDistance();
        assertEquals(0, ed.matchWithMinEditDistance2("", "", 1, 1, 1));
        assertEquals(3, ed.matchWithMinEditDistance2("abc", "", 1, 1, 1));
        assertEquals(0, ed.matchWithMinEditDistance2("abc", "eabcd", 1, 1, 1));
        assertEquals(3, ed.matchWithMinEditDistance2("abc", "edf", 1, 1, 1));
    }
}
