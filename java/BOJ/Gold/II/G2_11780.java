/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/11780
 *
 * ? 제목: 플로이드 2
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * n(1 ≤ n ≤ 100)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1 ≤ m ≤ 100,000)개의 버스가 있다. 각 버스는 한 번 사용할 때 필요한 비용이 있다.
 * 모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 도시의 개수 n이 주어지고 둘째 줄에는 버스의 개수 m이 주어진다. 그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 버스의 정보는 버스의 시작 도시 a, 도착 도시 b, 한 번 타는데 필요한 비용 c로 이루어져 있다. 시작 도시와 도착 도시가 같은 경우는 없다. 비용은 100,000보다 작거나 같은 자연수이다.
 *
 * 5        -> n
 * 14       -> m
 * 1 2 2    -> a b c
 * 1 3 3    -> a b c
 * 1 4 1    -> a b c
 * 1 5 10   -> a b c
 * 2 4 2    -> a b c
 * 3 4 1    -> a b c
 * 3 5 1    -> a b c
 * 4 5 3    -> a b c
 * 3 5 10   -> a b c
 * 3 1 8    -> a b c
 * 1 4 2    -> a b c
 * 5 1 7    -> a b c
 * 3 4 2    -> a b c
 * 5 2 4    -> a b c
 *
 * ?출력
 * 먼저, n개의 줄을 출력해야 한다. i번째 줄에 출력하는 j번째 숫자는 도시 i에서 j로 가는데 필요한 최소 비용이다. 만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.
 * 그 다음에는 n×n개의 줄을 출력해야 한다. i×n+j번째 줄에는 도시 i에서 도시 j로 가는 최소 비용에 포함되어 있는 도시의 개수 k를 출력한다. 그 다음, 도시 i에서 도시 j로 가는 경로를 공백으로 구분해 출력한다. 이때, 도시 i와 도시 j도 출력해야 한다. 만약, i에서 j로 갈 수 없는 경우에는 0을 출력한다.
 *
 * 0 2 3 1 4
 * 12 0 15 2 5
 * 8 5 0 1 1
 * 10 7 13 0 3
 * 7 4 10 6 0
 * 0
 * 2 1 2
 * 2 1 3
 * 2 1 4
 * 3 1 3 5
 * 4 2 4 5 1
 * 0
 * 5 2 4 5 1 3
 * 2 2 4
 * 3 2 4 5
 * 2 3 1
 * 3 3 5 2
 * 0
 * 2 3 4
 * 2 3 5
 * 3 4 5 1
 * 3 4 5 2
 * 4 4 5 1 3
 * 0
 * 2 4 5
 * 2 5 1
 * 2 5 2
 * 3 5 1 3
 * 3 5 2 4
 * 0
 *
 * ? 채점 결과
 * * 시간: 552ms
 * * 메모리: 63MB
 * * 언어: JAVA8
*/
package Gold.II;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G2_11780 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 이동 비용이 나올 수 있는 최댓값 + 1 => MAX값이면 이동할 수 없음을 뜻함
    static final int MAX = 10000001;

    // * 파싱
    static int n, m; // 도시 개수 | 버스 개수
    static int[][] arr; // i -> j 로 가는 최소 비용 배열(플로이드 와샬)

    // * 초기 설정
    static ArrayList<Integer>[][] dist; // 최소 비용으로 이동 할 때 지나는 도시 배열

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        floyd(); // * 2. 플로이드-와샬

        // * 3. 출력 ( 최소비용 배열 출력 )
        StringBuffer sb = new StringBuffer();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i][j] == MAX) { // * 도착할 수 없을 때는 0을 출력
                    sb.append(0 + " ");
                } else {
                    sb.append(arr[i][j] + " ");
                }
            }
            sb.append("\n");
        }

        // * 4. 출력 ( 최소비용으로 이동할 때의 도시 개수와 경로 출력 )
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i][j] == 0 || arr[i][j] == MAX) { // * 자기자신에서 자기자신으로 가는 경로 혹은 도착할 수 없는 경로일 때 0 출력
                    sb.append(0);
                } else { 
                    sb.append((dist[i][j].size() + 2) + " "); // * i -> j로 최소비용으로 이동했을 때 지나간 도시의 갯수(dist[i][j].size()) + 시작점,종료점(2)
                    sb.append(i + " "); // * 시작점
                    
                    // * i -> j 로 최소비용으로 이동했을 때 지나는 도시가 1개라도 있을 때 해당 도시들을 공백으로 구분해서 출력
                    if (dist[i][j].size() > 0) { 
                        for (int k : dist[i][j]) {
                            sb.append(k + " ");
                        }
                    }
                    sb.append(j + " "); // * 종료점
                }
                sb.append("\n");
            }
        }

        bw.write(sb.toString());

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! 플로이드-와샬
    public static void floyd() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    // * 자기 자신에서 자기 자신으로 도착하는 경로는 없기 때문에 해당 경우 패스
                    if (i == k || k == j) continue;
                    
                    // * 최소비용이 갱신되는 시점에서 
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j]; // * 최소비용 갱신
                        
                        dist[i][j] = new ArrayList<>(); // * i -> j를 최소비용으로 갈 때 지나는 도시 초기화
                        dist[i][j].addAll(dist[i][k]); // * i -> k를 최소비용으로 갈 때 지나는 도시 삽입
                        dist[i][j].add(k); // * k도시 삽입
                        dist[i][j].addAll(dist[k][j]); // * k -> j를 최소비용으로 갈 때 지나는 도시 삽입
                    }
                }
            }
        }
    }

    // ! 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        arr = new int[n+1][n+1];
        for (int[] a : arr) {
            Arrays.fill(a, MAX);
        }

        for (int i = 1; i <= n; i++) {
            arr[i][i] = 0;
        }

        dist = new ArrayList[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (arr[a][b] == MAX) {
                arr[a][b] = c;
            } else {
                arr[a][b] = Math.min(arr[a][b], c);
            }
        }
    }
}
