package com.alatamli.web.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("OTR")
public class InstanceOtherEntity extends InstanceEntity{

}
