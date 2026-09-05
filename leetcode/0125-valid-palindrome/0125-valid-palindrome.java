class Solution {
    public boolean isPalindrome(String s) {
        // Use two pointers starting from both ends
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // Move left pointer forward if character is not a letter or number
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            } 
            // Move right pointer backward if character is not a letter or number
            else if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            } 
            // Compare characters after converting them to lowercase
            else {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}
