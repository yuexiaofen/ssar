package com.sqlserver.ssar.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ssar_class")
@Data
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name = "class_name")
    private String className;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示grade不能为空。删除班级，不影响年级
    @JoinColumn(name="grade_id")//设置在class表中的关联字段(外键)
    private Grade grade;

    private Boolean enable = true;

//    @OneToMany(mappedBy = "aClass", cascade = CascadeType.ALL)
//    private List<ClassUser> classUsers;

//    @OneToMany(mappedBy = "aClass", cascade = CascadeType.ALL)
//    private List<PracticeClass> practiceClasses;

}
