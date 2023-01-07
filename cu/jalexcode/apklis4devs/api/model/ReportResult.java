package cu.jalexcode.apklis4devs.api.model;

import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportResult implements Serializable{
    private int id, transId, ammount, bank;
    private String buyer, seller, cid, buyerPhone, description, tmId, externalId, bankId, state;
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
        this.transId =obj.getInt("trans_id");
        this.ammount=obj.getInt("ammount");
        this.bank=obj.getInt("bank");

        this.buyer=obj.getString("buyer");
        this.seller=obj.getString("seller");
        this.cid=obj.getString("cid");
        this.buyerPhone =obj.getString("buyer_phone");
        this.description=obj.getString("description");
        this.tmId =obj.getString("tm_id");
        this.externalId =obj.getString("external_id");
        this.bankId =obj.getString("bank_id");
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

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
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

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTmId() {
        return tmId;
    }

    public void setTmId(String tmId) {
        this.tmId = tmId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
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
