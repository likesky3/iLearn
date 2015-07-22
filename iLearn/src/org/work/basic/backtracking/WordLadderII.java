package org.work.basic.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordLadderII {

    public static void main(String[] args) {
        // "hot", "dog", ["hot","dog","dot"]
        // "game", "thee", ["frye","heat","tree","thee","game","free","hell","fame","faye"]
        // "ta", "if", ["ts","sc","ph","ca","jr","hf","to","if","ha","is","io","cf","ta"]
        WordLadderII obj = new WordLadderII();
/*        String[] words = {"wilt","vows","pity","shat","foot","eden","boom",
                "anne","huhs","java","gnus","yeps","floe","eyck","died","flab",
                "urge","styx","iota","gybe","grit","anus","crab","zebu","pods",
                "cabs","kurt","bald","rips","pace","jays","acts","ryan","acne",
                "inti","snip","heep","cruz","grin","last","rake","rush","whir",
                "crew","kane","rasp","slop","shim","fain","howl","tuns","bias",
                "junk","zeke","jock","kens","rope","mars","arty","tuna","naps",
                "pols","judo","gone","rule","sale","guff","jove","porn","self",
                "etta","sump","ibex","saar","grus","rive","arid","face","ante",
                "grab","hobs","clam","brad","zits","alps","acid","grub","fade",
                "sulk","rent","nick","puns","west","pies","quiz","colo","hock",
                "whiz","dick","zibo","hack","care","hurl","open","lift","fend",
                "pros","beth","neva","drag","colt","ants","tamp","amen","visa",
                "nets","ache","turd"};*/
//        String[] words = {"ts","sc","ph","ca","jr","hf","to","if","ha","is","io","cf","ta"};
        String[] words = {"a", "b", "c"};
        List<String> list = Arrays.asList(words);
        Set<String> dict = new HashSet<>();
        dict.addAll(list);
        long start = System.currentTimeMillis();
//        List<List<String>> result = obj.findLadders3("a", "c", dict);
//        System.out.println(result.size());
//        result = obj.findLadders1("ta", "if", dict);
//        System.out.println(result.size());
        List<List<String>> result = obj.findLadders("a", "c", dict);
        System.out.println(result.size());
//        System.out.println(System.currentTimeMillis() - start);
    }
    
    public List<List<String>> findLadders3(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (dict == null || dict.size() == 0 || start == null || end == null)
            return result;
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        LinkedList<String> queue = new LinkedList<String>();
        queue.offer(end);
        int currLevel = 0, nextLevel = 1;
        int L = start.length();
        HashSet<String> set = new HashSet<String>();
        set.add(end);
        boolean done = false;
        int counter = 0;
        while (!queue.isEmpty()) {
            if (currLevel == 0) {
                if (done) break;
                currLevel = nextLevel;
                nextLevel = 0;
                dict.removeAll(set);
                set.clear();
            }
            String currStr = queue.poll();
            char[] curr = currStr.toCharArray();
            for (int i = 0; i < L; i++) {
                char oldChar = curr[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    counter++;
                    if (c == oldChar) continue;
                    curr[i] = c;
                    String newStr = new String(curr);
                    if (dict.contains(newStr)) {
                        ArrayList<String> nextList = null;
                        if (!map.containsKey(newStr)) {
                            nextList = new ArrayList<String>();
                        } else {
                            nextList = map.get(newStr);
                        }
                        nextList.add(currStr);
                        map.put(newStr, nextList);
                        if (!newStr.equals(start)) {
                            set.add(newStr);
                            queue.offer(newStr);
                            nextLevel++;
                        } else {
                            // 找到一条最短路径，该单词其他转换没有必要检查
                            done = true;
                            break;
                        }
                    }
                }
                if (done) break;
                curr[i] = oldChar;
            }
            currLevel--;
        }
        System.out.println("linlin=" + counter );
        if (done) {
            List<String> item =  new ArrayList<String>();
            item.add(start);
            getPath(map, result, item, start, end);
        }
        return result;
    }
    
    public void getPath(HashMap<String, ArrayList<String>> map, List<List<String>> result, 
        List<String> item, String start, String end) {
        if (start.equals(end)) {
            result.add(new ArrayList<String>(item));
            return;
        }
        ArrayList<String> nextList = map.get(start);
        for (String next : nextList) {
            item.add(next);
            getPath(map, result, item, next, end);
            item.remove(item.size() - 1);
        }
    }
    
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<List<String>>();
        LinkedList<String> queue = new LinkedList<String>();
        queue.offer(end);
        HashMap<String, ArrayList<String>> nextMap = new HashMap<String, ArrayList<String>>();
        HashSet<String> visited = new HashSet<String>();
        dict.remove(end);
        dict.add(start);
        int currLevel = 1, nextLevel = 0;
        int N = start.length();
        boolean done = false;
        while (!queue.isEmpty()) {
            if (currLevel == 0) {
                if (done) break;
                currLevel = nextLevel;
                nextLevel = 0;
                dict.removeAll(visited);
                visited.clear();
            }
            String currStr = queue.poll();
            char[] currCharArray = currStr.toCharArray();
            for (int i = 0; i < N; i++) {
                char oldChar = currCharArray[i];
                boolean endCurrStr = false;
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == oldChar) continue;
                    currCharArray[i] = c;
                    String newStr = new String(currCharArray);
                    if (dict.contains(newStr)) {
                        if (!nextMap.containsKey(newStr)) {
                            nextMap.put(newStr, new ArrayList<String>());
                        }
                        nextMap.get(newStr).add(currStr);
                        if (newStr.equals(start)) {
                            done = true;
                            endCurrStr = true;
                            break;
                        }
                        if (visited.add(newStr)) {
                            queue.offer(newStr);
                            nextLevel++;
                        }
                    }
                }
                if (endCurrStr) break;
                currCharArray[i] = oldChar;
            }
            currLevel--;
        }
        if (done) {
            ArrayList<String> item = new ArrayList<String>();
            item.add(start);
            getPath(start, end, item, result, nextMap);
        }
        return result;
    }
    private void getPath(String curr, String end, ArrayList<String> item, List<List<String>> result,
        HashMap<String, ArrayList<String>> nextMap) {
        if (curr.equals(end)) {
            result.add(new ArrayList<String>(item));
        } else {
            ArrayList<String> nextList = nextMap.get(curr);
            for (String next : nextList) {
                item.add(next);
                getPath(next, end, item, result, nextMap);
                item.remove(item.size() - 1);
            }
        }
    }
    // http://blog.csdn.net/linhuanmars/article/details/23071455
    class StringWithLevel {
        String str;
        int level;
        public StringWithLevel(String str, int level) {
            this.str = str;
            this.level = level;
        }
    }
    
    /*public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<List<String>>();
        LinkedList<StringWithLevel> queue = new LinkedList<StringWithLevel>();
        queue.add(new StringWithLevel(end, 0));
        HashMap<String, ArrayList<String>> nextMap = new HashMap<String, ArrayList<String>>();
        HashSet<String> visited = new HashSet<String>();
        // HashSet<String> unvisited = new HashSet<String>(dict.size());
        // unvisited.addAll(dict);
        // unvisited.add(start);
        // unvisited.remove(end);
        dict.add(start);
        dict.remove(end);
        int currLevel = 0, preLevel = 0;
        int finalLevel = Integer.MAX_VALUE;
        int L = start.length();
        boolean found = false;
        while (!queue.isEmpty()) {
            StringWithLevel curr = queue.poll();
            String currStr = curr.str;
            currLevel = curr.level;
            if (currLevel > preLevel) {
                if (found) break;
                preLevel = currLevel;
                dict.removeAll(visited);
                visited.clear();
            }
            char[] currCharArray = currStr.toCharArray();
            for (int i = 0; i < L; i++) {
                char oldChar = currCharArray[i];
                boolean foundCurCycle = false; // 没有这个标记会提前跳出，为啥？
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == oldChar) continue;
                    currCharArray[i] = c;
                    String newStr = new String(currCharArray);
                    if (dict.contains(newStr)) {
                        ArrayList<String> nextList = null;
                        if (nextMap.containsKey(newStr)) {
                            nextList = nextMap.get(newStr);
                        } else {
                            nextList = new ArrayList<String>();
                        }
                        nextList.add(currStr);
                        nextMap.put(newStr, nextList);
                        if (newStr.equals(start)) {
                            found = true;
                            foundCurCycle = true;
                            break;
                        }
                        if (visited.add(newStr))
                            queue.offer(new StringWithLevel(newStr, currLevel + 1)); // enqueue a new item of next level
                    }
                }
                currCharArray[i] = oldChar;
                if (foundCurCycle) break; // 使用found标记会提前跳出
            }
        }
        if (found) {
            List<String> item = new ArrayList<String>();
            item.add(start);
            getPath(start, end, item, nextMap, result);
        }
        return result;
    }
    
    public void getPath(String start, String end, List<String> item, 
        HashMap<String, ArrayList<String>> nextMap, List<List<String>> result) {
        if (start.equals(end)) {
            result.add(new ArrayList<String>(item));
        } else {
            ArrayList<String> nextList = nextMap.get(start);
            for (String next : nextList) {
                item.add(next);
                getPath(next, end, item, nextMap, result);
                item.remove(item.size() - 1);
            }
        }
    }*/
    /*
    public ArrayList<ArrayList<String>> findLadders2(String start, String end, Set<String> dict) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        HashSet<String> unvisitedSet = new HashSet<String>();
        unvisitedSet.addAll(dict);
        unvisitedSet.add(start);
        unvisitedSet.remove(end);
        Map<String, List<String>> nextMap = new HashMap<String, List<String>>();
        for (String e : unvisitedSet) {
           nextMap.put(e, new ArrayList<String>());
        }
        LinkedList<StringWithLevel> queue = new LinkedList<StringWithLevel>();
        queue.add(new StringWithLevel(end, 0));
        boolean found = false;
        int finalLevel = Integer.MAX_VALUE;
        int curLevel = 0;
        int preLevel = 0;
        HashSet<String> visitedCurLevel = new HashSet<String>();
        while (!queue.isEmpty()) {
           StringWithLevel cur = queue.poll();
           String curStr = cur.str;
           curLevel = cur.level;
           if (curLevel > preLevel) { // 结束以层遍历，开始下一层
             if (found) break;
             unvisitedSet.removeAll(visitedCurLevel);
             preLevel = curLevel;
           }
           
           char[] curStrCharArray = curStr.toCharArray();
           for (int i = 0; i < curStr.length(); ++i) {
              char originalChar = curStrCharArray[i];
              boolean foundCurCycle = false;
              for (char c = 'a'; c <= 'z'; ++c) {
                 curStrCharArray[i] = c;
                 String newStr = new String(curStrCharArray);
                 if(c != originalChar && unvisitedSet.contains(newStr)) {
                    nextMap.get(newStr).add(curStr);
                    if(newStr.equals(start)) {
                       found = true;
                       finalLevel = curLevel;
                       foundCurCycle = true;
                       break;
                    }
                    if(visitedCurLevel.add(newStr)) {
                       queue.add(new StringWithLevel(newStr, curLevel + 1));
                    }
                 }
              }
              if(foundCurCycle) {
                 break;
              }
              curStrCharArray[i] = originalChar;
          }
        }
        if(found) {
            ArrayList<String> list = new ArrayList<String>();
            list.add(start);
            getPaths(start, end, list, nextMap, res);
        }
        return res;
     }
     private void getPaths(String cur, String end, ArrayList<String> list, 
             Map<String, List<String>> nextMap, ArrayList<ArrayList<String>> res) {
        if(cur.equals(end)){
           res.add(new ArrayList<String>(list));
        } else {
           List<String> parentsSet = nextMap.get(cur);
           for (String parent : parentsSet) {
              list.add(parent);
              getPaths(parent, end, list,nextMap, res);
              list.remove(list.size() - 1);
           }
        }
     }*/
     
    public List<List<String>> findLadders1(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (dict == null || dict.size() == 0 || start == null || end == null)
            return result;
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        LinkedList<String> queue = new LinkedList<String>();
        queue.offer(start);
        int currLevel = 0, nextLevel = 1;
        int L = start.length();
        HashSet<String> set = new HashSet<String>();
        set.add(start);
        boolean done = false;
        int counter = 0;
        while (!queue.isEmpty()) {
            if (currLevel == 0) {
                if (done) break;
                currLevel = nextLevel;
                nextLevel = 0;
                for (String str : set) 
                    dict.remove(str);
                set.clear();
            }
            String currStr = queue.poll();
            char[] curr = currStr.toCharArray();
            for (int i = 0; i < L; i++) {
                char oldChar = curr[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    counter++;
                    if (c == oldChar) continue;
                    curr[i] = c;
                    String newStr = new String(curr);
                    if (dict.contains(newStr)) {
                        ArrayList<String> prevs = null;
                        if (!map.containsKey(newStr)) {
                            prevs = new ArrayList<String>();
                        } else {
                            prevs = map.get(newStr);
                        }
                        prevs.add(currStr);
                        map.put(newStr, prevs);
                        if (!newStr.equals(end)) {
                            set.add(newStr);
                            queue.offer(newStr);
                            nextLevel++;
                        } else {
                            // 找到一条最短路径，该单词其他转换没有必要检查
                            done = true;
                            break;
                        }
                    }
                }
                if (done) break;
                curr[i] = oldChar;
            }
            currLevel--;
        }
        System.out.println("method 1=" + counter);
        if (map.containsKey(end))
            getPath1(map, result, new ArrayList<String>(), start, end);
        return result;
    }
    
    public void getPath1(HashMap<String, ArrayList<String>> map, List<List<String>> result, 
        List<String> item, String start, String curr) {
        if (start.equals(curr)) {
            item.add(0, start);
            List<String> cloneItem = new ArrayList<String>();
            cloneItem.addAll(item);
            result.add(cloneItem);
            return;
        }
        
        item.add(0, curr);
        ArrayList<String> prevs = map.get(curr);
        for (String prev : prevs) {
            getPath1(map, result, item, start, prev);
        }
        item.remove(0);
    }
}
