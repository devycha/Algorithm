/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/1946
 *
 * ? 제목: 신입 사원
 * ? 시간 제한: 2초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 언제나 최고만을 지향하는 굴지의 대기업 진영 주식회사가 신규 사원 채용을 실시한다. 인재 선발 시험은 1차 서류심사와 2차 면접시험으로 이루어진다. 최고만을 지향한다는 기업의 이념에 따라 그들은 최고의 인재들만을 사원으로 선발하고 싶어 한다.
 * 그래서 진영 주식회사는, 다른 모든 지원자와 비교했을 때 서류심사 성적과 면접시험 성적 중 적어도 하나가 다른 지원자보다 떨어지지 않는 자만 선발한다는 원칙을 세웠다. 즉, 어떤 지원자 A의 성적이 다른 어떤 지원자 B의 성적에 비해 서류 심사 결과와 면접 성적이 모두 떨어진다면 A는 결코 선발되지 않는다.
 * 이러한 조건을 만족시키면서, 진영 주식회사가 이번 신규 사원 채용에서 선발할 수 있는 신입사원의 최대 인원수를 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에는 테스트 케이스의 개수 T(1 ≤ T ≤ 20)가 주어진다. 각 테스트 케이스의 첫째 줄에 지원자의 숫자 N(1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N개 줄에는 각각의 지원자의 서류심사 성적, 면접 성적의 순위가 공백을 사이에 두고 한 줄에 주어진다. 두 성적 순위는 모두 1위부터 N위까지 동석차 없이 결정된다고 가정한다.
 *
 * 2    -> t
 * 5    -> n
 * 3 2  -> arr[3] = 2
 * 1 4  -> arr[1] = 4
 * 4 1  ...
 * 2 3
 * 5 5  -> arr[5] = 5;
 * 7    -> n
 * 3 6  -> ...
 * 7 3
 * 4 2
 * 1 4
 * 5 7
 * 2 5
 * 6 1
 *
 * ? 출력
 * 각 테스트 케이스에 대해서 진영 주식회사가 선발할 수 있는 신입사원의 최대 인원수를 한 줄에 하나씩 출력한다.
 *
 * 4
 * 3
 *
 * ? 채점 결과
 * * 시간: 0.828초
 * * 메모리: 295MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Silver.I;

import java.io.*;
import java.util.StringTokenizer;

public class S1_1946 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        StringBuffer sb = new StringBuffer();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            input();

            int bMax = arr[1];
            int count = 1;
            for (int j = 2; j <= n; j++) {
                if (bMax > arr[j]) {
                    bMax = arr[j];
                    count++;
                }
            }

            sb.append(count + "\n");
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }
    }
}
