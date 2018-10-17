package com.company.primaseller;

import com.company.primaseller.service.BooksService;
import com.company.primaseller.service.SalesService;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        BooksService booksService = new BooksService();
        SalesService salesService = new SalesService();
        List<Books> booksList = booksService.readValuesFromTSV("D:/PrimaSeller/Books.txt");

        List<Sales> salesList = salesService.readValuesFromSales("D:/PrimaSeller/Sales.list.txt");

        for (int i = 0; i < booksList.size(); i++) {
          //  System.out.println("Result: " + booksList.get(i).getBook_id());

        }
    }




}
