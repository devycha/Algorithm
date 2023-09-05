// https://school.programmers.co.kr/learn/courses/30/lessons/178870

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {0, 0};
        int left = 0;
        int right = 0;
        int sum = 0;
        int gap = Integer.MAX_VALUE;
        
        while (left <= right && right < sequence.length) {
            sum += sequence[right];
            
            if (sum == k && gap > (right - left)) {
                gap = right - left;
                answer[0] = left;
                answer[1] = right;
            }
            
            while (sum >= k) {
                sum -= sequence[left];
                left++;
                
                if (sum == k && gap > (right - left)) {
                    gap = right - left;
                    answer[0] = left;
                    answer[1] = right;
                }
            }
            
            right++;
        }
        
        return answer;
    }
}