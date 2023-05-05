package com.superlilu.pojo;


public class UserInfo {

    //输入Cloudflare的账号邮箱等信息
    private String email = ""; // 邮箱
    private String key = ""; // key
    private String zone_id = ""; // zone id
    private String record_id = ""; // record id
    private String type = ""; // type
    private String name = ""; // name
    private int ttl = 120; // ttl
    private boolean select = true; //选择对本机ddns还是对已经ddns的域名做ddns
    // (true为本机，false为已经ddns的域名)
    private String ObjectHost = ""; //已经ddns的域名
    private boolean force=false;//是否强制更新
    public UserInfo() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setZone_id(String zone_id) {
        this.zone_id = zone_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public void setObjectHost(String objectHost) {
        ObjectHost = objectHost;
    }

    public String getEmail() {
        return email;
    }

    public String getKey() {
        return key;
    }

    public String getZone_id() {
        return zone_id;
    }

    public String getRecord_id() {
        return record_id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getTtl() {
        return ttl;
    }

    public boolean isSelect() {
        return select;
    }

    public String getObjectHost() {
        return ObjectHost;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "email='" + email + '\'' +
                ", key='" + key + '\'' +
                ", zone_id='" + zone_id + '\'' +
                ", record_id='" + record_id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", ttl=" + ttl +
                ", select=" + select +
                ", ObjectHost='" + ObjectHost + '\'' +
                '}';
    }
}
