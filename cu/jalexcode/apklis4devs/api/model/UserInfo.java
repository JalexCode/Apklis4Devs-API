package cu.jalexcode.apklis4devs.api.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import cu.jalexcode.apklis4devs.api.Session;
import cu.jalexcode.apklis4devs.api.utils.Province;

/**
 * Clase que contiene la informacion del usuario que accedio a la {@link Session}
 *
 * @see Session
 */
public final class UserInfo implements Serializable {
    private Session session;
    private int id;
    private String user, name, phone, email, avatar, last_name, sha1, fullName, regNumber, description, bussiness, cid, code;
    private boolean isDeveloper, isDeveloperActive, isSalesAdmin, isBetaTester, isActive, isStaff, isSuperUser;
    private Province province;
    private Date dateJoined;
    // SELLER
    private String sellerAccount, sellerAddress, sellerBankAccount, sellerCID, sellerEmail, sellerLicence, sellerMunicipality, sellerFullName, sellerNIT, sellerPhoneNumber, sellerProvince, sellerType, sellerUser;
    private int sellerBankBranch, sellerID;
    private Boolean sellerIsActive;
    private Date sellerJoined;

    /**
     * Interno
     */
    public UserInfo(Session s, String json) throws JSONException {
        this(s, new JSONObject(json));
    }

    /**
     * Interno
     */
    protected UserInfo(Session s, JSONObject obj) throws JSONException {
        this.session = s;
        this.id = obj.getInt("id");
        this.user = obj.getString("username");
        this.name = obj.getString("first_name");
        this.last_name = obj.getString("last_name");
        this.avatar = obj.getString("avatar");
        this.phone = obj.getString("phone_number");
        this.email = obj.getString("email");
        this.sha1 = obj.getString("sha1");
        this.isDeveloper = obj.getBoolean("is_developer");
        this.isDeveloperActive = obj.getBoolean("is_developer_active");
        this.isSalesAdmin = obj.getBoolean("sales_admin");
        this.isBetaTester = obj.getBoolean("beta_tester");
        this.province = Province.fromNumber(obj.getInt("province"));
        this.fullName = name + " " + last_name;
    }

    /**
     * Establece la informacion de desarrollador
     */
    public void setDevInfo(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        this.cid = obj.getString("cid");
        this.regNumber = obj.getString("reg_num");
        this.bussiness = obj.getString("bussiness");
        this.description = obj.getString("description");
    }

    /**
     * Establece la informacion del usuario Apklis
     */
    public void setApklisInfo(String json) throws JSONException, ParseException {
        JSONObject obj = new JSONObject(json);
        //
        this.code = obj.getString("code");
        this.isActive = obj.getBoolean("is_active");
        this.isStaff = obj.getBoolean("is_staff");
        this.isSuperUser = obj.getBoolean("is_superuser");
        //        this.avatar=obj.getString("avatar");
//        this.isBetaTester =obj.getBoolean("beta_tester");
        DateFormat df =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        TimeZone tz = TimeZone.getTimeZone("UTC");
        df.setTimeZone(tz);
        this.dateJoined = df.parse(obj.getString("date_joined"));
//        this.email=obj.getString("email");
//        this.name=obj.getString("first_name");
//        this.id= obj.getInt("id");
//        this.isDeveloper=obj.getBoolean("is_developer");
//        this.isDeveloperActive=obj.getBoolean("is_developer_active");
        //this.lastLogin=obj.getString("last_login");
//        this.user=obj.getString("username");
//        this.last_name=obj.getString("last_name");
//        this.phone=obj.getString("phone_number");
//        this.sha1=obj.getString("sha1");
        //this.isSalesAdmin =obj.getBoolean("sales_admin");
        //this.province=Province.fromNumber(obj.getInt("province"));
        //this.fullName=name+" "+last_name;
    }

    /**
     * Establece la informacion del vendedor
     */
    public void setSellerInfo(String json) throws JSONException, ParseException {
        JSONObject nativeJson = new JSONObject(json);
        JSONObject obj = nativeJson.getJSONObject("seller");
        this.sellerAccount = obj.getString("account");
        this.sellerIsActive = obj.getBoolean("active");
        this.sellerAddress = obj.getString("address");
        this.sellerBankBranch = obj.getInt("bank_branch");
        this.sellerCID = obj.getString("cid");
        this.sellerEmail = obj.getString("email");
        this.sellerBankAccount = obj.getString("bank_account");
        this.sellerJoined=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(obj.getString("joined"));
        this.sellerID = obj.getInt("id");
        this.sellerMunicipality = obj.getString("municipality");
        this.sellerFullName = obj.getString("name");
        this.sellerNIT = obj.getString("nit");
        this.sellerPhoneNumber = obj.getString("phone_number");
        this.sellerProvince = obj.getString("province");
        this.sellerType = obj.getString("type");
        this.sellerUser = obj.getString("user");
    }

