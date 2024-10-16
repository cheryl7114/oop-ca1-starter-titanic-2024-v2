package org.example;

import java.util.Comparator;

public class FareThenSurvivalComparator implements Comparator<Passenger> {
    public int compare(Passenger p1, Passenger p2) {
        int fareComparison = Double.compare(p1.getFare(), p2.getFare());
        // If fares are the same, compare by survival
        if (fareComparison == 0) {
            return Integer.compare(p1.getSurvived(),p2.getSurvived());
        }
        return fareComparison;
    }
}
