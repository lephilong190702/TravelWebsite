package com.lpl.pojo;

import com.lpl.pojo.Booking;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-09-02T23:52:28")
@StaticMetamodel(Review.class)
public class Review_ { 

    public static volatile SingularAttribute<Review, String> reviewComment;
    public static volatile SingularAttribute<Review, Date> reviewDate;
    public static volatile SingularAttribute<Review, Integer> reviewId;
    public static volatile SingularAttribute<Review, Float> reviewRating;
    public static volatile SingularAttribute<Review, Booking> bookingId;

}