public class Palindrome {
    public static void main(String[] args) {
        String text = "abba";        //reading the given string
        int len = text.length();      // finding the length of teh given string
        boolean x = true;
        for (int i = 0; i < len / 2; i++) {
            if (text.charAt(i) != text.charAt(len - i - 1)) {           //checking that the string is palindrome or not
                x = false;
                break;
            }
        }
        if (x) {
            System.out.println("This is a palindrome");
        } else {
            System.out.println("This is not a palindrome");
        }
        checkPalindrome("abba");
    }
    public static void checkPalindrome(String palindrometest){
        StringBuilder check = new StringBuilder(palindrometest);
        StringBuilder reversedString = check.reverse();
        if (palindrometest.equals(reversedString.toString())){
            System.out.println("It is palindrome");
        }
        else {
            System.out.println("It is not a palindorme");
        }


    }
}
