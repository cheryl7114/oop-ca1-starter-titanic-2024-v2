package org.example;
// CA1
import java.io. * ;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String fileName = "titanic-data-100.csv"; // file should be in the project folder (below pom.xml)

        ArrayList<Passenger> passengerList= new ArrayList<>();

        loadPassengerDataFromFile( passengerList, fileName);

        displayAllPassengers( passengerList );


        // Assignment: Implement and test the following methods.
        // See the description of each method in the CA1 Specification PDF file from Moodle

        System.out.println("\n1. " + Arrays.toString(getPassengerNames(passengerList)));
        System.out.println("\n2. " + getPassengersContainingName(passengerList,"William"));
        System.out.println("\n3. " + getPassengersOlderThan(passengerList,70));
        System.out.println("\n4. " + countPassengersByGender(passengerList,"male"));
        System.out.println("\n5. " + sumFares(passengerList));
        System.out.println("\n6. " + Arrays.toString(maleSurvivors(passengerList)));
        System.out.println("\n7. " + ticketOwner(passengerList,"A/4. 39886"));
        System.out.println("\n8. " + averageAge(passengerList));
        System.out.println("\n9. " + getPassengersByTicketClass(passengerList,PassengerClass.FIRST));
        System.out.println("\n10. " + sortPassengersByPassengerId(passengerList));
        System.out.println("\n11. " + sortPassengersByName(passengerList));
        System.out.println("\n12. " + sortPassengersByAgeThenName(passengerList));
        System.out.println("\n13. " + sortPassengersByGenderThenPassengerNumber(passengerList));
        System.out.println("\n14. " + sortPassengersByFareThenSurvival(passengerList));
        System.out.println("\n15. " + sortPassengersByTicketClass(passengerList));
        System.out.println("\n16. " + sortPassengersByAge(passengerList));
        System.out.println("\n17. " + sortPassengersByTicketNumberLambda(passengerList));
        System.out.println("\n18. " + sortPassengersByTicketNumberStatic(passengerList));
        System.out.println("\n19. " + findPassengerByTicketNumber(passengerList,"STON/O2. 3101282"));
        System.out.println("\n20. " + findPassengerByPassengerId(passengerList,"100"));

        System.out.println("Finished, Goodbye!");
    }

    /**
     * Populate an ArrayList of Passenger objects with data from a text file
     * @param passengerList - an ArrayList to be filled with Passenger objects
     * @param fileName - name of CSV text file containing passenger details
     */
    public static void loadPassengerDataFromFile( ArrayList<Passenger> passengerList, String fileName) {

        // Format of each row of data is:
        // Name,Age,Height(m),GPA  - these heading names are included as the first row in file
        // John Malone,20,1.78,3.55   for example

        // Use a Regular Expression to set both comma and newline as delimiters.
        //  sc.useDelimiter("[,\\r\\n]+");
        // Text files in windows have lines ending with "CR-LF" or "\r\n" sequences.
        // or may have only a newline - "\n"
        // Windows uses CRLF (\r\n, 0D 0A) line endings while Unix just uses LF (\n, 0A).
        //
        try (Scanner sc = new Scanner(new File(fileName))
                .useDelimiter("[,\\r\\n]+"))
        {

            // skip past the first line, as it has field names (not data)
            if(sc.hasNextLine())
                sc.nextLine();   // read the header line containing column titles, but don't use it

            // while there is a next token to read....
            System.out.println("Go...");

            while (sc.hasNext())
            {
                String passengerId = sc.next();    // read passenger ID
                int survived = sc.nextInt();     // 0=false, 1=true
                int passengerClass = sc.nextInt();  // passenger class, 1=1st, 2=2nd or 3rd
                String name = sc.next();
                String gender = sc.next();
                int age = sc.nextInt();
                int siblingsAndSpouses = sc.nextInt();
                int parentsAndChildren = sc.nextInt();
                String ticketNumber = sc.next();
                double fare = sc.nextDouble();
                String cabin = sc.next();
                String embarkedAt = sc.next();

                System.out.println(passengerId +", " + name);

                passengerList.add(
                        new Passenger( passengerId, survived, passengerClass,
                                name, gender, age, siblingsAndSpouses,parentsAndChildren,ticketNumber,
                                fare, cabin, embarkedAt)
                );
            }
        } catch (FileNotFoundException exception)
        {
            System.out.println("FileNotFoundException caught. The file " +fileName+ " may not exist." + exception);
        }
    }

    public static void displayAllPassengers( ArrayList<Passenger> passengerList ) {
        System.out.println("Displaying all passengers:");
        for( Passenger passenger : passengerList)
        {
            System.out.println(passenger);
        }
    }

    public static String[] getPassengerNames(ArrayList<Passenger> passengerList) {
        String [] passengerArray = new String[passengerList.size()];
        for(int i=0;i<passengerList.size();i++) {
            Passenger passenger = passengerList.get(i);
            passengerArray[i] = passenger.getName();
        }
        return passengerArray;
    }

    public static ArrayList<Passenger> getPassengersContainingName(ArrayList<Passenger> passengerList, String name) {
        ArrayList<Passenger> containsNameList = new ArrayList<>();
        for(Passenger passenger : passengerList) {
            if (passenger.getName().contains(name)) {
                containsNameList.add(passenger);
            }
        }

        return containsNameList;
    }

    public static ArrayList<Passenger> getPassengersOlderThan(ArrayList<Passenger> passengerList, int age) {
        ArrayList<Passenger> containsAgeList = new ArrayList<>();
        for(Passenger passenger : passengerList) {
            if (passenger.getAge()>age) {
                containsAgeList.add(passenger);
            }
        }
        return containsAgeList;
    }

    public static ArrayList<Passenger> countPassengersByGender(ArrayList<Passenger> passengerList, String gender) {
        ArrayList<Passenger> genderList = new ArrayList<>();
        for(Passenger passenger : passengerList) {
            if ((passenger.getGender()).equals(gender)) {
                genderList.add(passenger);
            }
        }
        return genderList;
    }

    public static double sumFares(ArrayList<Passenger> passengerList) {
        double total = 0;
        for(Passenger passenger : passengerList) {
            total += passenger.getFare();
        }
        return total;
    }

    public static String[] maleSurvivors(ArrayList<Passenger> passengerList) {
        ArrayList<String> maleSurvivedList = new ArrayList<>();
        for(Passenger passenger : passengerList) {
            if ((passenger.getGender()).equalsIgnoreCase("male") && passenger.getSurvived()==1) {
                maleSurvivedList.add(passenger.getName());
            }
        }

        return maleSurvivedList.toArray(new String[0]);
    }

    public static Passenger ticketOwner(ArrayList<Passenger> passengerList, String ticketNo) {
        for(Passenger passenger : passengerList) {
            if (passenger.getTicketNumber().equals(ticketNo)) {
                return passenger;
            }
        }
        return null;
    }

    public static double averageAge(ArrayList<Passenger> passengerList) {
        double total = 0;
        for(Passenger passenger : passengerList) {
            total += passenger.getAge();
        }
        return total/passengerList.size();
    }

    public static ArrayList<Passenger> getPassengersByTicketClass(ArrayList<Passenger> passengerList, PassengerClass passengerClass) {
        ArrayList<Passenger> ticketClassList = new ArrayList<>();
        for(Passenger passenger : passengerList) {
            if (passenger.getPassengerClass()==passengerClass) {
                ticketClassList.add(passenger);
            }
        }
        return ticketClassList;
    }

    public static ArrayList<Passenger> sortPassengersByPassengerId(ArrayList<Passenger> passengerList) {
        ArrayList<Passenger> sortedByID = new ArrayList<>(passengerList);
        Collections.sort(sortedByID);
        return sortedByID;
    }

    public static ArrayList<Passenger> sortPassengersByName(ArrayList<Passenger> passengerList) {
        ArrayList<Passenger> sortedByName = new ArrayList<>(passengerList);
        sortedByName.sort(Comparator.comparing(Passenger::getName)); //sortedByName.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        return sortedByName;
    }

    public static ArrayList<Passenger> sortPassengersByAgeThenName(ArrayList<Passenger> passengerList) {
//        Collections.sort(passengerList, new AgeThenNameComparator());
        passengerList.sort(new AgeThenNameComparator());
        return passengerList;
    }

    public static ArrayList<Passenger> sortPassengersByGenderThenPassengerNumber(ArrayList<Passenger> passengerList) {
        passengerList.sort(new GenderThenPassengerNumComparator());
        return passengerList;
    }

    public static ArrayList<Passenger> sortPassengersByFareThenSurvival(ArrayList<Passenger> passengerList) {
        passengerList.sort(new FareThenSurvivalComparator());
        return passengerList;
    }

    public static ArrayList<Passenger> sortPassengersByTicketClass(ArrayList<Passenger> passengerList) {
        passengerList.sort(new TicketClassComparator());
        return passengerList;
    }

    public static ArrayList<Passenger> sortPassengersByAge(ArrayList<Passenger> passengerList) {
        passengerList.sort(new Comparator<Passenger>() {
            @Override
            public int compare(Passenger p1, Passenger p2) {
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        });
        return passengerList;
    }

    public static ArrayList<Passenger> sortPassengersByTicketNumberLambda(ArrayList<Passenger> passengerList) {
        passengerList.sort((p1, p2) -> p1.getTicketNumber().compareTo(p2.getTicketNumber()));
        return passengerList;
    }

    public static ArrayList<Passenger> sortPassengersByTicketNumberStatic(ArrayList<Passenger> passengerList) {
//        passengerList.sort(Passenger.ticketNumberComparator);
        Collections.sort(passengerList, Passenger.ticketNumberComparator);

        return passengerList;
    }

    public static Passenger findPassengerByTicketNumber(ArrayList<Passenger> passengerList, String ticketNumber) {
        Collections.sort(passengerList, Passenger.ticketNumberComparator);

        // create a key with only the ticketNumber set for binary search comparison
        Passenger keyToFind = new Passenger();
        keyToFind.setTicketNumber(ticketNumber);

        // use binary search to find the index of the passenger with the matching ticket number
        int index = Collections.binarySearch(passengerList, keyToFind, Comparator.comparing(Passenger::getTicketNumber));

        if (index >= 0) {
            return passengerList.get(index); // found passenger
        } else {
            return null;
        }
    }

    public static Passenger findPassengerByPassengerId(ArrayList<Passenger> passengerList, String passID) {
        Collections.sort(passengerList);

        // create a key with only the passengerID set for binary search comparison
        Passenger keyToFind = new Passenger();
        keyToFind.setPassengerId(passID);

        // use binary search to find the index of the passenger with the matching passengerID
        int index = Collections.binarySearch(passengerList, keyToFind, Comparator.comparingInt(p -> Integer.parseInt(p.getPassengerId())));

        if (index >= 0) {
            return passengerList.get(index); // found passenger
        } else {
            return null;
        }
    }

}