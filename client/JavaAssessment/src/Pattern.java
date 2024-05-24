public class Pattern {
    public static void main(String[]args){

        int n = 6;  // Number of rows.


        for(int i=0;i<=n;i++) {
            for(int j=n;j>=i;j--){
                System.out.print(" ");
            }
            for(int k=1;k<=(i*2)-1;k++){
                if( k == 1||i==(n/2)+1 || k == (i * 2) -1)
                    System.out.print("*");
                else
                    System.out.print(" ");


            }


            System.out.println();

                    }




        }}






