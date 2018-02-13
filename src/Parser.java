import exception.CalculationException;
import expression.BinaryOperationExpression;
import expression.Expression;
import expression.NumberExpression;
import expression.UnaryOperationExpression;
import token.Token;
import token.TokenType;

import java.math.BigDecimal;
import java.util.List;

public final class Parser {

    private static final Token EOF = new Token(TokenType.EOF, "");
    private List<Token> tokens;
    private int pos;
    private int size;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        size = tokens.size();
    }

    public BigDecimal parse() throws CalculationException {

        Expression expression = expression();
        if (match(TokenType.EOF))
            return expression.eval();
        else {
            throw new CalculationException("невалидные данные");
        }
    }


    private Expression expression() throws CalculationException {
        return additive();
    }

    private Expression additive() throws CalculationException {

        Expression result = multiplicative();

        while (true) {
            if (match(TokenType.PLUS)) {
                result = new BinaryOperationExpression('+', result, multiplicative());
                continue;
            }
            if (match(TokenType.MINUS)) {
                result = new BinaryOperationExpression('-', result, multiplicative());
                continue;
            }
            break;
        }

        return result;
    }


    private Expression multiplicative() throws CalculationException {
        Expression result = elevate();

        while (true) {
            if (match(TokenType.MULTIPLU)) {
                result = new BinaryOperationExpression('*', result, elevate());
                continue;
            }
            if (match(TokenType.DIVIDE)) {
                result = new BinaryOperationExpression('/', result, elevate());
                continue;
            }
            break;
        }

        return result;
    }

    private Expression elevate() throws CalculationException {
        Expression result = unary();

        if (match(TokenType.ELEVETE)) {
            result = new BinaryOperationExpression('^', result, unary());
        }
        return result;
    }

    private Expression unary() throws CalculationException {

        if (match(TokenType.MINUS)) {
            return new UnaryOperationExpression('-', primary());
        }

        return primary();
    }

    private Expression primary() throws CalculationException {
        final Token currentToken = getCurrentToken();
        if (match(TokenType.NUMBER))
            return new NumberExpression(new BigDecimal(currentToken.getText()));
        else if (match(TokenType.LPAREN)) {
            Expression result = expression();
            match(TokenType.RPAREN);
            return result;
        }
        throw new CalculationException("не валидные данные");
    }


    private boolean match(TokenType tokenType) {
        final Token currentToken = getCurrentToken();
        if (tokenType != currentToken.getType()) return false;
        pos++;
        return true;
    }

    private Token getCurrentToken() {
        if (pos >= size) return EOF;
        return tokens.get(pos);
    }


}
