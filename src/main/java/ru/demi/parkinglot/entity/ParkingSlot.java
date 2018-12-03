package ru.demi.parkinglot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ParkingSlot extends BaseEntity implements Serializable {

    public ParkingSlot(int number) {
        this.number = number;
        this.isAvailable = true;
    }

    @Column(unique = true, updatable = false)
    private int number;
    private boolean isAvailable;
}
