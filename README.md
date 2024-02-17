# ReflectionUtils
A library for Java reflection API

# Introduce
This library taking `session` as the core idea.

For reflecting a member, you can use `InstanceReflectionSession`

For reflecting static members, you can use `TypeReflectionSession`

# Demo
[DemoTest.java](src/test/java/online/flowerinsnow/reflectionutils/test/DemoTest.java)

# Include
Repository URL of this project is [https://maven.pkg.github.com/flowerinsnowdh/ReflectionUtils](https://maven.pkg.github.com/flowerinsnowdh/ReflectionUtils)

## Maven
```xml
<repository>
    <id>ReflectionUtils</id>
    <url>https://maven.pkg.github.com/flowerinsnowdh/ReflectionUtils</url>
</repository>
```

```xml
<dependency>
    <groupId>online.flowerinsnow.reflectionutils</groupId>
    <artifactId>reflectionutils</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

## Gradle
```groovy
maven {
    url 'https://maven.pkg.github.com/flowerinsnowdh/ReflectionUtils'
}
```

```groovy
api 'online.flowerinsnow.reflectionutils:reflectionutils:1.0.0'
```

## Shade/Shadow
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