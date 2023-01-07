package cu.jalexcode.apklis4devs.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cu.jalexcode.apklis4devs.api.model.*;
import cu.jalexcode.apklis4devs.api.utils.Constants;

/**
 * Clase que muestra ejemplos de uso de esta api, recuerda que casi toda esta api es experimental asi que algunas partes pueden funcionar de forma inesperada, Disfrutalo!
 */
public class Examples {
    static String user = "TU USUARIO",
            pass = "TU CONTRASENNA",
            dateFormmat = "yyyy-MM-dd HH:mm:ss",
            initDate = "2022-01-01 00:00:00",
            finalDate = "2023-01-01 00:00:00";

    /**
     * Ejemplo de uso del login
     */
    public static final Session simpleLogin() throws Exception {
        Session s = null;
        try {
            System.out.println("===== Iniciando sesión... =====");
            s = new Session(user, pass);
            //ALERTA: en android necesitaras llamar este metodo en un hilo aparte, porque
            //realiza tareas de red y obtendrias un: NetworkOnMainThreadException
            s.updateCache();
            System.out.println("Hola " + s.getUserInfo().getName() + "[" + s.getUserInfo().getUser() + "]" + "!");
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
            System.out.println("= APLICACIONES =");
            int pos = 0;
            for (Application app :
                    s.getOwnApps()) {
                System.out.println("===============================");
                System.out.println("App #" + (++pos));
                System.out.println("Nombre: " + app.getName());
                System.out.println("Clasificación: " + app.getRating() + " estrellas");
                System.out.println("Cantidad de descargas: " + app.getDownloadCount());
            }
        } catch (Throwable e) {
            System.err.println("Error accediendo: " + e);
        }
    }

    public static void getNotifications() throws Exception {
        Session s = simpleLogin();
        System.out.println("= NOTIFICACIONES =");
        for (Notification n : s.getNotifications()) {
            System.out.println("===============================");
            System.out.println("Título: " + n.getName());
            System.out.println("Mensaje: " + n.getMessage());
        }
    }

    public static void getSalesReport() throws Exception {
        Session s = simpleLogin();
        Date date1 = new SimpleDateFormat(dateFormmat).parse(finalDate);
        Date date2 = new SimpleDateFormat(dateFormmat).parse(initDate);
        System.out.println("= REPORTE DE VENTAS =");
        for (ReportResult report :
                s.getSalesReport(date1, date2, 10, s.getUserInfo().getPhone())) {
            System.out.println("===============================");
            System.out.println("Comprador: " + report.getBuyer());
            System.out.println("Telef. Comprador: " + report.getBuyerPhone());
            System.out.println("Cantidad pagada: " + report.getAmmount());
            System.out.println("Estado: " + report.getState());
        }
    }

    public static void getDashboardStats() throws Exception {
        // No es necesario introducir credenciales para obtener los datos globales
        Session s = new Session();
        System.out.println("= ESTADISTICAS GLOBALES =");
        DashboardStats dashboardStats = s.getDashboardStats(true);
        System.out.println("Aplicaciones cubanas: " + dashboardStats.getCuban_apps());
        System.out.println("Cantidad de desarrolladores: " + dashboardStats.getDevelopers());
        System.out.println("Aplicaciones de pago: " + dashboardStats.getPaid_apps());
        System.out.println("Total de aplicaciones: " + dashboardStats.getApp());
        System.out.println("Cantidad de usuarios: " + dashboardStats.getUsers());
    }

    public static void getUserInfo() throws Exception {
        Session s = simpleLogin();
        System.out.println("= INFORMACION DEL USUARIO =");
        UserInfo userInfo = s.getUserInfo();
        System.out.println("Nombre completo: " + userInfo.getFullName());
        System.out.println("Cuenta de banco: " + userInfo.getSellerBankAccount());
    }

    public static void getSales() throws Exception {
        Session s = simpleLogin();
        //
        Date date1 = new SimpleDateFormat(dateFormmat).parse(finalDate);
        Date date2 = new SimpleDateFormat(dateFormmat).parse(initDate);
        //
        System.out.println("= VENTAS =");
        ArrayList<Sale> saleArrayList = s.getSales(date2, date1, s.getUserInfo().getPhone(), Constants.TYPE_DAY);
        for (Sale sale : saleArrayList){
            System.out.println("===============================");
            System.out.println("Fecha: " + new SimpleDateFormat(dateFormmat).format(sale.getDate()));
            System.out.println("Ventas: " + sale.getSales());
            System.out.println("Dinero recaudado: " + sale.getAmmount());
        }
    }

    public static void main(String[] args) throws Exception {
        // obtener las aplicaciones publicadas por ti
        getApps();
        // obtener tu informacion de usuario
        getUserInfo();
        // obtener ventas en un rang de fecha
        getSales();
        // obtener reporte de ventas
        getSalesReport();
        // obtener notificaciones
        getNotifications();
        // obtener datos globales de la plataforma
        getDashboardStats();
    }
}
