package com.subway.Subway_navi.djialgo;

import java.util.*;
public class Solution {
    public int stations;
    public int turnings;
    public ArrayList<Changes> changes;
    public ArrayList<String> allStations;
    public Solution(int stat, int turns, ArrayList<Changes> switches,ArrayList<String> ans){
        stations = stat;
        turnings = turns;
        changes = switches;
        allStations = ans;
    }
    @Override
    public String toString(){
        return stations + " stations; " + turnings + " turnings; ";
    }
}
