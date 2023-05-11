package ru.skypro.homework11.service;

import ru.skypro.homework11.dto.CalculatorDto;

public interface CalculatorService {
    String getAddition(CalculatorDto dto);

    String getSubtraction(CalculatorDto dto);

    String getMultiplication(CalculatorDto dto);

    String getDivision(CalculatorDto dto);

    String binaryToString(String strings);
}
