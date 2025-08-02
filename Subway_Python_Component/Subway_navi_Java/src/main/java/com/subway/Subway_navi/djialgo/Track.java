package com.subway.Subway_navi.djialgo;

import java.util.*;
public class Track{
    public int totalStations;
    public int totalTurns;
    public String currentStation;
    public int currentRoute;
    public ArrayList<String> allStations;
    public ArrayList<Changes> changes;
    public ArrayList<Integer> routes;
    public Track(int stations, int turns,String cur, ArrayList<String> all, ArrayList<Changes> swaps,int routeLine, ArrayList<Integer> lines){
        totalStations = stations;
        totalTurns = turns;
        currentStation = cur;
        allStations = all;
        changes = swaps;
        currentRoute = routeLine;
        routes = lines;
    }
}