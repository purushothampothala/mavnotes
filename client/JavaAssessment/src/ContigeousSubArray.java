public class ContigeousSubArray {
    public static void main(String[]args){
        int arr[]={10,40,20,60,50,10};
        int key=3;
        int index=0;
        int sum=0;
        int maxSum=0;
        for(int i=0;i<=arr.length-key;i++){
            for(int j=i;j<i+key;j++){

           sum=sum+arr[j];
             }
            if (sum>maxSum) {
                maxSum = sum;
                index=i;
            }
            sum=0;

        }

        System.out.print(maxSum+"( sum of subarray[ ");
        for(int i=0;i<key;i++){
            System.out.print(arr[index+i]+" ");
        }
System.out.print("])");

    }
}
