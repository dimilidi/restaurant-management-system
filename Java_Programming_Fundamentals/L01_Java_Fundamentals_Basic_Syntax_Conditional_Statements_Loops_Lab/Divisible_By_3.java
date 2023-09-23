package L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_Lab;


public class Divisible_By_3 {
    public static void main(String[] args) {
        for(int i = 1; i < 101; i++) {
            if(i % 3 == 0) {
                System.out.println(i);
            }
        }

    }
}
