import java.util.*;

class Solution {

    public int countCompleteComponents(int n, int[][] edges) {

        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                ArrayList<Integer> component = new ArrayList<>();
                dfs(i, graph, visited, component);

                int size = component.size();
                boolean complete = true;

                for (int node : component) {

                    if (graph[node].size() != size - 1) {
                        complete = false;
                        break;
                    }
                }

                if (complete) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private void dfs(int node, ArrayList<Integer>[] graph,
                     boolean[] visited, ArrayList<Integer> component) {

        visited[node] = true;
        component.add(node);

        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next, graph, visited, component);
            }
        }
    }
}