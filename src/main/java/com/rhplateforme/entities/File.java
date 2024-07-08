package com.rhplateforme.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idfile;
    private String titlefile;
    private String typefile;
    @Column(length = 920000)
    private byte[] taillefile;
    private String nomfichier;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonBackReference
    @ManyToOne
    private Employee emp;
 
    
    public File(String titlefile, String typefile, byte[] taillefile) {
        this.titlefile = titlefile;
        this.typefile = typefile;
        this.taillefile = taillefile;
    }
}
