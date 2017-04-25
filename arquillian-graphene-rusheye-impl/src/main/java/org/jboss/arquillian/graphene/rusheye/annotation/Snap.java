package org.jboss.arquillian.graphene.rusheye.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Snap {
	String value() default "";
	float perceptionTreshold() default -1f;
	float perceptionPercentage() default 0f;
	String[] masks() default {};
}
