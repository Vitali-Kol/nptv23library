package entity;

import entity.Cover;
import entity.Sneaker;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-04-07T11:12:02")
@StaticMetamodel(SneakerCover.class)
public class SneakerCover_ { 

    public static volatile SingularAttribute<SneakerCover, Cover> cover;
    public static volatile SingularAttribute<SneakerCover, Long> id;
    public static volatile SingularAttribute<SneakerCover, Sneaker> sneaker;

}