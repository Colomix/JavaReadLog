package ru.popkov.services;


import junit.framework.Assert;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.scp.UnknownCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogReaderTest {

    private static String[] executeCommand = new String[1];
    private SshServer sshServer;
    private int port = 2222;
    private String[] authentication = new String[2];

    @Before
    public void setUp() throws IOException {
        sshServer = SshServer.setUpDefaultServer();
        sshServer.setPort(port);
        File file = java.io.File.createTempFile("key", "ser");
        sshServer.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(file));
        sshServer.setPasswordAuthenticator((username, password, session) -> {
            authentication[0] = username;
            authentication[1] = password;
            return true;
        });

        sshServer.setCommandFactory(command -> {
            executeCommand[0] = command;
            return new UnknownCommand("Content file");
        });

        sshServer.start();
    }

    @After
    public void tearDown() throws IOException {
        sshServer.stop();
    }

    @Test
    public void Read_should_execute_command_remote() throws Exception {
        ISshConnectionConfig sshConnectionConfig = mock(ISshConnectionConfig.class);

        when(sshConnectionConfig.getServer()).thenReturn("127.0.0.1");
        when(sshConnectionConfig.getPort()).thenReturn(port);
        when(sshConnectionConfig.getLogin()).thenReturn("root");
        when(sshConnectionConfig.getPassword()).thenReturn("admin");
        when(sshConnectionConfig.getLineCount()).thenReturn(50);
        when(sshConnectionConfig.getFilePath()).thenReturn("1.log");

        LogReader logReader = new LogReader(sshConnectionConfig);

        Assert.assertEquals("Unknown command: Content file\n", logReader.read());
        Assert.assertEquals("head -n 50 1.log", executeCommand[0]);
        Assert.assertEquals("root", authentication[0]);
        Assert.assertEquals("admin", authentication[1]);
    }


}
