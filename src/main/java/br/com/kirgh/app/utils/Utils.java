package br.com.kirgh.app.utils;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The Utils class is an abstract class that provides utility methods.
 */
public abstract class Utils {
    /**
     * The function converts a byte array to a UUID object in Java.
     * 
     * @param bytes The "bytes" parameter is an array of bytes that represents a UUID.
     * @return The method is returning a UUID (Universally Unique Identifier) object.
     */
    public static UUID convertBytesToUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();

        return new UUID(high, low);
    }

    /**
     * The function removes the keys "page", "size", and "sort" from a given map.
     * 
     * @param filter A map containing key-value pairs.
     */
    public static void removePageableKeysFromFilter(Map<String, String> filter) {
        while (filter.get("page") != null || filter.get("size") != null || filter.get("sort") != null) {
            if (filter.get("page") != null) {
                filter.remove("page");
            }

            if (filter.get("size") != null) {
                filter.remove("size");
            }

            if (filter.get("sort") != null) {
                filter.remove("sort");
            }
        }
    }

    /**
     * The function validates if the fields in the given filters map are valid for the given entity
     * class.
     * 
     * @param filters A map containing the filters to be validated. The keys represent the field names
     * and the values represent the filter values.
     * @param entityClass The `entityClass` parameter is the class of the entity for which the filters
     * are being validated. It is used to determine the allowed fields for the filters.
     */
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

    /**
     * The function builds a specification for filtering data based on a map of filters, where each
     * filter is applied as a predicate to the root entity.
     * 
     * @param filters A map containing the filters to be applied to the query. The keys represent the
     * field names in the entity, and the values represent the filter values to be matched.
     * @return The method is returning a Specification object.
     */
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
