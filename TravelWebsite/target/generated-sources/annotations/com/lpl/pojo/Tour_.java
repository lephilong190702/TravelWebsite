package com.lpl.pojo;

import com.lpl.pojo.Booking;
import com.lpl.pojo.Comment;
import com.lpl.pojo.Destination;
import com.lpl.pojo.Employee;
import com.lpl.pojo.Image;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-08-28T11:07:45")
@StaticMetamodel(Tour.class)
public class Tour_ { 

    public static volatile ListAttribute<Tour, Image> imageSet;
    public static volatile SingularAttribute<Tour, Date> tourEnddate;
    public static volatile SingularAttribute<Tour, Destination> destinationId;
    public static volatile SetAttribute<Tour, Employee> employeeSet;
    public static volatile SingularAttribute<Tour, Float> tourPrice;
    public static volatile SingularAttribute<Tour, Integer> tourMaximumpeople;
    public static volatile SetAttribute<Tour, Comment> commentSet;
    public static volatile SingularAttribute<Tour, Integer> tourId;
    public static volatile SingularAttribute<Tour, Date> tourStartdate;
    public static volatile SingularAttribute<Tour, String> tourTitle;
    public static volatile SingularAttribute<Tour, String> tourDescription;
    public static volatile SetAttribute<Tour, Booking> bookingSet;
    public static volatile SingularAttribute<Tour, Date> tourDuration;

}