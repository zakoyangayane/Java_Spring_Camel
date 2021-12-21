package com.task.service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HotelGroup {

    private String group;

    private List<HotelResponse> entities;
}
