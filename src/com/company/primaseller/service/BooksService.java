package com.company.primaseller.service;

import com.company.primaseller.Books;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BooksService {
    public  List<Books> readValuesFromTSV(String fileName) throws FileNotFoundException {
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
        return books;
    }

    private static Books trimCode(String[] metadata) {
        String book_id = metadata[0];
        String book_title = metadata[1];
        String book_author = metadata[2];
        double book_price = Double.parseDouble(metadata[3]);

        return new Books(book_id,book_title,book_author,book_price);

    }
}
