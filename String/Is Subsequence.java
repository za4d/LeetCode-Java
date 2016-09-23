public class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException();
        }

        int i = 0;
        int j = 0;
        
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        
        return i == s.length();
    }
}
