package com.jjclade.therappy;

import java.util.ArrayList;

/**
 * Created by Jacob Throckmorton on 5/6/2015.
 */
public class StringLeaf extends StringTree {
    public StringLeaf(String s) {
        super(s);
        kids = new ArrayList<StringTree>(0);
    }

	public void add(StringTree s) {
		return;
	}
}
