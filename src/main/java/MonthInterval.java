import java.time.*;
import java.util.*;

public class MonthInterval {
    protected LocalDate date1;
    protected LocalDate date2;
    protected static final String[] mon = {
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
    public MonthInterval(){
    }

    public static void main(String[] args){
        MonthInterval monthInterval = new MonthInterval();
        monthInterval.getInputs();
        System.out.println("Months in Range: ");
        System.out.println(monthInterval.generateMonthsInInterval(monthInterval.date1,monthInterval.date2));
    }

    /**
     * This Method is used to fetch CLI inputs
     */
    public void getInputs(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Input Date 1 :");
        String input1 = scan.next();
        String[] splitString1 = input1.split("/");
        date1 = LocalDate.of(Integer.valueOf(splitString1[2]),
                Month.valueOf(mon[Integer.parseInt(splitString1[1])]),
                Integer.valueOf(splitString1[0]));

        System.out.print("Input Date 2: ");
        String input2 = scan.next();
        String[] splitString2 = input2.split("/");
        date2 = LocalDate.of(Integer.valueOf(splitString2[2]),
                Month.valueOf(mon[Integer.parseInt(splitString2[1])]),
                Integer.valueOf(splitString2[0]));
    }

    /**
     * This method is used to determine the months in between 2 dates
     * @param date1
     * @param date2
     * @return a list of months formated as Strings
     */
    public List<String> generateMonthsInInterval(LocalDate date1, LocalDate date2){
        List<String> rangeOfMonths = new ArrayList<>();
        Period diff = Period.between(date1, date2);
        int monthsDiff = diff.getMonths();
        int yearsDiff = diff.getYears();

        if(yearsDiff > 0){
            monthsDiff = (yearsDiff * 12) + monthsDiff;
        }

        int currMonth = date1.getMonthValue();
        int currYear  = date1.getYear();
        int monthCounter  = 1;

        while(monthCounter <= monthsDiff + 1){
            rangeOfMonths.add(mon[currMonth] + " " + Integer.valueOf(currYear));
            currMonth++;
            monthCounter++;
            if(currMonth == 12){
                rangeOfMonths.add(mon[currMonth] + " " + Integer.valueOf(currYear));
                currMonth = 1;
                currYear++;
                monthCounter++;
            }
        }
        return rangeOfMonths;
    }
}




