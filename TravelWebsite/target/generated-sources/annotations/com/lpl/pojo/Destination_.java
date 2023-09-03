package com.lpl.pojo;

import com.lpl.pojo.Tour;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-09-03T11:16:24")
@StaticMetamodel(Destination.class)
public class Destination_ { 

    public static volatile SingularAttribute<Destination, String> destinationCity;
    public static volatile SingularAttribute<Destination, String> destinationDescription;
    public static volatile SetAttribute<Destination, Tour> tourSet;
    public static volatile SingularAttribute<Destination, Integer> destinationId;
    public static volatile SingularAttribute<Destination, String> destinationImageurl;
    public static volatile SingularAttribute<Destination, String> destinationCountry;
    public static volatile SingularAttribute<Destination, Float> destinationAveragerating;
    public static volatile SingularAttribute<Destination, Integer> destinationTournumber;

}