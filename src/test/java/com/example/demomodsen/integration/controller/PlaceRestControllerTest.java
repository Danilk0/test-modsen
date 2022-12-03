package com.example.demomodsen.integration.controller;

import com.example.demomodsen.dto.event.EventCreateEditDto;
import com.example.demomodsen.dto.event.EventReadDto;
import com.example.demomodsen.dto.place.PlaceCreateEditDto;
import com.example.demomodsen.dto.place.PlaceReadDto;
import com.example.demomodsen.integration.IntegrationTestBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import liquibase.pro.packaged.P;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@RequiredArgsConstructor
public class PlaceRestControllerTest  extends IntegrationTestBase {
    private final MockMvc mockMvc;

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(obj);
    }

    private  <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return objectMapper.readValue(json, clazz);
    }

    @Test
    void getPlacesList() throws Exception {
        String uri = "/place";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        PlaceReadDto[] placeReadDtos = mapFromJson(content, PlaceReadDto[].class);
        assertTrue(placeReadDtos.length > 0);
    }

    @Test
    void getPLaceById() throws Exception {
        String uri = "/place/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        PlaceReadDto placeReadDto = mapFromJson(content, PlaceReadDto.class);
        assertEquals(placeReadDto.id(),1);

    }

    @Test
    void createPlace() throws Exception {
        String uri = "/place";
        PlaceCreateEditDto createDto = new PlaceCreateEditDto("test");

        String inputJson = mapToJson(createDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        PlaceReadDto placeReadDto = mapFromJson(content, PlaceReadDto.class);
        assertNotNull(placeReadDto.id());
    }

    @Test
    void updatePlace() throws Exception {
        String uri = "/place/2";
        PlaceCreateEditDto editDto = new PlaceCreateEditDto("test");


        String inputJson = mapToJson(editDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        PlaceReadDto eventReadDto = mapFromJson(content, PlaceReadDto.class);
        assertNotNull(eventReadDto.id());
        assertEquals(eventReadDto.address(),editDto.address());
    }

    @Test
    void deletePlace() throws Exception {
        String uri = "/place/2";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "");
    }

}
