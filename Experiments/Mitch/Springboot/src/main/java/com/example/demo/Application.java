package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
		Integer[] integers = new Integer[] {0, 1, -9, -9, 5, 3, 10, 10, 10, 2, 4, 4, 4, 2, 5, 6, 8, 10};
		System.out.println(sortAndRemoveDuplicates(new ArrayList<>(List.of(integers))));
	}

	public static ArrayList<Integer> sortAndRemoveDuplicates(ArrayList<Integer> nums) {
		ArrayList<Integer> results = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		while (nums.size() > 0) {
			int temp = nums.get(0);
			for (int j = 0; j < nums.size(); j++) {
				if (nums.get(j) < temp) {
					temp = nums.get(j);
				}
				else if (nums.get(j) == temp && j != 0){
					nums.remove(j);
				}

			}
			results.add(temp);
			nums.remove((Integer) temp);
		}
		return results;
	}

}
