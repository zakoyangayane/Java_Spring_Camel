package com.task.service.ftp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DockerFtpProperties implements FtpProperties {

    @Value("${ftp.server.protocol}")
    private String protocol;

    @Value("${ftp.server.username}")
    private String userName;

    @Value("${ftp.server.password}")
    private String password;

    @Value("${ftp.server.host}")
    private String host;

    @Value("${ftp.server.port}")
    private String port;

    @Value("${ftp.server.remote-directory}")
    private String remoteDirectory;

    @Value("${ftp.server.passive-mode}")
    private boolean passiveMode;

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPort() {
        return port;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getRemoteDirectory() {
        return remoteDirectory;
    }

    @Override
    public boolean getPassiveMode() {
        return passiveMode;
    }
}
