package Milestone3Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spreadsheet.Model.Cell.CellComponent;
import spreadsheet.Model.Cell.CellValue;
import spreadsheet.Model.CellRepository;
import spreadsheet.Model.Expression.Expression;
import spreadsheet.Model.Parser.ExpressionParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Milestone3AggregateTests {
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
    public void testMin1() {
        String raw = "=MIN(10)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(10.0, value.asDouble());
    }

    @Test
    public void testMin2() {
        String raw = "=MIN(A1, B1, 15)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(10.0, value.asDouble());
    }

    @Test
    public void testMin3() {
        String raw = "=MIN(A1:D2)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(5.0, value.asDouble());
    }

    @Test
    public void testMin4() {
        String raw = "=MIN(A1:B2, 2)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(2.0, value.asDouble());
    }

    @Test
    public void testMin5() {
        String raw = "=MIN(C1)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(0.0, value.asDouble());
    }

    @Test
    public void testMin6() {
        String raw = "=MIN(A1:B2, D2, 20)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(10.0, value.asDouble());
    }

    @Test
    public void testMax1() {
        String raw = "=MAX(10)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(10.0, value.asDouble());
    }

    @Test
    public void testMax2() {
        String raw = "=MAX(A1, B1, 15)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(20.0, value.asDouble());
    }

    @Test
    public void testMax3() {
        String raw = "=MAX(A1:D2)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(40.0, value.asDouble());
    }

    @Test
    public void testMax4() {
        String raw = "=MAX(A1:D2, 50)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(50.0, value.asDouble());
    }

    @Test
    public void testMax5() {
        String raw = "=MAX(C1)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(0.0, value.asDouble());
    }

    @Test
    public void testMax6() {
        String raw = "=MAX(A1:B2, D2, 20)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(40.0, value.asDouble());
    }

    @Test
    public void testMedian1() {
        String raw = "=MEDIAN(10)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(10.0, value.asDouble());
    }

    @Test
    public void testMedian2() {
        String raw = "=MEDIAN(A1, B1, 15, 1, 10)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(15.0, value.asDouble());
    }

    @Test
    public void testMedian3() {
        String raw = "=MEDIAN(A1:D2)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(17.5, value.asDouble());
    }

    @Test
    public void testMedian4() {
        String raw = "=MEDIAN(A1:D2, 50)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(20.0, value.asDouble());
    }

    @Test
    public void testMedian5() {
        String raw = "=MEDIAN(C1)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(0.0, value.asDouble());
    }
}
