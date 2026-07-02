import java.util.*;

class Solution {

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {

        int m = grid.size();
        int n = grid.get(0).size();

        int[][] best = new int[m][n];

        for (int[] row : best) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> queue = new LinkedList<>();

        int startHealth = health - grid.get(0).get(0);

        if (startHealth <= 0) {
            return false;
        }

        queue.offer(new int[]{0, 0, startHealth});
        best[0][0] = startHealth;

        int[] row = {-1, 1, 0, 0};
        int[] col = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];
            int h = current[2];

            if (r == m - 1 && c == n - 1) {
                return true;
            }

            for (int i = 0; i < 4; i++) {

                int nr = r + row[i];
                int nc = c + col[i];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {

                    int newHealth = h - grid.get(nr).get(nc);

                    if (newHealth > 0 && newHealth > best[nr][nc]) {

                        best[nr][nc] = newHealth;
                        queue.offer(new int[]{nr, nc, newHealth});
                    }
                }
            }
        }

        return false;
    }
}