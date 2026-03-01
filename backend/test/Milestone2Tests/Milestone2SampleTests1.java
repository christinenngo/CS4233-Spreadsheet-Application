package Milestone2Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spreadsheet.Model.Cell.CellComponent;
import spreadsheet.Model.Cell.CellValue;
import spreadsheet.Model.Parser.ExpressionParser;
import spreadsheet.Model.CellRepository;
import spreadsheet.Model.Expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Milestone2SampleTests1 {
    private CellRepository repository;
    private CellComponent cellA1, cellA2, cellB1, cellB2, cellC1, cellC2, cellD1, cellD2;

    @BeforeEach
    void setup() {
        CellRepository.resetInstance();
        repository = CellRepository.getInstance();
        cellA1 = repository.getReferenceCell(0, 0);
        cellA2 = repository.getReferenceCell(1, 0);
        cellB1 = repository.getReferenceCell(0, 1);
        cellB2 = repository.getReferenceCell(1, 1);
        cellC1 = repository.getReferenceCell(0, 2);
        cellC2 = repository.getReferenceCell(1, 2);
        cellD1 = repository.getReferenceCell(0, 3);
        cellD2 = repository.getReferenceCell(1, 3);

        cellA1.setCellValue(new CellValue(10));
        cellB1.setCellValue(new CellValue(20));
        cellD1.setCellValue(new CellValue(5));
        cellA2.setCellValue(new CellValue(30));
        cellB2.setCellValue(new CellValue(40));
        cellD2.setCellValue(new CellValue(15));
        // C1 and C2 are not assigned values.
    }

    @Test
    public void testEvaluate_SimpleArithmetic1() {
        String raw = "=1+A2";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(31.0, value.asDouble());
    }

    @Test
    public void testEvaluate_SimpleArithmetic2() {
        String raw = "=A1+A2-B2+6";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(6.0, value.asDouble());
    }

    @Test
    public void testEvaluate_ArithmeticWithPemdas1() {
        String raw = "=A1+A2*B2+10/A1";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(1211.0, value.asDouble());
    }

    @Test
    public void testEvaluate_ArithmeticWithPemdas2() {
        String raw = "=A1+A2*(B2+100/(10+B1))";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(1310.0, value.asDouble());
    }

    @Test
    public void testEvaluate_ArithmeticWithPemdas3() {
        // since cell C2 is blank, it's value is assumed to 0.0 (default value)
        String raw = "=A1+C2*(D2+100/(10+B1+D1))";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(10.0, value.asDouble());
    }
}