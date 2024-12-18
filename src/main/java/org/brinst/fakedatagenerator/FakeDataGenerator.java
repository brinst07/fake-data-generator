package org.brinst.fakedatagenerator;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import org.brinst.fakedatagenerator.annotation.FakeField;
import org.brinst.fakedatagenerator.service.FakeDataImporter;

public class FakeDataGenerator {
	static FakeDataImporter fakeDataImporter;
	public static <T> T generate(Class<T> clazz) {
		fakeDataImporter = new FakeDataImporter();
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
			case NAME -> fakeDataImporter.getFullName(); // Example value
			case FIRST_NAME -> fakeDataImporter.getFirstName(); // Example value
			case LAST_NAME -> fakeDataImporter.getLastName(); // Example value
			case EMAIL -> fakeDataImporter.getEmail();
			case PHONE -> "123-456-7890";
			case NUMBER -> ThreadLocalRandom.current().nextInt(fakeField.min(), fakeField.max() + 1);
			case DATE -> LocalDate.now();
		};
	}
}
