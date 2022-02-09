package com.example.demo.serviceiml;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.AdminModel;
import com.example.demo.entity.EmployeeModel;
import com.example.demo.repo.AdminRepo;
import com.example.demo.repo.Employeerepo;
import com.example.demo.response.MessageResponse;
import com.example.demo.security.JwtUtils;
import com.example.demo.security.iml.RestTemplateService;
import com.example.demo.service.RegisterService;

@Service
public class RegisterServiceiml implements RegisterService {

	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;

	@Value("${employee.url}")
	private String employBaseUrl;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AdminRepo adminrepo;

	@Autowired
	Employeerepo employeerepo;

	@Autowired
	Environment env;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	RestTemplateService restTemplateService;

	@Override
	public ResponseEntity<?> register(RegisterDto registerdto) {
		if (registerdto.getRole().equals("ROLE_ADMIN")) {
			AdminModel adminmodel = AdminModel.builder().name(registerdto.getName()).email(registerdto.getEmail())
					.password(encoder.encode(registerdto.getPassword())).role(registerdto.getRole()).build();
			adminrepo.save(adminmodel);
			return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), "successfully", adminmodel));
		}
		if (registerdto.getRole().equals("ROLE_EMPLOYEE")) {
			EmployeeModel employeemodel = EmployeeModel.builder().name(registerdto.getName())
					.email(registerdto.getEmail()).password(encoder.encode(registerdto.getPassword()))
					.role(registerdto.getRole()).build();
			employeerepo.save(employeemodel);
			return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), "successfully", employeemodel));
			// return ResponseEntity.ok(employeemodel);

		}
		return ResponseEntity.ok(registerdto.getEmail());
	}

	@Override
	public ResponseEntity<?> login(RegisterDto registerdto) {
		Optional<AdminModel> model = adminrepo.findByemail(registerdto.getEmail());
		Optional<EmployeeModel> employee = employeerepo.findByemail(registerdto.getEmail());

		if (model.isPresent()) {

			// String encryptedPassword = encoder.encode(registerdto.getPassword());
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(registerdto.getName(), registerdto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			model.get().setJwt(jwt);
			model.get().setActiveStatus("ACTIVE");

			adminrepo.save(model.get());
			return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), "successfully", model));
		}
		if (employee.isPresent()) {

			String encryptedPassword = encoder.encode(registerdto.getPassword());
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(registerdto.getName(), registerdto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			employee.get().setJwt(jwt);
			employee.get().setActiveStatus("ACTIVE");

			employeerepo.save(employee.get());
			return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), "successfully", employee));
		}

		return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), env.getProperty("loginerror")));
	}

	@Override
	public ResponseEntity<?> logout(RegisterDto registerdto) {
		Optional<AdminModel> model = adminrepo.findByemail(registerdto.getEmail());
		Optional<EmployeeModel> emp = employeerepo.findByemail(registerdto.getEmail());
		try {
			if (model.isPresent()) {

				String encryptedPassword = encoder.encode(registerdto.getPassword());
				Authentication authentication1 = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(registerdto.getName(), registerdto.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication1);

				model.get().setActiveStatus("NO_ACTIVE");
				model.get().setJwt("NO_TOKEN");
				adminrepo.save(model.get());
				return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), "successfully", model));
			}

			if (emp.isPresent()) {
				String encryptedPassword = encoder.encode(registerdto.getPassword());
				Authentication authentication1 = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(registerdto.getName(), registerdto.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication1);

				emp.get().setActiveStatus("NO_ACTIVE");
				emp.get().setJwt("NO_TOKEN");
				employeerepo.save(emp.get());
				return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), "successfully", emp));

			}
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), env.getProperty("loginerror")));
		}
		return null;
	}

	@Override
	public String forgetpassword(RegisterDto registerdto) {
		Optional<AdminModel> adminmodel = adminrepo.findByemail(registerdto.getEmail());
		Optional<EmployeeModel> employeemodell = employeerepo.findByemail(registerdto.getEmail());

		if (adminmodel.isPresent()) {
			AdminModel user = adminmodel.get();
			user.setTokens(generateToken());
			user.setTokenCreationDate(LocalDateTime.now());

			user = adminrepo.save(user);
			return user.getTokens();
		}
		if (employeemodell.isPresent()) {
			EmployeeModel empmodel = employeemodell.get();
			empmodel.setTokens(generateToken());
			empmodel = employeerepo.save(empmodel);
			return empmodel.getTokens();

		}

		else {
			return "Invalid email";
		}

	}

	private String generateToken() {
		StringBuilder token = new StringBuilder();

		return token.append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString()).toString();
	}

	private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);

		return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
	}

	@Override
	public ResponseEntity<?> resetpasswrod(RegisterDto registerdto) {
		Optional<AdminModel> userOptional = adminrepo.findBytokens(registerdto.getTokens());
		Optional<EmployeeModel> empmodel = employeerepo.findBytokens(registerdto.getTokens());

		LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

		if (isTokenExpired(tokenCreationDate)) {
			return ResponseEntity.ok("Token Expired");
		}

		if (userOptional.isPresent()) {
			AdminModel user = userOptional.get();
			user.setPassword(encoder.encode(registerdto.getPassword()));
			user.setTokens(null);
			user.setTokenCreationDate(null);
			adminrepo.save(userOptional.get());
		}

		if (empmodel.isPresent()) {
			EmployeeModel emp = empmodel.get();
			emp.setPassword(encoder.encode(registerdto.getPassword()));
			emp.setTokens(null);
			employeerepo.save(emp);
		}
		 else {
			return ResponseEntity.ok("invalid token");
		}
		return ResponseEntity.ok("password update sucessfully");
	}

	@Override
	public ResponseEntity<?> getbyidrest(String id, HttpServletRequest request) {
		MessageResponse response = getstudent(id, request);
		return ResponseEntity.ok(response);
	}

	private MessageResponse getstudent(String id, HttpServletRequest request) {
		MessageResponse objresponse = null;
		RequestEntity<?> response13 = null;
		try {
			String url3 = employBaseUrl + "/getbyid/" + id;
			response13 = RequestEntity.get(restTemplateService.uri(url3)).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		objresponse = restTemplate.exchange(response13, MessageResponse.class).getBody();
		return objresponse;

	}

	@Override
	public String save(RegisterDto registerdto) {
		if (registerdto.getRole().equals("ROLE_ADMIN")) {
			AdminModel adminmodel = AdminModel.builder().name(registerdto.getName()).email(registerdto.getEmail())
					.password(encoder.encode(registerdto.getPassword())).role(registerdto.getRole()).build();
			adminrepo.save(adminmodel);
			return registerdto.getEmail();
			// return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(),
			// "successfully", adminmodel));
		}
		if (registerdto.getRole().equals("ROLE_EMPLOYEE")) {
			EmployeeModel employeemodel = EmployeeModel.builder().name(registerdto.getName())
					.email(registerdto.getEmail()).password(encoder.encode(registerdto.getPassword()))
					.role(registerdto.getRole()).build();
			employeerepo.save(employeemodel);
			return registerdto.getEmail();

			// return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(),
			// "successfully", employeemodel));
			// return ResponseEntity.ok(employeemodel);

		}
		// return ResponseEntity.ok(registerdto.getEmail());
		return "thanks for Register";
	}
}

//userOptional.get().setTokens(null);
//userOptional.get().setPassword(encoder.encode(registerdto.getPassword()));
