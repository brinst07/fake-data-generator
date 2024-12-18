package org.brinst.fakedatagenerator.service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FakeDataDTO {
	private NameDTO name;
	private EmailDTO email;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class NameDTO {
		@JsonProperty("first_name")
		private List<String> firstName;
		@JsonProperty("last_name")
		private List<String> lastName;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class EmailDTO {
		private List<String> id;
		private List<String> domain;
	}
}
