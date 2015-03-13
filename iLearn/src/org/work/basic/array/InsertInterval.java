package org.work.basic.array;

import java.util.ArrayList;

public class InsertInterval {

	public static void main(String[] args) {
		Interval int1 = new Interval(2, 5);
		Interval int2 = new Interval(6, 7);
		Interval int3 = new Interval(8, 9);
		Interval newint = new Interval(0, 10);
		InsertInterval obj = new InsertInterval();
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		intervals.add(int1);
		intervals.add(int2);
		intervals.add(int3);
		ArrayList<Interval> res = obj.insert(intervals, newint);
	}
	
	  public ArrayList<Interval> insert2(ArrayList<Interval> intervals, Interval newInterval) {
	        // IMPORTANT: Please reset any member data you declared, as
	        // the same Solution instance will be reused for each test case.
	        ArrayList<Interval> result = new ArrayList<Interval>();
	        int i = 0;
	        int n = intervals.size();
	        while(i < n && newInterval.start > intervals.get(i).end){
	            result.add(intervals.get(i));
	            System.out.println(intervals.get(i));
	            i++;
	        }
	        while(i < n && newInterval.end >= intervals.get(i).start){
	            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
	            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
	            
	            i++;
	        }
	        result.add(newInterval);
	        System.out.println(newInterval);
	        while(i < n){
	            result.add(intervals.get(i));
	            System.out.println(intervals.get(i));
	            i++;
	        }
	        return result;
	    }
	  
	  public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
	        if(intervals == null){
	            intervals = new ArrayList<Interval>();
	            intervals.add(newInterval);
	        }else if(intervals.size() == 0)
	            intervals.add(newInterval);
	        else{
	            //find insert position
	            // int i = 0;
	            // while(i < n && intervals.get(i++).end < newInterval.start); //linear search the insert position
	            int i = findInsertPos(intervals, newInterval);//binary search, intervals[i].start > newInterval.start
	            
	            if(i == intervals.size() || newInterval.end < intervals.get(i).start){
	                intervals.add(i, newInterval);
	                return intervals;
	            }
	            
	            //insert and merge
	            intervals.add(i++, newInterval);
	            while(i < intervals.size() && intervals.get(i).start <= newInterval.end){
	                newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
	                intervals.remove(i);
	            }
	        }
//	        print(intervals);
	        return intervals;
	    }
	    public void print(ArrayList<Interval> intervals){
	    	for(Interval item : intervals)
	    		System.out.println(item.start + "\t" + item.end);
	    }
	    public int findInsertPos(ArrayList<Interval> intervals, Interval newInterval){
	        int left = 0;
	        int right = intervals.size() - 1;
	        int mid = left;
	        Interval curr = null;
	        while(left <= right){
	            mid = left + (right - left >> 1);
	            curr = intervals.get(mid);
	            if(curr.start == newInterval.start)
	                return mid;
	            else if(curr.start < newInterval.start)
	                left = mid + 1;
	            else
	                right = mid - 1;
	        }
	       return left;
	    }

}
