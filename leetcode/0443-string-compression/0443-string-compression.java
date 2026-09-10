class Solution {
    public int compress(char[] chars) {
        int write = 0;
        int read = 0;
        
        while (read < chars.length) {
            char currentChar = chars[read];
            int count = 0;
            
            // Count the occurrences of the current character
            while (read < chars.length && chars[read] == currentChar) {
                read++;
                count++;
            }
            
            // Write the character to the write pointer position
            chars[write] = currentChar;
            write++;
            
            // If the character repeated, write the count digits
            if (count > 1) {
                // Convert the integer count to a string to easily extract digits
                String countStr = Integer.toString(count);
                for (char c : countStr.toCharArray()) {
                    chars[write] = c;
                    write++;
                }
            }
        }
        
        // The write pointer represents the new length of the compressed array
        return write;
    }
}
