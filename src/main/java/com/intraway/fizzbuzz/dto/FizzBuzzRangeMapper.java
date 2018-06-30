package com.intraway.fizzbuzz.dto;

import com.intraway.fizzbuzz.FizzBuzzRange;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface FizzBuzzRangeMapper {

    @Mapping(source = "numberList", target = "list")
    FizzBuzzRangeDto fizzBuzzRangeToFizzBuzzRangeDto(FizzBuzzRange fizzBuzzRange);
}
