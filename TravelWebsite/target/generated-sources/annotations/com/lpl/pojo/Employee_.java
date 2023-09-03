package com.lpl.pojo;

import com.lpl.pojo.Tour;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-09-03T11:39:11")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile SingularAttribute<Employee, String> employeeLastname;
    public static volatile SingularAttribute<Employee, Tour> tourId;
    public static volatile SingularAttribute<Employee, String> employeeEmail;
    public static volatile SingularAttribute<Employee, Integer> employeeId;
    public static volatile SingularAttribute<Employee, String> employeeFirstname;

}