package com.task.service.ftp;

import java.io.File;

public interface FtpSender {

    /**
     * Uses the {@code ftpProperties} to transmit the provided {@code file} to a remote server
     *
     * @param ftpProperties The FTP properties of the remote server
     * @param file The file to transmit
     */
    void sendFile(FtpProperties ftpProperties, File file);
}
