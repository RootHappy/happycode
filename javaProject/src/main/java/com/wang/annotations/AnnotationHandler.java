package com.wang.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class AnnotationHandler {

	public static void handler(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		Method[] methods = clazz.getDeclaredMethods();
		for(Field field : fields) {
			if(field.isAnnotationPresent(PersonName.class)) {
				PersonName personName = field.getAnnotation(PersonName.class);
				System.out.println(personName.value());
			}
			if(field.isAnnotationPresent(PersonGender.class)) {
				PersonGender personGender = field.getAnnotation(PersonGender.class);
				System.out.println(personGender.value());
			}
			if(field.isAnnotationPresent(PersonBirthday.class)) {
				PersonBirthday personBirthday = field.getAnnotation(PersonBirthday.class);
				System.out.println(personBirthday.year() + "-" + personBirthday.month() + "-" + personBirthday.day());
			}
		}

		for(Method method : methods)  {
			method.getAnnotations();
			method.isAnnotationPresent(PersonName.class);
		}
	}

	public static void main(String[] args) {
		handler(Person.class);
	}

}
