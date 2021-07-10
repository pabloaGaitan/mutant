package com.meli.mutant.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.meli.mutant.dto.StadisticsDto;
import com.meli.mutant.entity.Kind;
import com.meli.mutant.repository.DnaAnalyzedRepository;

@SpringBootTest
public class StadisticsServiceTest {
	
	@InjectMocks
	private StadisticsServiceImpl stadisticsService;
	
	@Mock
	private DnaAnalyzedRepository dnaAnalyzedRepository;
	
	@Test
	public void mutantStadistics0HumansRatioTest() {
		when(dnaAnalyzedRepository.countByKind(Kind.HUMAN)).thenReturn(0D);
		when(dnaAnalyzedRepository.countByKind(Kind.MUTANT)).thenReturn(5D);
		
		StadisticsDto mutantStadistics = stadisticsService.mutantStadistics();
		
		assertNotNull(mutantStadistics);
		assertEquals(0D, mutantStadistics.getRatio());
	}
	
	@Test
	public void mutantStadisticsRatioTest() {
		when(dnaAnalyzedRepository.countByKind(Kind.HUMAN)).thenReturn(10D);
		when(dnaAnalyzedRepository.countByKind(Kind.MUTANT)).thenReturn(5D);
		
		StadisticsDto mutantStadistics = stadisticsService.mutantStadistics();
		
		assertNotNull(mutantStadistics);
		assertEquals(0.5D, mutantStadistics.getRatio());
	}

}
