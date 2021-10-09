package com.ujuezeoke.techplayground.apis.utils;

import com.ujuezeoke.techplayground.apis.model.Course;
import com.ujuezeoke.techplayground.apis.model.Member;
import lombok.SneakyThrows;

import java.beans.BeanInfo;
import java.beans.FeatureDescriptor;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

public class CsvWriter {
    private CsvWriter() {
    }

    public static <T> String toCsv(Collection<T> collection, Class<T> clazz) {
        try {
            final StringBuilder stringBuilder = new StringBuilder();
            final List<String> acceptableTypeNames = asList("java.lang.String", "double", "boolean", "int", "long");
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            List<PropertyDescriptor> descriptors = Arrays.stream(beanInfo.getPropertyDescriptors())
                    .filter(propertyDescriptor -> !"class".equalsIgnoreCase(propertyDescriptor.getName()))
                    .filter(propertyDescriptor -> allowedDescriptors(acceptableTypeNames, propertyDescriptor)
                    )
                    .sorted((o1, o2) -> {
                        if (o1.getName().equalsIgnoreCase("id")) {
                            return -1;
                        }
                        if (o2.getName().equalsIgnoreCase("id")) {
                            return 1;
                        }
                        return o1.getName().compareTo(o2.getName());
                    })
                    .collect(Collectors.toList());
            final String headers = descriptors.stream().map(FeatureDescriptor::getName).collect(joining(","));
            stringBuilder.append(headers);
            for (T item : collection) {
                final String line = descriptors.stream()
                        .map(propertyDescriptor -> getInvoke(item, propertyDescriptor))
                        .collect(joining(","));
                stringBuilder.append("\n").append(line);
            }

            return stringBuilder.toString();
        } catch (Exception e) {
            return "";
        }
    }

    private static boolean allowedDescriptors(List<String> acceptableTypeNames, PropertyDescriptor propertyDescriptor) {
        final Class<?> genericReturnType = propertyDescriptor.getReadMethod()
                .getReturnType();
        final boolean isCollection = Collection.class.isAssignableFrom(genericReturnType);
        return isCollection || acceptableTypeNames.contains(genericReturnType.getTypeName());
    }

    private static <T> String getInvoke(T item, PropertyDescriptor propertyDescriptor) {
        try {
            final Method readMethod = propertyDescriptor.getReadMethod();
            if (!Collection.class.isAssignableFrom(readMethod.getReturnType())) {
                return readMethod.invoke(item).toString();
            } else {
                final Collection collection = (Collection) readMethod.invoke(item);
                final String collect = (String) collection
                        .stream()
                        .map(CsvWriter::getGetId)
                        .collect(joining(","));

                return "\""+collect+"\"";
            }
        } catch (Exception e) {
            return "";
        }
    }

    @SneakyThrows
    private static String getGetId(Object it) {
        return it.getClass().getDeclaredMethod("getId").invoke(it).toString();
    }


    public static void main(String[] args) {
        final List<Course> courses = CoursesListGenerator.getCourses(8);
        final String s = CsvWriter.toCsv(courses, Course.class);
        System.out.println(s);
        System.out.println(CsvWriter.toCsv(MemberListGenerator.getMembers(2, courses), Member.class));
    }
}
