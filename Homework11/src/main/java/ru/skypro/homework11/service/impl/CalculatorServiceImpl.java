package ru.skypro.homework11.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework11.dto.CalculatorDto;
import ru.skypro.homework11.exceptions.IllegalArgumentException;
import ru.skypro.homework11.service.CalculatorService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public String getAddition(CalculatorDto dto) {
        if (checkParameters(dto)) {
            return "Отсутствует один или несколько параметров.";
        }
        return String.format("%.2f", (dto.num1().add(dto.num2())));
    }

    @Override
    public String getSubtraction(CalculatorDto dto) {
        if (checkParameters(dto)) {
            return "Отсутствует один или несколько параметров.";
        }
        return String.format("%.2f", (dto.num1().subtract(dto.num2())));
    }

    @Override
    public String getMultiplication(CalculatorDto dto) {
        if (checkParameters(dto)) {
            return "Отсутствует один или несколько параметров.";
        }
        return String.format("%.2f", (dto.num1().multiply(dto.num2())));
    }

    @Override
    public String getDivision(CalculatorDto dto) {
        if (checkParameters(dto)) {
            return "Отсутствует один или несколько параметров.";
        }
        if (dto.num2().equals(BigDecimal.valueOf(0))) {
            throw new IllegalArgumentException("Делить на ноль нельзя !");
        }
        return String.format("%.2f", (dto.num1().divide(dto.num2(), 2, RoundingMode.DOWN)));
    }

    public String binaryToString(String string) {
        return Arrays.stream(string.split(" ")).collect(StringBuilder::new,
                (sb, c) -> sb.append((char) Integer.parseInt(c, 2)),
                StringBuilder::append).toString();
    }

    public boolean checkParameters(CalculatorDto dto) {
        return dto.num1() == null || dto.num2() == null;
    }
}
