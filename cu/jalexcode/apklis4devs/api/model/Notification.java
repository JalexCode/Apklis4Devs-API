package cu.jalexcode.apklis4devs.api.model;

import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notification implements Serializable{
    private boolean accept;
    private String message, name, packageName, type;
    private Date createdAt;

    /**Interno*/
    public Notification(String json) throws Exception{
        this(new JSONObject(json));
    }

    /**Interno*/
    public Notification(JSONObject obj) throws Exception{
        this.accept=obj.getBoolean("accept");
        //
        this.message=obj.getString("message");
        this.name=obj.getString("name");
        this.packageName=obj.getString("package_name");
        this.type=obj.getString("type");
        //
        final String pubTmp = obj.getString("created_at");
        this.createdAt= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(pubTmp.trim());
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
