package org.brinst.fakedatagenerator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.brinst.fakedatagenerator.enums.DataType;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FakeField {
	DataType type();
	int min() default 0;
	int max() default 0;
}
