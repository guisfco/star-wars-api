package com.guisfco.starwars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guisfco.starwars.domain.dto.PlanetDto;
import com.guisfco.starwars.domain.request.PlanetRequest;
import com.guisfco.starwars.domain.response.ContentPageResponse;
import com.guisfco.starwars.exception.PlanetNotFoundException;
import com.guisfco.starwars.fixture.PlanetDtoFixture;
import com.guisfco.starwars.fixture.PlanetRequestFixture;
import com.guisfco.starwars.service.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PlanetController.class)
class PlanetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeletePlanetByIdService deletePlanetByIdService;

    @MockBean
    private FindAllPlanetsService findAllPlanetsService;

    @MockBean
    private FindPlanetByIdService findPlanetByIdService;

    @MockBean
    private SavePlanetService savePlanetService;

    @MockBean
    private SearchPlanetsByTextService searchPlanetsByTextService;

    @Captor
    private ArgumentCaptor<PlanetRequest> planetRequestArgumentCaptor;

    @Test
    @DisplayName("Must delete the planet by the provided id")
    public void deleteById() throws Exception {

        final String planetId = randomAlphabetic(5);

        mockMvc.perform(delete("/planets/{planetId}", planetId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(deletePlanetByIdService).deleteById(planetId);
    }

    @Test
    @DisplayName("Must respond with status code 404 when deleting is not possible")
    public void deleteByIdWhenPlanetNotFound() throws Exception {

        final String planetId = randomAlphabetic(5);

        doThrow(PlanetNotFoundException.class).when(deletePlanetByIdService).deleteById(planetId);

        mockMvc.perform(delete("/planets/{planetId}", planetId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(deletePlanetByIdService).deleteById(planetId);
    }

    @Test
    @DisplayName("Must return all the planets in a single list")
    public void findAll() throws Exception {

        final MvcResult mvcResult = mockMvc.perform(get("/planets").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        final String response = mvcResult.getResponse().getContentAsString();

        verify(findAllPlanetsService).findAll();
        assertThat(response).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(Collections.emptyList()));
    }

    @Test
    @DisplayName("Must find the planet by the provided id")
    public void findById() throws Exception {

        final String planetId = randomAlphabetic(5);
        final PlanetDto expected = PlanetDtoFixture.get().random().build();

        when(findPlanetByIdService.findById(planetId)).thenReturn(expected);

        final MvcResult mvcResult = mockMvc.perform(get("/planets/{planetId}", planetId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        final String response = mvcResult.getResponse().getContentAsString();

        verify(findPlanetByIdService).findById(planetId);
        assertThat(response).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expected));
    }

    @Test
    @DisplayName("Must respond with status code 404 when planet not found")
    public void findByIdWhenPlanetNotFound() throws Exception {

        final String planetId = randomAlphabetic(5);

        doThrow(PlanetNotFoundException.class).when(findPlanetByIdService).findById(planetId);

        mockMvc.perform(get("/planets/{planetId}", planetId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(findPlanetByIdService).findById(planetId);
    }

    @Test
    @DisplayName("Must save the planet")
    public void save() throws Exception {

        final PlanetRequest requestBody = PlanetRequestFixture.get().random().build();

        final PlanetDto expected = PlanetDto.builder()
                .climate(requestBody.getClimate())
                .id(randomAlphabetic(5))
                .filmsAppearances(nextInt(0, 5))
                .name(requestBody.getName())
                .terrain(requestBody.getTerrain())
                .build();

        when(savePlanetService.save(requestBody)).thenReturn(expected);

        final MvcResult mvcResult = mockMvc.perform(post("/planets").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isCreated())
                .andReturn();

        final String response = mvcResult.getResponse().getContentAsString();

        verify(savePlanetService).save(planetRequestArgumentCaptor.capture());
        assertEquals(requestBody, planetRequestArgumentCaptor.getValue());
        assertThat(response).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expected));
    }

    @Test
    @DisplayName("Must search the planets when all parameters were provided")
    public void searchByTextAllParameters() throws Exception {

        final ContentPageResponse<PlanetDto> expected = new ContentPageResponse<>(0, 0, true, true, Collections.emptyList());

        when(searchPlanetsByTextService.searchByText(anyString(), anyInt(), anyInt())).thenReturn(expected);

        final MvcResult mvcResult = mockMvc.perform(get("/planets/search").contentType(MediaType.APPLICATION_JSON)
                .param("text", "Tatooine").param("page", "4").param("size", "10"))
                .andExpect(status().isOk())
                .andReturn();

        final String response = mvcResult.getResponse().getContentAsString();

        verify(searchPlanetsByTextService).searchByText("Tatooine", 4, 10);
        assertThat(response).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expected));
    }

    @Test
    @DisplayName("Must search the planets even if all parameters weren't provided")
    public void searchByTextWithDefaultPageAndSize() throws Exception {

        final ContentPageResponse<PlanetDto> expected = new ContentPageResponse<>(0, 0, true, true, Collections.emptyList());

        when(searchPlanetsByTextService.searchByText(anyString(), anyInt(), anyInt())).thenReturn(expected);

        final MvcResult mvcResult = mockMvc.perform(get("/planets/search").contentType(MediaType.APPLICATION_JSON)
                .param("text", "Tatooine"))
                .andExpect(status().isOk())
                .andReturn();

        final String response = mvcResult.getResponse().getContentAsString();

        verify(searchPlanetsByTextService).searchByText("Tatooine", 1, 16);
        assertThat(response).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expected));
    }
}