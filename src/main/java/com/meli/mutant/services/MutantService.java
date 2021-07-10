package com.meli.mutant.services;

import java.util.List;

import com.meli.mutant.exception.NoSquareMatrixException;
import com.meli.mutant.exception.NoValidCharacterException;
import com.meli.mutant.exception.NoValidMatrixSizeException;

public interface MutantService {
	
	boolean isMutant(List<String> dna) throws NoSquareMatrixException, NoValidCharacterException, NoValidMatrixSizeException;

}
