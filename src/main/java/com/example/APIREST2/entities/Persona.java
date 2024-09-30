package com.example.APIREST2.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "RBAC", name = "Persona")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
@Audited
public class Persona extends Base{

    private String nombre;
    private String apellido;
    private int dni;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "Persona_Libro",
            joinColumns = @JoinColumn(name = "persona_id"),
            inverseJoinColumns = @JoinColumn(name = "libro_id")
    )
    @Builder.Default
    private List<Libro> libros = new ArrayList<Libro>();

}