package com.company.primaseller.service;

import com.company.primaseller.Sales;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SalesService {
    public  Map<String,Integer> onwardsMap = new HashMap<>();
    public  Map<String,Integer> topCustomers = new HashMap<>();
    public  Map<Date,Integer> salesOnDate = new HashMap<>();
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
        Date sale_date = new SimpleDateFormat("YYYY-MM-DD").parse(attributes[0]);
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
        /*onwardsMap = */convertListToMap(onwards);
        /*System.out.println("Onwards List :" + onwards);
        System.out.println("Onwards Map :" + onwardsMap);*/
        return new Sales(sale_date,sale_email,paymentMethods,sale_item_count,onwardsMap);
    }

    private  void convertListToMap(List<String> onwards) {
        //Map<String,Integer> onwardsMap = new HashMap<>();
        for (String convertor : onwards) {
            String[] s = convertor.split(";");
            if (onwardsMap.containsKey(s[0])) {
                onwardsMap.put(s[0], Integer.valueOf(s[1]) + Integer.valueOf(onwardsMap.get(s[0])));
            } else {
                onwardsMap.put(s[0], Integer.valueOf(s[1]));
            }

            // return onwardsMap;
        }

    }
}
