package com.meli.mutant.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.mutant.config.ConfigProperties;
import com.meli.mutant.entity.DnaAnalyzed;
import com.meli.mutant.entity.Kind;
import com.meli.mutant.exception.NoSquareMatrixException;
import com.meli.mutant.exception.NoValidCharacterException;
import com.meli.mutant.exception.NoValidMatrixSizeException;
import com.meli.mutant.repository.DnaAnalyzedRepository;
import com.meli.mutant.utils.MatrixUtils;

@Service
public class MutantServiceImpl implements MutantService {
	
	Logger logger = LoggerFactory.getLogger(MutantServiceImpl.class);
	
	@Autowired
	private ConfigProperties properties;
	
	@Autowired
	private DnaAnalyzedRepository dnaAnalizedRepository;

	@Override
	public boolean isMutant(List<String> dna) throws NoSquareMatrixException, NoValidCharacterException, NoValidMatrixSizeException {
		
		Integer numSequences = 0;
		validateDnaMatrix(dna);
		String oneLineMatrix = MatrixUtils.oneLineMatrix(dna);
		Boolean mutantAnalyzed = validateDnaAnalyzed(oneLineMatrix);
		
		if(mutantAnalyzed != null) {
			return mutantAnalyzed;
		}
		
		DnaAnalyzed dnaAnalyzed = new DnaAnalyzed();
		dnaAnalyzed.setDna(oneLineMatrix);
		
		numSequences += MatrixUtils.countSeq(dna, properties.getMutantNumConcurr());
		if(numSequences > properties.getNumSeqToBeMutant()) {
			dnaAnalyzed.setKind(Kind.MUTANT);
			dnaAnalizedRepository.save(dnaAnalyzed);
			return true;
		}
		
		List<String> dnaVert = MatrixUtils.getColumns(dna);
		numSequences += MatrixUtils.countSeq(dnaVert, properties.getMutantNumConcurr());
		if(numSequences > properties.getNumSeqToBeMutant()) {
			dnaAnalyzed.setKind(Kind.MUTANT);
			dnaAnalizedRepository.save(dnaAnalyzed);
			return true;
		}
		
		List<String> dnaDiagonalsLeftToRight = MatrixUtils.getLeftToRightDiagonals(dna, properties.getMutantNumConcurr());
		numSequences += MatrixUtils.countSeq(dnaDiagonalsLeftToRight, properties.getMutantNumConcurr());
		if(numSequences > properties.getNumSeqToBeMutant()) {
			dnaAnalyzed.setKind(Kind.MUTANT);
			dnaAnalizedRepository.save(dnaAnalyzed);
			return true;
		}
		
		List<String> dnaDiagonalRtl = MatrixUtils.getRigthToLeftDiagonals(dna, properties.getMutantNumConcurr());
		numSequences += MatrixUtils.countSeq(dnaDiagonalRtl, properties.getMutantNumConcurr());
		if(numSequences > properties.getNumSeqToBeMutant()) {
			dnaAnalyzed.setKind(Kind.MUTANT);
			dnaAnalizedRepository.save(dnaAnalyzed);
			return true;
		}
		
		dnaAnalyzed.setKind(Kind.HUMAN);
		dnaAnalizedRepository.save(dnaAnalyzed);
		return false;
	}
	
	private Boolean validateDnaAnalyzed(String oneLineMatrix) {
		DnaAnalyzed dnaAnalyzed = dnaAnalizedRepository.findByDna(oneLineMatrix);
		if(dnaAnalyzed != null) {
			return dnaAnalyzed.getKind().equals(Kind.MUTANT);
		}
		return null;
		
	}

	private void validateDnaMatrix(List<String> dna) throws NoSquareMatrixException, NoValidCharacterException, NoValidMatrixSizeException {
		if(dna == null) {
			logger.error("The matrix must not be null.");
			throw new NoValidMatrixSizeException();
		}
		String permitedLetters = properties.getPermitedLetters();
		if(dna.size() < properties.getMutantNumConcurr()) {
			logger.error("The row and columns are smaller than the concurrency characters num.");
			throw new NoValidMatrixSizeException();
		}
		for (String dnaLine : dna) {
			if(dna.size() != dnaLine.length()) {
				logger.error("Must be square matrix.");
				throw new NoSquareMatrixException();
			}
			char[] dnaLineCharArray = dnaLine.toCharArray();
			for (int i = 0 ;i < dnaLineCharArray.length; i++) {
				if(!permitedLetters.contains(dnaLineCharArray[i]+"")) {
					logger.error("Marix has not valid character, valid characters are : "+permitedLetters);
					throw new NoValidCharacterException();
				}
			}
		}
		
	}
	
	

}
