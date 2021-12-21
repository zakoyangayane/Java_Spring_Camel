package com.task.service.ftp.impl;

import com.task.service.ftp.FtpProperties;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class FtpRouteBuilder extends RouteBuilder {

    private final FtpProperties ftpProperties;
    private static final String CAMEL_FTP_PATTERN = "{0}://{1}@{2}:{3}/{4}?password={5}&passiveMode={6}";

    @Override
    public void configure() {
        from("file://xml")
                .to(createFtpUri(ftpProperties)).log("XML transfer done");
    }

    private String createFtpUri(FtpProperties ftpProperties) {
        return MessageFormat.format(CAMEL_FTP_PATTERN,
                ftpProperties.getProtocol(),
                ftpProperties.getUserName(),
                ftpProperties.getHost(),
                ftpProperties.getPort(),
                ftpProperties.getRemoteDirectory(),
                ftpProperties.getPassword(),
                ftpProperties.getPassiveMode());
    }
}
