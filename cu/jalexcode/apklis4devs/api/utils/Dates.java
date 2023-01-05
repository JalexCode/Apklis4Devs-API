package cu.jalexcode.apklis4devs.api.utils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dates {
    public static final String FORMAT = "(?<anno>\\d{4})-(?<mes>\\d{2})-(?<dia>\\d{2})T\\d{2}:\\d{2}:\\d{2}(\\.\\d{6})?-(\\d{2}:\\d{2})?";

    public static Date parse(String val){
        if(!val.matches(FORMAT)){
            return null;
        }

        Matcher m = Pattern.compile(FORMAT).matcher(val);
        while(m.find()){
            String ann = m.group(1),
                    mes = m.group(2),
                    dia = m.group(3);
            return new Date(Integer.parseInt(ann),
                    Integer.parseInt(mes),
                    Integer.parseInt(dia));
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    public static String format(Date date){
        return (date.getYear()+1900)+"-"+zero(date.getMonth())+"-"+zero(date.getDay())+"T"+zero(date.getHours())+":"+zero(date.getMinutes())+":"+zero(date.getSeconds());
    }

    public static String zero(int m){
        if(m>9){
            return m+"";
        }
        return "0"+m;
    }
}
