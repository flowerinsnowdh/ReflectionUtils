package online.flowerinsnow.reflectionutils.util;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class NameUtils {
    private NameUtils() {
    }

    /**
     * <p>Return classname#fieldName</p>
     * @param type class name
     * @param fieldName field name
     * @return classname#fieldName
     */
    public static String fieldAbsoluteName(@NotNull Class<?> type, @NotNull String fieldName) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(fieldName);
        return type.getName() + "#" + fieldName;
    }

    /**
     * <p>Return classname#methodName(paramTypes...)</p>
     * @param type class
     * @param methodName method name
     * @param paramTypes param types
     * @return classname#methodName(paramTypes...)
     */
    public static String methodAbsoluteName(@NotNull Class<?> type, @NotNull String methodName, @NotNull Class<?>... paramTypes) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(methodName);
        Objects.requireNonNull(paramTypes);

        return type.getName() +
                "#" +
                methodName +
                buildParamTypesString(paramTypes);
    }

    /**
     * <p>Return classname#&lt;init&gt;(paramTypes...)</p>
     * @param type class name
     * @param paramTypes param types
     * @return classname#&lt;init&gt;(paramTypes...)
     */
    public static @NotNull String constructorAbsoluteName(@NotNull Class<?> type, @NotNull Class<?>... paramTypes) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(paramTypes);

        return type.getName() +
                "#<init>" +
                buildParamTypesString(paramTypes);
    }

    /**
     * <p>Return (param1, param2, param3...)</p>
     * @param paramTypes param types
     * @return (param1, param2, param3...)
     */
    public static @NotNull String buildParamTypesString(@NotNull Class<?>... paramTypes) {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < paramTypes.length; i++) {
            sb.append(paramTypes[i].getName());
            if (i + 1 < paramTypes.length) {
                sb.append(", ");
            } else {
                sb.append(")");
            }
        }
        return sb.toString();
    }
}
