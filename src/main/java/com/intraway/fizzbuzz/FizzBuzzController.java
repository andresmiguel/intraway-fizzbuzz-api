package com.intraway.fizzbuzz;

import com.intraway.exceptions.BadRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/intraway/api/fizzbuzz")
public class FizzBuzzController {

    private FizzBuzzService fizzBuzzService;

    public FizzBuzzController(FizzBuzzService fizzBuzzService) {
        this.fizzBuzzService = fizzBuzzService;
    }

    @GetMapping("/{min}/{max}")
    public Object getFizzBuzzRange(@PathVariable int min, @PathVariable int max) throws BadRequest {
        return fizzBuzzService.generateFizzBuzzRange(min, max);
    }
}
