package spreadsheet.Command;

import spreadsheet.Model.Cell.CellComponent;
import spreadsheet.Model.Cell.CellValue;
import spreadsheet.Model.Expression.Expression;

public class EditCommand implements Command {
    private CellComponent editedCell;
    private CellValue oldValue;
    private CellValue newValue;
    private Expression oldExpression;
    private Expression newExpression;

    public EditCommand(CellComponent editedCell, CellValue oldValue, CellValue newValue, Expression oldExpression, Expression newExpression) {
        this.editedCell = editedCell;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.oldExpression = oldExpression;
        this.newExpression = newExpression;
    }

    @Override
    public void execute() {
        if (newExpression == null) {
            editedCell.setCellValue(newValue);
        } else {
            editedCell.setExpression(newExpression);
        }
    }

    @Override
    public void undo() {
        if (oldExpression == null) {
            editedCell.setCellValue(oldValue);
        } else {
            editedCell.setExpression(oldExpression);
        }
    }
}
