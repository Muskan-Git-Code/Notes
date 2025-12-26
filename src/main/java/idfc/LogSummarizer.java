package idfc;

import java.util.*;

public class LogSummarizer {

    static class Pair{
        int level;  String type; String endpt;   int statusCode; int resTime;

        Pair(int level, String type, String endpt, int statusCode, int resTime){
            this.level =  level;    this.type= type;    this.endpt= endpt;  this.statusCode= statusCode;    this.resTime= resTime;
        }
    }

    class Output1{
        String endPt;   int reqs;
        Output1(String endPt, int reqs){
            this.endPt= endPt;  this.reqs= reqs;
        }
    }

    void printOut1(HashMap<String, List<Integer>> reqCt){
        PriorityQueue<Output1> pq= new PriorityQueue<>(
                (p1, p2) -> {
                    return Integer.compare(p2.reqs, p1.reqs);
                }
        );

        ArrayList<Integer> reqPerEndPt= new ArrayList<>();
        for(String key: reqCt.keySet()){
            pq.add( new Output1(key, reqCt.get(key).get(0)) );
        }

        while(!pq.isEmpty()){
            Output1 ans= pq.remove();
            System.out.println(ans.endPt+ " : "+ ans.reqs);
        }
    }

    void printOut2(HashMap<String, List<Integer>> reqCt, HashMap<String, Integer> optionsCt){
        for(String key: reqCt.keySet()){
            List<Integer> curr= reqCt.get(key);

            long ans= Math.round( (double)curr.get(1)/ ((double)curr.get(0) - (double) optionsCt.getOrDefault(key, 0)));
            System.out.println(key+ " : "+ ans + " ms");
        }
    }

    void logAnalyzer(ArrayList<Pair> list){
        HashMap<String, List<Integer>> reqCt= new HashMap<>();    // endpt, noOf Req, adding all times
        Output1 slowTime= null;     int errorCt=0;
        HashMap<String, Integer> optionsCt= new HashMap<>();    // endPt, no of optional requests

        for(int i=0; i<list.size(); i++){
            Pair curr= list.get(i);


            List<Integer> currVal= reqCt.getOrDefault(curr.endpt, Arrays.asList(0, 0));
            reqCt.put(curr.endpt, Arrays.asList(currVal.get(0)+1, currVal.get(1)+curr.resTime ));


            if( curr.type.equals("OPTIONS") ){
                optionsCt.put(curr.endpt, optionsCt.getOrDefault(optionsCt, 0)+1);
            }

            if(curr.type!="OPTIONS" && (slowTime== null || (slowTime.reqs < curr.resTime)) ){
                slowTime= new Output1(curr.endpt, curr.resTime);
            }

            if(curr.statusCode >= 400){
                errorCt+= 1;
            }
        }


        // output 1:
        System.out.println("\n Requests per endpoint: ");
        printOut1(reqCt);


        // output 2:
        System.out.println("\n Average Response Time: ");
        printOut2(reqCt, optionsCt);

        // output 3:
        System.out.println("\n Slowest call time: ");
        System.out.println(slowTime.endPt + " : " + slowTime.reqs + " ms");


        // output 4:
        System.out.println("\n Error Rate: ");
        Double ansErrorRate= ((double)errorCt/ (double)list.size())*100;
        System.out.println( ansErrorRate + " % ");

    }

    public static void main(String[] args) {

        // Log Analyser

        /*
        Log Level: Integer (1: INFO, 2: DEBUG, 3: WARN, 4: ERROR)
        EndPoint: String
        StatusCOde: Integer
        Response TIme: Integer
        * */

        ArrayList<Pair> list= new ArrayList<>();

        list.add( new Pair(1, "GET", "/api/users", 200, 120));
        list.add( new Pair(1, "GET", "/api/orders", 404, 60));
        list.add( new Pair(1, "GET", "/api/users", 500, 95));
        list.add( new Pair(1, "GET", "/api/orders", 200, 180));
        list.add( new Pair(1, "GET", "/api/orders", 200, 310));
        list.add( new Pair(1, "OPTIONS", "/api/orders", 204, 0));
        list.add( new Pair(1, "GET", "/api/users", 201, 250));


        LogSummarizer m= new LogSummarizer();
        m.logAnalyzer(list);

    }
}
