import java.util.LinkedHashMap;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;
public class TopologicalSort{
	/**
	   Implements Kahn Topological Sort
	   Reference : https://courses.cs.washington.edu/courses/cse373/02au/lectures/lecture19l.pdf
	   @param adjList: An adjacency list of the the graph.
	   @param inDegrees: in degree of each node.
	   @return topOrder : A queue of the topological sort.
	 **/
	public static Queue<Integer> kahnTopo(LinkedHashMap<Integer, List<Integer>> adjList, int[] inDegrees) {
		//use a priorty queue in case of stable sort i.e. same rank elements will be ordered according
		//to their value for example if 1,2 have the same rank 1 will appear before 2.
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
