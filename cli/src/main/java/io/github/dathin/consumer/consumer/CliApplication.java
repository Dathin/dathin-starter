package io.github.dathin.consumer.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class CliApplication implements CommandLineRunner {

    @Autowired
    private Pipedream pipedream;

    public static void main(String[] args) {
        SpringApplication.run(CliApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var a = pipedream.response();
    }
}
