- Specify Dependency management
```xml
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>io.github.dathin.boot</groupId>
                <artifactId>dathin-starter-dependencies</artifactId>
                <version>0.0.3-SNAPSHOT</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```
- Spring version should be 2.6.7
```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.7</version>
        <relativePath/>
    </parent>
```
- Starter must not have a spring parent, and we must declare spring factory
- Starters could have it deps improved, like provided imports and some reduced