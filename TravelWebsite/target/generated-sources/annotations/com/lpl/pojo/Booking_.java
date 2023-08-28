package com.lpl.pojo;

import com.lpl.pojo.Payment;
import com.lpl.pojo.Tour;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-08-28T11:07:45")
@StaticMetamodel(Booking.class)
public class Booking_ { 

    public static volatile SingularAttribute<Booking, String> bookingNotes;
    public static volatile SingularAttribute<Booking, Integer> bookingNumberofchidren;
    public static volatile SingularAttribute<Booking, Payment> paymentId;
    public static volatile SingularAttribute<Booking, Tour> tourId;
    public static volatile SingularAttribute<Booking, Integer> bookingNumberofaudult;
    public static volatile SingularAttribute<Booking, Date> bookingDate;
    public static volatile SingularAttribute<Booking, Float> bookingUnitprice;
    public static volatile SingularAttribute<Booking, Integer> bookingId;

}