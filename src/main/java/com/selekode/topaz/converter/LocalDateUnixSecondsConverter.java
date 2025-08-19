package com.selekode.topaz.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Converter(autoApply = true)
public class LocalDateUnixSecondsConverter implements AttributeConverter<LocalDate, Long> {

    @Override
    public Long convertToDatabaseColumn(LocalDate localDate) {
        if (localDate == null) return null;
        return localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
    }

    @Override
    public LocalDate convertToEntityAttribute(Long epochSeconds) {
        if (epochSeconds == null) return null;
        return Instant.ofEpochSecond(epochSeconds).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
