package com.example.demomodsen.integration.controller;

import com.example.demomodsen.dto.event.EventCreateEditDto;
import com.example.demomodsen.dto.event.EventReadDto;
import com.example.demomodsen.integration.IntegrationTestBase;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class EventRestControllerTest extends IntegrationTestBase {

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
    void getEventList() throws Exception {
        String uri = "/";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        EventReadDto[] eventReadDtos = mapFromJson(content, EventReadDto[].class);
        assertTrue(eventReadDtos.length > 0);
    }

    @Test
    void getEventById() throws Exception {
        String uri = "/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        EventReadDto eventReadDto = mapFromJson(content, EventReadDto.class);
        assertEquals(eventReadDto.id(),1);

    }

    @Test
    void createEvent() throws Exception {
        String uri = "/";
        EventCreateEditDto createDto = new EventCreateEditDto("test","test", LocalDateTime.of(2022,12,29,18,0,0),1,1);

        String inputJson = mapToJson(createDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        EventReadDto eventReadDto = mapFromJson(content, EventReadDto.class);
        assertNotNull(eventReadDto.id());
    }

    @Test
    void updateEvent() throws Exception {
        String uri = "/2";
        EventCreateEditDto editDto = new EventCreateEditDto("test","test", LocalDateTime.of(2022,12,29,18,0,0),1,1);


        String inputJson = mapToJson(editDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        EventReadDto eventReadDto = mapFromJson(content, EventReadDto.class);
        assertNotNull(eventReadDto.id());
        assertEquals(eventReadDto.dsc(),editDto.dsc());
        assertEquals(eventReadDto.title(),editDto.title());
        assertEquals(eventReadDto.time(),editDto.time());
        assertEquals(eventReadDto.organizer().id(),editDto.organizer_id());
        assertEquals(eventReadDto.place().id(),editDto.place_id());
    }

    @Test
    void deleteEvent() throws Exception {
        String uri = "/2";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "");
    }
}