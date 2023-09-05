// https://school.programmers.co.kr/learn/courses/30/lessons/169199

import java.util.*;

class Solution {
    public int solution(String[] board) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        int[] robot = new int[2];
        int[] goal = new int[2];
        int[][] road = new int[board.length][board[0].length()];

        for (int i = 0; i < board.length; i++) {
          char[] line = board[i].toCharArray();
          for (int j = 0; j < line.length; j++) {
            if (line[j] == 'R') {
              robot[0] = i;
              robot[1] = j;
            } else if (line[j] == 'G') {
              goal[0] = i;
              goal[1] = j;
            } else if (line[j] == 'D') {
              road[i][j] = 1;
            }
          }
        }

        Queue<int[]> queue = new LinkedList<>();
        int[][] visit = new int[road.length][road[0].length];
        visit[robot[0]][robot[1]] = 1;
        queue.add(new int[] {robot[0], robot[1]});

        while (!queue.isEmpty()) {
          int[] cur = queue.poll();

          if (cur[0] == goal[0] && cur[1] == goal[1]) {
            return visit[cur[0]][cur[1]] - 1;
          }
            for (int k = 0; k < 4; k++) {
                int nx = cur[0];
                int ny = cur[1];
                
                while (true) {
                    if (nx + dx[k] < 0 || nx + dx[k] >= road.length) {
                      break;
                    }

                    if (ny + dy[k] < 0 || ny + dy[k] >= road[0].length) {
                      break;
                    }

                    if (road[nx + dx[k]][ny + dy[k]] == 1) {
                      break;
                    }

                    nx += dx[k];
                    ny += dy[k];   
                }

                if (visit[nx][ny] == 0) {
                  visit[nx][ny] = visit[cur[0]][cur[1]] + 1;
                  queue.add(new int[] {nx, ny});
                }
            }
        }
        
        return -1;
    }
}