package com.meli.mutant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meli.mutant.entity.DnaAnalyzed;
import com.meli.mutant.entity.Kind;

public interface DnaAnalyzedRepository extends JpaRepository<DnaAnalyzed, Long>{
	
	double countByKind(Kind kind);
	
	DnaAnalyzed findByDna(String dna);
	
}
