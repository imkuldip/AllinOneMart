package com.example.kuldip.allinonemart.DataModel;

public class NumberOfView {


    private String p_no;
    private String c_id;
    private  String status;
    private  String description;
    private  String price;
    private  String phone;
    private  String email;

    public NumberOfView(String p_no, String c_id, String status, String description, String price, String phone, String email) {
        this.p_no = p_no;
        this.c_id = c_id;
        this.status = status;
        this.description = description;
        this.price = price;
        this.phone = phone;
        this.email = email;
    }

    public String getP_no() {
        return p_no;
    }

    public void setP_no(String p_no) {
        this.p_no = p_no;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
