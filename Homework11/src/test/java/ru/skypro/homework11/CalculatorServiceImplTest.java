package ru.skypro.homework11;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.skypro.homework11.dto.CalculatorDto;
import ru.skypro.homework11.exceptions.IllegalArgumentException;
import ru.skypro.homework11.service.CalculatorService;
import ru.skypro.homework11.service.impl.CalculatorServiceImpl;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceImplTest {

    private static CalculatorService calculatorService;
    private static CalculatorDto bothPositiveParamDto;
    private static CalculatorDto bothNegativeParamDto;
    private static CalculatorDto firstNegativeSecondPositiveParamDto;
    private static CalculatorDto firstPositiveSecondNegativeParamDto;
    private static CalculatorDto firstPositiveSecondNullParamDto;
    private static CalculatorDto bothMissingParamDto;
    private static CalculatorDto firstPresentSecondMissingParamDto;
    private static CalculatorDto firstMissingSecondPresentParamDto;

    @BeforeAll
    static void init() {
        calculatorService = new CalculatorServiceImpl();
        bothPositiveParamDto = new CalculatorDto(BigDecimal.valueOf(7.4), BigDecimal.valueOf(7.2));
        bothNegativeParamDto = new CalculatorDto(BigDecimal.valueOf(-7.4), BigDecimal.valueOf(-7.2));
        firstNegativeSecondPositiveParamDto = new CalculatorDto(BigDecimal.valueOf(-7.4), BigDecimal.valueOf(7.2));
        firstPositiveSecondNegativeParamDto = new CalculatorDto(BigDecimal.valueOf(7.4), BigDecimal.valueOf(-7.2));
        firstPositiveSecondNullParamDto = new CalculatorDto(new BigDecimal(7), new BigDecimal(0));
        firstPresentSecondMissingParamDto = new CalculatorDto(new BigDecimal(1), null);
        firstMissingSecondPresentParamDto = new CalculatorDto(null, new BigDecimal(1));
        bothMissingParamDto = new CalculatorDto(null, null);
    }


    @Test
    void getAdditionPositiveFirstAndSecondTest() {
        var result = calculatorService.getAddition(bothPositiveParamDto);
        assertEquals(result, "14,60");
    }

    @Test
    void getAdditionNegativeFirstAndSecondTest() {
        var result = calculatorService.getAddition(bothNegativeParamDto);
        assertEquals(result, "-14,60");
    }

    @Test
    void getAdditionFirstPositiveSecondNegativeTest() {
        var result = calculatorService.getAddition(firstPositiveSecondNegativeParamDto);
        assertEquals(result, "0,20");
    }

    @Test
    void getAdditionFirstNegativeSecondPositiveTest() {
        var result = calculatorService.getAddition(firstNegativeSecondPositiveParamDto);
        assertEquals(result, "-0,20");
    }

    @Test
    void getSubtractionPositiveFirstAndSecondTest() {
        var result = calculatorService.getSubtraction(bothPositiveParamDto);
        assertEquals(result, "0,20");
    }

    @Test
    void getSubtractionNegativeFirstAndSecondTest() {
        var result = calculatorService.getSubtraction(bothNegativeParamDto);
        assertEquals(result, "-0,20");
    }

    @Test
    void getSubtractionFirstPositiveSecondNegativeTest() {
        var result = calculatorService.getSubtraction(firstPositiveSecondNegativeParamDto);
        assertEquals(result, "14,60");
    }

    @Test
    void getSubtractionFirstNegativeSecondPositiveTest() {
        var result = calculatorService.getSubtraction(firstNegativeSecondPositiveParamDto);
        assertEquals(result, "-14,60");
    }

    @Test
    void getMultiplicationPositiveFirstAndSecondTest() {
        var result = calculatorService.getMultiplication(bothPositiveParamDto);
        assertEquals(result, "53,28");
    }

    @Test
    void getMultiplicationNegativeFirstAndSecondTest() {
        var result = calculatorService.getMultiplication(bothNegativeParamDto);
        assertEquals(result, "53,28");
    }

    @Test
    void getMultiplicationFirstPositiveSecondNegativeTest() {
        var result = calculatorService.getMultiplication(firstPositiveSecondNegativeParamDto);
        assertEquals(result, "-53,28");
    }

    @Test
    void getMultiplicationFirstNegativeSecondPositiveTest() {
        var result = calculatorService.getMultiplication(firstNegativeSecondPositiveParamDto);
        assertEquals(result, "-53,28");
    }

    @Test
    void getDivisionPositiveFirstAndSecondTest() {
        var result = calculatorService.getDivision(bothPositiveParamDto);
        assertEquals(result, "1,02");
    }

    @Test
    void getDivisionNegativeFirstAndSecondTest() {
        var result = calculatorService.getDivision(bothNegativeParamDto);
        assertEquals(result, "1,02");
    }

    @Test
    void getDivisionFirstPositiveSecondNegativeTest() {
        var result = calculatorService.getDivision(firstPositiveSecondNegativeParamDto);
        assertEquals(result, "-1,02");
    }

    @Test
    void getDivisionFirstNegativeSecondPositiveTest() {
        var result = calculatorService.getDivision(firstNegativeSecondPositiveParamDto);
        assertEquals(result, "-1,02");
    }

    @Test
    void getDivisionFirstPositiveSecondNullTest() {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class, () ->
                calculatorService.getDivision(firstPositiveSecondNullParamDto)
        );
        assertEquals(illegalArgumentException.getMessage(), "Делить на ноль нельзя !");
    }

    @Test
    void checkParametersFirstPresentSecondMissingTest() {
        var result = calculatorService.checkParameters(firstPresentSecondMissingParamDto);
        assertTrue(result);
    }

    @Test
    void checkParametersFirstMissingSecondPresentTest() {
        var result = calculatorService.checkParameters(firstMissingSecondPresentParamDto);
        assertTrue(result);
    }

    @Test
    void checkParametersFirstMissingSecondMissingTest() {
        var result = calculatorService.checkParameters(bothMissingParamDto);
        assertTrue(result);
    }

    @Test
    void checkParametersFirstPresentSecondPresentTest() {
        var result = calculatorService.checkParameters(bothPositiveParamDto);
        assertFalse(result);
    }
}
