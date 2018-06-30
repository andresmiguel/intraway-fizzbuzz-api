package com.intraway.fizzbuzz;

import javax.persistence.*;

@Entity
public class FizzBuzzRange {

    @Id
    @SequenceGenerator(name = "fizzbuzzRangeGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fizzbuzzRangeGen")
    private Long id;

    private int min;
    private int max;
    private String description;
    private String numberList;

    public FizzBuzzRange() {
    }

    public FizzBuzzRange(int min, int max, String description, String numberList) {
        this.min = min;
        this.max = max;
        this.description = description;
        this.numberList = numberList;
    }

    public Long getId() {
        return id;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumberList() {
        return numberList;
    }

    public void setNumberList(String numberList) {
        this.numberList = numberList;
    }
}
