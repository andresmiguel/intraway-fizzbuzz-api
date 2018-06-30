package com.intraway.fizzbuzz;

import com.intraway.exceptions.BadRequest;
import com.intraway.fizzbuzz.dto.FizzBuzzRangeDto;
import com.intraway.fizzbuzz.dto.FizzBuzzRangeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
public class FizzBuzzServiceImplTest {

    @TestConfiguration
    static class FizzBuzzServiceImplTestContextConfig {

        @Bean
        public FizzBuzzService getFizzBuzzService() {
            return new FizzBuzzServiceImpl();
        }

        @Bean
        public FizzBuzzRangeMapper getFizzBuzzRangeMapper() {
            return Mappers.getMapper(FizzBuzzRangeMapper.class);
        }
    }

    @Autowired
    private FizzBuzzServiceImpl fizzBuzzService;

    @MockBean
    private FizzBuzzRangeGenerator fizzBuzzRangeGenerator;

    @MockBean
    private FizzBuzzRangeRepository fizzBuzzRangeRepository;


    @Test
    public void generateFizzBuzzRange_TwoCallsExecuted_ShouldGetConsecutiveCodes() throws BadRequest {

        int min = 1;
        int max = 2;
        FizzBuzzRange fizzBuzzRange = new FizzBuzzRange();
        Mockito.when(fizzBuzzRangeRepository.findByMinAndMax(min, max))
                .thenReturn(fizzBuzzRange);

        FizzBuzzRangeDto fizzBuzzRangeDto = fizzBuzzService.generateFizzBuzzRange(min, max);
        assertThat(fizzBuzzRangeDto.code).isEqualTo(1);

        fizzBuzzRangeDto = fizzBuzzService.generateFizzBuzzRange(min, max);
        assertThat(fizzBuzzRangeDto.code).isEqualTo(2);
    }

    @Test
    public void generateFizzBuzzRange_FizzBuzzRangeExists_ShouldReturnIt() throws BadRequest {

        FizzBuzzRange fizzBuzzRange = new FizzBuzzRange(1, 2, "description", "numberList");
        Mockito.when(fizzBuzzRangeRepository.findByMinAndMax(fizzBuzzRange.getMin(), fizzBuzzRange.getMax()))
                .thenReturn(fizzBuzzRange);

        FizzBuzzRangeDto generated = fizzBuzzService.generateFizzBuzzRange(1, 2);

        assertThat(generated.description).isEqualTo(fizzBuzzRange.getDescription());
        assertThat(generated.list).isEqualTo(fizzBuzzRange.getNumberList());
    }

    @Test
    public void generateFizzBuzzRange_FizzBuzzRangeDoNotExists_ShouldReturnIt() throws BadRequest {

        FizzBuzzRange fizzBuzzRange = new FizzBuzzRange(1, 2, "description", "numberList");
        Mockito.when(fizzBuzzRangeGenerator.createFizzBuzzRange(fizzBuzzRange.getMin(), fizzBuzzRange.getMax()))
                .thenReturn(fizzBuzzRange);

        FizzBuzzRangeDto generated = fizzBuzzService.generateFizzBuzzRange(1, 2);

        assertThat(generated.description).isEqualTo(fizzBuzzRange.getDescription());
        assertThat(generated.list).isEqualTo(fizzBuzzRange.getNumberList());
    }

}
