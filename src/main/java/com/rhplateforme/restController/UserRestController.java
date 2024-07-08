package com.rhplateforme.restController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.rhplateforme.Repos.UserRepository;
import com.rhplateforme.entities.*;

import com.rhplateforme.mail.EmailSenderService;
import com.rhplateforme.security.MyUserDetailsService;
import com.rhplateforme.service.UserService;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://tie-job.com:8080")
public class UserRestController {
	@Autowired
	UserService userService;
	@Autowired
	EmailSenderService emailService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
    EmailSenderService emailserv;
	 
	    @Autowired
	    private UserRepository personneRepo;

	@RequestMapping(path = "all", method = RequestMethod.GET)
	public List<Employee> getAllUsers() {
		return userService.findAllUsers();
	}
	@GetMapping("/getuserbyemail/{email}")
	public Employee getuserbyemail(@PathVariable String email) {
		return userService.findUserByEmail(email);
	}
	@GetMapping("/exists/{email}")
    public boolean checkUserExists(@PathVariable String email) {
        return userService.hasUserWithEmail(email);
    }
	@PostMapping("/addUser")
	public Employee addUser(@RequestBody Employee user) {
		List<Role> roles = new ArrayList<>();
	    Role r = new Role();
	    r.setRole("USER");
	    roles.add(r);
	    user.setEnabled(true);
		emailserv.sendemailinscrit(user.getEmail(),"Nous sommes ravis de vous accueillir sur TIE JOB ! Votre compte a été créé avec succès"
				+ "\n"
        		+ "Cordialement,\n"
        		+ "L'équipe de tiejob,\n"
        		+ "CEO El Mehri Mortadha");
	    user.setRoles(roles);
		
        return userService.saveUser(user);
    }
	@PostMapping("/addUserentr")
	public Employee addUserentr(@RequestBody Employee user) {
		List<Role> roles = new ArrayList<>();
	    Role r = new Role();
	    r.setRole("ENTR");
	    roles.add(r);
	    user.setEnabled(true);
	    emailserv.sendemailinscritemployeur(user.getEmail());
	    user.setRoles(roles);
		
        return userService.saveUser(user);
    }

	@PostMapping("/updateuser")
	public Employee updateuser(@RequestBody Employee user) {	
        return userService.updateuser(user);
    }
	@PostMapping("/updatepack")
	public Employee updatepack(@RequestBody Employee user) {	
        return userService.updatepack(user);
    }
	
	@PostMapping("/verifieremail")
	public ResponseEntity<Map<String, String>> registerUser(@RequestParam("email") String email) {
	    
	        // Send verification email and get the generated token
	        String verificationToken = emailService.sendVerificationEmail(email);
	        System.out.println("Email sent successfully. Verification token: " + verificationToken);
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Verification token sent to email");
	        response.put("token", verificationToken);
	        return ResponseEntity.ok(response);
	    }
	@PostMapping("/forgetPassword")
	public ResponseEntity<Map<String, String>> forgetPassword(@RequestParam("email") String email) {
	    
	        // Send verification email and get the generated token
	        String resetToken = emailService.sendVerificationEmail(email);
	        System.out.println("Email sent successfully. Verification token: " + resetToken);
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "Reset token sent to email");
	        response.put("token", resetToken);

	        return ResponseEntity.ok(response);
	    }
	@PostMapping("/update")
	public ResponseEntity<String> updatePassword(@RequestBody Employee user)  {
		System.out.println(user);
		// Validate the email and newPassword
        if (StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getPassword())) {
            return ResponseEntity.badRequest().body("Email and newPassword are required.");
        }

        // Update the password
        userService.updatePassword(user.getEmail(),user.getPassword());
        return ResponseEntity.ok("Password updated successfully.");
    }


    @GetMapping("/rechercheemployeeGold")
 public List<Employee> rechercheremployeeGold(){
	 return userService.rechercherEmployeursGold();
	 }
    @GetMapping("/rechercheemployeesuperieur")
    public List<Employee> rechercheremployeePremium(){
   	 return userService.rechercherEmployeursSuperieur();
   	 }
    //rechercheEmployeeRestaurer
    @GetMapping("/rechercheemployeerestaurer")
 public List<Employee> rechercheremployeerestaurer(){
	 return userService.rechercherEmployeursRestaurer();
	 }
    //rechercheEmployeeServir
    @GetMapping("/rechercheemployeeServir")
 public List<Employee> rechercheremployeeServir(){
	 return userService.rechercherEmployeursServir();
	 }
    
    //{**********Restaurant******}
    //rechercheEmployeeGold
    @GetMapping("/rechercheemployeeGold_r")
 public List<Employee> rechercheremployeeGold_r(){
	 return userService.rechercherEmployeursGold_restaurant();
	 }
    
    
    //rechercheEmployeeSuperieur
    @GetMapping("/rechercheemployeeSuperieur_r")
 public List<Employee> rechercheremployeePremium_r(){
	 return userService.rechercherEmployeursSuperieur_restaurant();
	 }
    
    
    //rechercheEmployeeRestaurer
    @GetMapping("/rechercheemployeeRestaurer_r")
 public List<Employee> rechercheremployeeRestaurer_r(){
	 return userService.rechercherEmployeursRestaurer_restaurant();
	 }
    
    //rechercheEmployeePremium
    @GetMapping("/rechercheemployeeServir_r")
 public List<Employee> rechercheremployeeServir_r(){
	 return userService.rechercherEmployeursServir_restaurant();
	 }

 
    @GetMapping("/getsp")
 public ResponseEntity<Object> getspecialite(){
	 return userService.getnbspecaliter();
	 }
    @GetMapping("/allemployee")
 public List<Employee> allemployee(){
	 return userService.getemployes();
	 }
    @GetMapping("/getuser/{id}")
 public Employee getuser(@PathVariable Long id){
	 return userService.getuser(id);
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}