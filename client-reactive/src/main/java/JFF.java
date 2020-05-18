import java.util.Arrays;

public class JFF {
    public int lengthOfLongestSubstring(String s) {
        if(s.isEmpty())
            return 0;
        int MAX = 0;
        int start = 0, end = 0;
        boolean chars[] = new boolean[256];
        Arrays.fill(chars, false);
        while(true) {
            if(start >= s.length() || end >= s.length())
                break;

            // iterate ends until duplicates
            while(end < s.length() && !chars[s.charAt(end)]) {
                chars[s.charAt(end)] = true;
                end++;
            }
            end--;

            // remember the result
            if(end - start + 1 > MAX) {
                System.out.println("{" + start + ", " + end + "}");
                MAX = end - start + 1;
            }

            if(end == s.length() - 1)
                break;

            // iterate start to remove duplicates
            while(s.charAt(start) != s.charAt(end+1)) {
                chars[s.charAt(start)] = false;
                start++;
            }
            chars[s.charAt(end+1)] = false;
            // continue
            end++;start++;
        }

        return MAX;
    }

    public static void main(String[] args) {
        System.out.println(new JFF().lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(new JFF().lengthOfLongestSubstring("bcaabcbb")); // 3
        System.out.println(new JFF().lengthOfLongestSubstring("bbbbb")); // 1
        System.out.println(new JFF().lengthOfLongestSubstring(" ")); // 1
        System.out.println(new JFF().lengthOfLongestSubstring("pwwkew")); // 3
        System.out.println(new JFF().lengthOfLongestSubstring("dvdf")); // 3
    }
}
