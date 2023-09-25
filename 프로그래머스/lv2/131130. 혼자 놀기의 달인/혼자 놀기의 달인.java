import java.util.*;
import java.io.*;

class Solution {
    Scanner sc = new Scanner(System.in);
    List<Pair> itemList = new ArrayList<>();
    List<Set> circleList = new ArrayList<>();
    
    int answer = 0;

    public int solution(int[] cards) {
        
        for (int i=0; i<cards.length; i++) {
            Pair pair = new Pair(cards[i]-1, i);
            itemList.add(pair);    
        }
        
        for (Pair each : itemList) {
            findCircle(each);
            closeAll();
        }
        
        if (circleList.size()==1) return 0;
        
        int answer = 1;
        List<Integer> circleSize = new ArrayList<>();
        
        for (Set each : circleList) {
            circleSize.add(each.size());
        }
        
        Collections.sort(circleSize);
        
        for (Set each : circleList) {
            System.out.println(each.size());
        }
        
        return circleSize.get(circleSize.size()-1) * circleSize.get(circleSize.size()-2);
    }
    
    void closeAll() {
        for (Pair each : itemList) {
            each.close();
        }
    }
    
    void findCircle (Pair each) {
        
        for (Set eachSet : circleList) {
            if (eachSet.contains(each.card)) return;
        }
        
        Set<Integer> eachCircle = new HashSet<>();
        eachCircle.add(each.card);
        each.open();
        
        while(!itemList.get(each.card).isOpen) {
            each = itemList.get(each.card);
            each.open();
            eachCircle.add(each.card);
        }
        
        circleList.add(eachCircle);
    }
    
}


class Pair {
    public int card;
    public int box;
    public boolean isOpen;
    
    public Pair (int card, int box) {
        this.card = card;
        this.box = box;
        this.isOpen = false;
    }
    
    public void open() {
        this.isOpen = true;
    }
    
    public void close() {
        this.isOpen = false;
    }
}