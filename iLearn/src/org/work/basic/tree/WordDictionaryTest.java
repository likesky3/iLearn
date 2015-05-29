package org.work.basic.tree;

import junit.framework.Assert;

import org.junit.Test;


public class WordDictionaryTest {
    @Test
    public void testSearch() {
        WordDictionary dict = new WordDictionary();
        // addWord("at"),addWord("and"),addWord("an"),addWord("add"),search("a"),
        // search(".at"),addWord("bat"),search(".at"),search("an."),search("a.d."),search("b."),search("a.d"),search(".")
        dict.addWord("at");
        dict.addWord("and");
        dict.addWord("an");
        dict.addWord("add");
        Assert.assertEquals(false, dict.search("a"));
        Assert.assertEquals(false, dict.search(".at"));
        dict.addWord("bat");
        Assert.assertEquals(true, dict.search(".at"));
        Assert.assertEquals(true, dict.search("an."));
        Assert.assertEquals(false, dict.search("a.d."));
        Assert.assertEquals(false, dict.search("b."));
        Assert.assertEquals(true, dict.search("a.d"));
        Assert.assertEquals(false, dict.search("."));
        
    }
}

class WordDictionary {
    
    private Trie dict;
    public WordDictionary() {
        dict = new Trie();
    }
    // Adds a word into the data structure.
    public void addWord(String word) {
        dict.insert(word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return dict.search(word);
    }

}


