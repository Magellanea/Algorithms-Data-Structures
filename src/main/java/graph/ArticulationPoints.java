package graph;

import java.util.List;

/**
 * Created by yakoub on 07/07/15.
 */
public class ArticulationPoints {

        public static int dfsDepth;
        public static List<Integer>[] adjList;
        public static int[] dfsNum;
        public static int[] dfsLow;
        public static int[] dfsParent;
        public static int UNVISITED = -1;
        public static int rootChildren = 0;
        public static int rootNode;
        public static boolean[] articulateNodes;

    /**
     * Evaluate articulation points using Tarjan Algorithm.
     * To run the code check this method over all unvisited nodes, add
     * edge twice for undirected graphs
     * @param node The given node
     */
        public static void findArticulatePoints(int node) {
            dfsNum[node] = dfsLow[node] = dfsDepth++;
            for (int element : adjList[node]) {
                if (dfsNum[element] == UNVISITED) {
                    if (node == rootNode) rootChildren++;
                    dfsParent[element] = node;
                    findArticulatePoints(element);
                    if ((dfsParent[node]!=0) && (dfsLow[element] >= dfsNum[node]))
                        //the node doesn't back-connect to an ancestor
                        articulateNodes[node] = true;
                    dfsLow[node] = Math.min(dfsLow[node], dfsLow[element]);

                } else if (element != dfsParent[node])
                    dfsLow[node] = Math.min(dfsLow[node], dfsNum[element]);
            }
        }

}
