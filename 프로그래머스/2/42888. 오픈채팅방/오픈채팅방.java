import java.util.*;

class Solution {
    static final String enter = "님이 들어왔습니다.";
    static final String leave = "님이 나갔습니다.";
    
    public String[] solution(String[] record) {
        List<String> result = new ArrayList<>();
        List<String> answerList = new ArrayList<>();
        List<String> uidList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        for (int i=0; i<record.length; i++) {
            String[] parseEachRecord = record[i].split(" ");
            
            String act = parseEachRecord[0];
            String uid = parseEachRecord[1];
            
            if (act.equals("Enter")) {
                String name = parseEachRecord[2];
                map.put(uid, name);
                uidList.add(uid);
                result.add(enter);
            }
            else if (act.equals("Leave")) {
                uidList.add(uid);
                result.add(leave);
            }
            else {
                String name = parseEachRecord[2];
                map.put(uid, name);
            }
        }
        
        for (int i=0; i<result.size(); i++) {
            String key = uidList.get(i);
            String name = map.get(key);
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(result.get(i));
            answerList.add(sb.toString());
        }
        
        String[] answer = new String[answerList.size()];
        answerList.toArray(answer);
        return answer;
    }
}