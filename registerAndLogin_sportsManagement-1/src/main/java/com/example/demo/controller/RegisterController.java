package com.example.demo.controller;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmailResponceDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.security.iml.EmailServiceImp;
import com.example.demo.service.RegisterService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/entrypoint")
public class RegisterController {

	@Autowired
	EmailServiceImp reference1;

	@Autowired
	Environment env;

	@Autowired
	RegisterService registerservice;

	@PostMapping(value = "/register", produces = "application/json")
	public String register(@RequestBody RegisterDto registerdto) {
		EmailResponceDto responseDto = new EmailResponceDto();

		try {
			System.out.println("insert controller");
			String output = registerservice.save(registerdto);
			System.out.println("output..." + output);
			if (output == null) {
				responseDto.setStatus(env.getProperty("failed"));
				responseDto.setMessage(env.getProperty("failed"));
				reference1.sendEmailDEV(output, "this is from admin", output);
				return new String(new Gson().toJson(responseDto));

			} else {

				responseDto.setStatus(env.getProperty("success"));
				responseDto.setMessage(env.getProperty("success"));
				String verificationLink = "hi Your Account has been Successfully registered click the link to login<br>"
						+ "<a href='http://localhost:8081/login'>login</a>";
				reference1.sendEmailDEV(output, verificationLink, output);

				return new String(new Gson().toJson(responseDto));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "thanks for Register";
	}

	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<?> login(@RequestBody RegisterDto registerdto) {
		ResponseEntity<?> output = registerservice.login(registerdto);
		return ResponseEntity.ok(output);
	}

	@PostMapping(value = "/test/logout",produces="application/json")
	public ResponseEntity<?> logout(@RequestBody RegisterDto registerDto) {
		ResponseEntity<?> output = registerservice.logout(registerDto);
		return ResponseEntity.ok(output);
	}

	@PostMapping(value = "/forgetpassword", produces = "application/json")
	public String forgetpassword(@RequestBody RegisterDto registerDto) {
		String output = registerservice.forgetpassword(registerDto);
		if (!output.equals("Invalid")) {
			output = "http://localhost:8081/entrypoint/reset-password?token=" + output;
		}
		return output;
	}

	@PutMapping(value = "/resetpasswrod", produces = "application/json")
	public ResponseEntity<?> resetpassword(@RequestBody RegisterDto registerdto) {
		ResponseEntity<?> output = registerservice.resetpasswrod(registerdto);
		return ResponseEntity.ok(output);
	}

}
