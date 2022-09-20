package utilities;

import java.util.Date;

public class TimeStampUtil {

    public static void timeStamp(){
        Date date = new Date();
        date.toString().replace(":","_").replace(" ","_");
    }
}
