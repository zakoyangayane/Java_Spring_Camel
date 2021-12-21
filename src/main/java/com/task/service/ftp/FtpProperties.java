package com.task.service.ftp;

public interface FtpProperties {

    String getProtocol();

    String getUserName();

    String getPort();

    String getPassword();

    String getHost();

    String getRemoteDirectory();

    boolean getPassiveMode();
}
