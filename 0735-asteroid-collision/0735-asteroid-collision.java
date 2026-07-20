import java.util.Stack;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {

        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {

            boolean destroyed = false;

            // Collision happens only when stack top moves right
            // and current asteroid moves left
            while (!stack.isEmpty() &&
                   stack.peek() > 0 &&
                   asteroid < 0) {

                if (stack.peek() < -asteroid) {
                    // Top asteroid is smaller
                    stack.pop();
                }
                else if (stack.peek() == -asteroid) {
                    // Both are equal, destroy both
                    stack.pop();
                    destroyed = true;
                    break;
                }
                else {
                    // Current asteroid is smaller
                    destroyed = true;
                    break;
                }
            }

            if (!destroyed) {
                stack.push(asteroid);
            }
        }

        int[] result = new int[stack.size()];

        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }
}