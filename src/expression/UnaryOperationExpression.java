package expression;

import exception.CalculationException;

import java.math.BigDecimal;

public class UnaryOperationExpression implements Expression {


    private Expression numberExpression;
    private char operation;

    public UnaryOperationExpression(char operation,Expression numberExpression) {
        this.operation = operation;
        this.numberExpression = numberExpression;
    }

    @Override
    public BigDecimal eval()  throws CalculationException {

        switch (operation)
        {
            case '-':return numberExpression.eval().negate();
            case '+':return numberExpression.eval();
            default:return  BigDecimal.valueOf(0);
        }

    }
}
