package org.kata;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.kata.Bowling;

/*
 * 
 * http://es.wikipedia.org/wiki/Bowling
 * http://codingdojo.org/cgi-bin/wiki.pl?KataBowlingByAndreasLarsson
 * http://codingdojo.org/cgi-bin/wiki.pl?KataBowling
 * http://www.topendsports.com/sport/tenpin/scoring.htm
 * 
 */
public class BowlingTest {
	private Bowling bowling;
	
	@Before
	public void setUp() {
		bowling = new Bowling();
	}
	
	// Gutter game = all zeroes, (score = 0)
	@Test
	public void gutterGame() {
		assertEquals("Score de diferente valor", 0, bowling.score("0000000000")); 
	}
	
	// One pin down in each roll, (score = 20)
	@Test
	public void onePinDownInEachRollGame() {
		assertEquals("Score de diferente valor", 20, bowling.score("11111111111111111111")); 
	}
	
	// Spare in first roll, one pin down in each other roll, (score = 10 + 1 + 18 = 29)
	@Test
	public void spareInFirstRollOnePinDownInEachOtherRollGame() {
		assertEquals("Score de diferente valor", 29, bowling.score("5/111111111111111111")); 
	}
	
	// Spare in last roll, one pin down in each other roll, (score = 18 + 10 + 1 = 29)
	@Test
	public void spareInLastRollOnePinDownInEachOtherRollGame() {
		assertEquals("Score de diferente valor", 29, bowling.score("1111111111111111117/")); 
	}
	
	// Strike in first roll, one pin down in each other roll, (score = 10 + 1 + 1 + 18 = 30)
	@Test
	public void strikeInFirstRollOnePinDownInEachOtherRollGame() {
		assertEquals("Score de diferente valor", 30, bowling.score("X111111111111111111")); 
	}

	//  Strike in last roll, one pin down in each other roll, (score = 18 + 10 + 1 + 1 = 30)
	@Test
	public void strikeInLastRollOnePinDownInEachOtherRollGame() {
		assertEquals("Score de diferente valor", 30, bowling.score("111111111111111111X")); 
	}
	
	// (20 rolls: 10 pairs of 9 and miss) = 9 + 9 + 9 + 9 + 9 + 9 + 9 + 9 + 9 + 9 = 90
	@Test
	public void tenPairOfNineAndMissGame() {
		assertEquals("Score de diferente valor", 90, bowling.score("9-9-9-9-9-9-9-9-9-9-")); 
	}
	
	// (21 rolls: 10 pairs of 5 and spare, with a final 5) = 10+5 + 10+5 + 10+5 + 10+5 + 10+5 + 10+5 + 10+5 + 10+5 + 10+5 + 10+5 = 150 
	@Test
	public void tenPairOfFiveAndSpareWithFinalFiveGame() {
		assertEquals("Score de diferente valor", 150, bowling.score("5/5/5/5/5/5/5/5/5/5/5")); 
	}
	
	// Golden game = all strikes (score = 300) 
	@Test
	public void goldenGame() {
		assertEquals("Score de diferente valor", 300, bowling.score("XXXXXXXXXXXX")); 
	}
	
	// Experiments (http://www.topendsports.com/sport/tenpin/scoring.htm)
	@Test
	public void partGame() {
		assertEquals("Score de diferente valor", 8, bowling.score("71"));
		
		assertEquals("Score de diferente valor", 6, bowling.score("6-"));
		
		assertEquals("Score de diferente valor", 26, bowling.score("X71"));
		
		assertEquals("Score de diferente valor", 27, bowling.score("6/81"));

		assertEquals("Score de diferente valor", 20, bowling.score("9/X"));
		
		assertEquals("Score de diferente valor", 18, bowling.score("4/8/"));
		
		assertEquals("Score de diferente valor", 16, bowling.score("X3-"));
		
		assertEquals("Score de diferente valor", 21, bowling.score("6/35"));
	}
	
	// http://www.bowling2u.com/trivia/game/scoring.asp
	@Test
	public void progresivedGame() {
		assertEquals("Score de diferente valor", 16, bowling.score("6271"));
		
		assertEquals("Score de diferente valor", 44, bowling.score("6271X9-"));
		
		assertEquals("Score de diferente valor", 53, bowling.score("6271X9-81"));
	}
	
}
