package com.rhplateforme.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rhplateforme.Repos.UserRepository;
import com.rhplateforme.entities.Role;
import com.rhplateforme.entities.Employee;


public interface UserService  {
	Employee saveUser(Employee user);

	void updatePassword(String email, String newPassword) ;
	Role addRole(Role role);

	Employee addRoleToUser(String username, String rolename);

	List<Employee> findAllUsers();
	
	Employee findUserByEmail(String email);
	
	boolean hasUserWithEmail(String email);
	
	Employee updateuser(Employee e);
	Employee updatepack(Employee employee);

	  public List<Employee> rechercherEmployeursGold();
	   public List<Employee> rechercherEmployeursSuperieur();
	   public List<Employee> rechercherEmployeursRestaurer();
	   public List<Employee> rechercherEmployeursServir();
	   public List<Employee> rechercherEmployeursGold_restaurant();
	   public List<Employee> rechercherEmployeursSuperieur_restaurant();
	   public List<Employee> rechercherEmployeursRestaurer_restaurant() ;
	   List<Employee> rechercherEmployeursServir_restaurant() ;
	    public ResponseEntity<Object> getnbspecaliter();
	    public List<Employee> getemployes() ;
	    public Employee getuser(Long id);
	    
}
