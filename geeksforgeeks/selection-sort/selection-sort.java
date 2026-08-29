class Solution {
    void selectionSort(int[] arr) {
        // code here
        int n = arr.length;
        
        for( int i = 0; i < n - 1; i++){
            int minPos = i;
            for(int j = i+1; j < n; j++){
                if(arr[minPos] > arr[j]){
                    minPos= j;
                }
            }
            int temp = arr[minPos];
            arr[minPos] = arr[i];
            arr[i] = temp;
        }
    }
}
