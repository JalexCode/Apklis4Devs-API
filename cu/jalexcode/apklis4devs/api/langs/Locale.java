package cu.jalexcode.apklis4devs.api.langs;


import java.io.Serializable;
import java.util.Properties;
import java.util.ResourceBundle;

public final class Locale implements Serializable {
    public static final Properties LANG = get();

    static final Properties get(){
        Properties p = new Properties();
        p.setProperty("this_release_already_exists_for_the_application", "Ya existe esta Version de la aplicacion");
        //TODO: Completar Lista De Traducciones
        return p;
    }

    public static final String translate(String s){
        return LANG.getProperty(process(s), s);
    }

    static String process(String s){
        return s.toLowerCase().replace(' ', '_').trim();
    }
}
