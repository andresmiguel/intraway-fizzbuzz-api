package com.intraway.fizzbuzz;

import com.intraway.FizzbuzzApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FizzbuzzApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integration.properties")
public class FizzBuzzControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void getFizzBuzzRange_MinAndMaxGiven_ShouldReturnOkJsonWithData() throws Exception {

        mvc.perform(get("/intraway/api/fizzbuzz/1/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(1)))
                .andExpect(jsonPath("$.list", is("1,2")))
                .andExpect(jsonPath("$.description", is("no se encontraron múltiplos de 3 ni de 5")));

        mvc.perform(get("/intraway/api/fizzbuzz/-3/-1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(2)))
                .andExpect(jsonPath("$.list", is("Fizz,-2,-1")))
                .andExpect(jsonPath("$.description", is("se encontraron múltiplos de 3")));

        mvc.perform(get("/intraway/api/fizzbuzz/4/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(3)))
                .andExpect(jsonPath("$.list", is("4,Buzz")))
                .andExpect(jsonPath("$.description", is("se encontraron múltiplos de 5")));

        mvc.perform(get("/intraway/api/fizzbuzz/1/15")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(4)))
                .andExpect(jsonPath("$.list", is("1,2,Fizz,4,Buzz,Fizz,7,8,Fizz,Buzz,11,Fizz,13,14,FizzBuzz")))
                .andExpect(jsonPath("$.description", is("se encontraron múltiplos de 3 y de 5")));

    }

    @Test
    public void getFizzBuzzRange_MinIsGreaterThanMaxGiven_ShouldReturnBadRequest() throws Exception {

        mvc.perform(get("/intraway/api/fizzbuzz/10/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }
}
