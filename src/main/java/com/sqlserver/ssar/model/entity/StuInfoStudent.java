package com.sqlserver.ssar.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
@Data
public class StuInfoStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String number;

    private int age;

    private Date birthday;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="class_id")
    private StuInfoClass clazz;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StuInfoScore> scoreList;
}
