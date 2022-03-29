package vn.techmaster.homework1.Repo;

import java.util.ArrayList;
import java.util.List;

import vn.techmaster.homework1.Model.Quote;

public class Repository {
    List<Quote> listqoute = new ArrayList<>();
     
    public List<Quote> getall(){
        listqoute.add(new Quote("123"));
        return listqoute;
    }
}
