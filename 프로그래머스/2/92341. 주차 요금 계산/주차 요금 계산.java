import java.util.*;

class Solution {
    
    List<Pair> carList = new ArrayList<>();
    int[] fee;
    
    public int[] solution(int[] fees, String[] records) {
        fee = fees;
    
        makeCarList(records);
        Collections.sort(carList); 
        
        calEachCarFee();
    
        
        int[] answer = new int[carList.size()];
        for (int i=0; i<carList.size(); i++) 
            answer[i] = carList.get(i).fee;
        
        return answer;
    }
    
    public void makeCarList(String[] records) {
        Map<String, String> map = new HashMap<>();
        
        for (String each : records) {
            String[] eachRecordArr = each.split(" ");
            
            String curTime = eachRecordArr[0];
            String carNumber = eachRecordArr[1];
            String inOut = eachRecordArr[2];
            
            //입차의 경우
            if (inOut.equals("IN")) {
                map.put(carNumber, curTime);
            }
            //출차의 경우
            else {
                String pastTime = map.get(carNumber);
                int totalTime = calTime (pastTime, curTime);
                
                boolean isExist = false;
                
                for (int i=0; i<carList.size(); i++) {
                    if (carList.get(i).carNumber.equals(carNumber)) {
                        carList.get(i).time += totalTime;        
                        isExist = true;
                    }
                }
                
                if (!isExist) {
                    carList.add(new Pair(carNumber, totalTime));
                }
                map.remove(carNumber);
            }
        }
        
        for (String carNumber : map.keySet()) {
            String curTime = "23:59";
            String pastTime = map.get(carNumber);
            int totalTime = calTime (pastTime, curTime);
                
            boolean isExist = false;
                
            for (int i=0; i<carList.size(); i++) {
                if (carList.get(i).carNumber.equals(carNumber)) {
                    carList.get(i).time += totalTime;        
                    isExist = true;
                }
            }
                
            if (!isExist) {
                carList.add(new Pair(carNumber, totalTime));
            }
        }
    }
    
    public int calTime (String pastTime, String curTime) {
        String[] past = pastTime.split(":");
        String[] cur = curTime.split(":");
        
        int h = Integer.parseInt(cur[0]) - Integer.parseInt(past[0]);
        int m = Integer.parseInt(cur[1]) - Integer.parseInt(past[1]);
        
        return h*60 + m;
    }
    
    public void calEachCarFee() {
        carList.stream().forEach(each -> each.fee = calFee(each.time));
    }
    
    public int calFee(int time) {
        int totalFee = 0;
        if (time <= fee[0]) return fee[1];
        else {
            time -= fee[0];
            totalFee += fee[1];
            int temp = time / fee[2];
            if (time % fee[2] != 0) temp++;
            return temp * fee[3] + fee[1];
        } 
            
    }
}

class Pair implements Comparable<Pair>{
    String carNumber;
    int time;
    int fee;
    
    public Pair (String carNumber, int time) {
        this.carNumber = carNumber;
        this.time = time;
        this.fee = 0;
    }
    
    @Override
    public int compareTo(Pair other) {
        return this.carNumber.compareTo(other.carNumber);
    }
}