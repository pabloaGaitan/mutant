package com.meli.mutant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.mutant.dto.DnaDto;
import com.meli.mutant.exception.NoSquareMatrixException;
import com.meli.mutant.exception.NoValidCharacterException;
import com.meli.mutant.exception.NoValidMatrixSizeException;
import com.meli.mutant.services.MutantService;

@RestController
@RequestMapping
public class MutantController {
	
	@Autowired
	private MutantService mutantService;
	
	@PostMapping(value = "/mutant")
	public ResponseEntity<Void> isMutant(@RequestBody DnaDto dna) throws NoSquareMatrixException, NoValidCharacterException, NoValidMatrixSizeException {
		return mutantService.isMutant(dna.getDna()) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

}
