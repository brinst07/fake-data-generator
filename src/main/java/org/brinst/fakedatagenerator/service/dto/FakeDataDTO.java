package org.brinst.fakedatagenerator.service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FakeDataDTO {
	private NameDTO name;
	private EmailDTO email;

	public FakeDataDTO() {
	}

	public FakeDataDTO(NameDTO name, EmailDTO email) {
		this.name = name;
		this.email = email;
	}

	public NameDTO getName() {
		return name;
	}

	public void setName(NameDTO name) {
		this.name = name;
	}

	public void setEmail(EmailDTO email) {
		this.email = email;
	}

	public EmailDTO getEmail() {
		return email;
	}

	public static class NameDTO {
		@JsonProperty("first_name")
		private List<String> firstName;
		@JsonProperty("last_name")
		private List<String> lastName;

		public List<String> getFirstName() {
			return firstName;
		}

		public List<String> getLastName() {
			return lastName;
		}

		public void setFirstName(List<String> firstName) {
			this.firstName = firstName;
		}

		public void setLastName(List<String> lastName) {
			this.lastName = lastName;
		}

		public NameDTO() {
		}

		public NameDTO(List<String> firstName, List<String> lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
	}


	public static class EmailDTO {
		private List<String> id;
		private List<String> domain;

		public EmailDTO() {
		}

		public void setId(List<String> id) {
			this.id = id;
		}

		public void setDomain(List<String> domain) {
			this.domain = domain;
		}

		public List<String> getId() {
			return id;
		}

		public List<String> getDomain() {
			return domain;
		}

		public EmailDTO(List<String> id, List<String> domain) {
			this.id = id;
			this.domain = domain;
		}
	}
}
