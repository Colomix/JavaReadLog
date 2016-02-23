package ru.main.JavaReadLog;

import java.io.IOException;

/**
 * Created by Олег on 23.02.2016.
 */
public class Main {
    public static void main(String... args) throws IOException {

        ISshConnectionConfig sshConnectionConfig = new SshConnectionConfig();

        ILogReader logReader = new LogReader(sshConnectionConfig);

        System.out.println(logReader.read());
    }
}
