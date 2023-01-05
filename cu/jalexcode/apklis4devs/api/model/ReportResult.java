package cu.jalexcode.apklis4devs.api.model;

import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportResult implements Serializable{
    private int id, trans_id, ammount, bank;
    private String buyer, seller, cid, buyer_phone, description, tm_id, external_id, bank_id, state;
    private Boolean send;
    private Date date;

    /**Interno*/
    public ReportResult(String json) throws Exception{
        this(new JSONObject(json));
    }
//http://172.28.116.21/apklis/v1/payment/?date__gte=2022-11-01+00%3A00%3A00&date__lte=2023-01-04+23%3A59%3A59&limit=10&offset=10&ordering=date&seller=%2B53+5+4655909&state=SUCCESS
    /**Interno*/
    public ReportResult(JSONObject obj) throws Exception{
        this.id=obj.getInt("id");
        this.trans_id=obj.getInt("trans_id");
        this.ammount=obj.getInt("ammount");
        this.bank=obj.getInt("bank");

        this.buyer=obj.getString("buyer");
        this.seller=obj.getString("seller");
        this.cid=obj.getString("cid");
        this.buyer_phone=obj.getString("buyer_phone");
        this.description=obj.getString("description");
        this.tm_id=obj.getString("tm_id");
        this.external_id=obj.getString("external_id");
        this.bank_id=obj.getString("bank_id");
        this.state=obj.getString("state");

        this.send=obj.getBoolean("send");

        final String pubTmp = obj.getString("date");
        this.date= new SimpleDateFormat("yyyy-MM-dd").parse(pubTmp.trim());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(int trans_id) {
        this.trans_id = trans_id;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getBuyer_phone() {
        return buyer_phone;
    }

    public void setBuyer_phone(String buyer_phone) {
        this.buyer_phone = buyer_phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTm_id() {
        return tm_id;
    }

    public void setTm_id(String tm_id) {
        this.tm_id = tm_id;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getSend() {
        return send;
    }

    public void setSend(Boolean send) {
        this.send = send;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
