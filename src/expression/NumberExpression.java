package expression;

import java.math.BigDecimal;

public final class NumberExpression implements Expression {

    private BigDecimal value;


    public NumberExpression(BigDecimal value)
    {
        this.value=value;
    }


    @Override
    public BigDecimal eval() {
        return value;
    }
}
