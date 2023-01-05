package cu.jalexcode.apklis4devs.api.model;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Clase que contiene la informacion de una categoria, como el titulo, icono, etc
 */
public final class Category implements Serializable{
    private String name, iconURL;
    private int id;
    private Group group;

    /**Interno*/
    public Category(String json) throws Exception{
        this(new JSONObject(json));
    }

    /**Interno*/
    public Category(JSONObject obj) throws Exception{
        name=obj.getString("name");
        id=obj.getInt("id");
        group= Group.valueOf(obj.getString("group"));
        iconURL=obj.getString("icon_url");
    }

    /**
     * Devuelve el nombre de esta categoria
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve la url del icono de esta categoria
     * @return
     */
    public String getIconURL() {
        return iconURL;
    }

    /**
     * Devuelve el id de esta categoria. INTERNO
     */
    public int getId() {
        return id;
    }

    /**
     * Devuelve el grupo de esta categoria, juegos o apps
     */
    public Group getGroup() {
        return group;
    }

    public String toString() {
        return name;
    }

    /**
     * Grupo de las categorias
     */
    public static enum Group implements Serializable{
        Applications,
        Games;

        public String toString() {
            switch(this){
                case Applications:
                    return "Aplicaciones";
                case Games:
                    return "Juegos";
            }
            return name();
        }
    }
}
