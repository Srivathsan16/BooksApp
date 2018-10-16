package com.company.primaseller.service;

import com.company.primaseller.Sales;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SalesService {
    public List<Sales> readValuesFromSales(String s) throws ParseException {

        List<Sales> salesList = new ArrayList<Sales>();
        try {
            FileInputStream fstream = new FileInputStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    fstream));
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Sales sales = trimCodeSales(attributes);
                salesList.add(sales);
                line = br.readLine();
            }
        } catch (java.io.IOException ioe) {
            ioe.printStackTrace();
        }
        return salesList;
    }

    private static Sales trimCodeSales(String[] attributes) throws ParseException {
        List<String> onwards = new ArrayList<>();
        Date sale_date = new SimpleDateFormat("YYYY-MM-DD").parse(attributes[0]);
        String sale_email = attributes[1];
        String paymentMethods = attributes[2];
        int sale_item_count = Integer.parseInt(attributes[3]);
        for(int i=1;i<=Integer.parseInt(attributes[3]);i++) {
            onwards.add(attributes[3+i].trim());
        }
        Map<String,Integer> onwardsMap = convertListToMap(onwards);
        /*System.out.println("Onwards List :" + onwards);
        System.out.println("Onwards Map :" + onwardsMap);*/
        return new Sales(sale_date,sale_email,paymentMethods,sale_item_count,onwardsMap);
    }

    private static Map<String,Integer> convertListToMap(List<String> onwards) {
        Map<String,Integer> onwardsMap = new HashMap<>();
        for(String convertor : onwards){
            String[] s = convertor.split(";");
            onwardsMap.put(s[0], Integer.valueOf(s[1]));
        }
        return onwardsMap;
    }
}
