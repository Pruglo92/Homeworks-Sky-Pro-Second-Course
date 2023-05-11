package ru.skypro.homework11.dto;

import java.math.BigDecimal;

public record CalculatorDto(
        BigDecimal num1,
        BigDecimal num2
) {
}
