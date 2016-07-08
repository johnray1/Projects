package com.oltranz.payfuel.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-08T13:05:40")
@StaticMetamodel(Voucher.class)
public class Voucher_ { 

    public static volatile SingularAttribute<Voucher, String> voucherNo;
    public static volatile SingularAttribute<Voucher, Date> expiryDate;
    public static volatile SingularAttribute<Voucher, Date> provisonDate;
    public static volatile SingularAttribute<Voucher, Double> remainAmount;
    public static volatile SingularAttribute<Voucher, Long> voucherId;
    public static volatile SingularAttribute<Voucher, Double> initialAmount;
    public static volatile SingularAttribute<Voucher, Long> customerId;
    public static volatile SingularAttribute<Voucher, String> numberPlate;
    public static volatile SingularAttribute<Voucher, Integer> status;

}