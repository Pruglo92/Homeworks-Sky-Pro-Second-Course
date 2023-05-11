package ru.skypro.homework11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skypro.homework11.dto.CalculatorDto;
import ru.skypro.homework11.exceptions.IllegalArgumentException;
import ru.skypro.homework11.service.CalculatorService;
import ru.skypro.homework11.service.impl.CalculatorServiceImpl;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorServiceImplTest {

    CalculatorService calculatorService;
    CalculatorDto bothPositiveParamDto;
    CalculatorDto bothNegativeParamDto;
    CalculatorDto firstNegativeSecondPositiveParamDto;
    CalculatorDto firstPositiveSecondNegativeParamDto;
    CalculatorDto firstPositiveSecondNullParamDto;

    @BeforeEach
    void init() {
        calculatorService = new CalculatorServiceImpl();
        bothPositiveParamDto = new CalculatorDto(new BigDecimal(7), new BigDecimal(7));
        bothNegativeParamDto = new CalculatorDto(new BigDecimal(-7), new BigDecimal(-7));
        firstNegativeSecondPositiveParamDto = new CalculatorDto(new BigDecimal(-7), new BigDecimal(7));
        firstPositiveSecondNegativeParamDto = new CalculatorDto(new BigDecimal(7), new BigDecimal(-7));
        firstPositiveSecondNullParamDto = new CalculatorDto(new BigDecimal(7), new BigDecimal(0));
    }


    @Test
    void getAdditionPositiveFirstAndSecond() {
        var result = calculatorService.getAddition(bothPositiveParamDto);
        assertEquals(result, "14");
    }

    @Test
    void getAdditionNegativeFirstAndSecond() {
        var result = calculatorService.getAddition(bothNegativeParamDto);
        assertEquals(result, "-14");
    }

    @Test
    void getAdditionFirstPositiveSecondNegative() {
        var result = calculatorService.getAddition(firstPositiveSecondNegativeParamDto);
        assertEquals(result, "0");
    }

    @Test
    void getAdditionFirstNegativeSecondPositive() {
        var result = calculatorService.getAddition(firstNegativeSecondPositiveParamDto);
        assertEquals(result, "0");
    }

    @Test
    void getSubtractionPositiveFirstAndSecond() {
        var result = calculatorService.getSubtraction(bothPositiveParamDto);
        assertEquals(result, "0");
    }

    @Test
    void getSubtractionNegativeFirstAndSecond() {
        var result = calculatorService.getSubtraction(bothNegativeParamDto);
        assertEquals(result, "0");
    }

    @Test
    void getSubtractionFirstPositiveSecondNegative() {
        var result = calculatorService.getSubtraction(firstPositiveSecondNegativeParamDto);
        assertEquals(result, "14");
    }

    @Test
    void getSubtractionFirstNegativeSecondPositive() {
        var result = calculatorService.getSubtraction(firstNegativeSecondPositiveParamDto);
        assertEquals(result, "-14");
    }

    @Test
    void getMultiplicationPositiveFirstAndSecond() {
        var result = calculatorService.getMultiplication(bothPositiveParamDto);
        assertEquals(result, "49");
    }

    @Test
    void getMultiplicationNegativeFirstAndSecond() {
        var result = calculatorService.getMultiplication(bothNegativeParamDto);
        assertEquals(result, "49");
    }

    @Test
    void getMultiplicationFirstPositiveSecondNegative() {
        var result = calculatorService.getMultiplication(firstPositiveSecondNegativeParamDto);
        assertEquals(result, "-49");
    }

    @Test
    void getMultiplicationFirstNegativeSecondPositive() {
        var result = calculatorService.getMultiplication(firstNegativeSecondPositiveParamDto);
        assertEquals(result, "-49");
    }

    @Test
    void getDivisionPositiveFirstAndSecond() {
        var result = calculatorService.getDivision(bothPositiveParamDto);
        assertEquals(result, "1");
    }

    @Test
    void getDivisionNegativeFirstAndSecond() {
        var result = calculatorService.getDivision(bothNegativeParamDto);
        assertEquals(result, "1");
    }

    @Test
    void getDivisionFirstPositiveSecondNegative() {
        var result = calculatorService.getDivision(firstPositiveSecondNegativeParamDto);
        assertEquals(result, "-1");
    }

    @Test
    void getDivisionFirstNegativeSecondPositive() {
        var result = calculatorService.getDivision(firstNegativeSecondPositiveParamDto);
        assertEquals(result, "-1");
    }

    @Test
    void getDivisionFirstPositiveSecondNull() {
        var illegalArgumentException = assertThrows(IllegalArgumentException.class, () ->
                calculatorService.getDivision(firstPositiveSecondNullParamDto)
        );
        assertEquals(illegalArgumentException.getMessage(), "Делить на ноль нельзя !");
    }
}
