package org.brinst.fakedatagenerator.service;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.brinst.fakedatagenerator.enums.LangType;
import org.brinst.fakedatagenerator.service.dto.FakeDataDTO;
import org.brinst.fakedatagenerator.utils.CustomPropertyUtils;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class FakeDataImporter {
	private final String fullName;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final LangType langType;

	public FakeDataImporter(LangType langType) {
		this.langType = langType;
		this.firstName = generateRandomValue(getFirstNames());
		this.lastName = generateRandomValue(getLastNames());
		this.fullName = firstName + " " + lastName;
		this.email = generateRandomValue(getEmailIds()) + "@" + generateRandomValue(getEmailDomains());
	}

	private <T> T generateRandomValue(List<T> values) {
		return values.get(ThreadLocalRandom.current().nextInt(values.size()));
	}

	private List<String> getFirstNames() {
		return getFakeData().getName().getFirstName();
	}

	private List<String> getLastNames() {
		return getFakeData().getName().getLastName();
	}

	private List<String> getEmailIds() {
		return getFakeData().getEmail().getId();
	}

	private List<String> getEmailDomains() {
		return getFakeData().getEmail().getDomain();
	}

	private FakeDataDTO getFakeData() {
		try (InputStream inputStream = this.getClass().getResourceAsStream(getFakeDataFileName())) {
			if (inputStream == null) {
				throw new IllegalArgumentException("YAML file not found");
			}
			Constructor constructor = new Constructor(FakeDataDTO.class, new LoaderOptions());
			constructor.setPropertyUtils(new CustomPropertyUtils());
			Yaml yaml = new Yaml(constructor);
			return yaml.loadAs(inputStream, FakeDataDTO.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to parse YAML file", e);
		}
	}

	private String getFakeDataFileName() {
		return switch (langType) {
			case EN -> "/en.yml";
			case KO -> "/ko.yml";
		};
	}

	public String getFullName() {
		return fullName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}
}
