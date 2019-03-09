package com.spring.rest.example.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.AttributeConverter;

public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
		System.out.println("Inside convertToDatabaseColumn : "+localDateTime);
		return Optional.ofNullable(localDateTime)
				.map(x-> Timestamp.valueOf(x))
				.orElse(null);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
		System.out.println("Inside convertToEntityAttribute : "+timestamp);
		return Optional.ofNullable(timestamp)
				.map(Timestamp :: toLocalDateTime)
				.orElse(null);
	}

	
}
