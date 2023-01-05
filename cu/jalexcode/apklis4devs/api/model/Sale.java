package cu.jalexcode.apklis4devs.api.model;

import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sale implements Serializable{
    private int sales, ammount;
    private Date date;

    /**Interno*/
    public Sale(String json) throws Exception{
        this(new JSONObject(json));
    }

    /**Interno*/
    public Sale(JSONObject obj) throws Exception{
        this.sales=obj.getInt("sales");
        this.ammount=obj.getInt("ammount");
        final String pubTmp = obj.getString("day");
        this.date= new SimpleDateFormat("yyyy-MM-dd").parse(pubTmp.trim());
    }

    /**Cantidad de ventas*/
    public int getSales() {
        return sales;
    }

    /**
     * Dinero acumulado en el dia
     */
    public int getAmmount() {
        return ammount;
    }

    /**
     * Obtiene la fecha de venta
     */
    public Date getDate() {
        return date;
    }
}
