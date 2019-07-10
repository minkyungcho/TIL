package day05;

public class selectionsort {
 
    public static void main(String[] args) {
 
        int arr[] = {10,8,14,5,20,17,11,15};
        
        for(int i=0; i<arr.length-1;i++) {
            for(int j=i+1; j<arr.length; j++) {
                if(arr[i]>arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
    
        }
    }
}
