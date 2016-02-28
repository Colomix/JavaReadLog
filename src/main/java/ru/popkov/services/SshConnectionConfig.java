package ru.popkov.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:connection.properties")
public class SshConnectionConfig implements ISshConnectionConfig {

    @Value("${ssh.server}")
    private String server;

    @Value("${ssh.port}")
    private int port;

    @Value("${ssh.login}")
    private String login;

    @Value("${ssh.password}")
    private String password;

    @Value("${ssh.file}")
    private String filePath;

    @Value("${ssh.lines}")
    private int lines;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public String getServer() {
        return this.server;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public int getPort() {
        return this.port;
    }

    public int getLineCount() {
        return this.lines;
    }
}
