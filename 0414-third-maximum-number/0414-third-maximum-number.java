import java.util.Arrays;

class Solution {
    public int thirdMax(int[] nums) {

        Arrays.sort(nums);

        int count = 1;
        int third = nums[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; i--) {

            if (nums[i] != nums[i + 1]) {
                count++;
                third = nums[i];
            }

            if (count == 3) {
                return third;
            }
        }

        return nums[nums.length - 1];
    }
}