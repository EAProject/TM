package com.tm.entities;

import com.tm.entities.Teamchecking;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-29T08:16:14")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile SingularAttribute<Student, Integer> id;
    public static volatile SingularAttribute<Student, String> middleName;
    public static volatile SingularAttribute<Student, String> lastName;
    public static volatile SingularAttribute<Student, Integer> contactNumber;
    public static volatile SingularAttribute<Student, String> email;
    public static volatile SingularAttribute<Student, String> program;
    public static volatile SingularAttribute<Student, String> batch;
    public static volatile SingularAttribute<Student, String> userName;
    public static volatile CollectionAttribute<Student, Teamchecking> teamcheckingCollection;
    public static volatile SingularAttribute<Student, String> firstName;
    public static volatile SingularAttribute<Student, Boolean> isDeleted;
    public static volatile SingularAttribute<Student, String> password;

}