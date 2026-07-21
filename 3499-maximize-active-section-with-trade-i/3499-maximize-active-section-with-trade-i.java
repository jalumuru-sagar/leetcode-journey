class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();

        // Count original number of 1s
        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                ones++;
            }
        }

        // Augment string with 1 at both ends
        String t = "1" + s + "1";

        int maxGain = 0;
        int i = 0;

        while (i < t.length()) {

            // Find first block of zeros
            if (t.charAt(i) == '0') {
                int leftZeros = 0;

                while (i < t.length() && t.charAt(i) == '0') {
                    leftZeros++;
                    i++;
                }

                // Find block of ones between two zero blocks
                int j = i;
                while (j < t.length() && t.charAt(j) == '1') {
                    j++;
                }

                // Check if another zero block exists
                if (j < t.length()) {
                    int rightZeros = 0;
                    int k = j;

                    while (k < t.length() && t.charAt(k) == '0') {
                        rightZeros++;
                        k++;
                    }

                    maxGain = Math.max(maxGain, leftZeros + rightZeros);
                }
            } else {
                i++;
            }
        }

        return Math.min(n, ones + maxGain);
    }
}