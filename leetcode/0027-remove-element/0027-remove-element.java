class Solution {
    public int removeElement(int[] nums, int val) {
        int index = 0; // Pointer to place the next valid (non-val) element
        
        for (int i = 0; i < nums.length; i++) {
            // If the current element is NOT the one we want to remove
            if (nums[i] != val) {
                nums[index] = nums[i]; // Move it to the front
                index++;               // Advance the valid element pointer
            }
        }
        
        return index; // 'index' automatically represents the count of valid elements
    }
}
