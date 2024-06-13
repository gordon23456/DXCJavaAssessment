package com.dxc.javaassessment;
import java.util.Scanner;

import com.dxc.javaassessment.encoder.Encoder;

public class Main {

	public static void main(String[] args) {

		Encoder encoder = new Encoder();

		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {

			System.out.println("Please choose an option:");
			System.out.println("1. Encode a text");
			System.out.println("2. Decode a text");
			System.out.println("3. Exit program");
			System.out.print("Enter your choice: ");
			try {
				
				int choice = scanner.nextInt();
				scanner.nextLine(); // consume newline left-over
				switch (choice) {
				
				case 1:
					System.out.print("Enter text to encode: ");
					String textToEncode = scanner.nextLine();
					System.out.println("Encoded text: " + encoder.encode(textToEncode));
					break;
				case 2:
					System.out.print("Enter text to decode: ");
					String textToDecode = scanner.nextLine();
					System.out.println("Decoded text: " + encoder.decode(textToDecode));
					break;
				case 3:
					running = false;
					System.out.println("Exiting program.");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					
				}
			} catch (Exception e){
				System.out.println("Error when parsing choices. Please try again.");
			}


		}

		scanner.close();

	}

}
