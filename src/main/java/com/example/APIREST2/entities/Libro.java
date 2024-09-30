package com.example.APIREST2.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
@Audited
public class Libro extends Base{
    private String titulo;
    private int fecha;
    private String genero;
    private int paginas;

    @ManyToMany(cascade = CascadeType.REFRESH)
    private List<Autor> autores;
}
