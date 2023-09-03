package com.lpl.pojo;

import com.lpl.pojo.Tour;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-09-03T11:21:11")
@StaticMetamodel(Image.class)
public class Image_ { 

    public static volatile SingularAttribute<Image, Integer> imageId;
    public static volatile SingularAttribute<Image, Tour> tourId;
    public static volatile SingularAttribute<Image, String> imageUrl;

}