import java.util.HashSet;

class Solution {
    public int firstMissingPositive(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (num > 0) {
                set.add(num);
            }
        }

        int missing = 1;

        while (true) {
            if (!set.contains(missing)) {
                return missing;
            }
            missing++;
        }
    }
}