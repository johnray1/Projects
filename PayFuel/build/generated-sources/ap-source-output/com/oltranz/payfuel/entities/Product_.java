package com.oltranz.payfuel.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-04T19:47:12")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Integer> productId;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, Double> hqPrice;
    public static volatile SingularAttribute<Product, String> measureUnity;
    public static volatile SingularAttribute<Product, Integer> productTypeId;
    public static volatile SingularAttribute<Product, Integer> status;

}