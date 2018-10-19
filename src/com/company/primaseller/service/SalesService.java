package com.company.primaseller.service;

import com.company.primaseller.Sales;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class SalesService {
    public  Map<String,Integer> onwardsMap = new HashMap<>();
    public  Map<String,Integer> topCustomers = new HashMap<>();
    public  Map<Date,Double> salesOnDate = new HashMap<>();
    public  Map<String,Double> bookListForSalesOnDate = new HashMap<>();
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
        System.out.println("Onwards Map Original ::" + onwardsMap);
       List<String> topBookList = sortMap(onwardsMap);
       List<String> customerList = sortMap(topCustomers);
       System.out.println("Top_Books:::");
       topBookList.stream().forEach(System.out::println);
       System.out.println("Top_Selling_books");
       customerList.stream().forEach(System.out::println);
        System.out.println("Sales Date Map : " + salesOnDate);
       return salesList;
    }

    private List<String> sortMap(Map<String,Integer> topCustomers) {
        //System.out.print("Top_Customers :: ");
     List<String> collector =   topCustomers.entrySet().stream()
                .sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
                .map(e->e.getKey())
                .collect(Collectors.toList());
return collector;
                //.forEach(k -> System.out.print(k.getKey() + "\t " ));
    }


    private Sales trimCodeSales(String[] attributes) throws ParseException {
        List<String> onwards = new ArrayList<>();
        String salesDate = attributes[0];
        Date sale_date = new SimpleDateFormat("YYYY-MM-DD").parse(salesDate);
        System.out.println("Sales Date ::" + salesDate);
        String sale_email = attributes[1];
        String paymentMethods = attributes[2];
        int sale_item_count = Integer.parseInt(attributes[3]);
        if(topCustomers.containsKey(attributes[1])){
            topCustomers.put(sale_email,sale_item_count+topCustomers.get(sale_email));
        }
        else{
            topCustomers.put(sale_email,sale_item_count);
        }
        for(int i=1;i<=Integer.parseInt(attributes[3]);i++) {
            onwards.add(attributes[3+i].trim());
        }
        /*onwardsMap = */convertListToMap(onwards,sale_date);
        /*System.out.println("Onwards List :" + onwards);
        System.out.println("Onwards Map :" + onwardsMap);*/
        return new Sales(sale_date,sale_email,paymentMethods,sale_item_count,onwardsMap);
    }

    private  void convertListToMap(List<String> onwards, Date sale_date) {
        //Map<String,Integer> onwardsMap = new HashMap<>();
        for (String convertor : onwards) {
            String[] s = convertor.split(";");
            if (onwardsMap.containsKey(s[0])) {
                onwardsMap.put(s[0], Integer.valueOf(s[1]) + Integer.valueOf(onwardsMap.get(s[0])));
            } else {
                onwardsMap.put(s[0], Integer.valueOf(s[1]));
            }
            if(salesOnDate.containsKey(sale_date)){
                salesOnDate.put(sale_date,getSalesForThatDate(onwards));
            }
            else{
                salesOnDate.put(sale_date, Double.valueOf(s[1]));
            }

        }

    }

    private Double getSalesForThatDate(List<String> onwards) {
        double local = 0;
        for(String salesOnDateValue : onwards){
            String[] s= salesOnDateValue.split(";");
        for(Map.Entry<String,Double> entry : bookListForSalesOnDate.entrySet()){
            if(entry.getKey().equals(s[0])){
                local =local + entry.getValue()*Integer.valueOf(s[1]);
            }
        }
        }
        return local ;
    }

    public void getForSalesDate(Map<String,Double> booksList) {
        bookListForSalesOnDate = booksList;
    }
}
