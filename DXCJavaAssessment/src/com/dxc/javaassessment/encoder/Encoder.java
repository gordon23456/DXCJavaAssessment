package com.dxc.javaassessment.encoder;

import java.util.Random;

public class Encoder implements Encode, Decode {

	private static final String REFERENCE_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";

	public String encode(String plainText) {

		StringBuilder encodedText = new StringBuilder();
		Random random = new Random();

		int refTableLen = REFERENCE_TABLE.length();
		int offsetIndex = random.nextInt(refTableLen);
		char offsetCharacter = REFERENCE_TABLE.charAt(offsetIndex);

		encodedText.append(offsetCharacter);

		for (char c : plainText.toCharArray()) {

			encodedText.append(encodeOne(c, offsetIndex, refTableLen));

		}

		return encodedText.toString();

	}

	private char encodeOne(char inputCharacter, int offsetIndex, int refTableLen) {

		int charIndex = REFERENCE_TABLE.indexOf(inputCharacter);
		if (charIndex == -1) {

			// if character does not exist in the table, map the character to itself
			return inputCharacter;

		} else {

			//to encode a character, its new index will be the current index minus the offset (since clockwise rotation for offset)
			//When the subtraction is negative, the current index is behind the offset, thus to wrap around the table, add the length of table
			int newIndex = 0;
			if (charIndex - offsetIndex < 0) {

				newIndex = charIndex + refTableLen - offsetIndex;

			} else {

				newIndex = charIndex - offsetIndex;

			}
			return REFERENCE_TABLE.charAt(newIndex);

		}

	}

	public String decode(String encodedText) {

		StringBuilder plainText = new StringBuilder();

		int refTableLen = REFERENCE_TABLE.length();
		char offsetChar = encodedText.charAt(0);
		int offsetIndex = REFERENCE_TABLE.indexOf(offsetChar);

		for (int i = 1; i < encodedText.length(); i++) {

			char c = encodedText.charAt(i);
			plainText.append(decodeOne(c, offsetIndex, refTableLen));

		}

		return plainText.toString();

	}

	private char decodeOne(char inputCharacter, int offsetIndex, int refTableLen) {

		int charIndex = REFERENCE_TABLE.indexOf(inputCharacter);

		if (charIndex == -1) {

			// if character does not exist in the table, map the character to itself
			return inputCharacter;

		} else {

			// To decode a character, its original index is the sum of the current index plus offset index
			//When the subtraction is over the table length, the original was behind the offset, thus as wrap around the table, we subtract it.
			int originalIndex = 0;
			if (charIndex + offsetIndex < refTableLen) {

				originalIndex = charIndex + offsetIndex;

			} else {

				originalIndex = charIndex + offsetIndex - refTableLen;

			}

			return REFERENCE_TABLE.charAt(originalIndex);

		}

	}

}
