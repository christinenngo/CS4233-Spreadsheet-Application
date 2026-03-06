package spreadsheet.Model.Expression;

import spreadsheet.Model.Cell.CellValue;

public class OperandExpression extends AbstractExpression {
    private final CellValue value;

    public OperandExpression(double val) {
        this.value = new CellValue(val);
    }

    public CellValue evaluate() {
        return value;
    }

}
