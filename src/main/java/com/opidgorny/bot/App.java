package com.opidgorny.bot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication

public class App {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
        builder.headless(false).run(args);
        new BotFrame();
    }



}
