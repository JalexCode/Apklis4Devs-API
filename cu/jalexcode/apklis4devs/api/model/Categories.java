package cu.jalexcode.apklis4devs.api.model;

import cu.jalexcode.apklis4devs.api.internal.Internal;
import cu.jalexcode.apklis4devs.api.utils.Constants;
import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;

import cu.jalexcode.apklis4devs.api.Session;
import okhttp3.Response;

/**
 * Clase que contiene las categorias (solo eso)
 */
public class Categories implements Serializable {
    private static Categories cached;
    
    private ArrayList<Category> categories;

    /**
     * Interno
     */
    public Categories(String json) throws Exception{
        this(new JSONArray(json));
    }

    /**
     * Interno
     */
    public Categories(JSONArray obj) throws Exception{
        categories=new ArrayList<>();
        for (int i = 0; i < obj.length(); i++) {
            categories.add(new Category(obj.getJSONObject(i)));
        }
    }

    /**
     * Interno
     */
    public Categories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    /**
     * Imprime en un texto todos las categorias .INTERNO
     */
    @Deprecated
    public String print(){
        String val = "";

        boolean st = true;
        for (Category cat: categories){
            if(st){
                st=false;
            }else val+=",";
            val+=cat.getName();
        }

        return val;
    }

    /**
     * Devuelve una lista de todas las categorias disponibles
     * @return
     */
    public ArrayList<Category> getCategories() {
        return categories;
    }

    /**
     * Lo mismo que {@link #getCategories()} pero en un array
     */
    public Category[] getCategoriesArray(){
        ArrayList<Category> cat = getCategories();
        return cat.toArray(new Category[cat.size()]);
    }

    /**
     * Devuelve una estancia de esta app. USA LA RED
     */
    public static Categories getInstance(){
        if(cached==null){
            resolveCache();
        }
        return cached;
    }
    
    static void resolveCache() {
        try {
            Response r = Internal.req(Constants.URL_API_CATEGORIES);
            if(!r.isSuccessful()) Session.CommonError.throwEx(r);
            cached=new Categories(r.body().string());
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }
}
