package com.jjclade.therappy;

import java.sql.Time;

/**
 * Created by jacob on 5/5/15.
 */
public class LogEntry {
    StringLeaf behavior;
    StringLeaf belief;
    StringLeaf trigger;
    MoodLeaf mood;
    Time time;
    long timestamp;


    public LogEntry(){
        timestamp = time.getTime();
    }

    public void addMood(MoodLeaf mood){
        this.mood = mood;
    }

    public void addTrigger(StringLeaf trigger){
        this.trigger = trigger;
    }

    public void addBelief(StringLeaf belief){
        this.belief = belief;
    }

    public void addBehavior(StringLeaf behavior){
        this.behavior = behavior;
    }
}
