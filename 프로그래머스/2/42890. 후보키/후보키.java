import java.util.*;

class Solution {
    Set<String> remainColumnSet = new HashSet<>();
    Set<String> candidateKeySet = new HashSet<>();
    String[][] g_relation;
    
    public int solution(String[][] relation) {
        g_relation = relation;
        int columnSize = relation[0].length;
        init_remainColumnSet(columnSize);
        getCandidateKey(1);

        return candidateKeySet.size();
    }
    
    private void init_remainColumnSet(int size) {
        for (int i=0; i<size; i++) {
            remainColumnSet.add(String.valueOf(i));
        }
    }
    
    Set<String> keyCombSet = new HashSet<>();
    List<String> remainColumnList;
    
    private void getCandidateKey (int size) {
        if (remainColumnSet.size() < size) return;
        
        remainColumnList = new ArrayList<>(remainColumnSet);
        keyCombSet.clear();
        
        init_keyCombList("", size, 0);
        
        for (String eachComb : keyCombSet) {
            if (isCandidateKey(eachComb)) {
                candidateKeySet.add(eachComb);
            }
        }
        
        getCandidateKey(++size);
    }
    
    private Boolean isCandidateKey (String key) {
        Set<String> set = new HashSet<>();
        for (String[] eachColumn : g_relation) {
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<key.length(); i++) {
                int index = key.charAt(i) - '0';
                sb.append(eachColumn[index]);
            }
            if (set.contains(sb.toString())) {
                return false;
            }
            set.add(sb.toString());
        }
        
        for (String eachKey : candidateKeySet) {
            Set<Integer> tempSet = new HashSet<>();
            for (int i=0; i<eachKey.length(); i++) {
                tempSet.add(eachKey.charAt(i)-'0');
            }
            for (int i=0; i<key.length(); i++) {
                if (tempSet.contains(key.charAt(i)-'0')) {
                    tempSet.remove(key.charAt(i)-'0');
                }
            }
            if (tempSet.isEmpty()) return false;
        }
        return true;
    }
    
    private void init_keyCombList(String str, int len, int cur) {
        if (str.length() == len) keyCombSet.add(str);
        if (cur >= remainColumnList.size()) return;
        
        init_keyCombList(str, len, cur+1);
        
        StringBuilder sb = new StringBuilder(str);
        sb.append(remainColumnList.get(cur));
        init_keyCombList(sb.toString(), len, cur+1);
    }
}