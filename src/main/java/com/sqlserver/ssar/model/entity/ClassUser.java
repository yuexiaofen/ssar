package com.sqlserver.ssar.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ssar_class_user")
@Data
public class ClassUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="class_id")
    private Class clazz;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="user_id")
    private User user;
}
