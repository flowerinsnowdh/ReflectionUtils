package online.flowerinsnow.reflectionutils.object;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class InstanceReflectionSession extends AbstractReflectionSession {
    @NotNull private final Class<?> type;
    @NotNull private final Object instance;

    public InstanceReflectionSession(@NotNull Class<?> type, @NotNull Object instance) throws IllegalArgumentException {
        this(type, instance, true);
    }

    public InstanceReflectionSession(@NotNull Class<?> type, @NotNull Object instance, boolean autoAccessible) throws IllegalArgumentException {
        super(autoAccessible);
        Objects.requireNonNull(type);
        Objects.requireNonNull(instance);
        if (!type.isInstance(instance)) {
            throw new IllegalArgumentException("Provided instance is not an instance of " + type.getName());
        }
        this.type = type;
        this.instance = instance;
    }

    @Override
    public @NotNull Class<?> getType() {
        return type;
    }

    @Override
    public @NotNull Object getInstance() {
        return instance;
    }

    @Override
    public @NotNull InstanceReflectionSession setLogDisabled(boolean logDisabled) {
        return (InstanceReflectionSession) super.setLogDisabled(logDisabled);
    }

    @Override
    public @NotNull InstanceReflectionSession setAutoAccessible(boolean autoAccessible) {
        return (InstanceReflectionSession) super.setAutoAccessible(autoAccessible);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        InstanceReflectionSession that = (InstanceReflectionSession) object;
        return type.equals(that.type) && instance.equals(that.instance);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + super.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + instance.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "InstanceReflectionSession{" +
                "super=" + super.toString() +
                ", type=" + type +
                ", instance=" + instance +
                '}';
    }
}
