import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MonthIntervalTest {
    @Test
    void testGenerateMonthsInIntervalOneMonth() {
        MonthInterval monthInterval = new MonthInterval();
        LocalDate date1 = LocalDate.of(2022, 01, 01);
        LocalDate date2 = LocalDate.of(2022, 02, 01);
        List<String> results = monthInterval.generateMonthsInInterval(date1, date2);
        List<String> expectedResults = new ArrayList<String>();
        expectedResults.add("JANUARY 2022");
        assertEquals(results,expectedResults);
    }

    @Test
    void testGenerateMonthsInIntervalOneYear(){
        MonthInterval monthInterval = new MonthInterval();
        LocalDate date1 = LocalDate.of(2022, 01, 01);
        LocalDate date2 = LocalDate.of(2023, 01, 01);
        List<String> results = monthInterval.generateMonthsInInterval(date1, date2);
        List<String> expectedResults = new ArrayList<String>();
        expectedResults.add("JANUARY 2022");
        expectedResults.add("FEBRUARY 2022");
        expectedResults.add("MARCH 2022");
        expectedResults.add("APRIL 2022");
        expectedResults.add("MAY 2022");
        expectedResults.add("JUNE 2022");
        expectedResults.add("JULY 2022");
        expectedResults.add("AUGUST 2022");
        expectedResults.add("SEPTEMBER 2022");
        expectedResults.add("OCTOBER 2022");
        expectedResults.add("NOVEMBER 2022");
        expectedResults.add("DECEMBER 2022");
        expectedResults.add("JANUARY 2023");
        assertEquals(results,expectedResults);
    }

}
