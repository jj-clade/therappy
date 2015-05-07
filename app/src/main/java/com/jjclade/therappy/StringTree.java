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
        kids.add(s);
    }

	public int hashCode() { return name.hashCode(); }

	public boolean equals(Object o) {
		if (getClass() != o.getClass()) {
			return false;
		}

		if (o == this) {
			return true;
		}

		StringTree rhs=(StringTree)o;

		if (!(name.equals(rhs.name))) {
			return false;
		}

		for (StringTree kid : kids) {
			if (!(rhs.kids.contains(kid))) {
				return false;
			}
		}

		return true;
	}

	public String[] toArray() {
		if (kids.size() == 0) {
			String[] ret=new String[1];
			ret[0]=name;
		}

		String[] ret=new String[kids.size()];
		for (int i=0; i<kids.size(); i++) {
			ret[i]=kids.get(i).name;
		}

		return ret;
	}
}
