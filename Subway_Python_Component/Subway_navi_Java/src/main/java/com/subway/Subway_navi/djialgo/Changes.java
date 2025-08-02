package com.subway.Subway_navi.djialgo;

public class Changes {
    public String station;
    public int ogline;
    public int newline;
    public Changes(String curr, int og, int newl){
        station = curr;
        ogline = og;
        newline = newl;
    }
    @Override
    public String toString(){
        return "station: " + station + ogline + " " + newline;
    }

}
