package com.task.service.ftp;

import java.io.File;

public interface FtpSender {

    void sendFile(FtpProperties ftpProperties, File file);
}
