package ru.skypro.homework11;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.skypro.homework11.dto.CalculatorDto;
import ru.skypro.homework11.service.CalculatorService;
import ru.skypro.homework11.service.impl.CalculatorServiceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceImplParamTest {

    static CalculatorService calculatorService;
    static CalculatorDto bothPositiveParamDto;
    static CalculatorDto bothNegativeParamDto;
    static CalculatorDto firstNegativeSecondPositiveParamDto;
    static CalculatorDto firstPositiveSecondNegativeParamDto;
    static CalculatorDto bothMissingParamDto;
    static CalculatorDto firstPresentSecondMissingParamDto;
    static CalculatorDto firstMissingSecondPresentParamDto;

    @BeforeAll
    static void init() {
        calculatorService = new CalculatorServiceImpl();
        bothPositiveParamDto = new CalculatorDto(BigDecimal.valueOf(7.4), BigDecimal.valueOf(7.2));
        bothNegativeParamDto = new CalculatorDto(BigDecimal.valueOf(-7.4), BigDecimal.valueOf(-7.2));
        firstNegativeSecondPositiveParamDto = new CalculatorDto(BigDecimal.valueOf(-7.4), BigDecimal.valueOf(7.2));
        firstPositiveSecondNegativeParamDto = new CalculatorDto(BigDecimal.valueOf(7.4), BigDecimal.valueOf(-7.2));
        firstPresentSecondMissingParamDto = new CalculatorDto(new BigDecimal(1), null);
        firstMissingSecondPresentParamDto = new CalculatorDto(null, new BigDecimal(1));
        bothMissingParamDto = new CalculatorDto(null, null);
    }

    public static Stream<Arguments> provideParamsForBasicCalculatorTests() {
        return Stream.of(
                Arguments.of(bothPositiveParamDto),
                Arguments.of(bothNegativeParamDto),
                Arguments.of(firstNegativeSecondPositiveParamDto),
                Arguments.of(firstPositiveSecondNegativeParamDto)
        );
    }

    public static Stream<Arguments> provideParamsForMinorCalculatorTests() {
        return Stream.of(
                Arguments.of(bothPositiveParamDto),
                Arguments.of(bothMissingParamDto),
                Arguments.of(firstPresentSecondMissingParamDto),
                Arguments.of(firstMissingSecondPresentParamDto)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForBasicCalculatorTests")
    void getAdditionParamTest(CalculatorDto dto) {
        var result = calculatorService.getAddition(dto);
        assertEquals(result, String.format("%.2f", (dto.num1().add(dto.num2()))));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForBasicCalculatorTests")
    void getSubtractionParamTest(CalculatorDto dto) {
        var result = calculatorService.getSubtraction(dto);
        assertEquals(result, String.format("%.2f", (dto.num1().subtract(dto.num2()))));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForBasicCalculatorTests")
    void getMultiplicationParamTest(CalculatorDto dto) {
        var result = calculatorService.getMultiplication(dto);
        assertEquals(result, String.format("%.2f", (dto.num1().multiply(dto.num2()))));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForBasicCalculatorTests")
    void getDivisionParamTest(CalculatorDto dto) {
        var result = calculatorService.getDivision(dto);
        assertEquals(result, String.format("%.2f", (dto.num1().divide(dto.num2(), 2, RoundingMode.DOWN))));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForMinorCalculatorTests")
    void checkParametersParamTest(CalculatorDto dto) {
        var result = calculatorService.checkParameters(dto);
        if (dto.num1() == null || dto.num2() == null) {
            assertTrue(result);
        } else assertFalse(result);
    }
}
