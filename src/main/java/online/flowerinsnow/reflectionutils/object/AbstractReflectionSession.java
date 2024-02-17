package online.flowerinsnow.reflectionutils.object;

import online.flowerinsnow.reflectionutils.exception.MethodThrowsException;
import online.flowerinsnow.reflectionutils.exception.UnableNewException;
import online.flowerinsnow.reflectionutils.exception.UnexpectedException;
import online.flowerinsnow.reflectionutils.exception.notfound.ConstructorNotFoundException;
import online.flowerinsnow.reflectionutils.exception.notfound.FieldNotFoundException;
import online.flowerinsnow.reflectionutils.exception.notfound.MethodNotFoundException;
import online.flowerinsnow.reflectionutils.util.LogUtils;
import online.flowerinsnow.reflectionutils.util.NameUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractReflectionSession implements ReflectionSession {
    protected boolean autoAccessible;
    protected boolean logDisabled;

    public AbstractReflectionSession() {
        this(true);
    }

    public AbstractReflectionSession(boolean autoAccessible) {
        this.autoAccessible = autoAccessible;
    }

    @Override
    public abstract @NotNull Class<?> getType();
    public abstract @Nullable Object getInstance();

    @Override
    public boolean isLogDisabled() {
        return logDisabled;
    }

    @Override
    public @NotNull AbstractReflectionSession setLogDisabled(boolean logDisabled) {
        this.logDisabled = logDisabled;
        return this;
    }

    @Override
    public boolean isAutoAccessible() {
        return autoAccessible;
    }

    @Override
    public @NotNull AbstractReflectionSession setAutoAccessible(boolean autoAccessible) {
        this.autoAccessible = autoAccessible;
        return this;
    }

    @Override
    public @Nullable Field findField(@NotNull String name) {
        Objects.requireNonNull(name);
        Field field;
        try {
            field = getType().getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            return null;
        }
        if (autoAccessible) {
            field.setAccessible(true);
        }
        return field;
    }

    @Override
    public @NotNull Field findFieldNotNull(@NotNull String name) {
        Objects.requireNonNull(name);
        return Objects.requireNonNull(this.findField(name));
    }

    @Override
    public @NotNull Optional<Field> findFieldOptional(@NotNull String name) {
        Objects.requireNonNull(name);
        return Optional.ofNullable(this.findField(name));
    }

    @Override
    public @Nullable Method findMethod(@NotNull String name, @NotNull Class<?>... paramTypes) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(paramTypes);
        Method method;
        try {
            method = getType().getDeclaredMethod(name, paramTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
        if (autoAccessible) {
            method.setAccessible(true);
        }
        return method;
    }

    @Override
    public @NotNull Method findMethodNotNull(@NotNull String name, @NotNull Class<?>... paramTypes) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(paramTypes);
        return Objects.requireNonNull(this.findMethod(name, paramTypes));
    }

    @Override
    public @NotNull Optional<Method> findMethodOptional(@NotNull String name, @NotNull Class<?>... paramTypes) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(paramTypes);
        return Optional.ofNullable(this.findMethod(name, paramTypes));
    }

    @Override
    public @Nullable Constructor<?> findConstructor(@NotNull Class<?>... paramTypes) {
        Objects.requireNonNull(paramTypes);
        Constructor<?> constructor;
        try {
            constructor = getType().getDeclaredConstructor(paramTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
        if (autoAccessible) {
            constructor.setAccessible(true);
        }
        return constructor;
    }

    @Override
    public @NotNull Constructor<?> findConstructorNotNull(@NotNull Class<?>... paramTypes) {
        Objects.requireNonNull(paramTypes);
        return Objects.requireNonNull(findConstructor(paramTypes));
    }

    @Override
    public @NotNull Optional<Constructor<?>> findConstructorOptional(@NotNull Class<?>... paramTypes) {
        Objects.requireNonNull(paramTypes);
        return Optional.ofNullable(findConstructor(paramTypes));
    }

    @Override
    public <V> @Nullable V getFieldValue(@NotNull String fieldName, @NotNull Class<V> valueType) throws FieldNotFoundException {
        Field field = findFieldOptional(fieldName)
                .orElseThrow(() -> new FieldNotFoundException(NameUtils.fieldAbsoluteName(getType(), fieldName)));
        field.setAccessible(true);
        try {
            return valueType.cast(field.get(getInstance()));
        } catch (IllegalAccessException e) {
            if (!isLogDisabled()) {
                LogUtils.warningUnexpectedIllegalAccess();
            }
            throw new UnexpectedException(e);
        }
    }

    @Override
    public <V> @Nullable V invokeMethod(@NotNull String methodName, @NotNull Class<?>[] paramTypes, @Nullable Class<V> returnValueType, @Nullable Object... params) throws MethodNotFoundException, MethodThrowsException {
        Method method = findMethodOptional(methodName, paramTypes)
                .orElseThrow(() -> new MethodNotFoundException(NameUtils.methodAbsoluteName(getType(), methodName, paramTypes)));
        method.setAccessible(true);
        try {
            Object returnValue = method.invoke(getInstance(), params);
            if (returnValueType != null) {
                return returnValueType.cast(returnValue);
            }
            //noinspection unchecked
            return (V) returnValue;
        } catch (IllegalAccessException e) {
            if (!isLogDisabled()) {
                LogUtils.warningUnexpectedIllegalAccess();
            }
            throw new UnexpectedException(e);
        } catch (InvocationTargetException e) {
            throw new MethodThrowsException(e.getCause());
        }
    }

    @Override
    public <T> @NotNull T newInstance(@NotNull Class<?>[] paramTypes, @Nullable Class<T> type, @Nullable Object... params) {
        Constructor<?> constructor = findConstructorOptional(paramTypes)
                .orElseThrow(() -> new ConstructorNotFoundException(NameUtils.constructorAbsoluteName(getType(), paramTypes)));
        constructor.setAccessible(true);
        try {
            Object newInstance = constructor.newInstance(params);
            if (type != null) {
                return type.cast(newInstance);
            }
            //noinspection unchecked
            return (T) newInstance;
        } catch (InvocationTargetException e) {
            throw new MethodThrowsException(e.getCause());
        } catch (InstantiationException e) {
            throw new UnableNewException(e);
        } catch (IllegalAccessException e) {
            if (!isLogDisabled()) {
                LogUtils.warningUnexpectedIllegalAccess();
            }
            throw new UnexpectedException(e);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AbstractReflectionSession that = (AbstractReflectionSession) object;
        return autoAccessible == that.autoAccessible;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (autoAccessible ? 1231 : 1237);
        result = 31 * result + (logDisabled ? 1231 : 1237);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractReflectionSession{" +
                "autoAccessible=" + autoAccessible +
                ", logDisabled=" + logDisabled +
                '}';
    }
}
