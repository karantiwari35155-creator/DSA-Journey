class Solution {
    public int removeDuplicates(int[] nums) {
        // Boundary case: if array is empty, return 0
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Pointer to keep track of the position of unique elements
        int uniqueIndex = 0;
        
        // Start checking from the second element
        for (int i = 1; i < nums.length; i++) {
            // If the current element is different from the last found unique element
            if (nums[i] != nums[uniqueIndex]) {
                uniqueIndex++;                  // Move the unique pointer forward
                nums[uniqueIndex] = nums[i];    // Update the array position in-place
            }
        }
        
        // The number of unique elements is the index + 1
        return uniqueIndex + 1;
    }
}
