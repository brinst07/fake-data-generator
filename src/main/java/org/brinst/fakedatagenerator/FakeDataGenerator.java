package org.brinst.fakedatagenerator;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import org.brinst.fakedatagenerator.annotation.FakeField;

public class FakeDataGenerator {
	public static <T> T generate(Class<T> clazz) {
		try {
			T instance = clazz.getDeclaredConstructor().newInstance();
			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(FakeField.class)) {
					FakeField fakeField = field.getAnnotation(FakeField.class);
					field.setAccessible(true);
					field.set(instance, generateFakeValue(fakeField));
				}
			}
			return instance;
		} catch (Exception e) {
			throw new RuntimeException("Error generating DTO", e);
		}
	}

	private static Object generateFakeValue(FakeField fakeField) {
		return switch (fakeField.type()) {
			case NAME -> "John Doe"; // Example value
			case EMAIL -> "john.doe@example.com";
			case PHONE -> "123-456-7890";
			case NUMBER -> ThreadLocalRandom.current().nextInt(fakeField.min(), fakeField.max() + 1);
			case DATE -> LocalDate.now();
		};
	}
}
