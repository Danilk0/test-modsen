package com.example.demomodsen.integration.controller;

import com.example.demomodsen.dto.organizer.OrganizerCreateEditDto;
import com.example.demomodsen.dto.organizer.OrganizerReadDto;
import com.example.demomodsen.integration.IntegrationTestBase;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@RequiredArgsConstructor
public class OrganizerRestControllerTest extends IntegrationTestBase {
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
    void getOrganizersList() throws Exception {
        String uri = "/organizer";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OrganizerReadDto[] organizerReadDtos = mapFromJson(content, OrganizerReadDto[].class);
        assertTrue(organizerReadDtos.length > 0);
    }

    @Test
    void getOrganizerById() throws Exception {
        String uri = "/organizer/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OrganizerReadDto organizerReadDto = mapFromJson(content, OrganizerReadDto.class);
        assertEquals(organizerReadDto.id(),1);

    }

    @Test
    void createOrganizer() throws Exception {
        String uri = "/organizer";
        OrganizerCreateEditDto createDto = new OrganizerCreateEditDto("testCompany");

        String inputJson = mapToJson(createDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        OrganizerReadDto organizerReadDto = mapFromJson(content, OrganizerReadDto.class);
        assertNotNull(organizerReadDto.id());
    }

    @Test
    void updateOrganizer() throws Exception {
        String uri = "/organizer/2";
        OrganizerCreateEditDto editDto = new OrganizerCreateEditDto("test2");


        String inputJson = mapToJson(editDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OrganizerReadDto organizerReadDto = mapFromJson(content, OrganizerReadDto.class);
        assertNotNull(organizerReadDto.id());
        assertEquals(organizerReadDto.name(),editDto.name());
    }

    @Test
    void deleteOrganizer() throws Exception {
        String uri = "/organizer/2";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "");
    }

}
