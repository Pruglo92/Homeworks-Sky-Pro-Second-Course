package ru.skypro.homework11;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.skypro.homework11.dto.CalculatorDto;
import ru.skypro.homework11.service.CalculatorService;
import ru.skypro.homework11.service.impl.CalculatorServiceImpl;

import java.math.BigDecimal;
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
        bothPositiveParamDto = new CalculatorDto(new BigDecimal(7), new BigDecimal(7));
        bothNegativeParamDto = new CalculatorDto(new BigDecimal(-7), new BigDecimal(-7));
        firstNegativeSecondPositiveParamDto = new CalculatorDto(new BigDecimal(-7), new BigDecimal(7));
        firstPositiveSecondNegativeParamDto = new CalculatorDto(new BigDecimal(7), new BigDecimal(-7));
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
        if (dto.num1().intValue() > 0 && dto.num2().intValue() > 0) {
            assertEquals(result, "14");
        } else if (dto.num1().intValue() < 0 && dto.num2().intValue() < 0) {
            assertEquals(result, "-14");
        } else assertEquals(result, "0");
    }

    @ParameterizedTest
    @MethodSource("provideParamsForBasicCalculatorTests")
    void getSubtractionParamTest(CalculatorDto dto) {
        var result = calculatorService.getSubtraction(dto);
        if (dto.num1().intValue() > 0 && dto.num2().intValue() < 0) {
            assertEquals(result, "14");
        } else if (dto.num1().intValue() < 0 && dto.num2().intValue() > 0) {
            assertEquals(result, "-14");
        } else assertEquals(result, "0");
    }

    @ParameterizedTest
    @MethodSource("provideParamsForBasicCalculatorTests")
    void getMultiplicationParamTest(CalculatorDto dto) {
        var result = calculatorService.getMultiplication(dto);
        if ((dto.num1().intValue() > 0 && dto.num2().intValue() > 0) ||
                (dto.num1().intValue() < 0 && dto.num2().intValue() < 0)) {
            assertEquals(result, "49");
        } else assertEquals(result, "-49");
    }

    @ParameterizedTest
    @MethodSource("provideParamsForBasicCalculatorTests")
    void getDivisionParamTest(CalculatorDto dto) {
        var result = calculatorService.getDivision(dto);
        if ((dto.num1().intValue() > 0 && dto.num2().intValue() > 0) ||
                (dto.num1().intValue() < 0 && dto.num2().intValue() < 0)) {
            assertEquals(result, "1");
        } else assertEquals(result, "-1");
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
