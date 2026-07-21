import java.util.*;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        
        int n = nums.length;
        int[] answer = new int[n];
        
        Arrays.fill(answer, -1);
        
        Stack<Integer> stack = new Stack<>();
        
        // Loop twice because array is circular
        for (int i = 0; i < 2 * n; i++) {
            
            int index = i % n;
            
            while (!stack.isEmpty() &&
                   nums[index] > nums[stack.peek()]) {
                
                int prevIndex = stack.pop();
                answer[prevIndex] = nums[index];
            }
            
            // Push indices only during first traversal
            if (i < n) {
                stack.push(index);
            }
        }
        
        return answer;
    }
}