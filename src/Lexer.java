import exception.CalculationException;
import token.Token;
import token.TokenType;

import java.util.ArrayList;
import java.util.List;

public final class Lexer {


    private static final String OPERATOR_CHARS = "+-*/^()";
    private static final String NUMBER_CHARS = ".,";

    private static final TokenType[] OPERATOR_TOKENS = {
            TokenType.PLUS,
            TokenType.MINUS,
            TokenType.MULTIPLU,
            TokenType.DIVIDE,
            TokenType.ELEVETE,
            TokenType.LPAREN,
            TokenType.RPAREN
    };
    private String input;
    private List<Token> tokens;
    private int pos;


    public Lexer(String input) {
        this.input = input;
        this.tokens = new ArrayList<>();
    }

    public List<Token> tokenize() throws CalculationException {

        while (pos < input.length()) {
            final char current = getCurrentSumbol();
            if (Character.isDigit(current)) tokenizeNumber();
            else if (OPERATOR_CHARS.indexOf(current) != -1) {
                tokenizeOperator();
            } else if (current == ' ') {
                next();
            } else {
                throw new CalculationException("Невалидные данные");
            }
        }

        return tokens;
    }

    private void tokenizeNumber() {

        final StringBuilder buffer = new StringBuilder();
        char current = getCurrentSumbol();
        boolean correctDoubleNumber = true;

        while (true) {
            if (Character.isDigit(current)) {
                buffer.append(current);
                current = next();
            } else if (NUMBER_CHARS.indexOf(current) != -1 && correctDoubleNumber) {
                buffer.append('.');
                current = next();
                correctDoubleNumber = false;
            } else
                break;
        }
        addToken(TokenType.NUMBER, buffer.toString());
    }

    private void tokenizeOperator() {
        final int position = OPERATOR_CHARS.indexOf(getCurrentSumbol());
        addToken(OPERATOR_TOKENS[position]);
        next();
    }

    private char next() {
        pos++;
        return getCurrentSumbol();
    }

    private char getCurrentSumbol() {
        if (pos >= input.length()) return '\0';
        return input.charAt(pos);
    }

    private void addToken(TokenType type) {
        addToken(type, "");
    }

    private void addToken(TokenType type, String text) {
        tokens.add(new Token(type, text));
    }
}
