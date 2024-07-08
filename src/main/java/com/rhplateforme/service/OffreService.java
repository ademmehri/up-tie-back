package com.rhplateforme.service;

import java.util.List;

import com.rhplateforme.entities.Offre;

public interface OffreService {
Offre addoffre(Long idemp,Long idempr,String desc);
List<Offre> getoffredeemployee(Long id);
List<Offre> getoffredeemployeur(Long id);
void deleteoffre(Long id);
Offre getoffre(Long id);
}
