package cu.jalexcode.apklis4devs.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cu.jalexcode.apklis4devs.api.model.*;
import cu.jalexcode.apklis4devs.api.utils.Constants;

/**
 * Clase que muestra ejemplos de uso de esta api, recuerda que casi toda esta api es experimental asi que algunas partes pueden funcionar de forma inesperada, Disfrutalo!
 * user = "Tu usuario",
 *             pass = "Tu contrasenna",
 */
public class Examples {
    static String user = "JavyIsCool01",
            pass = "Joker991030*",
            dateFormmat = "yyyy-MM-dd HH:mm:ss",
            initDate = "2022-01-01 00:00:00",
            finalDate = "2023-01-01 00:00:00";

    /**
     * Ejemplo de uso del login
     */
    public static final Session simpleLogin() throws Exception {
        Session s = null;
        try {
            s = new Session(user, pass);
            //ALERTA: en android necesitaras llamar este metodo en un hilo aparte, porque
            //realiza tareas de red y obtendrias un: NetworkOnMainThreadException
            s.updateCache();
            System.out.println("Hola " + s.getUserInfo().getName() + "!");
        } catch (Throwable e) {
            System.err.println("Error accediendo: " + e);
        }
        return s;
    }

    /**
     * Ejemplo de uso de obtener tus apps
     */
    public static final void getApps() {

        try {
            Session s = simpleLogin();
            int pos = 0;
            for (Application app :
                    s.getOwnApps()) {
                System.out.println("App #" + (++pos) + ": " + app.getName() + ", con un promedio de " + app.getRating() + " estrellas");
            }
        } catch (Throwable e) {
            System.err.println("Error accediendo: " + e);
        }
    }

    public static void getNotifications() throws Exception {
        Session s = simpleLogin();
        for (Notification n : s.getNotifications()) {
            System.out.println("Título: " + n.getName());
            System.out.println("Mensaje: " + n.getMessage());
        }
    }

    public static void getBuyers() throws Exception {
        Session s = simpleLogin();
        Date date1 = new SimpleDateFormat(dateFormmat).parse(finalDate);
        Date date2 = new SimpleDateFormat(dateFormmat).parse(initDate);
        for (ReportResult report :
                s.getSalesReport(date1, date2, 10, s.getUserInfo().getPhone())) {
            System.out.println("Comprador: " + report.getBuyer());
            System.out.println("Telef. Comprador: " + report.getBuyerPhone());
        }
    }

    public static void getDashboardStats() throws Exception {
        // No es necesario introducir credenciales para obtener los datos globales
        Session s = new Session();
        DashboardStats dashboardStats = s.getDashboardStats(true);
        System.out.println("Aplicaciones cubanas: " + dashboardStats.getCuban_apps());
    }

    public static void getUserInfo() throws Exception {
        Session s = simpleLogin();
        UserInfo userInfo = s.getUserInfo();
        System.out.println("Nombre completo: " + userInfo.getFullName());
    }

    public static void getSales() throws Exception {
        Session s = simpleLogin();
        //
        Date date1 = new SimpleDateFormat(dateFormmat).parse(finalDate);
        Date date2 = new SimpleDateFormat(dateFormmat).parse(initDate);
        //
        ArrayList<Sale> saleArrayList = s.getSales(date2, date1, s.getUserInfo().getPhone(), Constants.TYPE_DAY);
        for (Sale sale : saleArrayList){
            System.out.println("===============================");
            System.out.println("Fecha: " + new SimpleDateFormat(dateFormmat).format(sale.getDate()));
            System.out.println("Ventas: " + sale.getSales());
            System.out.println("Dinero recaudado: " + sale.getAmmount());
        }
    }

    public static void main(String[] args) throws Exception {
        getSales();
    }
}
