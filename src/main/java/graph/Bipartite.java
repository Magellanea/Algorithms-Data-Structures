package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yakoub on 05/07/15.
 */
public class Bipartite {
    /**
     * Check if a given graph is bipartite or not
     * @param adjList The adjacency list (1 - based)
     * @return a boolean indicating  if a given graph is bipartite or not
     */
    public static boolean isBipartite(List<Integer> adjList[]) {
        int WHITE = 1;
        int BLACK = -1;
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] color = new int[adjList.length];
        //use loop for unconnected graphs
        for (int i = 1; i < adjList.length; i++) {
            if (color[i] == 0) {
                queue.add(1);
                color[1] = WHITE;
            }
            while (!queue.isEmpty()) {
                int node = queue.poll();
                int nodeColor = color[node];
                List<Integer> adjacent = adjList[node];
                for (int element : adjacent) {
                    // for undirected graphs
                    if (color[element] == 0) {
                        color[element] = -1 * nodeColor;
                        queue.add(element);
                    } else if (color[element] == nodeColor)
                        return false;
                }
            }
        }
        return true;
    }
}
