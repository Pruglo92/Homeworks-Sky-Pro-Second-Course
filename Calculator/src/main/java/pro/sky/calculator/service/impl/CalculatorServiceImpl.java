package pro.sky.calculator.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.calculator.dto.CalculatorDto;
import pro.sky.calculator.service.CalculatorService;

import java.util.Arrays;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public String getAddition(CalculatorDto dto) {
        return removeZerosAfterDecimalPoint(String.format
                ("%.2f", Double.parseDouble(dto.num1())
                        + Double.parseDouble(dto.num2())));
    }

    @Override
    public String getSubtraction(CalculatorDto dto) {
        return removeZerosAfterDecimalPoint(String.format
                ("%.2f", Double.parseDouble(dto.num1())
                        - Double.parseDouble(dto.num2())));
    }

    @Override
    public String getMultiplication(CalculatorDto dto) {
        return removeZerosAfterDecimalPoint(String.format
                ("%.2f", Double.parseDouble(dto.num1())
                        * Double.parseDouble(dto.num2())));
    }

    @Override
    public String getDivision(CalculatorDto dto) {
        return removeZerosAfterDecimalPoint(String.format
                ("%.2f", Double.parseDouble(dto.num1())
                        / Double.parseDouble(dto.num2())));
    }

    public String binaryToString(String string) {
        return Arrays.stream(string.split(" ")).collect(StringBuilder::new,
                (sb, c) -> sb.append((char) Integer.parseInt(c, 2)),
                StringBuilder::append).toString();
    }

    private String removeZerosAfterDecimalPoint(String string) {
        if (string.split(",")[1].split("")[0].equals("0")
                && string.split(",")[1].split("")[1].equals("0")) {
            return string.split(",")[0];
        }
        return string;
    }
}
