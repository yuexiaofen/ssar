package com.sqlserver.ssar.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ssar_score")
@Data
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;

    private Double score = 0.0;

    private Boolean enable = true;

    // 错误提示
    private String tips;

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

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示user不能为空。删除score，不影响user
    @JoinColumn(name="student_id")//设置在score表中的关联字段(外键)
    private User student;
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="practice_id")
    private Practice practice;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示trainingLibrary不能为空。删除成绩，不影响训练题
    @JoinColumn(name="training_library_id")//设置在score表中的关联字段(外键)
    private TrainingLibrary trainingLibrary;
}
