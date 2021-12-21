package com.task.service.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.service.model.CovidResponse;
import com.task.service.model.HotelGroup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RequestManager {

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    @Value("${rapid.api.key}")
    private String rapidApiKey;

    @Value("${rapid.api.host_hotel}")
    private String rapidApiHostHotel;

    @Value("${rapid.api.host_covid}")
    private String rapidApiHostCovid;

    @SneakyThrows
    public List<HotelGroup> makeGetHotelRequest(String value) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-rapidapi-host", rapidApiHostHotel);
        headers.set("x-rapidapi-key", rapidApiKey);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://hotels4.p.rapidapi.com/locations/v2/search")
                .queryParam("query", value);

        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET, request,
                JsonNode.class);

        return objectMapper.readValue(responseEntity.getBody().path("suggestions").toPrettyString(),
                new TypeReference<>() {
                });
    }

    @SneakyThrows
    public List<CovidResponse> makeGetCovidStatsRequest(String name, final LocalDate date) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-rapidapi-host", rapidApiHostCovid);
        headers.set("x-rapidapi-key", rapidApiKey);
        name = name.trim().replace(" ", "+");
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://covid-19-data.p.rapidapi.com/report/country/name")
                .queryParam("name", name)
                .queryParam("date", date);

        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);
        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET, request,
                JsonNode.class);

        return objectMapper.readValue(responseEntity.getBody().get(0).path("provinces").toPrettyString(),
                new TypeReference<>() {
                });
    }
}
