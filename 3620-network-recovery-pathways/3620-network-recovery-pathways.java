import java.util.*;

class Solution {

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {

        int n = online.length;

        List<int[]>[] graph = new ArrayList[n];
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int maxCost = 0;

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            indegree[e[1]]++;
            maxCost = Math.max(maxCost, e[2]);
        }

        // Topological order
        int[] topo = new int[n];
        int index = 0;

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            topo[index++] = u;

            for (int[] edge : graph[u]) {
                indegree[edge[0]]--;
                if (indegree[edge[0]] == 0) {
                    q.offer(edge[0]);
                }
            }
        }

        int low = 0;
        int high = maxCost;
        int ans = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (check(mid, graph, topo, online, k)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean check(int limit, List<int[]>[] graph, int[] topo,
                          boolean[] online, long k) {

        int n = online.length;

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        dist[0] = 0;

        for (int u : topo) {

            if (dist[u] == Long.MAX_VALUE) {
                continue;
            }

            if (u != 0 && u != n - 1 && !online[u]) {
                continue;
            }

            for (int[] edge : graph[u]) {

                int v = edge[0];
                int cost = edge[1];

                if (cost < limit) {
                    continue;
                }

                if (v != n - 1 && !online[v]) {
                    continue;
                }

                if (dist[u] + cost < dist[v]) {
                    dist[v] = dist[u] + cost;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}