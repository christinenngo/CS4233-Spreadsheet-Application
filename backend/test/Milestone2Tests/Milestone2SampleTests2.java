package Milestone2Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spreadsheet.Model.Cell.CellComponent;
import spreadsheet.Model.Cell.CellValue;
import spreadsheet.Model.Parser.ExpressionParser;
import spreadsheet.Model.CellRepository;
import spreadsheet.Model.Expression.Expression;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Milestone2SampleTests2 {
    private CellRepository repository;
    private CellComponent cellA1, cellA2, cellB1, cellB2, cellC1, cellC2, cellD1, cellD2;

    @BeforeEach
    void setup(){
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
    public void testEvaluate_SimpleAggregate_SUM(){
        String raw = "=SUM(A1, 10, B2)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(60.0, value.asDouble());
    }

    @Test
    public void testEvaluate_SimpleAggregate_AVE(){
        String raw = "=AVE(A1, 10, B2)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(20.0, value.asDouble());
    }

    @Test
    public void testEvaluate_SimpleAggregate_COUNT(){
        String raw = "=COUNT(A1, 10, B2)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(3, value.asDouble());
    }

    @Test
    public void testEvaluate_SimpleAggregate_SUM_Range(){
        String raw = "=SUM(A1:B2, 20)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(120, value.asDouble());
    }

    @Test
    public void testEvaluate_SimpleAggregate_COUNT_Range(){
        String raw = "=COUNT(A1:B2, 20)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(5, value.asDouble());
    }

    @Test
    public void testEvaluate_Complex1(){
        String raw = "=60+B2+AVE(A1:A2, 10, B2)*20";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(550, value.asDouble());
    }

    @Test
    public void testEvaluate_Complex2(){
        String raw = "=60.5+B2/-80-AVE(A1:A2, 10, B2)*SUM(D1:D2)";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(-390, value.asDouble());
    }

    @Test
    public void testEvaluate_Complex3(){
        String raw = "=AVE(A1:A2, SUM(A1:B1), 20.0*(55+100*(-0.5)))";
        Expression expression = ExpressionParser.convertExpression(raw);

        CellValue value = expression.evaluate();
        assertEquals(42.5, value.asDouble());
    }


}
