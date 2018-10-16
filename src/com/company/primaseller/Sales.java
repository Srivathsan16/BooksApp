package com.company.primaseller;

import java.util.Date;
import java.util.Map;

public class Sales {

    Date sale_date;

    String sale_email;

    String paymentMethods;
    int sale_item_count;
    Map<String,Integer> onwards;

    public Sales(Date sale_date, String sale_email, String paymentMethods, int sale_item_count) {
        this.sale_date = sale_date;
        this.sale_email = sale_email;
        this.paymentMethods = paymentMethods;
        this.sale_item_count = sale_item_count;
    }

    public Date getSale_date() {
        return sale_date;
    }

    public void setSale_date(Date sale_date) {
        this.sale_date = sale_date;
    }

    public String getSale_email() {
        return sale_email;
    }

    public void setSale_email(String sale_email) {
        this.sale_email = sale_email;
    }

    public String getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(String paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public int getSale_item_count() {
        return sale_item_count;
    }

    public void setSale_item_count(int sale_item_count) {
        this.sale_item_count = sale_item_count;
    }

    public Map<String, Integer> getOnwards() {
        return onwards;
    }

    public void setOnwards(Map<String, Integer> onwards) {
        this.onwards = onwards;
    }

    public Sales(Date sale_date, String sale_email, String paymentMethods, int sale_item_count, Map<String, Integer> onwards) {
        this.sale_date = sale_date;
        this.sale_email = sale_email;
        this.paymentMethods = paymentMethods;
        this.sale_item_count = sale_item_count;
        this.onwards = onwards;
    }
}
