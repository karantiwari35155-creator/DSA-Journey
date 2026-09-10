class Solution {
    public int compress(char[] chars) {
        StringBuilder sb = new StringBuilder();
        int read = 0;
        
        while (read < chars.length) {
            char currentChar = chars[read];
            int count = 0;
            
            // 1. Count the group size
            while (read < chars.length && chars[read] == currentChar) {
                read++;
                count++;
            }
            
            // 2. Build the compressed version inside StringBuilder
            sb.append(currentChar);
            if (count > 1) {
                sb.append(count);
            }
        }
        
        // 3. Overwrite the original array with our built string characters
        for (int i = 0; i < sb.length(); i++) {
            chars[i] = sb.charAt(i);
        }
        
        // 4. Return the new compressed length
        return sb.length();
    }
}
