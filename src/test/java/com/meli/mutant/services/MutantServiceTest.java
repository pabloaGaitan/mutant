package com.meli.mutant.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.meli.mutant.config.ConfigProperties;
import com.meli.mutant.entity.DnaAnalyzed;
import com.meli.mutant.entity.Kind;
import com.meli.mutant.exception.NoSquareMatrixException;
import com.meli.mutant.exception.NoValidCharacterException;
import com.meli.mutant.exception.NoValidMatrixSizeException;
import com.meli.mutant.repository.DnaAnalyzedRepository;
import com.meli.mutant.utils.MatrixUtils;

@SpringBootTest
public class MutantServiceTest {
	
	@InjectMocks
	private MutantServiceImpl mutantService;
	
	@Mock
	private DnaAnalyzedRepository dnaAnalizedRepository;
	
	@Mock
	private ConfigProperties properties;
	
	private final List<String> dna = List.of("AGTC","CTAA", "CAGA","CAGA");
	
	private final List<String> dnaColumns = List.of("ACCC","GTAA","TAGG","CAAA");
	
	private final List<String> dnaLeftToRightDiagonal = List.of("ATGA");
	
	private final List<String> dnaRightToLeftDiagonal = List.of("AGTA");
	
	@BeforeEach
	public void setup() {
		when(properties.getPermitedLetters()).thenReturn("AGTC");
		when(properties.getNumSeqToBeMutant()).thenReturn(1);
		when(properties.getMutantNumConcurr()).thenReturn(4);
	}
	
	@Test
	public void isMutantNullMatrix() {
		assertThrows(NoValidMatrixSizeException.class, ()->{mutantService.isMutant(null);});
	}
	
	@Test
	public void isMutantNoSuficientSize() {
		when(properties.getMutantNumConcurr()).thenReturn(4);
		List<String> dna3x3 = List.of("AAA", "AAA", "AAA");
		assertThrows(NoValidMatrixSizeException.class, ()->{mutantService.isMutant(dna3x3);});
	}
	
	@Test
	public void isMutantNoSquareMatrix() {
		List<String> dnaNoSquare = List.of("AAA", "AAA", "AAAG", "AAAG");
		assertThrows(NoSquareMatrixException.class, ()->{mutantService.isMutant(dnaNoSquare);});
	}
	
	@Test
	public void isMutantNoValidCharacters() {
		List<String> dnaInvalidCharacters = List.of("AAAG", "AZAG", "AAAG", "AAAG");
		assertThrows(NoValidCharacterException.class, ()->{mutantService.isMutant(dnaInvalidCharacters);});
	}
	
	@Test
	public void isMutantAnalyzedCheck() throws NoSquareMatrixException, NoValidCharacterException, NoValidMatrixSizeException {
		List<String> dna = List.of("AGTC", "CTAA", "CAGA","CAGA");
		DnaAnalyzed dnaAnalyzed = new DnaAnalyzed();
		dnaAnalyzed.setKind(Kind.MUTANT);
		
		when(dnaAnalizedRepository.findByDna("AGTCCTAACAGACAGA")).thenReturn(dnaAnalyzed);
		
		assertTrue(mutantService.isMutant(dna));
	}
	
	@Test
	public void isMutantNoAnalyzedCheckRows() throws NoSquareMatrixException, NoValidCharacterException, NoValidMatrixSizeException {
		MockedStatic<MatrixUtils> matrixUtilsMock = Mockito.mockStatic(MatrixUtils.class);
		
		matrixUtilsMock.when(() -> MatrixUtils.countSeq(dna, properties.getMutantNumConcurr())).thenReturn(2);
		when(dnaAnalizedRepository.findByDna("AGTCCTAACAGACAGA")).thenReturn(null);
		
		boolean isMutant = mutantService.isMutant(dna);
		matrixUtilsMock.close();
		
		assertTrue(isMutant);
	}
	
	@Test
	public void isMutantNoAnalyzedCheckColumns() throws NoSquareMatrixException, NoValidCharacterException, NoValidMatrixSizeException {
		MockedStatic<MatrixUtils> matrixUtilsMock = Mockito.mockStatic(MatrixUtils.class);
		
		matrixUtilsMock.when(() -> MatrixUtils.getColumns(dna)).thenReturn(dnaColumns);
		matrixUtilsMock.when(() -> MatrixUtils.countSeq(dna, properties.getMutantNumConcurr())).thenReturn(1);
		matrixUtilsMock.when(() -> MatrixUtils.countSeq(dnaColumns, properties.getMutantNumConcurr())).thenReturn(1);
		when(dnaAnalizedRepository.findByDna("AGTCCTAACAGACAGA")).thenReturn(null);
		
		boolean isMutant = mutantService.isMutant(dna);
		matrixUtilsMock.close();
		
		assertTrue(isMutant);
	}
	
