package spreadsheet.Model.Expression.AggregateOperators;

import spreadsheet.Model.Cell.CellComponent;
import spreadsheet.Model.Cell.CellValue;
import spreadsheet.Model.Expression.AggregateOperatorExpression;

import java.util.OptionalDouble;
import java.util.stream.Stream;

public class MinOperator extends AggregateOperatorExpression {
    @Override
    public CellValue evaluate() {
        OptionalDouble result = operands.stream()
                .flatMap(operand -> {
                    if(operand.getReferencedCells().isEmpty()) {
                        CellValue value = operand.evaluate();
                        if(value.nonEmpty()) {
                            return Stream.of(value.asDouble());
                        }
                        return Stream.empty();
                    } else {
                        return operand.getReferencedCells().stream()
                                .map(CellComponent::getCellValue)
                                .filter(CellValue::nonEmpty)
                                .map(CellValue::asDouble);
                    }
                })
                .mapToDouble(num -> num)
                .min();

        if(result.isEmpty()) {
            return new CellValue(0);
        }
        return new CellValue(result.getAsDouble());
    }
}
