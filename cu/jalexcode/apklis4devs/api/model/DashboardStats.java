package cu.jalexcode.apklis4devs.api.model;

import java.io.Serializable;

import org.json.JSONObject;

/**
 * Estadisticas de la plataforma
 */
public final class DashboardStats implements Serializable{
    private int users, developers, app, paid_apps, cuban_apps;

    /**Interno*/
    public DashboardStats(JSONObject obj) throws Exception{
        users=obj.getInt("users");
        developers=obj.getInt("developers");
        app=obj.getInt("apps");
        paid_apps=obj.getInt("paid_apps");
        cuban_apps=obj.getInt("cuban_apps");
    }

    /**
     * Numero de usuarios registrados
     */
    public int getUsers() {
        return users;
    }

    /**
     * Numero de desarrolladores
     */
    public int getDevelopers() {
        return developers;
    }

    /**
     * Numero de apps
     */
    public int getApp() {
        return app;
    }

    /**
     * Numero de apps de pago
     */
    public int getPaid_apps() {
        return paid_apps;
    }

    /**
     * Numero de apps cubanas
     */
    public int getCuban_apps() {
        return cuban_apps;
    }
}
