package com.example.fevo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
@Entity
public class Photo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int nasaId;

    private int sol;

    @OneToOne(fetch = FetchType.EAGER)
    private Camera camera;

    private String img_src;

    private String earth_date;

    @OneToOne(fetch = FetchType.EAGER)
    private Rover rover;



}
