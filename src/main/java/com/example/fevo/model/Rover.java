package com.example.fevo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Rover implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int nasaId;

    private String name;

    private String landing_date;

    private String launch_date;

    private String status;
}
