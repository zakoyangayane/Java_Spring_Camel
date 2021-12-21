package com.task.service.ftp.impl;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class FtpRouteBuilder  extends RouteBuilder {

//    @Autowired
//    private FileProcessor fileProcessor;

//    public FtpRouteBuilder() {
//    }

    @Override
    public void configure() throws Exception {
        from("file://localDirectory")
                .to("ftp://user@localhost:21/remoteDirectory?password=user&passiveMode=true").log("transfer done");
//                .routeId("FtpRouteBuilder")
//                .bean(fileProcessor, "process");
    }
}
