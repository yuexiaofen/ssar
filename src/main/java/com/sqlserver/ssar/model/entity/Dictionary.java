package com.sqlserver.ssar.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ssar_dictionary")
@Data
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_name")
    private String tableName;           //表名

    @Column(name = "serial_number")
    private String serialNumber;        //序号

    @Column(name = "column_name")
    private String columnName;          //列名

    @Column(name = "column_description")
    private String columnDescription;   //列说明

    @Column(name = "data_type")
    private String dataType;            //数据类型

    @Column(name = "length")
    private String length;              //长度

    @Column(name = "decimal_places")
    private String decimalPlaces;       //小数位数

    @Column(name = "logo")
    private String logo;                //标识

    @Column(name = "primary_key")
    private String primaryKey;          //主键

    @Column(name = "allow_empty")
    private String allowEmpty;          //允许空

    @Column(name = "defaults")
    private String defaults;            //默认值

    //可选属性optional=false,表示TrainingLibrary不能为空。Dictionary删除，不影响TrainingLibrary
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="training_library_id")//设置在TrainingLibrary表中的关联字段(外键)
    private TrainingLibrary trainingLibrary;

}
