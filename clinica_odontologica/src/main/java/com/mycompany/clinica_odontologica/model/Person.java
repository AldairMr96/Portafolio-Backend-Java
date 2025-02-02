package com.mycompany.clinica_odontologica.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idPerson;
    private String dni;
    private String name ;
    private String lastname;
    private  String numberPhone ;
    private  String address;
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;
}
