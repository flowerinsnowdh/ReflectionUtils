package online.flowerinsnow.reflectionutils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public abstract class ReflectionUtils {
    private ReflectionUtils() {
    }

    public static @Nullable Class<?> findClass(@NotNull String name) {
        Objects.requireNonNull(name);
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static @NotNull Class<?> findClassNotNull(@NotNull String name) {
        Objects.requireNonNull(name);
        return Objects.requireNonNull(findClass(name));
    }

    public static @NotNull Optional<Class<?>> findClassOptional(@NotNull String name) {
        Objects.requireNonNull(name);
        return Optional.ofNullable(findClass(name));
    }
}