	@Test
	public void isMutantNoAnalyzedCheckColumnsLeftToRightDiagonal() throws NoSquareMatrixException, NoValidCharacterException, NoValidMatrixSizeException {
		MockedStatic<MatrixUtils> matrixUtilsMock = Mockito.mockStatic(MatrixUtils.class);
		
		matrixUtilsMock.when(() -> MatrixUtils.getColumns(dna)).thenReturn(dnaColumns);
		matrixUtilsMock.when(() -> MatrixUtils.getLeftToRightDiagonals(dna,properties.getMutantNumConcurr())).thenReturn(dnaLeftToRightDiagonal);
		matrixUtilsMock.when(() -> MatrixUtils.countSeq(dna, properties.getMutantNumConcurr())).thenReturn(0);
		matrixUtilsMock.when(() -> MatrixUtils.countSeq(dnaColumns, properties.getMutantNumConcurr())).thenReturn(1);
		matrixUtilsMock.when(() -> MatrixUtils.countSeq(dnaLeftToRightDiagonal, properties.getMutantNumConcurr())).thenReturn(1);
		when(dnaAnalizedRepository.findByDna("AGTCCTAACAGACAGA")).thenReturn(null);
		
		boolean isMutant = mutantService.isMutant(dna);
		matrixUtilsMock.close();
		
		assertTrue(isMutant);
	}
	
	@Test
	public void isMutantNoAnalyzedCheckColumnsRightToLeftDiagonal() throws NoSquareMatrixException, NoValidCharacterException, NoValidMatrixSizeException {
		MockedStatic<MatrixUtils> matrixUtilsMock = Mockito.mockStatic(MatrixUtils.class);
		
		matrixUtilsMock.when(() -> MatrixUtils.getColumns(dna)).thenReturn(dnaColumns);
		matrixUtilsMock.when(() -> MatrixUtils.getLeftToRightDiagonals(dna,properties.getMutantNumConcurr())).thenReturn(dnaLeftToRightDiagonal);
		matrixUtilsMock.when(() -> MatrixUtils.getRigthToLeftDiagonals(dna,properties.getMutantNumConcurr())).thenReturn(dnaRightToLeftDiagonal);
		matrixUtilsMock.when(() -> MatrixUtils.countSeq(dna, properties.getMutantNumConcurr())).thenReturn(0);
		matrixUtilsMock.when(() -> MatrixUtils.countSeq(dnaColumns, properties.getMutantNumConcurr())).thenReturn(0);
		matrixUtilsMock.when(() -> MatrixUtils.countSeq(dnaLeftToRightDiagonal, properties.getMutantNumConcurr())).thenReturn(1);
		matrixUtilsMock.when(() -> MatrixUtils.countSeq(dnaRightToLeftDiagonal, properties.getMutantNumConcurr())).thenReturn(1);
		when(dnaAnalizedRepository.findByDna("AGTCCTAACAGACAGA")).thenReturn(null);
		
		boolean isMutant = mutantService.isMutant(dna);
		matrixUtilsMock.close();
		
		assertTrue(isMutant);
	}
	
	@Test
	public void isNotMutantNoAnalyzedCheck() throws NoSquareMatrixException, NoValidCharacterException, NoValidMatrixSizeException {
		MockedStatic<MatrixUtils> matrixUtilsMock = Mockito.mockStatic(MatrixUtils.class);
		
		matrixUtilsMock.when(() -> MatrixUtils.getColumns(dna)).thenReturn(dnaColumns);
		matrixUtilsMock.when(() -> MatrixUtils.getLeftToRightDiagonals(dna,properties.getMutantNumConcurr())).thenReturn(dnaLeftToRightDiagonal);
		matrixUtilsMock.when(() -> MatrixUtils.getRigthToLeftDiagonals(dna,properties.getMutantNumConcurr())).thenReturn(dnaRightToLeftDiagonal);
		matrixUtilsMock.when(() -> MatrixUtils.countSeq(dna, properties.getMutantNumConcurr())).thenReturn(0);
		matrixUtilsMock.when(() -> MatrixUtils.countSeq(dnaColumns, properties.getMutantNumConcurr())).thenReturn(0);
		matrixUtilsMock.when(() -> MatrixUtils.countSeq(dnaLeftToRightDiagonal, properties.getMutantNumConcurr())).thenReturn(0);
		matrixUtilsMock.when(() -> MatrixUtils.countSeq(dnaRightToLeftDiagonal, properties.getMutantNumConcurr())).thenReturn(1);
		when(dnaAnalizedRepository.findByDna("AGTCCTAACAGACAGA")).thenReturn(null);
		
		boolean isMutant = mutantService.isMutant(dna);
		matrixUtilsMock.close();
		
		assertFalse(isMutant);
	}
}
