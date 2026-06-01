// src/main/java/com/exemplo/mvc/MvcApplication.java
package com.exemplo.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de entrada da aplicação.
 *
 * @SpringBootApplication ativa:
 *   - @ComponentScan  → detecta @Controller, @Service, @Repository
 *   - @EnableAutoConfiguration → configura Thymeleaf, DispatcherServlet etc.
 *   - @Configuration  → permite beans definidos nesta classe
 */
@SpringBootApplication
public class MvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcApplication.class, args);
    }
}
