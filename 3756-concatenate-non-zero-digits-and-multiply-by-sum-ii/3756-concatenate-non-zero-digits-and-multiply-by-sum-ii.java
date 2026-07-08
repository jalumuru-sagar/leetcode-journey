import java.util.*;

class Solution {

    static final int MOD = 1000000007;

    public int[] sumAndMultiply(String s, int[][] queries){

        int n = s.length();

        // powers of 10
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // prefix sum of non-zero digits
        int[] digitSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            digitSum[i + 1] = digitSum[i];
            if (s.charAt(i) != '0')
                digitSum[i + 1] += s.charAt(i) - '0';
        }

        // Segment Tree
        SegmentTree tree = new SegmentTree(s);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int l = queries[i][0];
            int r = queries[i][1];

            Node res = tree.query(1, 0, n - 1, l, r);

            long x = res.value;
            long sum = digitSum[r + 1] - digitSum[l];

            ans[i] = (int) ((x * sum) % MOD);
        }

        return ans;
    }

    class Node {
        long value;
        int len;

        Node(long value, int len) {
            this.value = value;
            this.len = len;
        }
    }

    class SegmentTree {

        Node[] tree;
        long[] pow10;

        SegmentTree(String s) {

            int n = s.length();

            tree = new Node[4 * n];

            pow10 = new long[n + 1];
            pow10[0] = 1;

            for (int i = 1; i <= n; i++)
                pow10[i] = (pow10[i - 1] * 10) % MOD;

            build(1, 0, n - 1, s);
        }

        void build(int idx, int l, int r, String s) {

            if (l == r) {

                char c = s.charAt(l);

                if (c == '0')
                    tree[idx] = new Node(0, 0);
                else
                    tree[idx] = new Node(c - '0', 1);

                return;
            }

            int mid = (l + r) / 2;

            build(idx * 2, l, mid, s);
            build(idx * 2 + 1, mid + 1, r, s);

            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
        }

        Node merge(Node a, Node b) {

            long value = (a.value * pow10[b.len]) % MOD;
            value = (value + b.value) % MOD;

            return new Node(value, a.len + b.len);
        }

        Node query(int idx, int l, int r, int ql, int qr) {

            if (ql > r || qr < l)
                return new Node(0, 0);

            if (ql <= l && r <= qr)
                return tree[idx];

            int mid = (l + r) / 2;

            Node left = query(idx * 2, l, mid, ql, qr);
            Node right = query(idx * 2 + 1, mid + 1, r, ql, qr);

            return merge(left, right);
        }
    }
}