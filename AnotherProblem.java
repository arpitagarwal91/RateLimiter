package RateLimiter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class AnotherProblem {
    static long minTime(int[] arr,int n,int k){
        //code here
        int start=0, total = 0;
        for(int i=0;i<n;i++){
            start = Math.max(start, arr[i]);
            total+=arr[i];
        }
        int low = start, high = total;
        while(low<high){
            int mid = low + (high - low) / 2;
            int painters = checkFeasibility(arr, n, mid);
            if(painters<=k){
                high = mid;
            }
            else{
                low = mid+1;
            }
        }
        return low;
    }
    
    static int checkFeasibility(int arr[], int n, int limit){
        int sum = 0, painters = 1;
        for(int i=0;i<n;i++){
            sum+=arr[i];
            if(sum>limit){
                sum = arr[i];
                painters++;
            }
        }
        return painters;
    }

    public static void main(String[] args) {
        //int[] arr = {10,20,30,40};
        int arr[] = {5, 10, 30, 20, 15};
        System.out.println(minTime(arr, 5, 3));
    }
}
