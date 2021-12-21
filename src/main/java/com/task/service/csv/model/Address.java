package com.task.service.csv.model;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class Address implements Serializable {

    @CsvBindByPosition(position = 0)
    private String country;

    @CsvBindByPosition(position = 1)
    private String city;

    @CsvDate(value = "dd.MM.yy")
    @CsvBindByPosition(position = 2)
    private LocalDate date;
}
