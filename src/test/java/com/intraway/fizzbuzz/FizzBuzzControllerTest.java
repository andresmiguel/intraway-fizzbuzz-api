package com.intraway.fizzbuzz;

import com.intraway.exceptions.BadRequest;
import com.intraway.fizzbuzz.dto.FizzBuzzRangeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FizzBuzzController.class)
public class FizzBuzzControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FizzBuzzService fizzBuzzService;


    @Test
    public void getFizzBuzzRange_MinAndMaxGiven_ShouldReturnOkJsonWithData() throws Exception {
        FizzBuzzRangeDto fizzBuzzRangeDto = new FizzBuzzRangeDto();
        fizzBuzzRangeDto.timestamp = 1L;
        fizzBuzzRangeDto.code = 10L;
        fizzBuzzRangeDto.list = "somelist";
        fizzBuzzRangeDto.description = "somedescription";

        given(fizzBuzzService.generateFizzBuzzRange(1, 2)).willReturn(fizzBuzzRangeDto);

        mvc.perform(get("/intraway/api/fizzbuzz/1/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.timestamp", is(1)))
                .andExpect(jsonPath("$.code", is(10)))
                .andExpect(jsonPath("$.list", is(fizzBuzzRangeDto.list)))
                .andExpect(jsonPath("$.description", is(fizzBuzzRangeDto.description)));

    }

    @Test
    public void getFizzBuzzRange_MinIsGreaterThanMaxGiven_ShouldReturnBadRequest() throws Exception {

        given(fizzBuzzService.generateFizzBuzzRange(10, 2)).willThrow(BadRequest.class);

        mvc.perform(get("/intraway/api/fizzbuzz/10/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

}
