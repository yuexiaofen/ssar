package com.sqlserver.ssar.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ssar_permission")
@Getter
@Setter
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Permission parent;

    @Column(nullable = false)
    private String name;

    @Column(name = "permission_key", nullable = false, length = 500)
    private String permissionKey;

    /**
     * 权限类型
     */
    @Enumerated(EnumType.STRING)
    private Type type;

    /**
     *  权限路径
     */
    private String path;

    private String resource;

    private Boolean enable = false;

    private String description;

    private Integer weight = 0;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", updatable = false)
    private List<Permission> children;

    @JsonProperty("text")
    public String getText(){
        return this.name;
    }

    /**
     * 权限类型
     */
    public enum Type{
        MENU("菜单"),
        FUNCTION("功能"),
        BLOCK("区域");

        private String display;

        Type(String display) {
            this.display = display;
        }

        public String display(){
            return display;
        }

        @Override
        public String toString() {
            return this.display+"[" + this.name() + "]";
        }
    }



}
