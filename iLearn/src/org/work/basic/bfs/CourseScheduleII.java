package org.work.basic.bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class CourseScheduleII {

    public static void main(String[] args) {
        int[][] prerequisites = {{5,8},{3,5},{1,9},{4,5},{0,2},{1,9},{7,8},{4,9}};
        CourseScheduleII obj = new CourseScheduleII();
        int[] result = obj.findOrder(10, prerequisites);
        for (int i : result)
            System.out.println(i);
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
            for (int i = 0; i < numCourses; i++)
                result[i] = i;
            return result;
        }
        //build graph
        ArrayList<HashSet<Integer>> graph = new ArrayList<HashSet<Integer>>(numCourses);
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++)
            graph.add(new HashSet<Integer>());
        int edges = prerequisites.length;
        for (int i = 0; i < edges;i++) {
            if (graph.get(prerequisites[i][1]).add(prerequisites[i][0]))
                indegree[prerequisites[i][0]]++;
        }
        //search
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }
        int counter = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            System.out.println(course);
            result[counter] = course;
            counter++;
            for (int w : graph.get(course)) {
                if (--indegree[w] == 0) queue.offer(w);
            }
        }
        System.out.println("@@" + counter);
        if (counter == numCourses)
            return result;
        else
            return new int[0];
    }
}
