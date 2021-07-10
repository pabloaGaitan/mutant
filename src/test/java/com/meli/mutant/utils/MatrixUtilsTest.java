package com.meli.mutant.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MatrixUtilsTest {
	
	private final int numConcurr = 4;
	
	private final List<String> matriForConutSeq = List.of("TTTTTTTTA","ATAAAAAATT","GGGGTGGGG","ACCCGGCGCA","AAACAAGATC","GGGGGAAGTC",
			"ATTGATTTTA","ACGTGTAGTAG","GGGTTTCCCT","AAACCCTTAA");
	
	private final List<String> matrix7x7 = List.of("ATTGTTT","TAAAGAA","GGGTGGG","CCCGGCG","AAACAGA","GGGCGAG","ATTGATT");
	
	private final List<String> matrix6x6 = List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
	
	private final List<String> matrix4x4 = List.of("TGAC","TGAC","TGAC","ACTG");
	
	private final String oneLine4x4Matrix = "TGACTGACTGACACTG";
	
	private final List<String> matrix4x4Columns = List.of("TTTA","GGGC","AAAT","CCCG");
	
	private final List<String> matrix4x4LeftToRightDiagonals = List.of("TGAG");
	
	private final List<String> matrix7x7LeftToRightDiagonals = List.of("AAGGAAT","TGCCGT","GCACA","CAGG","TATGGG","TAGCA","GGGG");
	
	private final List<String> matrix6x6LeftToRightDiagonals = List.of("AAAATG","CTACT","TGCC","TGTGA","GTGG");
	
	private final List<String> matrix4x4RightToLeftDiagonals = List.of("CAGA");
	
	private final List<String> matrix7x7RightToLeftDiagonals = List.of("TAGGAGA","TGTCAG","TAGCA","GAGC","AGGCGT","GCACT","GGGG");
	
	private final List<String> matrix6x6RightToLeftDiagonals = List.of("AGTACT","GTAGC","CGTA","CGACC","TGCA");
	
	@Test
	public void getColumnsTest() {
		List<String> columns = MatrixUtils.getColumns(matrix4x4);
		assertEquals(matrix4x4Columns.size(), columns.size());
		for (int i = 0; i < columns.size() ; i++) {
			assertEquals(matrix4x4Columns.get(i), columns.get(i));
		}
	}
	
	@Test
	public void getLeftToRightDiagonals4x4Test() {
		List<String> diagonals = MatrixUtils.getLeftToRightDiagonals(matrix4x4,numConcurr);
		assertEquals(matrix4x4LeftToRightDiagonals.size(), diagonals.size());
		for (int i = 0; i < diagonals.size() ; i++) {
			assertEquals(matrix4x4LeftToRightDiagonals.get(i), diagonals.get(i));
		}
	}
	
	@Test
	public void getLeftToRightDiagonals7x7Test() {
		List<String> diagonals = MatrixUtils.getLeftToRightDiagonals(matrix7x7,numConcurr);
		assertEquals(matrix7x7LeftToRightDiagonals.size(), diagonals.size());
		for (int i = 0; i < diagonals.size() ; i++) {
			assertEquals(matrix7x7LeftToRightDiagonals.get(i), diagonals.get(i));
		}
	}
	
	@Test
	public void getLeftToRightDiagonals6x6Test() {
		List<String> diagonals = MatrixUtils.getLeftToRightDiagonals(matrix6x6,numConcurr);
		assertEquals(matrix6x6LeftToRightDiagonals.size(), diagonals.size());
		for (int i = 0; i < diagonals.size() ; i++) {
			assertEquals(matrix6x6LeftToRightDiagonals.get(i), diagonals.get(i));
		}
	}
	
	@Test
	public void getRightToLeftDiagonals4x4Test() {
		List<String> diagonals = MatrixUtils.getRigthToLeftDiagonals(matrix4x4,numConcurr);
		assertEquals(matrix4x4RightToLeftDiagonals.size(), diagonals.size());
		for (int i = 0; i < diagonals.size() ; i++) {
			assertEquals(matrix4x4RightToLeftDiagonals.get(i), diagonals.get(i));
		}
	}
	
	@Test
	public void getRightToLeftDiagonals7x7Test() {
		List<String> diagonals = MatrixUtils.getRigthToLeftDiagonals(matrix7x7,numConcurr);
		assertEquals(matrix7x7RightToLeftDiagonals.size(), diagonals.size());
		for (int i = 0; i < diagonals.size() ; i++) {
			assertEquals(matrix7x7RightToLeftDiagonals.get(i), diagonals.get(i));
		}
	}
	
	@Test
	public void getRightToLeftDiagonals6x6Test() {
		List<String> diagonals = MatrixUtils.getRigthToLeftDiagonals(matrix6x6,numConcurr);
		assertEquals(matrix6x6RightToLeftDiagonals.size(), diagonals.size());
		for (int i = 0; i < diagonals.size() ; i++) {
			assertEquals(matrix6x6RightToLeftDiagonals.get(i), diagonals.get(i));
		}
	}
	
	@Test
	public void oneLineMatrixTest() {
		String oneLineMatrix = MatrixUtils.oneLineMatrix(matrix4x4);
		assertEquals(oneLine4x4Matrix, oneLineMatrix);
	}
	
	@Test
	public void countSeqTest() {
		int numSeq = MatrixUtils.countSeq(matriForConutSeq, numConcurr);
		assertEquals(7, numSeq);
	}
	
}
