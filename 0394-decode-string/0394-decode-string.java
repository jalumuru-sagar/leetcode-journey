import java.util.*;

class Solution {
    public String decodeString(String s) {

        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();

        String current = "";
        int number = 0;

        for (char ch : s.toCharArray()) {

            if (Character.isDigit(ch)) {

                number = number * 10 + (ch - '0');

            } else if (ch == '[') {

                countStack.push(number);
                stringStack.push(current);

                number = 0;
                current = "";

            } else if (ch == ']') {

                int repeat = countStack.pop();

                String previous = stringStack.pop();

                String temp = "";

                for (int i = 0; i < repeat; i++) {
                    temp += current;
                }

                current = previous + temp;

            } else {

                current += ch;
            }
        }

        return current;
    }
}