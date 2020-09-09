package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFun {

    public static String getDateInFormate(String format,String date) throws ParseException {
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date1);
    }

    public static int compareDate(String format,String date1, String date2) throws ParseException {
        Date dateFirst=new SimpleDateFormat(format).parse(date1);
        Date dateSecond=new SimpleDateFormat(format).parse(date2);
        return dateFirst.compareTo(dateSecond);
    }

}
