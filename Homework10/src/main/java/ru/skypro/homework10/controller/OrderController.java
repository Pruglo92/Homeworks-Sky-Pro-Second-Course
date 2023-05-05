package ru.skypro.homework10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework10.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/add")
    public ResponseEntity<String> addOrder(@RequestParam Integer id) {
        return ResponseEntity.ok(orderService.addOrderInBucket(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Integer>> getOrders() {
        return ResponseEntity.ok(orderService.getOrdersFromBucket());
    }
}
