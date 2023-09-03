package com.lpl.pojo;

import com.lpl.pojo.News;
import com.lpl.pojo.Tour;
import com.lpl.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-09-03T11:21:11")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, News> newsId;
    public static volatile SingularAttribute<Comment, Tour> tourId;
    public static volatile SingularAttribute<Comment, Integer> commentId;
    public static volatile SingularAttribute<Comment, String> commentContent;
    public static volatile SingularAttribute<Comment, Date> commentDatetime;
    public static volatile SingularAttribute<Comment, User> userId;

}