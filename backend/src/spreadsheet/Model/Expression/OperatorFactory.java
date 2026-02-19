package spreadsheet.Model.Expression;

import spreadsheet.Model.Expression.AggregateOperators.AveOperator;
import spreadsheet.Model.Expression.AggregateOperators.CountAOperator;
import spreadsheet.Model.Expression.AggregateOperators.CountOperator;
import spreadsheet.Model.Expression.AggregateOperators.SumOperator;
import spreadsheet.Model.Expression.ArithmeticOperators.AddOperator;
import spreadsheet.Model.Expression.ArithmeticOperators.DivideOperator;
import spreadsheet.Model.Expression.ArithmeticOperators.MultiplyOperator;
import spreadsheet.Model.Expression.ArithmeticOperators.SubtractOperator;

import java.util.HashMap;
import java.util.function.Supplier;

public class OperatorFactory {
    private static final HashMap<String, Supplier<OperatorExpression>> operators = new HashMap<>();
    private static final Supplier<OperatorExpression> ADD = AddOperator::new;
    private static final Supplier<OperatorExpression> SUBTRACT = SubtractOperator::new;
    private static final Supplier<OperatorExpression> MULTIPLY = MultiplyOperator::new;
    private static final Supplier<OperatorExpression> DIVIDE = DivideOperator::new;
    private static final Supplier<OperatorExpression> SUM = SumOperator::new;
    private static final Supplier<OperatorExpression> COUNT = CountOperator::new;
    private static final Supplier<OperatorExpression> COUNTA = CountAOperator::new;
    private static final Supplier<OperatorExpression> AVE = AveOperator::new;

    static {
        operators.put("+", ADD);
        operators.put("-", SUBTRACT);
        operators.put("*", MULTIPLY);
        operators.put("/", DIVIDE);
        operators.put("SUM", SUM);
        operators.put("COUNT", COUNT);
        operators.put("COUNTA", COUNTA);
        operators.put("AVE", AVE);
    }

    public static OperatorExpression getOperator(String operatorType) {
        return operators.get(operatorType).get();
    }

    public static String getOperatorString(OperatorExpression operator) {
        for(String key : operators.keySet()) {
            if(operators.get(key).get().getClass() == operator.getClass()) {
                return key;
            }
        }
        return null;
    }
}
