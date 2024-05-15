package bean;

import log.IModel;

import java.sql.Date;

public class Log implements IModel {
    private int id;
    private int ip;
    private String action;
    private String level;
    private String address;
    private String previousValue;
    private String currentValue;
    private Date created_at;
    private Date updated_at;

    public Log() {
    }

    public Log(int id, int ip, String action, String level, String address, String previousValue, String currentValue, Date created_at, Date updated_at) {
        this.id = id;
        this.ip = ip;
        this.action = action;
        this.level = level;
        this.address = address;
        this.previousValue = previousValue;
        this.currentValue = currentValue;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(String previousValue) {
        this.previousValue = previousValue;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String getTable() {
        return null;
    }

    @Override
    public String beforeData() {
        return null;
    }

    @Override
    public String afterData() {
        return null;
    }
}
