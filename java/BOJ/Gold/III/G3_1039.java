/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/1039
 * 
 * ! 제목: 교환
 * ! 시간 제한: 2초
 * ! 메모리 제한: 128MB
 * 
 * ! 문제
 * 0으로 시작하지 않는 정수 N이 주어진다. 이때, M을 정수 N의 자릿수라고 했을 때, 다음과 같은 연산을 K번 수행한다.
 * 1 ≤ i < j ≤ M인 i와 j를 고른다. 그 다음, i번 위치의 숫자와 j번 위치의 숫자를 바꾼다. 이때, 바꾼 수가 0으로 시작하면 안 된다.
 * 위의 연산을 K번 했을 때, 나올 수 있는 수의 최댓값을 구하는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 정수 N과 K가 주어진다. N은 1,000,000보다 작거나 같은 자연수이고, K는 10보다 작거나 같은 자연수이다.
 *
 * 16375 1 -> num, k
 *
 * ! 출력
 * 첫째 줄에 문제에 주어진 연산을 K번 했을 때, 만들 수 있는 가장 큰 수를 출력한다. 만약 연산을 K번 할 수 없으면 -1을 출력한다.
 *
 * 76315
 *
 * ? 채점 결과
 * 시간: 204ms
 * 메모리: 59540KB
 * 언어: JAVA8
 */
package Gold.III;

import java.io.*;
import java.util.LinkedList;

public class G3_1039 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // 파싱
    static int num, k;
    
    // 초기 설정
    static int max = 0;
    static int[][] visited;

    // * 메인 함수
    public static void main(String[] args) throws IOException {
        String[] str = br.readLine().split(" ");
        num = Integer.parseInt(str[0]);
        k = Integer.parseInt(str[1]);
        visited = new int[k+1][1000001];

        bfs(num); // ! BFS
        bw.write(String.valueOf(max == 0 ? -1 : max)); // * 출력

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! BFS
    public static void bfs(int start) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {start, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int num = cur[0]; // * 현재 숫자
            int count = cur[1]; // * 현재 숫자의 자릿수 교환 횟수

            if (count == k) continue; // * K번만큼 바꿨다면 패스

            String s = Integer.toString(num); // * 현재 숫자를 문자열로 변환
            for (int i = 0; i < s.length()-1; i++) {
                for (int j = i+1; j < s.length(); j++) {
                    if (i == 0 && s.charAt(j) == '0') continue; // * 맨 앞자리와 0을 바꾸는 경우가 없이

                    String newStr = swap(s, i, j); // * 문자열의 i와 j를 스왑한 문자열을 반환
                    int newNum = Integer.parseInt(newStr);// * 위 문자열을 다시 숫자로 변환

                    // * count+1 횟수로 newNum의 숫자로 도달한 경우가 없을 때만
                    if (visited[count+1][newNum] == 0) {
                        visited[count+1][newNum] = 1; // * 방문표시 후
                        queue.add(new int[] {newNum, count+1}); // * 큐에 삽입
                    }
                }
            }
        }

        // * visited[k]의 숫자들 중 (총 K번 교환이 완료된 숫자들 중)
        for (int i = 0; i < visited[k].length; i++) {
            if (visited[k][i] != 0) { // * 방문표시가 되어있는 인덱스의 숫자들 중 가장 큰 값을 max에 저장
                if (max < i) {
                    max = i;
                }
            }
        }
    }

    // * 문자열과 i, j번째 인덱스의 문자를 서로 스왑하는 함수
    public static String swap(String s, int i, int  j) {
        char[] str = s.toCharArray();
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
        return String.valueOf(str);
    }


}
