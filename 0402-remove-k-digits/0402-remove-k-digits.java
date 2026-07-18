class Solution {
    public String removeKdigits(String num, int k) {

        StringBuilder stack = new StringBuilder();

        for (char digit : num.toCharArray()) {

            while (k > 0 && stack.length() > 0 &&
                   stack.charAt(stack.length() - 1) > digit) {
                stack.deleteCharAt(stack.length() - 1);
                k--;
            }

            stack.append(digit);
        }

        // Remove remaining digits from end
        while (k > 0) {
            stack.deleteCharAt(stack.length() - 1);
            k--;
        }

        // Remove leading zeros
        int index = 0;
        while (index < stack.length() && stack.charAt(index) == '0') {
            index++;
        }

        String result = stack.substring(index);

        return result.length() == 0 ? "0" : result;
    }
}