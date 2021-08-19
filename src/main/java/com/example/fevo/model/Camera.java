package com.example.fevo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Camera implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int nasaId;

    private String name;

    private int rover_id;

    private String full_name;

}
