package org.brinst.fakedatagenerator.service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Value;

public class FakeDataImporter {
	@Value("${ko.name.first_name}")
	private List<String> firstNames;
	@Value("${ko.name.last_name}")
	private List<String> lastNames;
	@Value("${ko.phone_number.format}")
	private String phoneNumberFormat;

	public String getFakeName() {
		String firstName = getFakeFirstName();
		String lastName = getFakeLastName();
		return lastName + firstName;
	}

	public String getFakePhoneNumber() {
		Random random = new Random();
		StringBuilder phoneNumber = new StringBuilder();
		for (char str : phoneNumberFormat.toCharArray()) {
			if (str == '#') {
				phoneNumber.append(random.nextInt(10));
			} else {
				phoneNumber.append(str);
			}
		}
		return phoneNumber.toString();
	}

	private String getFakeFirstName() {
		return firstNames.get(ThreadLocalRandom.current().nextInt(firstNames.size()));
	}

	private String getFakeLastName() {
		return lastNames.get(ThreadLocalRandom.current().nextInt(lastNames.size()));
	}
}
