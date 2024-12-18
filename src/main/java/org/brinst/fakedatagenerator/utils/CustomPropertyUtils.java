package org.brinst.fakedatagenerator.utils;

import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;

public class CustomPropertyUtils extends PropertyUtils {
	@Override
	public Property getProperty(Class<?> type, String name) {
		// Convert snake_case to camelCase
		String camelCaseName = convertToCamelCase(name);
		return super.getProperty(type, camelCaseName);
	}

	private String convertToCamelCase(String name) {
		StringBuilder result = new StringBuilder();
		boolean toUpperCase = false;

		for (char c : name.toCharArray()) {
			if (c == '_') {
				toUpperCase = true;
			} else {
				result.append(toUpperCase ? Character.toUpperCase(c) : c);
				toUpperCase = false;
			}
		}

		return result.toString();
	}
}
