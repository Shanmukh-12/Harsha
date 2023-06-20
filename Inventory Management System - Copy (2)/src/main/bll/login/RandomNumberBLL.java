package main.bll.login;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomNumberBLL {
	public int getNumber() {
		// Create a Random object
		Random random = new Random();

		// Generate a random four-digit number
		int randomNum = random.nextInt(9000) + 1000;

		// Print the random number
		System.out.println("Random Number: " + randomNum);
		return randomNum;

	}
}
