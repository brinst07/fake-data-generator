package org.brinst.fakedatagenerator;

import static org.assertj.core.api.Assertions.*;

import java.io.InputStream;
import java.time.LocalDate;

import org.brinst.fakedatagenerator.dto.TestDTO;
import org.brinst.fakedatagenerator.enums.LangType;
import org.brinst.fakedatagenerator.service.dto.FakeDataDTO;
import org.brinst.fakedatagenerator.utils.CustomPropertyUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

class FakeGeneratorTests {
	@Test
	@DisplayName("이름 데이터 주입 테스트")
	void init_fake_data_test() {
		//given
		FakeDataDTO fakeData = getFakeData("/ko.yml");
		//when
		TestDTO generateDTO = FakeDataGenerator.generate(TestDTO.class);
		//then
		assertThat(fakeData.getName().getFirstName()).contains(generateDTO.getFirstName());
		assertThat(fakeData.getName().getLastName()).contains(generateDTO.getLastName());
	}

	@Test
	@DisplayName("number 데이터 주입 테스트")
	void init_number_test() {
		TestDTO generateDTO = FakeDataGenerator.generate(TestDTO.class);
		assertThat(generateDTO.getAge()).isInstanceOf(Number.class);
	}

	@Test
	@DisplayName("number range(min, max) 데이터 주입 테스트")
	void init_number_range_test() {
		TestDTO generateDTO = FakeDataGenerator.generate(TestDTO.class);
		assertThat(generateDTO.getRangeAge()).isInstanceOf(Number.class);
		assertThat(generateDTO.getRangeAge()).isBetween(1, 100);
	}

	@Test
	@DisplayName("number min 데이터 주입 테스트")
	void init_number_min_test() {
		TestDTO generateDTO = FakeDataGenerator.generate(TestDTO.class);
		assertThat(generateDTO.getMinAge()).isInstanceOf(Number.class);
		assertThat(generateDTO.getMinAge()).isGreaterThan(5000);
	}

	@Test
	@DisplayName("number max 데이터 주입 테스트")
	void init_number_max_test() {
		TestDTO generateDTO = FakeDataGenerator.generate(TestDTO.class);
		assertThat(generateDTO.getMaxAge()).isInstanceOf(Number.class);
		assertThat(generateDTO.getMaxAge()).isLessThan(5000);
	}

	@Test
	@DisplayName("date 데이터 주입 테스트")
	void init_date_test() {
		TestDTO generateDTO = FakeDataGenerator.generate(TestDTO.class);
		assertThat(generateDTO.getBirthday()).isInstanceOf(LocalDate.class);
		assertThat(generateDTO.getBirthday()).isBetween(LocalDate.now().minusDays(365), LocalDate.now().plusDays(365));
	}

	@Test
	@DisplayName("en 데이터 주입 테스트")
	void init_en_data_test() {
		FakeDataDTO enFakeData = getFakeData("/en.yml");
		TestDTO generateDTO = FakeDataGenerator.generate(TestDTO.class, LangType.EN);
		//then
		assertThat(enFakeData.getName().getFirstName()).contains(generateDTO.getFirstName());
		assertThat(enFakeData.getName().getLastName()).contains(generateDTO.getLastName());
	}

	@Test
	@DisplayName("phone number 주입")
	void init_phone_number_test() {
		TestDTO generateDTO = FakeDataGenerator.generate(TestDTO.class);
		assertThat(generateDTO.getPhone()).isNotNull();
	}

	private FakeDataDTO getFakeData(String fileName) {
		try (InputStream inputStream = this.getClass().getResourceAsStream(fileName)) {
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
}
