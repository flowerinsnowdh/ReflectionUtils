package online.flowerinsnow.reflectionutils.object;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class TypeReflectionSession extends AbstractReflectionSession {
    @NotNull private final Class<?> type;

    public TypeReflectionSession(@NotNull Class<?> type) {
        this(type, true);
    }

    public TypeReflectionSession(@NotNull Class<?> type, boolean autoAccessible) {
        super(autoAccessible);
        this.type = Objects.requireNonNull(type);
    }

    @Override
    public @NotNull Class<?> getType() {
        return type;
    }

    @Override
    public @Nullable Object getInstance() {
        return null;
    }

    @Override
    public @NotNull TypeReflectionSession setLogDisabled(boolean logDisabled) {
        return (TypeReflectionSession) super.setLogDisabled(logDisabled);
    }

    @Override
    public @NotNull TypeReflectionSession setAutoAccessible(boolean autoAccessible) {
        return (TypeReflectionSession) super.setAutoAccessible(autoAccessible);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        TypeReflectionSession that = (TypeReflectionSession) object;
        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + super.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TypeReflectionSession{" +
                "super=" + super.toString() +
                ", type=" + type +
                '}';
    }
}
