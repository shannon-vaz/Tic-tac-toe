package com.main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("TicTacToe")
class TestTicTacToe {
	TicTacToe ticTacToe;
	
	@BeforeEach
	void init() {
		ticTacToe = new TicTacToe();
	}
	
	@Nested
	@DisplayName("makeEntry")
	class TestMakeEntry {
		
		@Test
		@DisplayName("should throw if slot number is out of range")
		void test1() {
			assertAll(
					() -> assertThrows(IllegalArgumentException.class, () -> ticTacToe.makeEntry(0)),
					() -> assertThrows(IllegalArgumentException.class, () -> ticTacToe.makeEntry(10))
			);
			
		}
		
		@Test
		@DisplayName("should throw if entry is made in already occupied slot")
		void test2() {
			ticTacToe.makeEntry(1);
			assertThrows(IllegalArgumentException.class, () -> ticTacToe.makeEntry(1));
		}
	}
}
