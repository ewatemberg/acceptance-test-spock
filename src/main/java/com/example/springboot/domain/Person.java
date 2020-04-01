package com.example.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private static final long serialVersionUID = -2618582928571746770L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
