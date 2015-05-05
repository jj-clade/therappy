package com.jjclade.therappy;

import java.util.ArrayList;

/**
 * Created by jacob on 5/5/15.
 */
public class StringTree {
    public String name;
    public ArrayList<StringTree> kids;

    public StringTree(String name) {
        this.name = name;
        kids = new ArrayList<StringTree>();
    }

    public void add(StringTree s){
        kids.push(s);
    }
}
