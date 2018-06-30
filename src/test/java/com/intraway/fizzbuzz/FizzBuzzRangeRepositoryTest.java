package com.intraway.fizzbuzz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class FizzBuzzRangeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private FizzBuzzRangeRepository fizzBuzzRangeRepository;

    @Test
    public void findByMinAndMax_MinAndMaxGiven_ShouldReturnFizzBussRange() {
        int min = 1;
        int max = 15;
        String description = "se encontraron múltiplos de 3 y de 5";
        String numberList = "1,2,Fizz,4,Buzz,Fizz,7,8,Fizz,Buzz,11,Fizz,13,14,FizzBuzz";

        FizzBuzzRange fizzBuzzRange = new FizzBuzzRange(min, max, description, numberList);
        testEntityManager.persist(fizzBuzzRange);
        testEntityManager.flush();

        FizzBuzzRange found = fizzBuzzRangeRepository.findByMinAndMax(min, max);

        assertThat(found.getMin()).isEqualTo(min);
        assertThat(found.getMax()).isEqualTo(max);
        assertThat(found.getDescription()).isEqualTo(description);
        assertThat(found.getNumberList()).isEqualTo(numberList);
    }
}
