package com.maranata.budgetplanner.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="validations")
public class Validation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn (name="validation_types_id")
    private ValidationType validationType;

}
