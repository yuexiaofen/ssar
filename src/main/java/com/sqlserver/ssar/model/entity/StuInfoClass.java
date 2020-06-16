package com.sqlserver.ssar.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "class")
@Data
public class StuInfoClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;//	nvarchar(50)

    @Column(nullable = false, length = 50)
    private String number;//	nvarchar(50)



}
