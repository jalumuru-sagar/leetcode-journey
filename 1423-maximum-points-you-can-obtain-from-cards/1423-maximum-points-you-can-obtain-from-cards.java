class Solution {
    public int maxScore(int[] cardPoints, int k) {

        int n = cardPoints.length;

        // Take first k cards
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += cardPoints[i];
        }

        int maxScore = sum;

        int left = k - 1;
        int right = n - 1;

        // Replace left cards with right cards one by one
        while (left >= 0) {

            sum -= cardPoints[left];
            sum += cardPoints[right];

            maxScore = Math.max(maxScore, sum);

            left--;
            right--;
        }

        return maxScore;
    }
}