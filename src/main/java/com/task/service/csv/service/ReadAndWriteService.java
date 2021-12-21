package com.task.service.csv.service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.List;

@Component
public class ReadAndWriteService<T> {


    public List<T> read(final Reader reader, final Class clazz) {

        final ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
        ms.setType(clazz);

        final CsvToBean cb = new CsvToBeanBuilder(reader)
                .withType(clazz)
                .withSkipLines(1)
                .withMappingStrategy(ms)
                .build();

        return cb.parse();
    }
}
