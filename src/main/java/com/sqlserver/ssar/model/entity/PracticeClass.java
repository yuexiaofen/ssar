package com.sqlserver.ssar.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ssar_practice_class")
@Data
public class PracticeClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="class_id")
    private Class clazz;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="practice_id")
    private Practice practice;
}
