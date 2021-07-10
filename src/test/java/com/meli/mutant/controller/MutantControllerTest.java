package com.meli.mutant.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.mutant.dto.DnaDto;
import com.meli.mutant.exception.NoValidCharacterException;
import com.meli.mutant.services.MutantService;

@SpringBootTest
@AutoConfigureMockMvc
public class MutantControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private MutantService mutantService;
	
	List<String> dna = List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
	
	ObjectMapper ob = new ObjectMapper();
	
	DnaDto dnaDto = new DnaDto();
	
	@BeforeEach
	public void setup() {
		dnaDto.setDna(dna);
	}
	
	@Test
	public void testIsMutant() throws JsonProcessingException, Exception {
		when(mutantService.isMutant(dna)).thenReturn(true);
		mvc.perform(post("/mutant")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(dnaDto)))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testIsMutantBadRequest() throws JsonProcessingException, Exception {
		when(mutantService.isMutant(dna)).thenThrow(NoValidCharacterException.class);
		mvc.perform(post("/mutant")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(dnaDto)))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testIsNotMutant() throws JsonProcessingException, Exception {
		when(mutantService.isMutant(dna)).thenReturn(false);
		mvc.perform(post("/mutant")
				.contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(dnaDto)))
		.andExpect(status().isForbidden());
	}

}
