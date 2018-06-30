package com.intraway.fizzbuzz;

import com.intraway.exceptions.BadRequest;
import com.intraway.fizzbuzz.dto.FizzBuzzRangeDto;
import com.intraway.fizzbuzz.dto.FizzBuzzRangeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class FizzBuzzServiceImpl implements FizzBuzzService {

    private AtomicLong codeCounter = new AtomicLong(1);

    @Autowired
    private FizzBuzzRangeGenerator createFizzBuzzRangeGenerator;

    @Autowired
    private FizzBuzzRangeRepository fizzBuzzRangeRepository;

    @Autowired
    private FizzBuzzRangeMapper fizzBuzzRangeMapper;


    public FizzBuzzRangeDto generateFizzBuzzRange(int min, int max) throws BadRequest {

        FizzBuzzRange fizzBuzzRange = fizzBuzzRangeRepository.findByMinAndMax(min, max);
        if (fizzBuzzRange == null) {
            fizzBuzzRange = createFizzBuzzRangeGenerator.createFizzBuzzRange(min, max);
            fizzBuzzRangeRepository.save(fizzBuzzRange);
        }

        FizzBuzzRangeDto fizzBuzzRangeDto = buildFizzBuzzRangeDto(fizzBuzzRange);

        return fizzBuzzRangeDto;

    }

    private FizzBuzzRangeDto buildFizzBuzzRangeDto(FizzBuzzRange fizzBuzzRange) {
        FizzBuzzRangeDto fizzBuzzRangeDto = fizzBuzzRangeMapper.fizzBuzzRangeToFizzBuzzRangeDto(fizzBuzzRange);
        fizzBuzzRangeDto.code = codeCounter.getAndIncrement();
        fizzBuzzRangeDto.timestamp = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return fizzBuzzRangeDto;
    }

}
