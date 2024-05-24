import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
    public static void main(String[]args) {
        System.out.println("Enter Key");
Scanner sc= new Scanner(System.in);
int key=sc.nextInt();
        int arr[]={10,40,60,50,20,80,70};
        Arrays.sort(arr);
        int first=0;
        //int key=20;
        int last=arr.length-1;
        int mid=(first+last)/2;
        while(first<=last){
            if(arr[mid]<key) {
                first = mid + 1;
            }
            else if(arr[mid]==key){
                System.out.println("given key is in this index "+mid);
                break;
            }
            else{
                last=mid-1;
            }
            mid = (first + last)/2;
        }

        if(first>last){
            System.out.println("element not found");
        }
    }
    }
