package com.sqlserver.ssar.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ssar_grade")
@Data
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // 入学年份
    private int year;

    private Boolean enable = true;

    @JsonIgnore
    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
    private List<Class> classes;
}
