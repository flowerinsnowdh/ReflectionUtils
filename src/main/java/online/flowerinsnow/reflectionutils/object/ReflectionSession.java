package online.flowerinsnow.reflectionutils.object;

import online.flowerinsnow.reflectionutils.ReflectionUtils;
import online.flowerinsnow.reflectionutils.exception.IllegalTypeException;
import online.flowerinsnow.reflectionutils.exception.MethodThrowsException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>A reflection session of an object and class.</p>
 */
public interface ReflectionSession {
    static @NotNull InstanceReflectionSession ofInstance(@NotNull Class<?> type, @NotNull Object instance) throws IllegalTypeException {
        return new InstanceReflectionSession(type, instance);
    }

    static @Nullable InstanceReflectionSession ofInstance(@NotNull String classname, @NotNull Object instance) throws IllegalTypeException {
        return ofInstanceOptional(classname, instance).orElse(null);
    }

    static @NotNull InstanceReflectionSession ofInstanceNotNull(@NotNull String classname, @NotNull Object instance) throws IllegalTypeException {
        return Objects.requireNonNull(ofInstanceOptional(classname, instance).orElse(null));
    }

    static @NotNull Optional<InstanceReflectionSession> ofInstanceOptional(@NotNull String classname, @NotNull Object instance) throws IllegalTypeException {
        return ReflectionUtils.findClassOptional(classname).map(type -> ReflectionSession.ofInstance(type, instance));
    }

    static @NotNull TypeReflectionSession ofType(@NotNull Class<?> type) {
        return new TypeReflectionSession(type);
    }

    static @Nullable TypeReflectionSession ofType(@NotNull String classname) {
        return ReflectionSession.ofTypeOptional(classname).orElse(null);
    }

    static @NotNull TypeReflectionSession ofTypeNotNull(@NotNull String classname) {
        return new TypeReflectionSession(ReflectionUtils.findClassNotNull(classname));
    }

    static @NotNull Optional<TypeReflectionSession> ofTypeOptional(@NotNull String classname) {
        return ReflectionUtils.findClassOptional(classname).map(ReflectionSession::ofType);
    }

    /**
     * <p>Get the class type of this session.</p>
     * @return The class type of this session
     */
    @NotNull Class<?> getType();

    /**
     * <p>Get the instance of this session.</p>
     * <p>Null if static mode.</p>
     * @return The instance of this session.
     */
    @Nullable Object getInstance();

    /**
     * <p>True if log disabled</p>
     * @return True if log disabled
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    boolean isLogDisabled();

    /**
     * <p>Set log disabled</p>
     * @param logDisabled log disabled
     * @return this
     */
    @NotNull ReflectionSession setLogDisabled(boolean logDisabled);

    /**
     * <p>True if automatic call setAccessible(true) enabled</p>
     * @return True if automatic call setAccessible(true) enabled
     * @see java.lang.reflect.Field#setAccessible(boolean)
     * @see java.lang.reflect.Method#setAccessible(boolean)
     * @see java.lang.reflect.Constructor#setAccessible(boolean)
     */
    boolean isAutoAccessible();

    /**
     * <p>Set automatic call setAccessible(true) enable or not.</p>
     * @param autoAccessible Automatic call setAccessible(true) enabled
     * @return this
     * @see java.lang.reflect.Field#setAccessible(boolean)
     * @see java.lang.reflect.Method#setAccessible(boolean)
     * @see java.lang.reflect.Constructor#setAccessible(boolean)
     */
    @NotNull AbstractReflectionSession setAutoAccessible(boolean autoAccessible);

    /**
     * <p>Look for a field by name.</p>
     * <p>Null if not found.</p>
     * @param name field name
     * @return field found, or null if not found.
     */
    @Nullable Field findField(@NotNull String name);

    /**
     * <p>Look for a field by name.</p>
     * <p>{@link NullPointerException} throws if not found.</p>
     * @param name field name
     * @return field found
     * @throws NullPointerException if not found
     */
    @NotNull Field findFieldNotNull(@NotNull String name) throws NullPointerException;

    @NotNull Optional<Field> findFieldOptional(@NotNull String name);

    /**
     * <p>Look for a method by name and param types.</p>
     * <p>Null if not found.</p>
     * @param name method name
     * @param paramTypes param types
     * @return method found, or null if not found.
     */
    @Nullable Method findMethod(@NotNull String name, @NotNull Class<?>... paramTypes);

    /**
     * <p>Look for a method by name and param types.</p>
     * <p>{@link NullPointerException} throws if not found.</p>
     * @param name method name
     * @param paramTypes param types
     * @return method found
     * @throws NullPointerException if not found
     */
    @NotNull Method findMethodNotNull(@NotNull String name, @NotNull Class<?>... paramTypes);

    @NotNull Optional<Method> findMethodOptional(@NotNull String name, @NotNull Class<?>... paramTypes);

    /**
     * <p>Look for a constructor by param types.</p>
     * <p>Null if not found.</p>
     * @param paramTypes param types
     * @return constructor found, or null if not found.
     */
    @Nullable Constructor<?> findConstructor(@NotNull Class<?>... paramTypes);

    /**
     * <p>Look for a con constructor param types.</p>
     * <p>{@link NullPointerException} throws if not found.</p>
     * @param paramTypes param types
     * @return constructor found
     * @throws NullPointerException if not found
     */
    @NotNull Constructor<?> findConstructorNotNull(@NotNull Class<?>... paramTypes);

    @NotNull Optional<Constructor<?>> findConstructorOptional(@NotNull Class<?>... paramTypes);

    /**
     * <p>Get field value in this instance.</p>
     * <p>And automatic cast to specified type</p>
     * @param fieldName field name
     * @param valueType specified type
     * @return field value
     * @param <V> specified type
     */
    <V> @Nullable V getFieldValue(@NotNull String fieldName, @NotNull Class<V> valueType);

    /**
     * <p>Invoke a method in this instance.</p>
     * <p>And automatic cast the return value to specified type</p>
     * @param methodName method name
     * @param paramTypes param types
     * @param returnValueType specified type
     * @param params params
     * @return return value
     * @param <V> specified type
     * @throws MethodThrowsException throws when method throws exception
     */
    <V> @Nullable V invokeMethod(@NotNull String methodName, @NotNull Class<?>[] paramTypes, @Nullable Class<V> returnValueType, @Nullable Object... params) throws MethodThrowsException;

    /**
     * <p>New an instance with constructor.</p>
     * @param paramTypes param types
     * @param params params
     * @return instance created
     * @throws MethodThrowsException throws when method throws exception
     * @param <T> type
     */
    <T> @Nullable Object newInstance(@NotNull Class<?>[] paramTypes, @Nullable Class<T> type, @Nullable Object... params);
}
