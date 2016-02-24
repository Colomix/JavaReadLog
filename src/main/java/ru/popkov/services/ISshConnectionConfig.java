package ru.popkov.services;

/**
 * Created by Олег on 24.02.2016.
 */
public interface ISshConnectionConfig {
    String getServer();
    String getLogin();
    String getPassword();
    String getFilePath();
    int getPort();
    int getLineCount();
}
