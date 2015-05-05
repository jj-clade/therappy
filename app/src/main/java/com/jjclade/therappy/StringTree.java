package com.jjclade.therappy;

import java.util.ArrayList;

/**
 * Created by jacob on 5/5/15.
 */
public class StringTree {
    public String name;
    public ArrayList<String> kids;

    public StringTree(String name) {
        this.name = name;
        kids = new ArrayList<String>();
    }

    public void add(String s){
        kids.add(s);
    }
}
