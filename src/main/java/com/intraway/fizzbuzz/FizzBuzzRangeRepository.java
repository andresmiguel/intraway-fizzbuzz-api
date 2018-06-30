package com.intraway.fizzbuzz;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FizzBuzzRangeRepository extends JpaRepository<FizzBuzzRange, Long> {

    FizzBuzzRange findByMinAndMax(int min, int max);
}
