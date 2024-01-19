import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class DatesPlayground {
    public static void main(String[] args) throws ParseException {

        // Old way to deal with dates
        Date date = new Date();
        System.out.println("Date printed is "+date);
        System.out.println("Epoch Time: "+date.getTime());

        //If you get a String as a date, use this method to convert to Date Object
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Singapore")));
        Date parsedDate = simpleDateFormat.parse("2023-01-29");
        System.out.println("Parsed date from String is "+parsedDate);

        // Instant deals with UTC by default
        Instant now = Instant.now();
        Instant fiveMinutesAgo = now.minusSeconds(300);
        System.out.println(" Now Instant is "+now+ ", and fiveMinutesAgo is "+fiveMinutesAgo);
        ZonedDateTime zonedDateTime = fiveMinutesAgo.atZone(ZoneId.systemDefault());
        System.out.println("Five minutes ago time zone is "+zonedDateTime);

//        // Latest way to deal with dates using LocalDate and LocalDateTime
//        LocalDate nowLD = LocalDate.now();
//        System.out.println("Local Date Now is "+nowLD+" and 3 years ago, it was "+nowLD.minusMonths(36));
//
//        LocalDateTime nowTime = LocalDateTime.now();
//        System.out.println("Local Date Time is "+nowTime+", and 45 minutes ago, it was "+nowTime.minusMinutes(45));
//
//        LocalDate parsedStringToDate = LocalDate.parse("2023-02-25", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        System.out.println("parsedStringToDate is "+parsedStringToDate+" and 3 months earlier on the String parsed date is "+parsedStringToDate.minusMonths(3));


        System.out.println(" ");
        LocalDate localDateNow = LocalDate.now();       //used to get teh current date
        System.out.println("Local Date is : "+localDateNow);
        System.out.println("three months ago the date is : "+localDateNow.minusMonths(3));
        System.out.println("67 Days after the date is : "+localDateNow.plusDays(67));

        System.out.println(" ");
        LocalDateTime localDateTime =LocalDateTime.now();
        System.out.println("Time now is : "+localDateTime);
        System.out.println("Time 85 mins ago is :"+localDateTime.minusMinutes(85));
        System.out.println("Time 1065 seconds after is : "+localDateTime.plusSeconds(1065));


        LocalDate partialLocalDate = LocalDate.parse("2023-03-16",DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("Generated Date is "+partialLocalDate+" and the date generated after that is :"+partialLocalDate.minusMonths(3));
    }
}
