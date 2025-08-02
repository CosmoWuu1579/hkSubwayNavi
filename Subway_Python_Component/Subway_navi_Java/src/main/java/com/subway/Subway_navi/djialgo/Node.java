package com.subway.Subway_navi.djialgo;

public class Node {
    public String destination;
    public int routeLine;
    public Node(String stat, int id){
        destination = stat;
        routeLine = id;
    }
    @Override
    public String toString(){
        return "Destination :" + destination + " RouteLine: " + String.valueOf(routeLine);
    }
}
