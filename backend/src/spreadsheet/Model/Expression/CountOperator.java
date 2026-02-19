package spreadsheet.Model.Expression;

import spreadsheet.Model.Cell.CellComponent;
import spreadsheet.Model.Cell.CellValue;

import java.util.ArrayList;

public class CountOperator extends OperatorExpression {
    @Override
    public CellValue evaluate() {
        int count = 0;

        for(Expression operand : operands) {
            CellValue value = operand.evaluate();
            if(operand instanceof CellReferenceExpression cellReferenceExpression) {
                count += cellReferenceExpression.getCellComponent().getNumCells();
            } else {
                count++;
            }
        }
        return new CellValue(count);
    }
}
