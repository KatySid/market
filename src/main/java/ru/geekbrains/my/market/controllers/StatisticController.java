package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.my.market.dtos.CartDto;
import ru.geekbrains.my.market.utils.Cart;
import ru.geekbrains.my.market.utils.Statistic;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statistic")
@Slf4j

public class StatisticController {
    private final Statistic statistic;


    @GetMapping
    public HashMap<String, Long> getStatistic() {
        return (HashMap<String, Long>) statistic.getStatistic();
    }

}
