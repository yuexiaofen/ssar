package com.sqlserver.ssar.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ssar_training_library_practice")
@Data
public class TrainingLibraryPractice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="training_library_id")
    private TrainingLibrary trainingLibrary;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="practice_id")
    private Practice practice;
}
