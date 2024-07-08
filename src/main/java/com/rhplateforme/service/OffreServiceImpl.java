package com.rhplateforme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhplateforme.Repos.OffreRepository;
import com.rhplateforme.Repos.UserRepository;
import com.rhplateforme.entities.Employee;
import com.rhplateforme.entities.Offre;
import com.rhplateforme.mail.EmailSenderService;
@Service
public class OffreServiceImpl implements OffreService {
    @Autowired
    OffreRepository offreRepo;
  
     @Autowired
   UserRepository employeeRepo;
     @Autowired
 	EmailSenderService emailService;

	@Override
	public Offre addoffre(Long idemp, Long idempr, String desc) {
		Employee emp=employeeRepo.findById(idemp).get();
    	 Employee empr=employeeRepo.findById(idempr).get();
    	 Offre offre=new Offre();
    	 offre.setDescriptionoffre(desc);
    	 offre.setEmployee(emp);
    	 offre.setEmployeur(empr);
    	 emailService.sendemailoffre(emp.getEmail(),empr.getNom(),desc);
    	 return offreRepo.save(offre);
	}

	@Override
	public List<Offre> getoffredeemployee(Long id) {
		
		return offreRepo.getoffre(id);
	}

	@Override
	public List<Offre> getoffredeemployeur(Long id) {
		// TODO Auto-generated method stub
		return offreRepo.getoffreemployeur(id);
	}

	@Override
	public void deleteoffre(Long id) {
		// TODO Auto-generated method stub
		offreRepo.deleteById(id);
	}

	@Override
	public Offre getoffre(Long id) {
		// TODO Auto-generated method stub
		return offreRepo.findById(id).get();
	}

}
