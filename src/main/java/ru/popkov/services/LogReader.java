package ru.popkov.services;

import com.jcabi.ssh.SSHByPassword;
import com.jcabi.ssh.Shell;

import java.io.IOException;

/**
 * Created by Олег on 24.02.2016.
 */
public class LogReader implements ILogReader {

    private final ISshConnectionConfig sshConnectionConfig;

    public LogReader(ISshConnectionConfig sshConnectionConfig) {
        this.sshConnectionConfig = sshConnectionConfig;

    }

    public String read() throws IOException {
        Shell sshByPassword = new SSHByPassword(sshConnectionConfig.getServer(), sshConnectionConfig.getPort(),
                sshConnectionConfig.getLogin(), sshConnectionConfig.getPassword());

        String command = String.format("head -n %1$d %2$s", sshConnectionConfig.getLineCount(),
                sshConnectionConfig.getFilePath());

        return new Shell.Plain(sshByPassword).exec(command);

    }
}
