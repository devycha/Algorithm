// https://school.programmers.co.kr/learn/courses/30/lessons/172927

import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int dia = picks[0];
        int iron = picks[1];
        int stone = picks[2];
        int Minpiro = Integer.MAX_VALUE;
        
        HashMap<String, int[]> hm = new HashMap<>();
        hm.put("diamond", new int[] {1, 5, 25});
        hm.put("iron", new int[] {1, 1, 5});
        hm.put("stone", new int[] {1, 1, 1});
        
        // 현재 곡괭이, 남은 횟수, 현재 피로도, 광물 순서, dia, iron, stone
        // 0: dia
        // 1: iron
        // 2: stone
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[2] - b[2];           
        });
        
        if (dia > 0) {
            pq.add(new int[] {0, 5, 0, 0, dia-1, iron, stone});
        }
        
        if (iron > 0) {
            pq.add(new int[] {1, 5, 0, 0, dia, iron-1, stone});
        }
        
        if (stone > 0) {
            pq.add(new int[] {2, 5, 0, 0, dia, iron, stone-1});
        }
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[3] == minerals.length) {
                return cur[2];
            }

            int piro = hm.get(minerals[cur[3]])[cur[0]]; // 현재 곡괭이로 현재 위치한 광물을 캘 때 피로도
            cur[1]--; // 곡괭이 남은 횟수 감소
            
            if (cur[1] == 0) {
                if (cur[4] == 0 && cur[5] == 0 && cur[6] == 0) {
                  Minpiro = Math.min(Minpiro, cur[2] + piro);
                } else {
                  if (cur[4] > 0) {
                    pq.add(new int[] {0, 5, cur[2] + piro, cur[3]+1, cur[4]-1, cur[5], cur[6]});
                  }

                  if (cur[5] > 0) {
                      pq.add(new int[] {1, 5, cur[2] + piro, cur[3]+1, cur[4], cur[5]-1, cur[6]});
                  }

                  if (cur[6] > 0) {
                      pq.add(new int[] {2, 5, cur[2] + piro, cur[3]+1, cur[4], cur[5], cur[6]-1});
                  }
                }

            } else {
                pq.add(new int[] {cur[0], cur[1], cur[2] + piro, cur[3]+1, cur[4], cur[5], cur[6]});
            }
        }
        
        return Minpiro;
    }
}