package com.rhplateforme.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rhplateforme.Repos.RoleRepository;
import com.rhplateforme.Repos.UserRepository;
import com.rhplateforme.entities.Employee;
import com.rhplateforme.entities.*;


@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRep;
	@Autowired
	RoleRepository roleRep;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Employee saveUser(Employee user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRep.save(user);
	}

	@Override
	public Employee addRoleToUser(String email, String rolename) {
		Employee usr = userRep.findByEmail(email);
		Role role = roleRep.findByRole(rolename);
		usr.getRoles().add(role);
		return usr;
	}

	@Override
	public Role addRole(Role role) {
		return roleRep.save(role);
	}

	

	@Override
	public List<Employee> findAllUsers() {
		
		return userRep.findAll();
	}

	@Override
	public Employee findUserByEmail(String email) {
		
		return userRep.findByEmail(email);
	}
	@Override
	public boolean hasUserWithEmail(String email) {
		return userRep.existsByEmail(email);
	}

	 @Override
	    public void updatePassword(String email, String newPassword)  {
	        Employee user = userRep.findByEmail(email);
	        if (user != null) {
	            // Update the user's password
	            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	            userRep.save(user);
	        
	    }

	 }

	@Override
	public Employee updateuser(Employee employee) {
		 Employee e;
	        e=  userRep.findById(employee.getId()).get();
	       
	        e.setEmail(employee.getEmail());
	        e.setDate_nais(employee.getDate_nais());
	        e.setNum(employee.getNum());
	        e.setDescription(employee.getDescription());
	        e.setNom(employee.getNom());
	      
	     e.setCity(employee.getCity());
	        e.setSexe(employee.getSexe());
	        e.setSpecialite(employee.getSpecialite());
	        e.setExp(employee.getExp());
	        e.setGouvernerat(employee.getGouvernerat());
	        e.setDescription(employee.getDescription());
	        e.setCp(employee.getCp());
	        e.setPack(employee.getPack());
	        e.setDuree(employee.getDuree());
	        return userRep.save(e) ;
	}

	
	    //goldhotel
	    public List<Employee> rechercherEmployeursGold() {
	        // Créer manuellement une liste de noms de métiers
	        List<String> nomsMetiersRecherches = java.util.Arrays.asList("Directeur d’hôtel", "Directeur d'hebergement", 
	        		"Adjoint de directeur en hotellerie","Directeur de la restauration",
	        		"Directuer financier d'un hotel","Guest relation manager",
	        		"Manager dans la restauration","Spa manager","Yield manager",
	        		"Chef de cuisine","Chef de partie",
	        		"Commis de cuisine","Chef-gérant en restauration collective", 
	        		"Cuisinier","Pizzaiolo",
	        		"Barman","Patissier",
	        		"Boucher","Boulanger","Poissonnier","Chocalatier-confisseur","Charcutier-traiteur","Econome","Gérant",
	        		"Serveur de restaurant ","Chef de rang", 
	        		"Maitre d'hotel","Garçon de café",
	        		"Plongeur","Serveuse",
	        		"Majordome(Butler)","Bagagiste","Chasseur","liftier","Groom","Portier");

	        // Récupérer tous les employeurs de la base de données
	        List<Employee> employeurs = userRep.findAll();
	        List<Employee> resultats = new ArrayList<>();

	        // Parcourir tous les employeurs
	        for (Employee employeur : employeurs) {
	            String metierEmployeur = employeur.getSpecialite();
	            // Vérifier si l'un des métiers de l'employeur correspond à un métier recherché
	                if (nomsMetiersRecherches.contains(metierEmployeur)) {
	                    resultats.add(employeur);
	                     
	                }
	            }
	        return resultats;
	        

	        
	    }
	    public List<Employee> rechercherEmployeursSuperieur() {
	        // Créer manuellement une liste de noms de métiers
	        List<String> nomsMetiersRecherches = java.util.Arrays.asList("Directeur d’hôtel", "Directeur d'hebergement", 
	        		"Adjoint de directeur en hotellerie","Directeur de la restauration",
	        		"Directuer financier d'un hotel","Guest relation manager",
	        		"Manager dans la restauration","Spa manager","Yield manager");

	        // Récupérer tous les employeurs de la base de données
	        List<Employee> employeurs = userRep.findAll();
	        List<Employee> resultats = new ArrayList<>();

	        // Parcourir tous les employeurs
	        for (Employee employeur : employeurs) {
	            String metierEmployeur = employeur.getSpecialite();
	            // Vérifier si l'un des métiers de l'employeur correspond à un métier recherché
	            System.out.println("okkkkkkjhh");
	                if (nomsMetiersRecherches.contains(metierEmployeur)) {
	                	System.out.println("okkkkkk");
	                    resultats.add(employeur);
	                     
	                }
	            }
	        return resultats;
	        

	        
	    }
	    
	    public List<Employee> rechercherEmployeursRestaurer() {
	        // Créer manuellement une liste de noms de métiers
	        List<String> nomsMetiersRecherches = java.util.Arrays.asList("Chef de cuisine","Chef de partie",
	        		"Commis de cuisine","Chef-gérant en restauration collective", 
	        		"Cuisinier","Pizzaiolo",
	        		"Barman","Patissier",
	        		"Boucher","Boulanger","Poissonnier","Chocalatier-confisseur","Charcutier-traiteur","Econome","Gérant");

	        // Récupérer tous les employeurs de la base de données
	        List<Employee> employeurs = userRep.findAll();
	        List<Employee> resultats = new ArrayList<>();

	        // Parcourir tous les employeurs
	        for (Employee employeur : employeurs) {
	            String metierEmployeur = employeur.getSpecialite();
	            // Vérifier si l'un des métiers de l'employeur correspond à un métier recherché
	            
	                if (nomsMetiersRecherches.contains(metierEmployeur)) {
	                    resultats.add(employeur);
	                     
	                }
	            }
	        return resultats;
	        

	        
	    }
	    
	    public List<Employee> rechercherEmployeursServir() {
	    	System.out.println("pkk");
	        // Créer manuellement une liste de noms de métiers
	        List<String> nomsMetiersRecherches = java.util.Arrays.asList("Serveur de restaurant ","Chef de rang", 
	        		"Maitre d'hotel","Garçon de café",
	        		"Plongeur","Serveuse",
	        		"Majordome(Butler)","Bagagiste","Chef de cuisine","Chasseur","liftier","Groom","Portier");

	        // Récupérer tous les employeurs de la base de données
	        List<Employee> employeurs = userRep.findAll();
	        List<Employee> resultats = new ArrayList<>();

	        // Parcourir tous les employeurs
	        for (Employee employeur : employeurs) {
	            String metierEmployeur = employeur.getSpecialite();
	            // Vérifier si l'un des métiers de l'employeur correspond à un métier recherché
	            
	                if (nomsMetiersRecherches.contains(metierEmployeur)) {
	                    resultats.add(employeur);
	                     
	                }
	            }
	        return resultats;
	        

	        
	    }

	  //{**************************pack Hotel--------------------------------------}
	    
	    
	    
	    
	  //{-----------------------pack restaurant--------------------------------------}
	    //Gold
	    public List<Employee> rechercherEmployeursGold_restaurant() {
	        // Créer manuellement une liste de noms de métiers
	        List<String> nomsMetiersRecherches = java.util.Arrays.asList("Directeur de la restauration",
	        		"Directeur financier d'un restaurant", 
	        		"Gérant","Econome",
	        		"Manager de restauration","Chef de cuisine","Chef de partie",
	        		"Commis de cuisine", 
	        		"Cuisinier","Pizzaiolo",
	        		"Barman","Patissier",
	        		"Boucher","Boulanger","Poissonnier","Chocalatier-confisseur","Charcutier-traiteur","Econome","Gérant",
	        		"Serveur","Chef de rang",
	        		"Plongeur","Serveuse",
	        		"Portier");

	        // Récupérer tous les employeurs de la base de données
	        List<Employee> employeurs = userRep.findAll();
	        List<Employee> resultats = new ArrayList<>();

	        // Parcourir tous les employeurs
	        for (Employee employeur : employeurs) {
	            String metierEmployeur = employeur.getSpecialite();
	            // Vérifier si l'un des métiers de l'employeur correspond à un métier recherché
	            
	                if (nomsMetiersRecherches.contains(metierEmployeur)) {
	                    resultats.add(employeur);
	                     
	                }
	            }
	        return resultats;
	        

	        
	    }
	    
	    
	    
	  //rechercheMetiersPremium
	    

	    public List<Employee> rechercherEmployeursSuperieur_restaurant() {
	        // Créer manuellement une liste de noms de métiers
	        List<String> nomsMetiersRecherches = java.util.Arrays.asList("Directeur de la restauration",
	        		"Directeur financier d'un restaurant", 
	        		"Gérant","Econome",
	        		"Manager de restauration");

	        // Récupérer tous les employeurs de la base de données
	        List<Employee> employeurs = userRep.findAll();
	        List<Employee> resultats = new ArrayList<>();

	        // Parcourir tous les employeurs
	        for (Employee employeur : employeurs) {
	            String metierEmployeur = employeur.getSpecialite();
	            // Vérifier si l'un des métiers de l'employeur correspond à un métier recherché
	            
	                if (nomsMetiersRecherches.contains(metierEmployeur)) {
	                    resultats.add(employeur);
	                     
	                }
	            }
	        return resultats;
	        

	        
	    }
	    
	    public List<Employee> rechercherEmployeursRestaurer_restaurant() {
	        // Créer manuellement une liste de noms de métiers
	        List<String> nomsMetiersRecherches = java.util.Arrays.asList("Chef de cuisine","Chef de partie",
	        		"Commis de cuisine", 
	        		"Cuisinier","Pizzaiolo",
	        		"Barman","Patissier",
	        		"Boucher","Boulanger","Poissonnier","Chocalatier-confisseur","Charcutier-traiteur","Econome","Gérant");

	        // Récupérer tous les employeurs de la base de données
	        List<Employee> employeurs = userRep.findAll();
	        List<Employee> resultats = new ArrayList<>();

	        // Parcourir tous les employeurs
	        for (Employee employeur : employeurs) {
	            String metierEmployeur = employeur.getSpecialite();
	            // Vérifier si l'un des métiers de l'employeur correspond à un métier recherché
	            
	                if (nomsMetiersRecherches.contains(metierEmployeur)) {
	                    resultats.add(employeur);
	                     
	                }
	            }
	        return resultats;
	        

	        
	    }
	    
	    public List<Employee> rechercherEmployeursServir_restaurant() {
	        // Créer manuellement une liste de noms de métiers
	        List<String> nomsMetiersRecherches = java.util.Arrays.asList("Serveur","Chef de rang",
	        		"Plongeur","Serveuse",
	        		"Portier");

	        // Récupérer tous les employeurs de la base de données
	        List<Employee> employeurs = userRep.findAll();
	        List<Employee> resultats = new ArrayList<>();

	        // Parcourir tous les employeurs
	        for (Employee employeur : employeurs) {
	            String metierEmployeur = employeur.getSpecialite();
	            // Vérifier si l'un des métiers de l'employeur correspond à un métier recherché
	            
	                if (nomsMetiersRecherches.contains(metierEmployeur)) {
	                    resultats.add(employeur);
	                     
	                }
	            }
	        return resultats;
	        

	        
	    }
	    public ResponseEntity<Object> getnbspecaliter(){
	    	 Map<String, Object> responseData = new HashMap<>();
	    	int nbs=0;
	    	int nbp=0;
	    	int nbB=0;
	    	int nbb=0;
	    	int nbi=0;
	    	int nbpor=0;
	    	int nbchef;
	    	int nbdir;
	    	int nbm;
	    	  List<String> nomsMetiersRecherches = java.util.Arrays.asList("Serveur",
	          		"Plongeur","Barman","Baggagiste","Pizzaiolo",
	          		"Portier");
	    	  nbchef= userRep.getempspecialiste("Chef");
	    	  nbdir= userRep.getempspecialiste("Directeur");
	    	  nbm= userRep.getempspecialiste("Manager");
	    	   List<Employee> employees = userRep.findAll();
	    	   for (Employee employee : employees) {
	               String metierEmployee = employee.getSpecialite();
	                   if (metierEmployee.equals("Serveur")) {
	                     nbs++;
	                   }
	                   if (metierEmployee.equals("Plongeur")) {
	                       nbp++;
	                     }
	                   if (metierEmployee.equals("Barman")) {
	                       nbB++;
	                     }
	                   if (metierEmployee.equals("Baggagiste")) {
	                       nbb++;
	                     }
	                   if (metierEmployee.equals("Pizzaiolo")) {
	                       nbi++;
	                     }
	                   if (metierEmployee.equals("Portier")) {
	                       nbpor++;
	                     }
	                   
	               }
	    	     responseData.put("Serveur", nbs);
	    	     responseData.put("Plongeur", nbp);
	    	     responseData.put("Barman", nbB); 
	    	     responseData.put("Baggagiste", nbb); 
	    	     responseData.put("Pizzaiolo", nbi);  
	    	     responseData.put("Portier", nbpor);
	    	     responseData.put("Chef", nbchef);
	    	     responseData.put("Directeur", nbdir);
	    	     responseData.put("Manager", nbm);
	    	     return ResponseEntity.ok(responseData);
	    }

		@Override
		public List<Employee> getemployes() {
			
			return userRep.findAll();
		}

		@Override
		public Employee getuser(Long id) {
			// TODO Auto-generated method stub
			return userRep.findById(id).get();
		}

		@Override
		public Employee updatepack(Employee employee) {
			Employee e;
	        e=  userRep.findById(employee.getId()).get();
	        e.setPack(employee.getPack());
	        e.setDuree(employee.getDuree());
	        e.setD_inscrit(employee.getD_inscrit());
	        return userRep.save(e) ;
		}
	
}
