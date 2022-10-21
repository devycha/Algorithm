/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/4811
 *
 * ? 제목: 알약
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 70세 박종수 할아버지는 매일 매일 약 반알을 먹는다. 손녀 선영이는 종수 할아버지에게 약이 N개 담긴 병을 선물로 주었다.
 * 첫째 날에 종수는 병에서 약 하나를 꺼낸다. 그 다음, 그 약을 반으로 쪼개서 한 조각은 먹고, 다른 조각은 다시 병에 넣는다.
 * 다음 날부터 종수는 병에서 약을 하나 꺼낸다. (약은 한 조각 전체 일 수도 있고, 쪼갠 반 조각 일 수도 있다) 반 조각이라면 그 약을 먹고, 아니라면 반을 쪼개서 한 조각을 먹고, 다른 조각은 다시 병에 넣는다.
 * 종수는 손녀에게 한 조각을 꺼낸 날에는 W를, 반 조각을 꺼낸 날에는 H 보낸다. 손녀는 할아버지에게 받은 문자를 종이에 기록해 놓는다. 총 2N일이 지나면 길이가 2N인 문자열이 만들어지게 된다. 이때, 가능한 서로 다른 문자열의 개수는 총 몇 개일까?
 *
 * ? 입력 & 파싱
 * 입력은 최대 1000개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스는 한 줄이며, 병에 들어있는 약의 개수 N ≤ 30 가 주어진다.
 * 입력의 마지막 줄에는 0이 하나 주어진다.
 *
 * 6    -> n
 * 1    -> n
 * 4    -> n
 * 2    -> n
 * 3    -> n
 * 30   -> n
 * 0    -> end
 *
 * ? 출력
 * 각 테스트 케이스에 대해서 가능한 문자열의 개수를 출력한다.
 *
 * 132
 * 1
 * 14
 * 2
 * 5
 * 3814986502092304
 *
 * ? 채점 결과
 * * 시간: 0.08초
 * * 메모리: 11MB
 * * 언어: JAVA8
 * * 시도: 2
 */
package Gold.IV;

import java.io.*;

public class G4_4811 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = -1;

        StringBuffer sb = new StringBuffer();
        long[] dp = new long[31];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= 30; i++) {
            long total = 0;
            for (int j = 0; j < i; j++) {
                total += (dp[j] * dp[i - j - 1]);
            }
            dp[i] = total;
        }

        while ((n = Integer.parseInt(br.readLine())) != 0) {
            sb.append(dp[n] + "\n");
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}
