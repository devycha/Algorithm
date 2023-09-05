// https://school.programmers.co.kr/learn/courses/30/lessons/176962

import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        int p = 0;
        
        Arrays.sort(plans, (a, b) -> {
            String[] aTime = a[1].split(":");
            String[] bTime = b[1].split(":");

            if (aTime[0].equals(bTime[0])) {
                return Integer.parseInt(aTime[1]) - Integer.parseInt(bTime[1]);
            }

            return Integer.parseInt(aTime[0]) - Integer.parseInt(bTime[0]);    
        });
        
        Stack<String[]> stack = new Stack<>();
        
        for (int i = 0; i < plans.length; i++) {
            if (i == plans.length - 1) {
              System.out.println(plans[i][0] + " finished");
              answer[p++] = plans[i][0];
              // Stack으로 넘어가기
              while (!stack.isEmpty()) {
                answer[p++] = stack.pop()[0];
              }
            } else {
              int gap = timeGap(plans[i][1], plans[i+1][1]);
              
              if (gap >= Integer.parseInt(plans[i][2])) {
                answer[p++] = plans[i][0];
                gap -= Integer.parseInt(plans[i][2]);
                System.out.println(plans[i][0] + " finished" + ", gap : " + gap);
                // 남은 시간동안 스택에 쌓아온 일 하기
                while (gap > 0 && !stack.isEmpty()) {
                  String[] task = stack.pop();
                  int leftTime = Integer.parseInt(task[1]);
                  if (leftTime > gap) {
                    leftTime -= gap;
                    gap = 0;
                    stack.push(new String[] {task[0], Integer.toString(leftTime)});
                  } else {
                    System.out.println(task[0] + " finished" + ", gap : " + gap);
                    answer[p++] = task[0];
                    gap -= leftTime;
                  }
                }
              } else {
                int left = Integer.parseInt(plans[i][2]) - gap;
                stack.push(new String[] {plans[i][0], Integer.toString(left)});
              }
            }
        }
        
        return answer;
    }
    
    public int timeGap(String a, String b) {
        int aHour = Integer.parseInt(a.split(":")[0]);
        int bHour = Integer.parseInt(b.split(":")[0]);
        int aMinute = Integer.parseInt(a.split(":")[1]);
        int bMinute = Integer.parseInt(b.split(":")[1]);
        return 60 * bHour + bMinute - 60*aHour - aMinute;
      
    }
}