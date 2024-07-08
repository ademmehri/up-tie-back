package com.rhplateforme.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "utilisateur")
public class Employee {
@Id
@GeneratedValue (strategy=GenerationType.IDENTITY)
private Long id;
private String nom;
private String gouvernerat;
private String num;
private String sexe;
private String date_nais;
private String specialite;
private String description;
private String exp;
private String cin;
private String email;
private String password;
private boolean enable;
private String cp;
private String city;
private String etat;
private Boolean enabled;
private TypePack pack;
private Duree duree;
private LocalDateTime d_inscrit=LocalDateTime.now();

 @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
@JoinTable(name="user_role",joinColumns = @JoinColumn(name="user_id") ,
 inverseJoinColumns = @JoinColumn(name="role_id"))
private List<Role> roles;

 @OneToMany(mappedBy = "emp")
 private List<File> files=new ArrayList<>();


}