class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {

        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        long[] count = new long[max + 1];

        // Frequency of each number
        for (int num : nums) {
            count[num]++;
        }

        long[] gcdPairs = new long[max + 1];

        // Count pairs whose GCD is exactly g
        for (int g = max; g >= 1; g--) {

            long total = 0;

            // Count numbers divisible by g
            for (int multiple = g; multiple <= max; multiple += g) {
                total += count[multiple];
            }

            gcdPairs[g] = total * (total - 1) / 2;

            // Remove pairs having GCD greater than g
            for (int multiple = g * 2; multiple <= max; multiple += g) {
                gcdPairs[g] -= gcdPairs[multiple];
            }
        }

        // Prefix sum:
        // prefix[g] = number of pairs with GCD <= g
        long[] prefix = new long[max + 1];

        for (int g = 1; g <= max; g++) {
            prefix[g] = prefix[g - 1] + gcdPairs[g];
        }

        int[] answer = new int[queries.length];

        // Binary search for each query
        for (int i = 0; i < queries.length; i++) {

            long target = queries[i] + 1;

            int left = 1;
            int right = max;

            while (left < right) {

                int mid = left + (right - left) / 2;

                if (prefix[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            answer[i] = left;
        }

        return answer;
    }
}