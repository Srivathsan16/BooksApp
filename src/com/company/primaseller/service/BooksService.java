package com.company.primaseller.service;

import com.company.primaseller.Books;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BooksService {
    Map<String,Double> forSalesOnDate = new HashMap<>();
    public Map<String,Double>  readValuesFromTSV(String fileName) throws FileNotFoundException {
        List<Books> books = new ArrayList<Books>();
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    fstream));
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Books book = trimCode(attributes);
                books.add(book);
                line = br.readLine();
            }
        } catch (java.io.IOException ioe) {
            ioe.printStackTrace();
        }
        return forSalesOnDate;
    }

    private  Books trimCode(String[] metadata) {
        String book_id = metadata[0];
        String book_title = metadata[1];
        String book_author = metadata[2];
        double book_price = Double.parseDouble(metadata[3]);
if(!forSalesOnDate.containsKey(book_id)){
    forSalesOnDate.put(book_id,book_price);
}
        return new Books(book_id,book_title,book_author,book_price);

    }
}
