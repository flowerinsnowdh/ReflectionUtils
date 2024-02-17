package online.flowerinsnow.reflectionutils.util;

import online.flowerinsnow.reflectionutils.ReflectionUtils;
import online.flowerinsnow.reflectionutils.object.ReflectionSession;

public abstract class LogUtils {
    private LogUtils() {
    }

    public static final String LOGGER_NAME = ReflectionUtils.class.getName();

    public static void warningUnexpectedIllegalAccess() {
        // Object logger = org.slf4j.LoggerFactory.getLogger(LOGGER_NAME);
        // logger.warn(param0);
        ReflectionSession.ofTypeOptional("org.slf4j.LoggerFactory")
                .map(rs -> rs.invokeMethod("getLogger", new Class[]{String.class}, Object.class, LOGGER_NAME))
                .ifPresent(logger -> {
                    ReflectionSession.ofInstanceNotNull("org.slf4j.Logger", logger)
                            .invokeMethod("warn", new Class[]{String.class}, null, "Reflection throws " + IllegalAccessException.class.getName() + ", it should never throws!");
                });
    }
}
