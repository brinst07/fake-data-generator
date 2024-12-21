package org.brinst.fakedatagenerator;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.brinst.fakedatagenerator.annotation.FakeField;
import org.brinst.fakedatagenerator.enums.LangType;
import org.brinst.fakedatagenerator.service.FakeDataImporter;

public class FakeDataGenerator {
	private static FakeDataImporter fakeDataImporter;

	public static <T> T generate(Class<T> clazz) {
		fakeDataImporter = new FakeDataImporter(LangType.KO);
		try {
			T instance = clazz.getDeclaredConstructor().newInstance();
			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(FakeField.class)) {
					FakeField fakeField = field.getAnnotation(FakeField.class);
					field.setAccessible(true);
					field.set(instance, generateFakeValue(fakeField, field));
				}
			}
			return instance;
		} catch (Exception e) {
			throw new RuntimeException("Error generating DTO", e);
		}
	}

	public static <T> T generate(Class<T> clazz, LangType langType) {
		fakeDataImporter = new FakeDataImporter(langType);
		try {
			T instance = clazz.getDeclaredConstructor().newInstance();
			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(FakeField.class)) {
					FakeField fakeField = field.getAnnotation(FakeField.class);
					field.setAccessible(true);
					field.set(instance, generateFakeValue(fakeField, field));
				}
			}
			return instance;
		} catch (Exception e) {
			throw new RuntimeException("Error generating DTO", e);
		}
	}

	private static Object generateFakeValue(FakeField fakeField, Field fieldType) {
		return switch (fakeField.type()) {
			case NAME -> fakeDataImporter.getFullName(); // Example value
			case FIRST_NAME -> fakeDataImporter.getFirstName(); // Example value
			case LAST_NAME -> fakeDataImporter.getLastName(); // Example value
			case EMAIL -> fakeDataImporter.getEmail();
			case PHONE -> fakeDataImporter.getPhoneNumber();
			case NUMBER -> generateNumber(fakeField.min(), fakeField.max());
			case DATE -> {
				// 필드 타입에 맞게 LocalDate 또는 LocalDateTime 생성
				if (fieldType.getType().equals(LocalDate.class)) {
					yield generateRandomDate();
				} else if (fieldType.getType().equals(LocalDateTime.class)) {
					yield generateRandomDateTime();
				} else {
					throw new IllegalArgumentException("Unsupported field type for DATE: " + fieldType);
				}
			}
		};
	}

	private static int generateNumber(int min, int max) {
		boolean isMinSet = (min != -1); // min이 설정되었는지 확인
		boolean isMaxSet = (max != -1); // max가 설정되었는지 확인

		if (!isMinSet && isMaxSet) {
			// max만 설정된 경우 -> 0 이상 max 미만의 값 생성
			return ThreadLocalRandom.current().nextInt(max);
		} else if (isMinSet && !isMaxSet) {
			// min만 설정된 경우 -> min 이상 Integer.MAX_VALUE 미만의 값 생성
			return ThreadLocalRandom.current().nextInt(min, Integer.MAX_VALUE);
		} else if (!isMinSet && !isMaxSet) {
			// min, max 둘 다 설정하지 않은 경우 -> 양수 범위에서 무작위 값 생성
			return ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE); // 1 이상 Integer.MAX_VALUE 미만의 값 생성
		} else {
			// min, max 둘 다 설정된 경우 -> min 이상 max 이하의 값 생성
			if (min > max) {
				throw new IllegalArgumentException("min cannot be greater than max");
			}
			return ThreadLocalRandom.current().nextInt(min, max + 1);
		}
	}

	private static LocalDate generateRandomDate() {
		// 무작위 LocalDate 생성
		LocalDate now = LocalDate.now();
		long randomDays = ThreadLocalRandom.current().nextLong(-365, 365); // 무작위로 -365일부터 +365일까지
		return now.plusDays(randomDays);
	}

	private static LocalDateTime generateRandomDateTime() {
		// 무작위 LocalDateTime 생성
		LocalDateTime now = LocalDateTime.now();
		long randomDays = ThreadLocalRandom.current().nextLong(-365, 365); // 무작위로 -365일부터 +365일까지
		return now.plusDays(randomDays);
	}
}
