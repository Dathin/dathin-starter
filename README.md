# How to use it

```xml
<!--Import parent pom-->
<parent>
    <groupId>io.github.dathin.boot</groupId>
    <artifactId>dathin-starter-parent</artifactId>
    <version>0.0.3-SNAPSHOT</version>
    <relativePath/>
</parent>
```

```xml
<!--Use Managed Maven Compiler Plugin-->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
</plugin>
```

```xml
<!--Use Managed Spring Boot Maven Plugin-->
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
```

```xml
<!--Use All Managed Plugins-->
<plugins>
    <plugin>
        <groupId>io.github.dathin</groupId>
        <artifactId>jhusky</artifactId>
    </plugin>
    <plugin>
        <groupId>io.spring.javaformat</groupId>
        <artifactId>spring-javaformat-maven-plugin</artifactId>
    </plugin>
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
    </plugin>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
    </plugin>
    <plugin>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
    </plugin>
</plugins>
```
Just it!

# What it does

Specify Parent will do a couple of things:
- Choose default dependencies under ```<dependencies>```
- Configure plugin management for ```maven-compiler-plugin``` to already support mapstruct under ```<pluginManagement>```
- Dathin and Spring dependency management (version) under ```<dependencyManagement>```

# Versions
- Spring: 2.6.7
- Java: 11
