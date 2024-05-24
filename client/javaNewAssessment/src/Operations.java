public class Operations {
    public static void main(String[] args) {
        String str1 = "Hello, Purushotham";

        String str2 = "Hello, ";
        String str3 = "Purushotham....";
        String concatenatedStr = str2.concat(str3);

        int length = str1.length();

         String substring = str1.substring(7);


        String lowercase = str1.toLowerCase();
        String uppercase = str1.toUpperCase();
        System.out.println("First operation Original String: " + str1);
        System.out.println("Second operation Concatenated String: " + concatenatedStr);
        System.out.println("third operation Length of String 1: " + length);
        System.out.println("Fourth operationSubstring: " + substring);
        System.out.println("fifth operation Lowercase: " + lowercase);
        System.out.println("Uppercase: " + uppercase);
    }
}
