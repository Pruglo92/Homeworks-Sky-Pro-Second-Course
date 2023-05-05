package ru.skypro.homework10.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework10.exceptions.IdNotNullException;
import ru.skypro.homework10.repository.OrderRepository;
import ru.skypro.homework10.service.OrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public String addOrderInBucket(Integer id) {
        if (id == 0) throw new IdNotNullException();
        orderRepository.getBucket().add(id);
        return "Заказ № " + id + " успешно добавлен в корзину.";
    }

    public List<Integer> getOrdersFromBucket() {
        return orderRepository.getBucket();
    }
}
