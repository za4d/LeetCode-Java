public class Solution {
    public String longestPalindrome(String s) {
        String res = "";
        
        for (int i = 0; i < s.length(); i++) {
            String candidate1 = expandAroundCenter(s, i, i);
            String candidate2 = expandAroundCenter(s, i, i + 1);
            
            if (candidate1.length() > res.length()) {
                res = candidate1;
            }
            
            if (candidate2.length() > res.length()) {
                res = candidate2;
            }
        }
        
        return res;
    }
    
    private String expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        
        return right - left - 1 > 0 ? s.substring(left + 1, right) : "";
    }
}

public class Solution {
    public String longestPalindrome(String s) {
        int start = 0;
        int end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            int len = Math.max(expandAroundCenter(s, i, i), expandAroundCenter(s, i, i + 1));
            
            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        
        return s.substring(start, end + 1);
    }
    
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        
        return right - left - 1;
    }
}
