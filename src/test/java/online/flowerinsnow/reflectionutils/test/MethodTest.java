package online.flowerinsnow.reflectionutils.test;

import online.flowerinsnow.reflectionutils.util.LogUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodTest {
    @Test
    public void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = MethodTest.class.getDeclaredMethod("testMethod");
        Object returnValue = method.invoke(null);
        System.out.println(returnValue == null); // true
    }

    @Test
    public void test2() {
        LogUtils.warningUnexpectedIllegalAccess();
    }

    private static void testMethod() {

    }
}
