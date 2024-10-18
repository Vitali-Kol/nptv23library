package entity;

import entity.Buyer;
import entity.Sneaker;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-07T11:12:02")
@StaticMetamodel(History.class)
public class History_ { 

    public static volatile SingularAttribute<History, Date> soldSneaker;
    public static volatile SingularAttribute<History, Long> id;
    public static volatile SingularAttribute<History, Sneaker> sneaker;
    public static volatile SingularAttribute<History, Buyer> buyer;

}