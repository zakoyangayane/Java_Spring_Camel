package com.task.service.ftp;

public interface FtpProperties {
    /**
     * Gets the protocol
     * @return One of {@code ftp}, {@code ftps} or {@code sftp}
     */
    String getProtocol();

    /**
     * Gets the user name
     * @return The name of the user
     */
    String getUserName();

    /**
     * Gets the password
     * @return The password
     */
    String getPassword();

    /**
     * Gets the FTP host
     * @return The FTP host
     */
    String getHost();

    /**
     * Gets the name of the directory on the server where the file will be transferred
     * @return The name of the remote directory
     */
    String getRemoteDirectory();

    /**
     * Gets the passive mode, e.g. if the server is behind a firewall
     * @return whether or not passive mode should be used
     */
    boolean getPassiveMode();
}
