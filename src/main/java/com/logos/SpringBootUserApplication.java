package com.logos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import com.logos.entity.User;
import com.logos.repository.UserRepository;

@SpringBootApplication
public class SpringBootUserApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootUserApplication.class);
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootUserApplication.class, args);
		fillDatabase(ctx);
	}

	private static void fillDatabase(ConfigurableApplicationContext ctx) {
		int userNum = 2000;
		UserRepository userRepository = ctx.getBean(UserRepository.class);
		Long userCount = userRepository.count();
		System.out.println("Users in DB: " + userCount);

		BufferedReader firstNameStream = null;
		BufferedReader lastNameStream = null;
		try {
			firstNameStream = new BufferedReader(new FileReader("first_name.txt"));
			lastNameStream = new BufferedReader(new FileReader("last_name.txt"));
			int firstNameCount = 0;
			int lastNameCount = 0;
			List<String> firstNameList = new ArrayList<>();
			List<String> lastNameList = new ArrayList<>();
			String firstName;
			String lastName;
			
			while ((firstName = firstNameStream.readLine()) != null) {
				firstNameList.add(firstName);
				firstNameCount++;
			}
			while ((lastName = lastNameStream.readLine()) != null) {
				lastNameList.add(lastName);
				lastNameCount++;
			}
			
			if (userCount < 200) {
				 for(int i = 0; i < userNum; i++){
					User user = new User();
					user.setFirstName(firstNameList.get(new Random().nextInt(firstNameCount)));
					user.setLastName(lastNameList.get(new Random().nextInt(lastNameCount)));
					user.setEmail(
							user.getFirstName().toLowerCase() + "." + user.getLastName().toLowerCase() + "@gmail.com");
					user.setLogin(user.getLastName().charAt(0) + user.getFirstName() + new Random().nextInt(999));
					user.setPassword(RandomStringUtils.randomAlphanumeric(10));
					userRepository.save(user);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
