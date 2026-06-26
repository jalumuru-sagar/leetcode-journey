class Solution {
    public boolean repeatedSubstringPattern(String s) {

        int n = s.length();

        for (int i = 1; i <= n / 2; i++) {

            if (n % i == 0) {

                String sub = s.substring(0, i);
                String result = "";

                int times = n / i;

                for (int j = 0; j < times; j++) {
                    result += sub;
                }

                if (result.equals(s)) {
                    return true;
                }
            }
        }

        return false;
    }
}