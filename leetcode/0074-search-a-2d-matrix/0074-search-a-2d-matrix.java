class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int low = 0;
        int high = (rows * cols) - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2; // Prevents potential integer overflow
            
            // Map 1D index back to 2D coordinates
            int midElement = matrix[mid / cols][mid % cols];
            
            if (midElement == target) {
                return true;
            } else if (midElement < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return false;
    }
}
