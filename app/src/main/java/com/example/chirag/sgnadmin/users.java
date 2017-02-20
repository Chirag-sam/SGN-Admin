package com.example.chirag.sgnadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chirag on 09-Feb-17.
 */

public class users {

    String name;
    String phone;
    String add1;
    String add2;
    String pin;
    String email;
    ArrayList <mycart> mycart;
    HashMap<String,myorders> myorders;

    public ArrayList<users.mycart> getMycart() {
        return mycart;
    }

    public HashMap<String, users.myorders> getMyorders() {
        return myorders;
    }

    public void setMyorders(HashMap<String, users.myorders> myorders) {
        this.myorders = myorders;
    }

    public void setMycart(ArrayList<users.mycart> mycart) {
        this.mycart = mycart;
    }


    public users() {
    }

    public users(String name, String phone, String add1, String add2, String pin, String email) {
        this.name = name;
        this.phone = phone;
        this.add1 = add1;
        this.add2 = add2;
        this.pin = pin;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public static class mycart
    {
        int pid;
        int qty;
        double amt;

        public mycart() {
        }

        public mycart(int pid, int qty, double amt) {
            this.pid = pid;
            this.qty = qty;
            this.amt = amt;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public double getAmt() {
            return amt;
        }

        public void setAmt(double amt) {
            this.amt = amt;
        }
    }
    public static class myorders
    {
        String orderid;
        String orderdate;
        String amount;
        String status;
        ArrayList<users.mycart> items;
        String prescription;

        public myorders() {
        }

        public myorders(String orderid, String orderdate, String amount, String status, ArrayList<users.mycart> items, String prescription) {
            this.orderid = orderid;
            this.orderdate = orderdate;
            this.amount = amount;
            this.status = status;
            this.items = items;
            this.prescription = prescription;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getOrderdate() {
            return orderdate;
        }

        public void setOrderdate(String orderdate) {
            this.orderdate = orderdate;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public ArrayList<users.mycart> getItems() {
            return items;
        }

        public void setItems(ArrayList<users.mycart> items) {
            this.items = items;
        }

        public String getPrescription() {
            return prescription;
        }

        public void setPrescription(String prescription) {
            this.prescription = prescription;
        }
    }

    public static class cartdetails
    {
        int pid;
        String name;
        String category;
        int qty;
        double amt;
        String picture;
        double price;

        public cartdetails() {
        }

        public cartdetails(int pid, String name, String category, int qty, double amt, String picture, double price) {
            this.pid = pid;
            this.name = name;
            this.category = category;
            this.qty = qty;
            this.amt = amt;
            this.picture = picture;
            this.price = price;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public double getAmt() {
            return amt;
        }

        public void setAmt(double amt) {
            this.amt = amt;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}

