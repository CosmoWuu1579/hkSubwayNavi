package com.subway.Subway_navi.djialgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class AlgoRespository {
    //Maps the route "number" to the actual name of the line
    HashMap<Integer, String> idToName = new HashMap<>();
    //Maps each route to the stations part of it via the id
    HashMap<Integer, List<String>> routesToStation = new HashMap<>();
    //Maps each station to the list of routes/lines that it's a part of
    HashMap<String, List<Integer>> stationToRoutes = new HashMap<>();
    //Station name-> a list of nodes that each has line ID and name
    HashMap<String, List<Node>> adjacencyList = new HashMap<>();

    @PostConstruct
    private void init(){
        idToName.put(1,"East Rail line");
        idToName.put(2,"Kwun Tong line");
        idToName.put(3,"Tsuen Wan line");
        idToName.put(4,"Island line");
        idToName.put(5,"Tung Chung line");
        idToName.put(6,"Airport Express");
        idToName.put(7,"Tseung Kwan O line");
        idToName.put(8,"Tuen Ma line");
        idToName.put(9,"Disneyland Resort line");
        idToName.put(10,"South Island line");

        //Initializing routeToStation
        ArrayList<String> names1= new ArrayList<>(Arrays.asList("Admiralty", "Exhibition Centre", "Hung Hom ", "Mong Kok East", "Kowloon Tong", "Tai Wai", "Sha Tin", "Fo Tan", "Racecourse", "University ", "Tai Po Market", "Tai Wo", "Fanling", "Sheung Shui", "Lo Wu", "Lok Ma Chau"));
        ArrayList<String> names2= new ArrayList<>(Arrays.asList("Whampoa", "Ho Man Tin", "Yau Ma Tei", "Mong Kok", "Prince Edward", "Shek Kip Mei", "Kowloon Tong", "Lok Fu", "Wong Tai Sin", "Diamond Hill", "Choi Hung", "Kowloon Bay", "Ngau Tau Kok", "Kwun Tong", "Lam Tin", "Yau Tong", "Tiu Keng Leng"));
        ArrayList<String> names3= new ArrayList<>(Arrays.asList("Tsuen Wan", "Tai Wo Hau", "Kwai Hing", "Kwai Fong", "Lai King", "Mei Foo", "Lai Chi Kok", "Cheung Sha Wan", "Sham Shui Po", "Prince Edward", "Mong Kok", "Yau Ma Tei", "Jordan", "Tsim Sha Tsui", "Admiralty", "Central"));
        ArrayList<String> names4= new ArrayList<>(Arrays.asList("Kennedy Town", "HKU", "Sai Ying Pun", "Sheung Wan", "Central", "Admiralty", "Wan Chai", "Causeway Bay", "Tin Hau", "Fortress Hill", "North Point", "Quarry Bay", "Tai Koo", "Sai Wan Ho", "Shau Kei Wan", "Heng Fa Chuen", "Chai Wan"));
        ArrayList<String> names5= new ArrayList<>(Arrays.asList("Tung Chung", "Sunny Bay", "Tsing Yi", "Lai King", "Nam Cheong", "Olympic", "Kowloon", "Hong Kong"));
        ArrayList<String> names6= new ArrayList<>(Arrays.asList("AsiaWorld–Expo", "Airport", "Tsing Yi", "Kowloon", "Hong Kong"));
        ArrayList<String> names7= new ArrayList<>(Arrays.asList("Po Lam", "Hang Hau", "LOHAS Park", "Tseung Kwan O", "Tiu Keng Leng", "Yau Tong", "Quarry Bay", "North Point"));
        ArrayList<String> names8= new ArrayList<>(Arrays.asList("Wu Kai Sha", "Ma On Shan", "Heng On", "Tai Shui Hang", "Shek Mun", "City One", "Sha Tin Wai", "Che Kung Temple", "Tai Wai", "Hin Keng", "Diamond Hill", "Kai Tak", "Sung Wong Toi", "To Kwa Wan", "Ho Man Tin", "Hung Hom", "East Tsim Sha Tsui", "Austin", "Nam Cheong", "Mei Foo", "Tsuen Wan West", "Kam Sheung Road", "Yuen Long", "Long Ping", "Tin Shui Wai", "Siu Hong", "Tuen Mun"));
        ArrayList<String> names9= new ArrayList<>(Arrays.asList("Disneyland Resort", "Sunny Bay"));
        ArrayList<String> names10= new ArrayList<>(Arrays.asList("South Horizons", "Lei Tung", "Wong Chuk Hang", "Ocean Park", "Admiralty"));

        routesToStation.put(1, names1);
        routesToStation.put(2, names2);
        routesToStation.put(3, names3);
        routesToStation.put(4, names4);
        routesToStation.put(5, names5);
        routesToStation.put(6, names6);
        routesToStation.put(7, names7);
        routesToStation.put(8, names8);
        routesToStation.put(9, names9);
        routesToStation.put(10, names10);

        ArrayList<Integer> stationToRoutes0= new ArrayList<>(Arrays.asList(1, 3, 4, 10));
        stationToRoutes.put("Admiralty",stationToRoutes0);
        ArrayList<Integer> stationToRoutes1= new ArrayList<>(Arrays.asList(1));
        stationToRoutes.put("Exhibition Centre",stationToRoutes1);
        ArrayList<Integer> stationToRoutes2= new ArrayList<>(Arrays.asList(1));
        stationToRoutes.put("Hung Hom ",stationToRoutes2);
        ArrayList<Integer> stationToRoutes3= new ArrayList<>(Arrays.asList(1));
        stationToRoutes.put("Mong Kok East",stationToRoutes3);
        ArrayList<Integer> stationToRoutes4= new ArrayList<>(Arrays.asList(1, 2));
        stationToRoutes.put("Kowloon Tong",stationToRoutes4);
        ArrayList<Integer> stationToRoutes5= new ArrayList<>(Arrays.asList(1, 8));
        stationToRoutes.put("Tai Wai",stationToRoutes5);
        ArrayList<Integer> stationToRoutes6= new ArrayList<>(Arrays.asList(1));
        stationToRoutes.put("Sha Tin",stationToRoutes6);
        ArrayList<Integer> stationToRoutes7= new ArrayList<>(Arrays.asList(1));
        stationToRoutes.put("Fo Tan",stationToRoutes7);
        ArrayList<Integer> stationToRoutes8= new ArrayList<>(Arrays.asList(1));
        stationToRoutes.put("Racecourse",stationToRoutes8);
        ArrayList<Integer> stationToRoutes9= new ArrayList<>(Arrays.asList(1));
        stationToRoutes.put("University ",stationToRoutes9);
        ArrayList<Integer> stationToRoutes10= new ArrayList<>(Arrays.asList(1));
        stationToRoutes.put("Tai Po Market",stationToRoutes10);
        ArrayList<Integer> stationToRoutes11= new ArrayList<>(Arrays.asList(1));
        stationToRoutes.put("Tai Wo",stationToRoutes11);
        ArrayList<Integer> stationToRoutes12= new ArrayList<>(Arrays.asList(1));
        stationToRoutes.put("Fanling",stationToRoutes12);
        ArrayList<Integer> stationToRoutes13= new ArrayList<>(Arrays.asList(1));
        stationToRoutes.put("Sheung Shui",stationToRoutes13);
        ArrayList<Integer> stationToRoutes14= new ArrayList<>(Arrays.asList(1));
        stationToRoutes.put("Lo Wu",stationToRoutes14);
        ArrayList<Integer> stationToRoutes15= new ArrayList<>(Arrays.asList(1));
        stationToRoutes.put("Lok Ma Chau",stationToRoutes15);
        ArrayList<Integer> stationToRoutes16= new ArrayList<>(Arrays.asList(2));
        stationToRoutes.put("Whampoa",stationToRoutes16);
        ArrayList<Integer> stationToRoutes17= new ArrayList<>(Arrays.asList(2, 8));
        stationToRoutes.put("Ho Man Tin",stationToRoutes17);
        ArrayList<Integer> stationToRoutes18= new ArrayList<>(Arrays.asList(2, 3));
        stationToRoutes.put("Yau Ma Tei",stationToRoutes18);
        ArrayList<Integer> stationToRoutes19= new ArrayList<>(Arrays.asList(2, 3));
        stationToRoutes.put("Mong Kok",stationToRoutes19);
        ArrayList<Integer> stationToRoutes20= new ArrayList<>(Arrays.asList(2, 3));
        stationToRoutes.put("Prince Edward",stationToRoutes20);
        ArrayList<Integer> stationToRoutes21= new ArrayList<>(Arrays.asList(2));
        stationToRoutes.put("Shek Kip Mei",stationToRoutes21);
        ArrayList<Integer> stationToRoutes22= new ArrayList<>(Arrays.asList(2));
        stationToRoutes.put("Lok Fu",stationToRoutes22);
        ArrayList<Integer> stationToRoutes23= new ArrayList<>(Arrays.asList(2));
        stationToRoutes.put("Wong Tai Sin",stationToRoutes23);
        ArrayList<Integer> stationToRoutes24= new ArrayList<>(Arrays.asList(2, 8));
        stationToRoutes.put("Diamond Hill",stationToRoutes24);
        ArrayList<Integer> stationToRoutes25= new ArrayList<>(Arrays.asList(2));
        stationToRoutes.put("Choi Hung",stationToRoutes25);
        ArrayList<Integer> stationToRoutes26= new ArrayList<>(Arrays.asList(2));
        stationToRoutes.put("Kowloon Bay",stationToRoutes26);
        ArrayList<Integer> stationToRoutes27= new ArrayList<>(Arrays.asList(2));
        stationToRoutes.put("Ngau Tau Kok",stationToRoutes27);
        ArrayList<Integer> stationToRoutes28= new ArrayList<>(Arrays.asList(2));
        stationToRoutes.put("Kwun Tong",stationToRoutes28);
        ArrayList<Integer> stationToRoutes29= new ArrayList<>(Arrays.asList(2));
        stationToRoutes.put("Lam Tin",stationToRoutes29);
        ArrayList<Integer> stationToRoutes30= new ArrayList<>(Arrays.asList(2, 7));
        stationToRoutes.put("Yau Tong",stationToRoutes30);
        ArrayList<Integer> stationToRoutes31= new ArrayList<>(Arrays.asList(2, 7));
        stationToRoutes.put("Tiu Keng Leng",stationToRoutes31);
        ArrayList<Integer> stationToRoutes32= new ArrayList<>(Arrays.asList(3));
        stationToRoutes.put("Tsuen Wan",stationToRoutes32);
        ArrayList<Integer> stationToRoutes33= new ArrayList<>(Arrays.asList(3));
        stationToRoutes.put("Tai Wo Hau",stationToRoutes33);
        ArrayList<Integer> stationToRoutes34= new ArrayList<>(Arrays.asList(3));
        stationToRoutes.put("Kwai Hing",stationToRoutes34);
        ArrayList<Integer> stationToRoutes35= new ArrayList<>(Arrays.asList(3));
        stationToRoutes.put("Kwai Fong",stationToRoutes35);
        ArrayList<Integer> stationToRoutes36= new ArrayList<>(Arrays.asList(3, 5));
        stationToRoutes.put("Lai King",stationToRoutes36);
        ArrayList<Integer> stationToRoutes37= new ArrayList<>(Arrays.asList(3, 8));
        stationToRoutes.put("Mei Foo",stationToRoutes37);
        ArrayList<Integer> stationToRoutes38= new ArrayList<>(Arrays.asList(3));
        stationToRoutes.put("Lai Chi Kok",stationToRoutes38);
        ArrayList<Integer> stationToRoutes39= new ArrayList<>(Arrays.asList(3));
        stationToRoutes.put("Cheung Sha Wan",stationToRoutes39);
        ArrayList<Integer> stationToRoutes40= new ArrayList<>(Arrays.asList(3));
        stationToRoutes.put("Sham Shui Po",stationToRoutes40);
        ArrayList<Integer> stationToRoutes41= new ArrayList<>(Arrays.asList(3));
        stationToRoutes.put("Jordan",stationToRoutes41);
        ArrayList<Integer> stationToRoutes42= new ArrayList<>(Arrays.asList(3));
        stationToRoutes.put("Tsim Sha Tsui",stationToRoutes42);
        ArrayList<Integer> stationToRoutes43= new ArrayList<>(Arrays.asList(3, 4));
        stationToRoutes.put("Central",stationToRoutes43);
        ArrayList<Integer> stationToRoutes44= new ArrayList<>(Arrays.asList(4));
        stationToRoutes.put("Kennedy Town",stationToRoutes44);
        ArrayList<Integer> stationToRoutes45= new ArrayList<>(Arrays.asList(4));
        stationToRoutes.put("HKU",stationToRoutes45);
        ArrayList<Integer> stationToRoutes46= new ArrayList<>(Arrays.asList(4));
        stationToRoutes.put("Sai Ying Pun",stationToRoutes46);
        ArrayList<Integer> stationToRoutes47= new ArrayList<>(Arrays.asList(4));
        stationToRoutes.put("Sheung Wan",stationToRoutes47);
        ArrayList<Integer> stationToRoutes48= new ArrayList<>(Arrays.asList(4));
        stationToRoutes.put("Wan Chai",stationToRoutes48);
        ArrayList<Integer> stationToRoutes49= new ArrayList<>(Arrays.asList(4));
        stationToRoutes.put("Causeway Bay",stationToRoutes49);
        ArrayList<Integer> stationToRoutes50= new ArrayList<>(Arrays.asList(4));
        stationToRoutes.put("Tin Hau",stationToRoutes50);
        ArrayList<Integer> stationToRoutes51= new ArrayList<>(Arrays.asList(4));
        stationToRoutes.put("Fortress Hill",stationToRoutes51);
        ArrayList<Integer> stationToRoutes52= new ArrayList<>(Arrays.asList(4, 7));
        stationToRoutes.put("North Point",stationToRoutes52);
        ArrayList<Integer> stationToRoutes53= new ArrayList<>(Arrays.asList(4, 7));
        stationToRoutes.put("Quarry Bay",stationToRoutes53);
        ArrayList<Integer> stationToRoutes54= new ArrayList<>(Arrays.asList(4));
        stationToRoutes.put("Tai Koo",stationToRoutes54);
        ArrayList<Integer> stationToRoutes55= new ArrayList<>(Arrays.asList(4));
        stationToRoutes.put("Sai Wan Ho",stationToRoutes55);
        ArrayList<Integer> stationToRoutes56= new ArrayList<>(Arrays.asList(4));
        stationToRoutes.put("Shau Kei Wan",stationToRoutes56);
        ArrayList<Integer> stationToRoutes57= new ArrayList<>(Arrays.asList(4));
        stationToRoutes.put("Heng Fa Chuen",stationToRoutes57);
        ArrayList<Integer> stationToRoutes58= new ArrayList<>(Arrays.asList(4));
        stationToRoutes.put("Chai Wan",stationToRoutes58);
        ArrayList<Integer> stationToRoutes59= new ArrayList<>(Arrays.asList(5));
        stationToRoutes.put("Tung Chung",stationToRoutes59);
        ArrayList<Integer> stationToRoutes60= new ArrayList<>(Arrays.asList(5, 9));
        stationToRoutes.put("Sunny Bay",stationToRoutes60);
        ArrayList<Integer> stationToRoutes61= new ArrayList<>(Arrays.asList(5, 6));
        stationToRoutes.put("Tsing Yi",stationToRoutes61);
        ArrayList<Integer> stationToRoutes62= new ArrayList<>(Arrays.asList(5, 8));
        stationToRoutes.put("Nam Cheong",stationToRoutes62);
        ArrayList<Integer> stationToRoutes63= new ArrayList<>(Arrays.asList(5));
        stationToRoutes.put("Olympic",stationToRoutes63);
        ArrayList<Integer> stationToRoutes64= new ArrayList<>(Arrays.asList(5, 6));
        stationToRoutes.put("Kowloon",stationToRoutes64);
        ArrayList<Integer> stationToRoutes65= new ArrayList<>(Arrays.asList(5, 6));
        stationToRoutes.put("Hong Kong",stationToRoutes65);
        ArrayList<Integer> stationToRoutes66= new ArrayList<>(Arrays.asList(6));
        stationToRoutes.put("AsiaWorld–Expo",stationToRoutes66);
        ArrayList<Integer> stationToRoutes67= new ArrayList<>(Arrays.asList(6));
        stationToRoutes.put("Airport",stationToRoutes67);
        ArrayList<Integer> stationToRoutes68= new ArrayList<>(Arrays.asList(7));
        stationToRoutes.put("Po Lam",stationToRoutes68);
        ArrayList<Integer> stationToRoutes69= new ArrayList<>(Arrays.asList(7));
        stationToRoutes.put("Hang Hau",stationToRoutes69);
        ArrayList<Integer> stationToRoutes70= new ArrayList<>(Arrays.asList(7));
        stationToRoutes.put("LOHAS Park",stationToRoutes70);
        ArrayList<Integer> stationToRoutes71= new ArrayList<>(Arrays.asList(7));
        stationToRoutes.put("Tseung Kwan O",stationToRoutes71);
        ArrayList<Integer> stationToRoutes72= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Wu Kai Sha",stationToRoutes72);
        ArrayList<Integer> stationToRoutes73= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Ma On Shan",stationToRoutes73);
        ArrayList<Integer> stationToRoutes74= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Heng On",stationToRoutes74);
        ArrayList<Integer> stationToRoutes75= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Tai Shui Hang",stationToRoutes75);
        ArrayList<Integer> stationToRoutes76= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Shek Mun",stationToRoutes76);
        ArrayList<Integer> stationToRoutes77= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("City One",stationToRoutes77);
        ArrayList<Integer> stationToRoutes78= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Sha Tin Wai",stationToRoutes78);
        ArrayList<Integer> stationToRoutes79= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Che Kung Temple",stationToRoutes79);
        ArrayList<Integer> stationToRoutes80= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Hin Keng",stationToRoutes80);
        ArrayList<Integer> stationToRoutes81= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Kai Tak",stationToRoutes81);
        ArrayList<Integer> stationToRoutes82= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Sung Wong Toi",stationToRoutes82);
        ArrayList<Integer> stationToRoutes83= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("To Kwa Wan",stationToRoutes83);
        ArrayList<Integer> stationToRoutes84= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Hung Hom",stationToRoutes84);
        ArrayList<Integer> stationToRoutes85= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("East Tsim Sha Tsui",stationToRoutes85);
        ArrayList<Integer> stationToRoutes86= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Austin",stationToRoutes86);
        ArrayList<Integer> stationToRoutes87= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Tsuen Wan West",stationToRoutes87);
        ArrayList<Integer> stationToRoutes88= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Kam Sheung Road",stationToRoutes88);
        ArrayList<Integer> stationToRoutes89= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Yuen Long",stationToRoutes89);
        ArrayList<Integer> stationToRoutes90= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Long Ping",stationToRoutes90);
        ArrayList<Integer> stationToRoutes91= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Tin Shui Wai",stationToRoutes91);
        ArrayList<Integer> stationToRoutes92= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Siu Hong",stationToRoutes92);
        ArrayList<Integer> stationToRoutes93= new ArrayList<>(Arrays.asList(8));
        stationToRoutes.put("Tuen Mun",stationToRoutes93);
        ArrayList<Integer> stationToRoutes94= new ArrayList<>(Arrays.asList(9));
        stationToRoutes.put("Disneyland Resort",stationToRoutes94);
        ArrayList<Integer> stationToRoutes95= new ArrayList<>(Arrays.asList(10));
        stationToRoutes.put("South Horizons",stationToRoutes95);
        ArrayList<Integer> stationToRoutes96= new ArrayList<>(Arrays.asList(10));
        stationToRoutes.put("Lei Tung",stationToRoutes96);
        ArrayList<Integer> stationToRoutes97= new ArrayList<>(Arrays.asList(10));
        stationToRoutes.put("Wong Chuk Hang",stationToRoutes97);
        ArrayList<Integer> stationToRoutes98= new ArrayList<>(Arrays.asList(10));
        stationToRoutes.put("Ocean Park",stationToRoutes98);

        for(int i:routesToStation.keySet()){
            List<String> station_curr = routesToStation.get(i);
            for(int j = 0;j<station_curr.size()-1;j++){
                String first = station_curr.get(j);
                String second = station_curr.get(j+1);
                AddStation(first,second,i,true);
            }

        }
    }

    public void AddStation(String station, String destination, int route,boolean repeat){
        if(!adjacencyList.containsKey(station)){
            adjacencyList.put(station,new ArrayList<>());
        }
        Node nodeInfo = new Node(destination,route);
        adjacencyList.get(station).add(nodeInfo);
        //A condition that checks if the Node adjacency is already added so that it doesn't keep adding
        if(repeat){
            AddStation(destination, station, route,false);
        }
    }

    public Track[] getInitialTrack(String station){

        List<Integer> routeLines = stationToRoutes.getOrDefault(station, null);
        int n = routeLines.size();
        Track[] ans = new Track[n];
        for(int i = 0;i<n;i++){
            ArrayList<Integer> r = new ArrayList<>();
            r.add(routeLines.get(i));
            ArrayList<String> s = new ArrayList<>();
            s.add("Start at station " + station + " in route " + String.valueOf(routeLines.get(i)));
            s.add(station);
            ans[i] = new Track(0,0,station,s,new ArrayList<>(),routeLines.get(i),r);
        }
        return ans;

    }

    public Map<String,Object> findDirection(String station, String finalDestination){
        PriorityQueue<Track> minHeap = new PriorityQueue<>(Comparator.comparingInt(a->a.totalStations));
        for(Track track:getInitialTrack(station)){
            minHeap.offer(track);
        }
        ArrayList<Solution> result = new ArrayList<>();
        int minStat = 200;
        int minTurns = 200;
        while(!minHeap.isEmpty()){
            Track currentTrack = minHeap.poll();
            String current = currentTrack.currentStation;
            if(currentTrack.totalStations >= minStat && currentTrack.totalTurns >= minTurns){
                continue;
            }
            if(current.equals(finalDestination)){
                Solution newSolution = new Solution(currentTrack.totalStations,currentTrack.totalTurns,currentTrack.changes,currentTrack.allStations);
                result.add(newSolution);
                minStat = Math.min(currentTrack.totalStations,minStat);
                minTurns = Math.min(currentTrack.totalTurns,minTurns);
                continue;
            }

            if(adjacencyList.containsKey(current)){
                List<Node> adj = adjacencyList.get(current);
                for(Node node: adj){
                    String tempDestination = node.destination;
                    if(currentTrack.allStations.contains(tempDestination)){
                        continue;
                    }
                    List<Integer> routeLines = stationToRoutes.get(tempDestination);
                    for(int route: routeLines){
                        if(route != currentTrack.currentRoute && currentTrack.routes.contains(route)){
                            continue;
                        }
                        if(!stationToRoutes.get(current).contains(route)){
                            continue;
                        }
                        //NOTE THAT IS A SHALLOW COPY (MEANING THAT THE OBJECT REFERENCES IN THE ELEMENTS REMAIN TO THE SAME)
                        ArrayList<String> newAllStations = new ArrayList<>(currentTrack.allStations);
                        ArrayList<Changes> newChanges =new ArrayList<>(currentTrack.changes);
                        ArrayList<Integer> newRoutes = new ArrayList<>(currentTrack.routes);
                        newAllStations.add(tempDestination);
                        int destRoute = currentTrack.currentRoute;
                        int turns = currentTrack.totalTurns;
                        if(route != currentTrack.currentRoute){
                            Changes differences = new Changes(current, currentTrack.currentRoute, route);
                            destRoute = route;
                            turns++;
                            newChanges.add(differences);
                            newRoutes.add(route);
                        }
                        Track newTrack = new Track(currentTrack.totalStations+1,turns,tempDestination,newAllStations,newChanges,destRoute,newRoutes);
                        minHeap.offer(newTrack);
                    }
                }
            }
        }
        Map<String, Object> jsonres = new HashMap<>();
        int stationIndex = 0;
        int turnIndex = 0;
        for(int i = 0;i<result.size();i++){
            Solution sol = result.get(i);
            if(sol.stations < result.get(stationIndex).stations){
                stationIndex = i;
            }
            else if(sol.stations == result.get(stationIndex).stations && sol.turnings < result.get(stationIndex).turnings){
                stationIndex = i;
            }
            if(sol.turnings < result.get(turnIndex).turnings){
                turnIndex = i;
            }
            else if(sol.turnings == result.get(turnIndex).turnings && sol.stations < result.get(turnIndex).stations){
                turnIndex=  i;
            }
        }
        Solution lowestStation = result.get(stationIndex);
        Solution lowestTurns = result.get(turnIndex);

        ArrayList<String> stationChanges = new ArrayList<>();
        ArrayList<String> turnsChanges = new ArrayList<>();
        for(Changes change: lowestStation.changes){
            String changing = change.station;
            int og = change.ogline;
            int new_one = change.newline;

            String toAdd = "Switch from route line " + idToName.get(og) + "-" + String.valueOf(og) + " to route line " + idToName.get(new_one) + "-" + String.valueOf(new_one) + " at station " + changing + " ";
            stationChanges.add(toAdd);
        }
        for(Changes change: lowestTurns.changes){
            String changing = change.station;
            int og = change.ogline;
            int new_one = change.newline;
            String toAdd = "Switch from route line " + idToName.get(og) + "-" + String.valueOf(og) + " to route line " + idToName.get(new_one) + "-" + String.valueOf(new_one) + " at station " + changing + " ";
            turnsChanges.add(toAdd);
        }
        Map<String, Object> stationBasedMap = new HashMap<>();
        stationBasedMap.put("stations",String.valueOf(lowestStation.stations+1));
        stationBasedMap.put("turns",String.valueOf(lowestStation.turnings));
        stationBasedMap.put("switching",stationChanges);
        stationBasedMap.put("stationlist",lowestStation.allStations);
        Map<String, Object> turnBasedMap = new HashMap<>();
        turnBasedMap.put("stations",String.valueOf(lowestTurns.stations+1));
        turnBasedMap.put("turns",String.valueOf(lowestTurns.turnings));
        turnBasedMap.put("switching",turnsChanges);
        turnBasedMap.put("stationlist",lowestTurns.allStations);
        jsonres.put("Least Stations",stationBasedMap);
        jsonres.put("Least Switchings",turnBasedMap);
        return jsonres;
    }

}
