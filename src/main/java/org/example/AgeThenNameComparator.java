package org.example;

import java.util.Comparator;

public class AgeThenNameComparator implements Comparator<Passenger> {
    public int compare(Passenger p1, Passenger p2) {
        int ageComparison = Integer.compare(p1.getAge(), p2.getAge());
        // If ages are the same, compare by name
        if (ageComparison == 0) {
            return p1.getName().compareTo(p2.getName());
        }
        return ageComparison;
    }

}
