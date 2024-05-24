public class Pattern {

    public static void main(String[] args) {
        for (int i = 0; i <= 8; i++) {

            for (int j = 0; j <= 5; j++) {
         if(i==0||j==0||i==8/2||j==5&&i<8/2) {
             System.out.print("*");
         }else{
             System.out.print(" ");
         }
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
    }


