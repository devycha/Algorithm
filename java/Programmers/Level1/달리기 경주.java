// https://school.programmers.co.kr/learn/courses/30/lessons/178871

import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        HashMap<String, Integer> hm = new HashMap<>();
        HashMap<Integer, String> hm2 = new HashMap<>();
        String[] answer = new String[players.length];
        
        for (int i = 0; i < players.length; i++) {
            hm.put(players[i], i);
            hm2.put(i, players[i]);
        }
        
        for (String call : callings) {
            // 바로 앞의 선수 이름
            String loser = hm2.get(hm.get(call)-1);

            hm.put(call, hm.get(call) - 1);
            hm.put(loser, hm.get(loser) + 1);
            hm2.put(hm.get(call), call);
            hm2.put(hm.get(loser), loser);
        }
        
        hm.forEach((key, value) -> {
            answer[value] = key;
        });
        
        return answer;
    }
}