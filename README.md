# ReflectionUtils
A library for Java reflection API

# Introduce
This library taking `session` as the core idea.

For reflecting a member, you can use `InstanceReflectionSession`

For reflecting static members, you can use `TypeReflectionSession`

# Demo
[DemoTest.java](src/test/java/online/flowerinsnow/reflectionutils/test/DemoTest.java)

# Shade/Shadow
If you are using this library. It is recommended that you use Relocation packages to shade/shadow into jar.

E.g.

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>3.5.1</version>
    <configuration>
        <createDependencyReducedPom>false</createDependencyReducedPom>
        <relocations>
            <relocation>
                <pattern>online.flowerinsnow.reflectionutils</pattern>
                <shadedPattern>your.pkg.hear.shaded.online.flowerinsnow.reflectionutils</shadedPattern>
            </relocation>
        </relocations>
    </configuration>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```