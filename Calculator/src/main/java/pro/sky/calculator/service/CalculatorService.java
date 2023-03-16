package pro.sky.calculator.service;

import pro.sky.calculator.dto.CalculatorDto;

public interface CalculatorService {
    String getAddition(CalculatorDto dto);

    String getSubtraction(CalculatorDto dto);

    String getMultiplication(CalculatorDto dto);

    String getDivision(CalculatorDto dto);

    String binaryToString(String strings);
}
