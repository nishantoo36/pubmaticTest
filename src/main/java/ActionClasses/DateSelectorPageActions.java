package ActionClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import utility.DateFun;
import utility.SeleniumUtility;

import java.text.ParseException;
import java.util.*;

public class DateSelectorPageActions extends SeleniumUtility {

    public DateSelectorPageActions(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "FromSector_show")
    WebElement fromSelector;

    @FindBy(xpath = "//div[@class='tp-cit']//li")
    WebElement firstCityFromList;

    @FindBy(id = "ddate")
    WebElement departureDate;

    @FindBy(xpath = "//div[@id='error']/p")
    WebElement errorMessage;

    @FindBy(id = "img2Nex")
    WebElement nextMonthSelector;

    @FindBy(id = "img2Prv")
    WebElement preMonthSelector;

    @FindBy(xpath = "//div[@class='month']/child::div[@class='month2']")
    WebElement monthSelector;

    @FindAll(value = {@FindBy(xpath = "//div[@class='box']/descendant::li[@style='visibility:show' and @class!='old-dt']")})
    List<WebElement> daysNumberInFromMonth;

    @FindAll(value = {@FindBy(xpath = "//div[@class='box']/descendant::li[@style='visibility:show']/child::span")})
    List<WebElement> daysRateInFromMonth;

    int catchTry = 0;
    public static int minimumRate;
    public static List<String> dates;

    public void selectFirstDefaultCity() {
        clickOnElement(fromSelector, 10);
        clickOnElement(firstCityFromList, 5);
    }

    public void selectDepartureDateField() {
        clickOnElement(departureDate, 5);
    }

    public List<String> lowestRateDateSuggestion(String date) throws ParseException, InterruptedException {
        List<String> lowestDate = new ArrayList<>();
        String dayVal = DateFun.getDateInFormate("dd", date);
        if (dayVal.startsWith("0")) {
            dayVal = dayVal.replace("0", "");
        }
        setMonthForEnteredDate(date);
        int  actRate = Integer.parseInt(getRateForDate(dayVal));
        minimumRate = getMinRate(getAllDaysAndRates());
        if (actRate == minimumRate) {
            lowestDate.add(date);
            return lowestDate;
        } else {
            HashMap<Integer, Integer> dayAndVal = new HashMap<>();
            dayAndVal.putAll(getAllDaysAndRates());
            List<Integer> dateVal = getNearestLowRateDays(getAllDayWithLeastValue(dayAndVal), Integer.parseInt(dayVal));
            for (int i : dateVal) {
                lowestDate.add((i) + "/" + DateFun.getDateInFormate("MM/yyyy", date));
            }
            return lowestDate;
        }
    }

    public int getMinRate(HashMap<Integer, Integer> dayAndRate) {
        return Collections.min(dayAndRate.values());
    }

//------------Code to set login of get lowest rate date---------//

    private String getRateForDate(String day) throws InterruptedException {
        Thread.sleep(2000);
        List<String> daysValues = getAllElementsTextFromElementList(daysNumberInFromMonth, 30);
        try {
            for (String val : daysValues) {
                if (val.contains(day + "\n")) {
                    return val.split("\n")[1];
                }
            }
            throw new RuntimeException(day + " is not available in list So retry for few times");
        } catch (Exception e) {
            if (catchTry < 10) {
                getRateForDate(day);
                catchTry = catchTry + 1;
            }
        }
        throw new RuntimeException(day + " is not available in list");
    }

    private HashMap<Integer, Integer> getAllDaysAndRates() throws InterruptedException {
        HashMap<Integer, Integer> dayAndRate = new HashMap<>();
        List<String> daysValues = getAllElementsTextFromElementList(daysNumberInFromMonth, 20);
        for (String val : daysValues) {
            dayAndRate.put(Integer.parseInt(val.split("\n")[0]), Integer.parseInt(val.split("\n")[1]));
        }
        return dayAndRate;
    }


    private List<Integer> getAllDayWithLeastValue(HashMap<Integer, Integer> dayAndRate) {
        int val = getMinRate(dayAndRate);
        List<Integer> days = new ArrayList<>();
        for (HashMap.Entry<Integer, Integer> set : dayAndRate.entrySet()) {
            if (set.getValue() == val) {
                days.add(set.getKey());
            }
        }
        return days;
    }

    private List<Integer> getNearestLowRateDays(List<Integer> val, int day) {
        int temp = 1;
        List<Integer> nearestVal = new ArrayList<>();

        nearestVal.add(val.stream()
                .min(Comparator.comparingInt(i -> Math.abs(i - day)))
                .orElseThrow(() -> new NoSuchElementException("No value present")));

        int diff = nearestVal.get(0) - day;

        if (diff > 0) {
            if (val.contains(day - diff)) {
                nearestVal.add(day - diff);
            }
        } else {
            if (val.contains(day + Math.abs(diff))) {
                nearestVal.add(day + Math.abs(diff));
            }
        }
        return nearestVal;
    }


    private void setMonthForEnteredDate(String date) throws ParseException {
        String expectedMonthYear = DateFun.getDateInFormate("MMM yyyy", date);
        String actualMonthYear = getText(monthSelector, 2);
        int val = DateFun.compareDate("MMM yyyy", expectedMonthYear, actualMonthYear);
        if (val == 0) {
            return;
        } else if (val < 0) {
            clickOnElement(preMonthSelector, 3);
            setMonthForEnteredDate(date);
        } else {
            clickOnElement(nextMonthSelector, 3);
            setMonthForEnteredDate(date);
        }
    }

}

