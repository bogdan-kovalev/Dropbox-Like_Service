package com.teamdev.dropbox.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author Bogdan Kovalev.
 */

@Configuration
@ComponentScan(basePackages = {
        "com.teamdev.dropbox.services",
        "com.teamdev.dropbox.repository"})
public class TestConfig {
}
