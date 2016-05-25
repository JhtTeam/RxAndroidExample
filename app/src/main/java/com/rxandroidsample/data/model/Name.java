package com.rxandroidsample.data.model;

import io.realm.RealmObject;

/**
 * Created by anhndt on 5/25/16.
 */
public class Name{
    public String first;
    public String last;

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;
        if (first != null ? !first.equals(name.first) : name.first != null) return false;
        return !(last != null ? !last.equals(name.last) : name.last != null);
    }

    @Override
    public String toString() {
        return first + " " + last;
    }
}
