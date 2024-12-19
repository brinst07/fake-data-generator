package org.brinst.fakedatagenerator.dto;

import java.time.LocalDate;

import org.brinst.fakedatagenerator.annotation.FakeField;
import org.brinst.fakedatagenerator.enums.DataType;

public class TestDTO {
	@FakeField(type = DataType.NAME)
	private String name;
	@FakeField(type = DataType.FIRST_NAME)
	private String firstName;
	@FakeField(type = DataType.LAST_NAME)
	private String lastName;
	@FakeField(type = DataType.NUMBER)
	private int age;
	@FakeField(type = DataType.NUMBER, min = 1, max = 100)
	private int rangeAge;
	@FakeField(type = DataType.NUMBER, min = 5000)
	private int minAge;
	@FakeField(type = DataType.NUMBER, max = 5000)
	private int maxAge;
	@FakeField(type = DataType.DATE)
	private LocalDate birthday;
	@FakeField(type = DataType.EMAIL)
	private String email;

	public int getMinAge() {
		return minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public int getRangeAge() {
		return rangeAge;
	}

	public String getName() {
		return name;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public String getEmail() {
		return email;
	}
}