    /**
     * Devuelve el nombre de usuario
     */
    public String getUser() {
        return user;
    }

    /**
     * Devuelve el nombre del usuario
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve el numero telefonico del usuario
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Correo del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * INTERNO
     */
    public int getId() {
        return id;
    }

    /**
     * Devuelve la url del icono del usuario
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Devuelve los Apellidos del usuario
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Devuelve el SHA1 del usuario
     */
    public String getSha1() {
        return sha1;
    }

    /**
     * Devuelve el nombre completo del usuario
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Devuelve si puede acceder a la consola de ventas
     */
    public boolean isSalesAdmin() {
        return isSalesAdmin;
    }

    /**
     * Devuelve si es un beta tester
     */
    public boolean isBetaTester() {
        return isBetaTester;
    }

    /**
     * Provincia del usuario
     */
    public Province getProvince() {
        return province;
    }

    /**
     * Devuelve si has accedido a apklis admin
     */
    public boolean isDeveloper() {
        return isDeveloper;
    }

    /**
     * Devuelve si tu cuenta esta activa
     */
    public boolean isDeveloperActive() {
        return isDeveloperActive;
    }

    /**
     * Carnet de id del usuario
     */
    public String getCI() {
        return cid;
    }

    public void setCI(String cid) {
        this.cid = cid;
    }

    /**
     * Numero de registro del usuario
     */
    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String number) {
        this.regNumber = number;
    }

    /**
     * Negocio del usuario
     */
    public String getBussiness() {
        return bussiness;
    }

    public void setBussiness(String bussiness) {
        this.bussiness = bussiness;
    }

    /**
     * Descripcion del usuario
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerBankAccount() {
        return sellerBankAccount;
    }

    public void setSellerBankAccount(String sellerBankAccount) {
        this.sellerBankAccount = sellerBankAccount;
    }

    public String getSellerCID() {
        return sellerCID;
    }

    public void setSellerCID(String sellerCID) {
        this.sellerCID = sellerCID;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSellerLicence() {
        return sellerLicence;
    }

    public void setSellerLicence(String sellerLicence) {
        this.sellerLicence = sellerLicence;
    }

    public String getSellerMunicipality() {
        return sellerMunicipality;
    }

    public void setSellerMunicipality(String sellerMunicipality) {
        this.sellerMunicipality = sellerMunicipality;
    }

    public String getSellerFullName() {
        return sellerFullName;
    }

    public void setSellerFullName(String sellerFullName) {
        this.sellerFullName = sellerFullName;
    }

    public String getSellerNIT() {
        return sellerNIT;
    }

    public void setSellerNIT(String sellerNIT) {
        this.sellerNIT = sellerNIT;
    }

    public String getSellerPhoneNumber() {
        return sellerPhoneNumber;
    }

    public void setSellerPhoneNumber(String sellerPhoneNumber) {
        this.sellerPhoneNumber = sellerPhoneNumber;
    }

    public String getSellerProvince() {
        return sellerProvince;
    }

    public void setSellerProvince(String sellerProvince) {
        this.sellerProvince = sellerProvince;
    }

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    public String getSellerUser() {
        return sellerUser;
    }

    public void setSellerUser(String sellerUser) {
        this.sellerUser = sellerUser;
    }

    public int getSellerBankBranch() {
        return sellerBankBranch;
    }

    public void setSellerBankBranch(int sellerBankBranch) {
        this.sellerBankBranch = sellerBankBranch;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public Boolean getSellerIsActive() {
        return sellerIsActive;
    }

    public void setSellerIsActive(Boolean sellerIsActive) {
        this.sellerIsActive = sellerIsActive;
    }

    public Date getSellerJoined() {
        return sellerJoined;
    }

    public void setSellerJoined(Date sellerJoined) {
        this.sellerJoined = sellerJoined;
    }

    /**
     * Devuelve un asistente para realizar cambios en tu informacion
     */
    public UserPatchWizard patchWizard() {
        return new UserPatchWizard(session);
    }

    public String toString() {
        return "UserInfo[user:" + user + ", name:" + name + ", phone:" + phone + ", email:" + email + ", id:" + id + ", isDev: " + isDeveloper + ", isDevActive:" + isDeveloperActive + "]";
    }
}
