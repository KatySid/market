package ru.geekbrains.my.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.geekbrains.my.market.error_handling.MarketError;
import ru.geekbrains.my.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.my.market.models.Category;
import ru.geekbrains.my.market.models.Product;
import ru.geekbrains.my.market.services.CategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping ("/{id}")
    public Category getOneCategoryById(@PathVariable Long id){

        return categoryService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category doesn't exists " + id));
    }

}
