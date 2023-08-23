package br.com.kirgh.app.utils;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Utils {
    public static UUID convertBytesToUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();

        return new UUID(high, low);
    }

    public static void removePageableKeysFromFilter(Map<String, String> filter) {
        while (filter.get("page") != null || filter.get("size") != null) {
            if (filter.get("page") != null) {
                filter.remove("page");
            }

            if (filter.get("size") != null) {
                filter.remove("size");
            }
        }
    }

    public static void validateFilters(Map<String, String> filters, Class entityClass) {
        Set<String> allowedFields = Arrays.stream(entityClass.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toSet());

        for (String key : filters.keySet()) {
            if (!allowedFields.contains(key)) {
                throw new IllegalArgumentException("invalid filter field: " + key);
            }
        }
    }

    public static Specification buildSpecification(Map<String, String> filters) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            filters.forEach((key, value) -> {
                Class<?> fieldType = root.get(key).getJavaType();

                if (Enum.class.isAssignableFrom(fieldType)) {
                    Enum<?> enumValue = Enum.valueOf((Class<Enum>) fieldType, value);
                    predicates.add(builder.equal(root.get(key), enumValue));
                } else {
                    predicates.add(builder.like(root.get(key), "%" + value + "%"));
                }
            });

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
