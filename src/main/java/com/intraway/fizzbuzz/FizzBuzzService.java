package com.intraway.fizzbuzz;

import com.intraway.exceptions.BadRequest;
import com.intraway.fizzbuzz.dto.FizzBuzzRangeDto;

interface FizzBuzzService {
    FizzBuzzRangeDto generateFizzBuzzRange(int min, int max) throws BadRequest;
}
