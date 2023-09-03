package com.lpl.pojo;

import com.lpl.pojo.Comment;
import com.lpl.pojo.Payment;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-09-03T11:21:11")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> userImageurl;
    public static volatile SingularAttribute<User, String> userUsername;
    public static volatile SingularAttribute<User, String> userPassword;
    public static volatile SetAttribute<User, Comment> commentSet;
    public static volatile SingularAttribute<User, Date> userDob;
    public static volatile SingularAttribute<User, String> userPhone;
    public static volatile SingularAttribute<User, String> userEmail;
    public static volatile SingularAttribute<User, String> userRole;
    public static volatile SingularAttribute<User, Integer> userId;
    public static volatile SetAttribute<User, Payment> paymentSet;

}