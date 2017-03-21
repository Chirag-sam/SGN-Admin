package com.notadeveloper.app.sgnadmin;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chirag on 09-Feb-17.
 */
public class users {
    String uid;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public users(String uid, String name, String phone, String add1, String add2, String pin, String email) {
        this.uid = uid;
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
        boolean presc;
        public mycart() {
        }

        public mycart(int pid, int qty, double amt) {
            this.pid = pid;
            this.qty = qty;
            this.amt = amt;
        }

        public mycart(int pid, int qty, double amt, boolean presc) {
            this.pid = pid;
            this.qty = qty;
            this.amt = amt;
            this.presc = presc;
        }

        public boolean isPresc() {
            return presc;
        }

        public void setPresc(boolean presc) {
            this.presc = presc;
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
        String prescribedby;
        String deliverby;
        String add1;
        String add2;
        String addpin;
        String phone;
        String uid;
        String name;
        String uploadedprescription;

        public myorders(String orderid, String orderdate, String amount, String status, ArrayList<users.mycart> items, String prescribedby, String deliverby, String add1, String add2, String addpin, String phone, String uid, String name, String uploadedprescription) {
            this.orderid = orderid;
            this.orderdate = orderdate;
            this.amount = amount;
            this.status = status;
            this.items = items;
            this.prescribedby = prescribedby;
            this.deliverby = deliverby;
            this.add1 = add1;
            this.add2 = add2;
            this.addpin = addpin;
            this.phone = phone;
            this.uid = uid;
            this.name = name;
            this.uploadedprescription = uploadedprescription;
        }

        public String getPrescribedby() {
            return prescribedby;
        }

        public void setPrescribedby(String prescribedby) {
            this.prescribedby = prescribedby;
        }

        public String getDeliverby() {
            return deliverby;
        }

        public void setDeliverby(String deliverby) {
            this.deliverby = deliverby;
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


        public String getAddpin() {
            return addpin;
        }

        public void setAddpin(String addpin) {
            this.addpin = addpin;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUploadedprescription() {
            return uploadedprescription;
        }

        public void setUploadedprescription(String uploadedprescription) {
            this.uploadedprescription = uploadedprescription;
        }

        public myorders() {
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