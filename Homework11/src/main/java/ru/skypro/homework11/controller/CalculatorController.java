package ru.skypro.homework11.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework11.dto.CalculatorDto;
import ru.skypro.homework11.service.CalculatorService;


@RestController
@RequestMapping(value = "/calculator")
public class CalculatorController {

    @Value("${very.important.constant}")
    private String GREETINGS;

    private final CalculatorService calculatorService;

    private CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping
    public String calculator() {
        return calculatorService.binaryToString(GREETINGS);
    }

    @GetMapping("/plus")
    public String addition(CalculatorDto dto) {
        return calculatorService.getAddition(dto);
    }

    @GetMapping("/minus")
    public String subtraction(CalculatorDto dto) {
        return calculatorService.getSubtraction(dto);
    }

    @GetMapping("/multiply")
    public String multiplication(CalculatorDto dto) {
        return calculatorService.getMultiplication(dto);
    }

    @GetMapping("/divide")
    public String division(CalculatorDto dto) {
        return calculatorService.getDivision(dto);
    }
}
