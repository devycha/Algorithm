/**
 * 문제URL
 * https://programmers.co.kr/learn/courses/30/lessons/42862?language=java
 */
class JogginSuit{
    public int solution(int n, int[] lost, int[] reserve) {
		int answer = 0;
		
		// 1. student 배열 생성. 인덱스 앞뒤 계산의 편의성을 위해서 크기를 2 크게 만듬
		int[] student = new int[n+2];
		
		// 2. reserve +1 / lost -1 
		for (int r : reserve) {
			student[r]++;
		}
		for (int l : lost) {
			student[l]--;
		}
		
		// 3. 1이면 여분이 있다고 판단해 앞뒤로 -1인 사람에게 체육복을 빌려줌 
		for (int i = 1; i <= n; i++) {
			if (student[i] == 1) {
				if (student[i - 1] == -1) {
					student[i]--;
					student[i - 1]++;
				} else if (student[i + 1] == -1) {
					student[i]--;
					student[i + 1]++;
				}
			}
		}
		
		// 4. 체육복을 갖고 있는 학생수를 계산
		for (int i = 1; i <= n; i++) {
			if (student[i] >= 0) {
				answer++;
			}
		}
		
		return answer;
    }
    
    // 다른사람의 set을 이요한 풀이
    public int solution2(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        // 1. 여분이 있는 학생과 도난 당한 학생을 set으로 만든다.
        HashSet<Integer> resSet = new HashSet<>();
        HashSet<Integer> lostSet = new HashSet<>();
        
        for (int i : reserve) {
            resSet.add(i);
        }
        
        for (int i : lost) {
            if (resSet.contains(i)) { // 여분이 있는 학생이 도난 당한 경우
                resSet.remove(i);
            } else {
                lostSet.add(i);
            }
        }
        
        // 2. 여분을 기준으로 앞뒤로 확인하여 체육복을 빌려줌
        for (int i : resSet) {
            if (lostSet.contains(i - 1)) {
                lostSet.remove(i - 1);
            } else if (lostSet.contains(i + 1)) {
                lostSet.remove(i + 1);
            }
        }
        
        // 3. 전체 학생 수에서 lostSet에 남은 학생수를 빼줌
        answer = n - lostSet.size();
        
        return answer;
    }
}
