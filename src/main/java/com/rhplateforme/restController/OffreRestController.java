package com.rhplateforme.restController;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.rhplateforme.entities.Offre;

import com.rhplateforme.service.OffreService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://tie-job.com:8080")
public class OffreRestController {
@Autowired
OffreService offreserv;
@PostMapping("/addoffre/{idemp}/{idempr}")
public Offre addoffre(@PathVariable Long idemp,@PathVariable Long idempr,@RequestBody String desc) {
return offreserv.addoffre(idemp, idempr, desc);
}
@GetMapping("/getoffre/{idemp}")
public List<Offre> getoffre(@PathVariable Long idemp) {
return offreserv.getoffredeemployee(idemp);
}
@GetMapping("/getseuloffre/{id}")
public Offre getseuloffre(@PathVariable Long id) {
return offreserv.getoffre(id);
}
@GetMapping("/getoffreempr/{idempr}")
public List<Offre> getoffreempr(@PathVariable Long idempr) {
return offreserv.getoffredeemployeur(idempr);
}
@DeleteMapping("/deleteoffre/{id}")
public void deleteoffre(@PathVariable Long id) {
 offreserv.deleteoffre(id);
}
}
