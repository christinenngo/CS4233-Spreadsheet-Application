package spreadsheet.Model.Expression.AggregateOperators;

import spreadsheet.Model.Cell.CellComponent;
import spreadsheet.Model.Cell.CellValue;
import spreadsheet.Model.Expression.AggregateOperatorExpression;

import java.util.Objects;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class MedianOperator extends AggregateOperatorExpression {
    @Override
    public CellValue evaluate() {
        double[] values = operands.stream()
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
                .sorted()
                .toArray();

        int size = values.length;
        int medianIndex = 0;
        if(size == 0) {
            return new CellValue(0);
        } else if(size != 1){
            medianIndex = size / 2;
        }
        if(size % 2 == 0) {
            return new CellValue((values[medianIndex-1] + values[medianIndex]) / 2);
        }
        return new CellValue(values[medianIndex]);
    }
//        int size = operands.stream()
//                .filter(Objects::nonNull)
//                .toArray()
//                .length;
//        double[] result = operands.stream()
//                .mapToDouble(num -> num.evaluate().asDouble())
//                .sorted()
//                .toArray();
//        int medianIndex = size / 2;

//    }
}
