// https://school.programmers.co.kr/learn/courses/30/lessons/172928

import java.util.*;

class Solution {
    public int[] solution(String[] park, String[] routes) {
        int[] start = new int[] {0, 0};
        
        HashMap<String, int[]> hm = new HashMap<>();
        hm.put("N", new int[] {-1, 0});
        hm.put("S", new int[] {1, 0});
        hm.put("W", new int[] {0, -1});
        hm.put("E", new int[] {0, 1});
        
        char[][] board = new char[park.length][park[0].length()];
        for (int i = 0; i < park.length; i++) {
            char[] line = park[i].toCharArray();
            for (int j = 0; j < line.length; j++) {
                board[i][j] = line[j];
                if (board[i][j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        
        for (String route : routes) {
            String way = route.split(" ")[0];
            int weight = Integer.parseInt(route.split(" ")[1]);
            int nx = start[0];
            int ny = start[1];
            
            for (int i = 0; i < weight; i++) {
                nx += hm.get(way)[0];
                ny += hm.get(way)[1];    
                
                if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length || board[nx][ny] == 'X') {
                    break;
                }
                
                if (i == weight-1) {
                    start[0] = nx;
                    start[1] = ny;   
                }
            }
        }
        
        return start;
    }
}