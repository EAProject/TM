package com.tm.entities;

import com.tm.entities.Student;
import com.tm.entities.Teacher;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-29T08:16:14")
@StaticMetamodel(Teamchecking.class)
public class Teamchecking_ { 

    public static volatile SingularAttribute<Teamchecking, Integer> id;
    public static volatile SingularAttribute<Teamchecking, Student> studentId;
    public static volatile SingularAttribute<Teamchecking, Boolean> pending;
    public static volatile SingularAttribute<Teamchecking, String> checkingTime;
    public static volatile SingularAttribute<Teamchecking, Boolean> checked;
    public static volatile SingularAttribute<Teamchecking, Teacher> teacherId;

}