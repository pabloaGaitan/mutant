package com.meli.mutant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.mutant.dto.StadisticsDto;
import com.meli.mutant.entity.Kind;
import com.meli.mutant.repository.DnaAnalyzedRepository;

@Service
public class StadisticsServiceImpl implements StadisticsService {
	
	@Autowired
	private DnaAnalyzedRepository dnaAnalyzedRepository;
	
	@Override
	public StadisticsDto mutantStadistics() {
		StadisticsDto stadistics = new StadisticsDto();
		double numHumans = dnaAnalyzedRepository.countByKind(Kind.HUMAN);
		double numMutants = dnaAnalyzedRepository.countByKind(Kind.MUTANT);
		stadistics.setCountHumanDna(numHumans);
		stadistics.setCountMutantDna(numMutants);
		stadistics.setRatio(numHumans != 0 ? numMutants/numHumans : 0);
		return stadistics;
	}

}
