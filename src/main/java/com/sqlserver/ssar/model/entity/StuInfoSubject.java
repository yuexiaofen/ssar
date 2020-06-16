package com.sqlserver.ssar.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject")
@Data
public class StuInfoSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 500)
    private String description;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<StuInfoScore> scoreList;
}
