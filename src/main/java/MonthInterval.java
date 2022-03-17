import java.time.*;
import java.util.*;

public class MonthInterval {
    protected LocalDate date1;
    protected LocalDate date2;
    protected static final String[] mon = {
            // this empty spot is here so i can use index to locate the value of the Month
            "",
            "January".toUpperCase(),
            "February".toUpperCase(),
            "March".toUpperCase(),
            "April".toUpperCase(),
            "May".toUpperCase(),
            "June".toUpperCase(),
            "July".toUpperCase(),
            "August".toUpperCase(),
            "September".toUpperCase(),
            "October".toUpperCase(),
            "November".toUpperCase(),
            "December".toUpperCase()
    };

    /**
     * Default Constructor
     */
    public MonthInterval() {
    }

    /**
     * This Method is used to fetch CLI inputs
     */
    public void getInputs() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Date Format: MM/DD/YYY");
        System.out.println("Input Date 1 :");
        String input1 = scan.next();
        String[] splitString1 = input1.split("/");
        validateDatesAreValid(splitString1);
        date1 = LocalDate.of(Integer.valueOf(splitString1[2]),
                Month.valueOf(mon[Integer.parseInt(splitString1[1])]),
                Integer.valueOf(splitString1[0]));

        System.out.print("Input Date 2: ");
        String input2 = scan.next();
        String[] splitString2 = input2.split("/");
        validateDatesAreValid(splitString2);
        date2 = LocalDate.of(Integer.valueOf(splitString2[2]),
                Month.valueOf(mon[Integer.parseInt(splitString2[1])]),
                Integer.valueOf(splitString2[0]));

        validateDate1isBeforeDate2();
    }

    /**
     * This method is used to determine the months in between 2 dates
     *
     * @param date1
     * @param date2
     * @return a list of months formatted as Strings
     */
    public List<String> generateMonthsInInterval(LocalDate date1, LocalDate date2) {
        List<String> rangeOfMonths = new ArrayList<>();
        Period diff = Period.between(date1, date2);
        int monthsDiff = diff.getMonths();
        int yearsDiff = diff.getYears();

        if (yearsDiff > 0) {
            monthsDiff = (yearsDiff * 12) + monthsDiff;
        }

        int currMonth = date1.getMonthValue();
        int currYear = date1.getYear();
        int monthCounter = 1;

        while (monthCounter <= monthsDiff + 1) {
            rangeOfMonths.add(mon[currMonth] + " " + Integer.valueOf(currYear));
            currMonth++;
            monthCounter++;
            if (currMonth == 12) {
                rangeOfMonths.add(mon[currMonth] + " " + Integer.valueOf(currYear));
                currMonth = 1;
                currYear++;
                monthCounter++;
            }
        }
        return rangeOfMonths;
    }

    /**
     * Validate that the Date1 is before Date 2
     */
    public void validateDate1isBeforeDate2() {
        if (date1.isAfter(date2)) {
            System.out.println("Date 1 is After Date 2 : Not a Valid Input");
            System.exit(-1);
        }
    }

    /**
     * Validate that dates are valid and correct
     *
     * @param dateArray
     */
    public void validateDatesAreValid(String[] dateArray) {
        String month = dateArray[0];
        String day = dateArray[1];
        String year = dateArray[2];
        HashMap<Integer, Integer> monthDayMap = new HashMap<>();

        monthDayMap.put(4, 30);
        monthDayMap.put(6, 30);
        monthDayMap.put(9, 30);
        monthDayMap.put(11, 30);
        monthDayMap.put(1, 31);
        monthDayMap.put(3, 31);
        monthDayMap.put(5, 31);
        monthDayMap.put(7, 31);
        monthDayMap.put(8, 31);
        monthDayMap.put(10, 31);
        monthDayMap.put(12, 31);
        monthDayMap.put(2, isLeapYear(year) ? 29 : 28);

        boolean error = false;

        while (!error) {
            if (month.length() != 2) {
                System.out.println("Month Format incorrect");
                error = true;
            }
            if (Integer.valueOf(month) < 1 || Integer.valueOf(month) > 12) {
                System.out.println("Month is out of range");
                error = true;
            }
            if (year.length() != 4) {
                System.out.println("Year format is incorrect");
                error = true;
            }
            if (day.length() != 2) {
                System.out.println("Day format is incorrect");
                error = true;
            }

            if (Integer.valueOf(day) < 0 || Integer.valueOf(day) > (Integer) monthDayMap.get(Integer.valueOf(month))) {
                System.out.println(("Day is out of range"));
                error = true;
            }
        }

        if (error) {
            System.exit(-1);
        }
    }

    /**
     * This method is used to determine if a year is a leap year.
     *
     * @param year
     * @return
     */
    public boolean isLeapYear(String year) {
        if (((Integer.valueOf(year) % 4 == 0) && (Integer.valueOf(year) % 100 != 0)) || (Integer.valueOf(year) % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        MonthInterval monthInterval = new MonthInterval();
        monthInterval.getInputs();
        System.out.println("Months in Range: ");
        System.out.println(monthInterval.generateMonthsInInterval(monthInterval.date1, monthInterval.date2));
    }

}
