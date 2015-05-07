package com.jjclade.therappy;

/**
 * Created by Jacob Throckmorton on 5/6/2015.
 */
public class MoodLeaf extends StringLeaf {
    public double intensity;

    public MoodLeaf(String s){
        super(s);
        intensity = 0;
    }
}
