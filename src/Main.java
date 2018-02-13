import exception.CalculationException;
import token.Token;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        final String input = scanner.nextLine();
        final List<Token> tokens;


        try {
            tokens = new Lexer(input).tokenize();
            BigDecimal result = new Parser(tokens).parse();
            System.out.println(result);
        } catch (CalculationException e) {
            System.out.println(e.getMessage());
        }
    }
}



