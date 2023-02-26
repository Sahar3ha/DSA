package DSA.Assignment;

public class Question3 {
    public static boolean matchesPattern(String str, String pattern) {
        int n = str.length();
        int m = pattern.length();
        int i = 0, j = 0;

        while (i < n && j < m) {
            if (pattern.charAt(j) == '@') {
                j++;
                // Match the rest of the string
                while (i < n && j < m && str.charAt(i) == pattern.charAt(j)) {
                    i++;
                    j++;
                }
                // If we have reached the end of the pattern, the pattern matches the string
                if (j == m) {
                    return true;
                }
                // If not, move the pointer in the string back to the previous match position
                while (i > 0 && str.charAt(i - 1) != pattern.charAt(j - 1)) {
                    i--;
                }
            } else if (pattern.charAt(j) == '#') {
                // If pattern contains '#', match any single character
                i++;
                j++;
            } else {
                // If pattern contains a character, it should match the corresponding character in the string
                if (pattern.charAt(j) != str.charAt(i)) {
                    return false;
                }
                i++;
                j++;
            }
        }

        // If we have reached the end of both the string and the pattern, the pattern matches the string
        return i == n && j == m;
    }
}
