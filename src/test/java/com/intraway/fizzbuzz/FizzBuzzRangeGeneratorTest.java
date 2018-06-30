package com.intraway.fizzbuzz;


import com.intraway.exceptions.BadRequest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class FizzBuzzRangeGeneratorTest {

    private FizzBuzzRangeGenerator fizzBuzzRangeGenerator = new FizzBuzzRangeGenerator();

    @Test
    public void createFizzBuzzRange_MinAndMaxGiven_ShouldCreateAFizzBuzzRange() throws BadRequest {
        int min = 1;
        int max = 15;
        String description = "se encontraron múltiplos de 3 y de 5";
        String numberList = "1,2,Fizz,4,Buzz,Fizz,7,8,Fizz,Buzz,11,Fizz,13,14,FizzBuzz";

        FizzBuzzRange fizzBuzzRange = fizzBuzzRangeGenerator.createFizzBuzzRange(min, max);

        assertThat(fizzBuzzRange.getMin()).isEqualTo(min);
        assertThat(fizzBuzzRange.getMax()).isEqualTo(max);
        assertThat(fizzBuzzRange.getDescription()).isEqualTo(description);
        assertThat(fizzBuzzRange.getNumberList()).isEqualTo(numberList);

        min = -3;
        max = -1;
        description = "se encontraron múltiplos de 3";
        numberList = "Fizz,-2,-1";

        fizzBuzzRange = fizzBuzzRangeGenerator.createFizzBuzzRange(min, max);

        assertThat(fizzBuzzRange.getMin()).isEqualTo(min);
        assertThat(fizzBuzzRange.getMax()).isEqualTo(max);
        assertThat(fizzBuzzRange.getDescription()).isEqualTo(description);
        assertThat(fizzBuzzRange.getNumberList()).isEqualTo(numberList);

        min = 4;
        max = 5;
        description = "se encontraron múltiplos de 5";
        numberList = "4,Buzz";

        fizzBuzzRange = fizzBuzzRangeGenerator.createFizzBuzzRange(min, max);

        assertThat(fizzBuzzRange.getMin()).isEqualTo(min);
        assertThat(fizzBuzzRange.getMax()).isEqualTo(max);
        assertThat(fizzBuzzRange.getDescription()).isEqualTo(description);
        assertThat(fizzBuzzRange.getNumberList()).isEqualTo(numberList);

        min = 1;
        max = 2;
        description = "no se encontraron múltiplos de 3 ni de 5";
        numberList = "1,2";

        fizzBuzzRange = fizzBuzzRangeGenerator.createFizzBuzzRange(min, max);

        assertThat(fizzBuzzRange.getMin()).isEqualTo(min);
        assertThat(fizzBuzzRange.getMax()).isEqualTo(max);
        assertThat(fizzBuzzRange.getDescription()).isEqualTo(description);
        assertThat(fizzBuzzRange.getNumberList()).isEqualTo(numberList);
    }

    @Test(expected = BadRequest.class)
    public void createFizzBuzzRange_MinAndMaxGiven_ShouldThrowBadRequest() throws BadRequest {
        int min = 30;
        int max = 15;
        FizzBuzzRange fizzBuzzRange = fizzBuzzRangeGenerator.createFizzBuzzRange(min, max);
    }
}
