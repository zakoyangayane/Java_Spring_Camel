package com.task.service.csv.model;

import com.task.service.model.CovidResponse;
import com.task.service.model.HotelResponse;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "combine_xml")
public class CombineXml {

    @XmlAttribute(name = "country")
    private String country;

    @XmlAttribute(name = "city")
    private String city;

    @XmlAttribute(name = "date")
    private String date;

    @XmlElementWrapper
    @XmlElement(name = "hotel")
    private List<HotelResponse> hotels;

    @XmlElement(name = "covid_results")
    private CovidResponse covidResults;
}
