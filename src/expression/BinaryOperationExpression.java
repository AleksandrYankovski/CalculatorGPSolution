package expression;

import exception.CalculationException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BinaryOperationExpression implements Expression {

    private Expression leftNumberExpression;
    private Expression rigthNumberExpression;
    private char operation;


    public BinaryOperationExpression(char operation, Expression leftNumberExpression,
                                     Expression rigthNumberExpression) throws ArithmeticException {
        this.operation = operation;
        this.leftNumberExpression = leftNumberExpression;
        this.rigthNumberExpression = rigthNumberExpression;

    }

    @Override
    public BigDecimal eval() throws CalculationException {

        switch (operation) {
            case '+':
                return leftNumberExpression.eval().add(rigthNumberExpression.eval());
            case '-':
                return leftNumberExpression.eval().add(rigthNumberExpression.eval().negate());
            case '*':
                return leftNumberExpression.eval().multiply( rigthNumberExpression.eval());
            case '/':
                BigDecimal rigthResult = rigthNumberExpression.eval();
                if(rigthResult.compareTo(BigDecimal.ZERO)!=0)
                return leftNumberExpression.eval().divide(rigthResult,
                        new MathContext(8500, RoundingMode.HALF_UP));
                else
                    throw new CalculationException("деление на 0");
            case '^':
                return leftNumberExpression.eval().pow(rigthNumberExpression.eval().intValue(),
                        new MathContext(8500, RoundingMode.HALF_UP));


            default:
                return new BigDecimal("0");

        }
    }

}
