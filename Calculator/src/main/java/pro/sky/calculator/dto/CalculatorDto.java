package pro.sky.calculator.dto;

import java.math.BigDecimal;

public record CalculatorDto(
        BigDecimal num1,
        BigDecimal num2
) {
}
