package ru.popkov.services;

import com.jcabi.ssh.SSHByPassword;
import com.jcabi.ssh.Shell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class LogReader implements ILogReader {

    private final ISshConnectionConfig sshConnectionConfig;

    @Autowired
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
