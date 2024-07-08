package com.rhplateforme.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rhplateforme.entities.Employee;
import com.rhplateforme.entities.File;

public interface FileRepository extends JpaRepository<File, Long> {
	
}
