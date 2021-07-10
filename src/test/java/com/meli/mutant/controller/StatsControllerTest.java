package com.meli.mutant.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meli.mutant.dto.StadisticsDto;
import com.meli.mutant.services.StadisticsService;

@SpringBootTest
@AutoConfigureMockMvc
public class StatsControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private StadisticsService stadisticsService;
	
	@Test
	public void testIsMutant() throws JsonProcessingException, Exception {
		when(stadisticsService.mutantStadistics()).thenReturn(new StadisticsDto());
		mvc.perform(get("/stats")
				.contentType(MediaType.APPLICATION_JSON))	
		.andExpect(status().isOk());
	}

}
