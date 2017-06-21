package com.epam.mentoring.concurrency;

/**
 * Created by Aleksandr_Ruzanov on 20.06.2017.
 */
public class EntityNumber implements Comparable<EntityNumber> {
    private Integer value;
    private String valueName;
    private boolean isWork;

    public EntityNumber(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public boolean isWork() {
        return isWork;
    }

    public void setIsWork(boolean isWork) {
        this.isWork = isWork;
    }

    @Override
    public String toString() {
        return "EntityNumber{" +
                "value=" + value +
                ", valueName='" + valueName + '\'' +
                '}';
    }

    public int compareTo(EntityNumber entityNumber) {
        if (getValue() == entityNumber.getValue())
            return 0;
        else if (getValue() >= entityNumber.getValue())
            return 1;
        else
            return 0;
    }
}
