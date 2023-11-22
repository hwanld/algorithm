import java.util.*;

class Solution {
    Map<Integer, List<Node>> pathMap = new HashMap<>();
    int[] answer;
    int[] g_gates;
    int[] g_summits;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        answer = new int[2];
        answer[0] = Integer.MAX_VALUE;
        answer[1] = Integer.MAX_VALUE;
        
        g_gates = gates;
        g_summits = summits;
        
        Arrays.sort(g_summits);
        
        initializePathMap(paths);
        dijkstra(n);
        
        return answer;
    }
    
    private void dijkstra (int n) {
        int[] minDisArr = new int[n+1];
        Arrays.fill(minDisArr, Integer.MAX_VALUE);

        Queue<Node> queue = new ArrayDeque<>();
        
        for (int eachGate : g_gates) {
            queue.add(new Node(eachGate, 0));
            minDisArr[eachGate] = 0;
        }
        
        while(!queue.isEmpty()) {
            Node curNode = queue.poll();
            
            if (curNode.intensity > minDisArr[curNode.des]) continue;
            if (isSummits(curNode.des)) {
                if (minDisArr[curNode.des] > curNode.intensity) {
                    minDisArr[curNode.des] = curNode.intensity;
                }
                continue;
            }
            
            List<Node> nextPath = pathMap.get(curNode.des);
            for (Node eachNode : nextPath) {
                int dis = Math.max(minDisArr[curNode.des], eachNode.intensity);
                if (minDisArr[eachNode.des] > dis) {
                    minDisArr[eachNode.des] = dis;
                    queue.add(new Node(eachNode.des, dis));
                }
            }
        }
        
        for (int eachSummit : g_summits) {
            if (minDisArr[eachSummit] < answer[1]) {
                answer[0] = eachSummit;
                answer[1] = minDisArr[eachSummit];
            }
        }
        
    }
    
    private boolean isGates (int des) {
        for (int eachGates : g_gates) {
            if (des == eachGates) return true;
        }
        return false;
    }
    
    private boolean isSummits (int des) {
        for (int eachSummits : g_summits) {
            if (des == eachSummits) return true;
        }
        return false;
    }
    
    private void initializePathMap(int[][] paths) {
        for (int[] eachPath : paths) {
            int stp = eachPath[0];
            int des = eachPath[1];
            int dis = eachPath[2];
            
            Node newNode = new Node(des, dis);
            List<Node> nodeList = pathMap.getOrDefault(stp, new ArrayList<Node>());
            nodeList.add(newNode);
            pathMap.put(stp, nodeList);
            
            newNode = new Node(stp, dis);
            nodeList = pathMap.getOrDefault(des, new ArrayList<Node>());
            nodeList.add(newNode);
            pathMap.put(des, nodeList);
        }
    }
}

class Node implements Comparable<Node> {
    int des;
    int intensity;
    
    public Node (int des, int intensity) {
        this.des = des;
        this.intensity = intensity;
    }
    
    @Override
    public int compareTo (Node other) {
        return this.intensity - other.intensity;
    }
}