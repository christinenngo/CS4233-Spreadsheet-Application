package spreadsheet.Model.Expression;

import spreadsheet.Model.Cell.CellValue;

public class SubtractOperator extends OperatorExpression {
    @Override
    public CellValue evaluate() {
        double difference = 0.0;
        for (Expression operand : operands) {
            difference -= operand.evaluate().asDouble();
        }
        return new CellValue(difference);
    }

}
