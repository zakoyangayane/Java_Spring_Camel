package com.task.service.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "hotel")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class HotelResponse {

    @XmlAttribute(name = "geo_id")
    private String geoId;
    @XmlAttribute(name = "destination_id")
    private String destinationId;
    @XmlAttribute(name = "landmark_city_destination_id")
    private String landmarkCityDestinationId;
    @XmlAttribute(name = "type")
    private String type;
    @XmlAttribute(name = "redirect_page")
    private String redirectPage;
    @XmlAttribute(name = "latitude")
    private String latitude;
    @XmlAttribute(name = "longitude")
    private String longitude;
    @XmlAttribute(name = "search_detail")
    private String searchDetail;
    @XmlAttribute(name = "caption")
    private String caption;
    @XmlAttribute(name = "name")
    private String name;
}
