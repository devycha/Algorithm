// https://school.programmers.co.kr/learn/courses/30/lessons/181187

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        for (int x = 1; x < r2; x++) {
            double sy = Math.sqrt(Math.pow(r1, 2) - Math.pow(x, 2));
            double by = Math.sqrt(Math.pow(r2, 2) - Math.pow(x, 2));
            long count = (long) by - (long) sy;
            if (x == r1) {
                count -= 1;
            }
            
            answer += 4L * count;
            
            if (sy % 1 == 0) {
                answer += 4L;
            } 
        }
        
        answer += 4 * (r2 - r1 + 1);
        
        return answer;
    }
}