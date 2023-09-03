package com.lpl.pojo;

import com.lpl.pojo.Comment;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-09-03T11:39:11")
@StaticMetamodel(News.class)
public class News_ { 

    public static volatile SingularAttribute<News, String> newsPostdate;
    public static volatile SingularAttribute<News, Integer> newsId;
    public static volatile SingularAttribute<News, String> newsContent;
    public static volatile SetAttribute<News, Comment> commentSet;
    public static volatile SingularAttribute<News, String> newsTitle;
    public static volatile SingularAttribute<News, String> newsImageurl;

}