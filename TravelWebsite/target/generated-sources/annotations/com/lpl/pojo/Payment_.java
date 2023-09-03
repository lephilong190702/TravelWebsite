package com.lpl.pojo;

import com.lpl.pojo.Booking;
import com.lpl.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-09-03T11:16:24")
@StaticMetamodel(Payment.class)
public class Payment_ { 

    public static volatile SingularAttribute<Payment, Integer> paymentId;
    public static volatile SingularAttribute<Payment, Date> paymentCreatedate;
    public static volatile SetAttribute<Payment, Booking> bookingSet;
    public static volatile SingularAttribute<Payment, Float> paymentAmount;
    public static volatile SingularAttribute<Payment, User> userId;

}