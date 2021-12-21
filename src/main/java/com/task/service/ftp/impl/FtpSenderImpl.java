package com.task.service.ftp.impl;

import com.task.service.ftp.FtpProperties;
import com.task.service.ftp.FtpSender;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.MessageFormat;

@Service
class FtpSenderImpl implements FtpSender {

    private static final String CAMEL_FTP_PATTERN = "{0}://{1}@{2}/{3}?password={4}&passiveMode={5}";

    @Autowired
    private ProducerTemplate producerTemplate;

    @Override
    public void sendFile(FtpProperties ftpProperties, File file) throws RuntimeException {
        producerTemplate.sendBodyAndHeader("ftp://user@localhost:21/remoteDirectory?password=user", file, Exchange.FILE_NAME, file.getName());
    }

    private String createFtpUri(FtpProperties ftpProperties) {
        return MessageFormat.format(CAMEL_FTP_PATTERN,
                ftpProperties.getProtocol(),
                ftpProperties.getUserName(),
                ftpProperties.getHost(),
                ftpProperties.getRemoteDirectory(),
                ftpProperties.getPassword(),
                ftpProperties.getPassiveMode());
    }
}
