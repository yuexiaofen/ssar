package com.sqlserver.ssar.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ssar_training_library")
@Data
public class TrainingLibrary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String problem;

    @Column
    private double totalScore;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false)
    private String referenceAnswer;

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

    private Boolean enable = true;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示user不能为空。删除，不影响题库
    @JoinColumn(name="user_id")//设置在training_library表中的关联字段(外键)
    private User publishUser;

    @OneToMany(mappedBy = "trainingLibrary", cascade = CascadeType.ALL)
    private List<Dictionary> dictionaries;

    @OneToMany(mappedBy = "trainingLibrary", cascade = CascadeType.ALL)
    private List<TrainingLibraryPractice> trainingLibraryPractices;

    /**
     * 权限类型
     */
    public enum Type{
        SELECT("查询"),
        UPDATE("更新"),
        DELETE("删除"),
        CREATE("创建"),
        OTHERS("其他");

        private String display;

        Type(String display) {
            this.display = display;
        }

        public String display(){
            return display;
        }
    }
}
