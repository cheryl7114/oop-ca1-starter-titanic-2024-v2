
package org.example;

import java.util.Comparator;

public class GenderThenPassengerNumComparator implements Comparator<Passenger> {
    public int compare(Passenger p1, Passenger p2) {
        int genderComparison = p1.getGender().compareTo(p2.getGender());
        // If genders are the same, compare by passenger ID
        if (genderComparison == 0) {
            return p1.compareTo(p2);
        }
        return genderComparison;
    }
}
