package expression;

import exception.CalculationException;

import java.math.BigDecimal;

public interface Expression {

    BigDecimal eval() throws CalculationException;

}
