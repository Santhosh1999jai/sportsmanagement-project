package com.example.demo.security.iml;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AdminModel;
import com.example.demo.entity.EmployeeModel;
import com.example.demo.repo.AdminRepo;
import com.example.demo.repo.Employeerepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	//@Autowired
	//CustomerRepo Repository;
	@Autowired
	Employeerepo empRepo;
	
	@Autowired
	AdminRepo Repository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Optional<AdminModel> user = Repository.findByname(name);
				Optional<EmployeeModel> admin= empRepo.findByname(name);
			if(user.isPresent()) {
				return UserDetailsImpl.build(user.get());

			}
			else if(admin.isPresent()) {
				return UserDetailsImpl.build(admin.get());

			} else {
				throw new UsernameNotFoundException("User Not Found with username: " + name);
			}
		
	}
}
