package ru.main.JavaReadLog;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

/**
 * Created by Олег on 24.02.2016.
 */
@Resource.Classpath("SshConnection.properties")
public class SshConnectionConfig implements ISshConnectionConfig {

    @Property("server")
    private String server;

    @Property("port")
    private int port;

    @Property("login")
    private String login;

    @Property("password")
    private String password;

    @Property("file")
    private String filePath;

    @Property("lines")
    private int lines;

    public SshConnectionConfig() {
        PropertyLoader.populate(this); //инициализация полей класса значениями из файла
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
