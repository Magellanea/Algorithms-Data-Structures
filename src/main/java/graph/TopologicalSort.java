package graph;


import java.util.*;

/**
 * Created by yakoub on 03/07/15.
 */
public class TopologicalSort {
    /**
     * Implement Kahn Topological Sort
     * Reference : https://courses.cs.washington.edu/courses/cse373/02au/lectures/lecture19l.pdf
     * @param adjList Adjacency list of the graph
     * @param inDegrees In-Degree of each node (the function will tamper with it)
     * @return A queue of the topological sort
     */
    public Queue<Integer> kahnTopo(LinkedHashMap<Integer, List<Integer>> adjList, int[] inDegrees) {
        // Adding priority queue for stable sort
        // i.e. if 1 and 2 have the same rank 1 will be before 2 in the topological order
        Queue<Integer> queue = new PriorityQueue<Integer>();
        Queue<Integer> topOrder = new LinkedList<Integer>();
        for(int i=1;i<inDegrees.length;i++)
            if(inDegrees[i] == 0) {
                queue.add(i);
                inDegrees[i] = -1;
            }

        while (!queue.isEmpty()){
            Integer nodeId = queue.poll();
            topOrder.add(nodeId);
            List<Integer> out = adjList.get(nodeId);
            if(out!=null) {
                for (Integer outElement : out) {
                    inDegrees[outElement]--;
                    if(inDegrees[outElement] == 0)
                        queue.add(outElement);
                }
            }
        }
        return topOrder;
    }
}
