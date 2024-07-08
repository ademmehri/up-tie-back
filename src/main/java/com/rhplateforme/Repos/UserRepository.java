package com.rhplateforme.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rhplateforme.entities.Employee;


import java.util.List;

public interface UserRepository extends JpaRepository<Employee, Long> {
Employee findByEmail(String email);
List<Employee> findByemail(String username);
boolean existsByEmail(String email);
List<Employee> findByEtat(String etat);
List <Employee> findByGouvernerat(String gov);
boolean existsByemail(String mail);
List<Employee> findByEtatAndGouvernerat(String etat ,String gouvernerat);
List<Employee> findBySpecialite(String specialite);
List<Employee> findBySexe(String sexe);
List<Employee> findByEtatAndGouverneratAndSpecialiteAndSexe(String etat, String gouvernerat, String specialite,
		String sexe);
List<Employee> findBySexeAndSpecialiteAndGouvernerat(String sexe, String specialite, String gouvernerat);
@Query(value = "SELECT COUNT(*) FROM utilisateur e WHERE e.specialite LIKE :ch%", nativeQuery = true)
public int getempspecialiste(@Param("ch") String ch);

}
