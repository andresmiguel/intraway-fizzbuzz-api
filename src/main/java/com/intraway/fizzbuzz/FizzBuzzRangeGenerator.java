package com.intraway.fizzbuzz;

import com.intraway.exceptions.BadRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FizzBuzzRangeGenerator {

    public FizzBuzzRange createFizzBuzzRange(int min, int max) throws BadRequest {

        validateRange(min, max);

        List<String> numbers = new ArrayList<>();
        boolean isMultipleOf3 = false;
        boolean isMultipleOf5 = false;

        for (int i = min; i <= max; i++) {
            if (i % 15 == 0) {
                numbers.add("FizzBuzz");
                isMultipleOf3 = true;
                isMultipleOf5 = true;
            } else if (i % 3 == 0) {
                numbers.add("Fizz");
                isMultipleOf3 = true;
            } else if (i % 5 == 0) {
                numbers.add("Buzz");
                isMultipleOf5 = true;
            } else {
                numbers.add(i + "");
            }
        }

        String numberList = numbers.stream().collect(Collectors.joining(","));
        String description = "no se encontraron múltiplos de 3 ni de 5";

        if (isMultipleOf3 && isMultipleOf5) {
            description = "se encontraron múltiplos de 3 y de 5";
        } else if (isMultipleOf3) {
            description = "se encontraron múltiplos de 3";
        } else if (isMultipleOf5) {
            description = "se encontraron múltiplos de 5";
        }

        return new FizzBuzzRange(min, max, description, numberList);
    }

    private void validateRange(int min, int max) throws BadRequest {
        if (max < min) throw new BadRequest();
    }
}
