class Solution {
    public String removeDuplicateLetters(String s) {

        int[] last = new int[26];
        boolean[] used = new boolean[26];

        // Store last occurrence of each character
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            // Skip if already added
            if (used[ch - 'a']) {
                continue;
            }

            // Remove bigger characters if they appear again later
            while (stack.length() > 0 &&
                   stack.charAt(stack.length() - 1) > ch &&
                   last[stack.charAt(stack.length() - 1) - 'a'] > i) {

                char removed = stack.charAt(stack.length() - 1);
                used[removed - 'a'] = false;
                stack.deleteCharAt(stack.length() - 1);
            }

            stack.append(ch);
            used[ch - 'a'] = true;
        }

        return stack.toString();
    }
}