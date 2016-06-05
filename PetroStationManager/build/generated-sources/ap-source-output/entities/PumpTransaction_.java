package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-27T13:20:35")
@StaticMetamodel(PumpTransaction.class)
public class PumpTransaction_ { 

    public static volatile SingularAttribute<PumpTransaction, Date> datetime;
    public static volatile SingularAttribute<PumpTransaction, String> puName;
    public static volatile SingularAttribute<PumpTransaction, String> indexafter;
    public static volatile SingularAttribute<PumpTransaction, String> uName;
    public static volatile SingularAttribute<PumpTransaction, String> indexbefore;
    public static volatile SingularAttribute<PumpTransaction, Integer> traId;
    public static volatile SingularAttribute<PumpTransaction, String> proName;
    public static volatile SingularAttribute<PumpTransaction, Integer> puTraId;

}