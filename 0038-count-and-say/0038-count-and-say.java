class Solution {
    public String countAndSay(int n) {

        String result = "1";

        for (int i = 2; i <= n; i++) {

            String temp = "";
            int count = 1;

            for (int j = 0; j < result.length(); j++) {

                while (j < result.length() - 1 &&
                       result.charAt(j) == result.charAt(j + 1)) {
                    count++;
                    j++;
                }

                temp = temp + count + result.charAt(j);
                count = 1;
            }

            result = temp;
        }

        return result;
    }
}