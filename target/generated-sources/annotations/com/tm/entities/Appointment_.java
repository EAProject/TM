package com.tm.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-29T08:16:14")
@StaticMetamodel(Appointment.class)
public class Appointment_ { 

    public static volatile SingularAttribute<Appointment, Integer> id;
    public static volatile SingularAttribute<Appointment, String> time;
    public static volatile SingularAttribute<Appointment, Integer> studentId;
    public static volatile SingularAttribute<Appointment, String> date;
    public static volatile SingularAttribute<Appointment, Integer> teacherId;

}