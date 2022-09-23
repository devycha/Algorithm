/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/14267
 *
 * ? 제목: 회사 문화 1
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 영선회사에는 매우 좋은 문화가 있는데, 바로 상사가 직속 부하를 칭찬하면 그 부하가 부하의 직속 부하를 연쇄적으로 칭찬하는 내리 칭찬이 있다. 즉, 상사가 한 직속 부하를 칭찬하면 그 부하의 모든 부하들이 칭찬을 받는다.
 * 모든 칭찬에는 칭찬의 정도를 의미하는 수치가 있는데, 이 수치 또한 부하들에게 똑같이 칭찬 받는다.
 * 직속 상사와 직속 부하관계에 대해 주어지고, 칭찬에 대한 정보가 주어질 때, 각자 얼마의 칭찬을 받았는지 출력하시오,
 *
 * ? 입력 & 파싱
 * 첫째 줄에는 회사의 직원 수 n명, 최초의 칭찬의 횟수 m이 주어진다. 직원은 1번부터 n번까지 번호가 매겨져 있다. (2 ≤ n, m ≤ 100,000)
 * 둘째 줄에는 직원 n명의 직속 상사의 번호가 주어진다. 직속 상사의 번호는 자신의 번호보다 작으며, 최종적으로 1번이 사장이다. 1번의 경우, 상사가 없으므로 -1이 입력된다.
 * 다음 m줄에는 직속 상사로부터 칭찬을 받은 직원 번호 i, 칭찬의 수치 w가 주어진다. (2 ≤ i ≤ n, 1 ≤ w ≤ 1,000)
 * 사장은 상사가 없으므로 칭찬을 받지 않는다.
 *
 * 5 3          -> n m
 * -1 1 2 3 4   -> arr[1] ~ arr[n]
 * 2 2          -> sub p
 * 3 4          -> sub p
 * 5 6          -> sub p
 *
 * ? 출력
 * 1번부터 n번의 직원까지 칭찬을 받은 정도를 출력하시오.
 *
 * 0 2 6 6 12
 *
 * ? 채점 결과
 * * 시간: 0.54초
 * * 메모리: 65MB
 * * 언어: JAVA8
 * * 시도: 3
 */
package Gold.IV;

import java.io.*;
import java.util.*;

public class G4_14267 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static int[] arr;

    // * 초기 설정
    static int[] point;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int sub = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            point[sub] += p; // 시작 포인트 활성화
        }

        // * 2. DP(Tabulation: Bottom-Up)
        for (int i = 2; i <= n; i++) {
            point[i] += point[arr[i]];
        }

        // * 3. 출력
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= n; i++) {
            sb.append(point[i] + " ");
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    // 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n+1];
        point = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

}
