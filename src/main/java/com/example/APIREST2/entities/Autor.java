package com.example.APIREST2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "Autor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
@Audited
public class Autor extends Base{

    private String nombre;

    private String apellido;

    @Column(name = "Biografia", length = 1500)
    private String biografia;

}
