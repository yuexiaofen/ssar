package com.sqlserver.ssar.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ssar_practice")
@Data
public class Practice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name = "practice_name")
    private String practiceName;

    //默认创建时间
    @Column(name = "create_time")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    //默认更新时间
    @Column(name = "update_time")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    private Boolean done = false;

    private Boolean enable = true;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ssar_practice_training_library",
            joinColumns = @JoinColumn(name = "practice_id"),
            inverseJoinColumns = @JoinColumn(name = "training_library_id")
    )
    private List<TrainingLibrary> trainingLibraries;*/

//    @OneToMany(mappedBy = "practice", cascade = CascadeType.ALL)
//    private List<TrainingLibraryPractice> trainingLibraryPractices;

//    @OneToMany(mappedBy = "practice", cascade = CascadeType.ALL)
//    private List<PracticeClass> practiceClasses;

    /**
     * 发布老师
     */
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="user_id")
    private User teacher;

    @JsonIgnore
    private Double score;

//    @OneToMany(mappedBy = "practice",cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Set<Score> scores;
}
