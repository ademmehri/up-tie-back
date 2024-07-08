package com.rhplateforme.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rhplateforme.entities.Offre;
import com.rhplateforme.entities.Role;

public interface OffreRepository extends JpaRepository<Offre, Long> {
	 @Query(value = "select * from offre o where o.employee_id = :idemp ", nativeQuery = true)
	    public List<Offre> getoffre(@Param("idemp") Long idemp);
	 @Query(value = "select * from offre o where o.employeur_id = :idempr ", nativeQuery = true)
	    public List<Offre> getoffreemployeur(@Param("idempr") Long idempr);
}
