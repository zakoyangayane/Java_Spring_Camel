package com.task.service.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "covid_results")
public class CovidResponse implements Serializable {

    @XmlAttribute(name = "province")
    private String province;

    @XmlAttribute(name = "confirmed")
    private Long confirmed;

    @XmlAttribute(name = "recovered")
    private Long recovered;

    @XmlAttribute(name = "deaths")
    private Long deaths;

    @XmlAttribute(name = "active")
    private Long active;
}
