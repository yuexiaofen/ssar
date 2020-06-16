package com.sqlserver.ssar.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ssar_user")
@Data
public class User {

    /**
     * GenerationType属性
     * TABLE：使用一个特定的数据库表格来保存主键。
     * SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
     * IDENTITY：主键由数据库自动生成（主要是自动增长型）
     * AUTO：主键由程序控制。
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 16, nullable = false)
    private String account;

    @Column(nullable = false, length = 128)
    @JsonIgnore
    private String password;

    private String salt;

    @Column(name = "user_name",length = 32)
    private String userName;

    private Date birthday;

    private String sex;

    private String tel;

    private String email;

    private Boolean enable = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ssar_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    // 删除用户时，删除该用户的所有成绩
//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
//    private Set<Score> scores;

    // 删除用户时，级联删除该用户发布的所有练习
//    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
//    private List<Practice> practices;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private  List<ClassUser> classUsers;
}
