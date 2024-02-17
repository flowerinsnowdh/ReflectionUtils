package online.flowerinsnow.reflectionutils.test;

import online.flowerinsnow.reflectionutils.ReflectionUtils;
import online.flowerinsnow.reflectionutils.object.InstanceReflectionSession;
import online.flowerinsnow.reflectionutils.object.ReflectionSession;
import org.junit.jupiter.api.Test;

public class DemoTest {
    @Test
    public void test() {
        // Rabbit rabbit = new Rabbit();
        Object rabbit = ReflectionSession.ofTypeNotNull(
                "online.flowerinsnow.reflectionutils.test.object.Rabbit"
        ).newInstance(new Class[0], null);

        // Tiger tiger = new Tiger();
        Object tiger = ReflectionSession.ofTypeNotNull(
                "online.flowerinsnow.reflectionutils.test.object.Tiger"
        ).newInstance(new Class[0], null);

        InstanceReflectionSession tigerSession = ReflectionSession.ofInstanceNotNull("online.flowerinsnow.reflectionutils.test.object.Tiger", tiger);
        // tiger.rest();
        tigerSession.invokeMethod("rest", new Class[0], null);
        // tiger.eat(rabbit)
        tigerSession.invokeMethod("eat", new Class[]{
                ReflectionUtils.findClassNotNull("online.flowerinsnow.reflectionutils.test.object.Animal")
        }, null, rabbit);
    }
}
