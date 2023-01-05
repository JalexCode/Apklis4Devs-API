package cu.jalexcode.apklis4devs.api;

import java.util.Scanner;

import cu.jalexcode.apklis4devs.api.model.Application;
import cu.jalexcode.apklis4devs.api.model.Notification;

/**
 * Clase que muestra ejemplos de uso de esta api, recuerda que casi toda esta api es experimental asi que algunas partes pueden funcionar de forma inesperada, Disfrutalo!
 */
public class Examples {
    static String user = "Tu usuario",
                pass = "Tu contrasenna";
    /**
     * Ejemplo de uso del login
     */
    public static final void simpleLogin() throws Exception{
        Scanner sc = new Scanner(System.in);
        String user = null,
                pass = null;

        System.out.print("Introduzca su nombre de usuario: ");
        user=sc.nextLine();
        System.out.print("Introduzca su contraseña: ");
        pass=sc.nextLine();

        try{
            Session s = new Session(user, pass);
            //ALERTA: en android necesitaras llamar este metodo en un hilo aparte, porque
            //realiza tareas de red y obtendrias un: NetworkOnMainThreadException
            s.updateCache();
            System.out.println("Hola "+s.getUserInfo().getName()+"!");
        }catch(Throwable e){
            System.err.println("Error accediendo: "+e);
        }
    }

    /**
     * Ejemplo de uso de obtener tus apps
     */
    public static final void getApps(){

        try{
            Session s = new Session(user, pass);
            //ALERTA: en android necesitaras llamar este metodo en un hilo aparte, porque
            //realiza tareas de red y obtendrias un: NetworkOnMainThreadException
            s.updateCache();
            System.out.println("Buscando tus apps, "+s.getUserInfo().getName()+"...");
            int pos = 0;
            for (Application app:
                s.getOwnApps()){
                System.out.println("App #"+(++pos)+": "+app.getName()+", con un promedio de "+app.getRating()+" estrellas");
            }
        }catch(Throwable e){
            System.err.println("Error accediendo: "+e);
        }
    }

    public static void main(String[] args) throws Exception {
        try{
            Session s = new Session(user, pass);
            //ALERTA: en android necesitaras llamar este metodo en un hilo aparte, porque
            //realiza tareas de red y obtendrias un: NetworkOnMainThreadException
            s.updateCache();
//            Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-01-04 16:30:00");
//            Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-11-01 00:30:00");
//            for (ReportResult report:
//                    s.getSalesReport(date1, date2, 10, s.getUserInfo().getPhone())){
//                System.out.println(report.getBuyer());
//            }
//            UserInfo userInfo = s.resolveAndGetUserInfo();
            for (Notification n:s.getNotifications()){
                System.out.println(n.getMessage());
            }

        }catch(Throwable e){
            System.err.println("Error accediendo: "+e);
        }
    }
}
