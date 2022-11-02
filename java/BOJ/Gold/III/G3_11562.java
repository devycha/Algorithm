/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/11562
 *
 * ? 제목: 백양로 브레이크
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 서울 소재 Y모 대학교에서 대규모 공사를 진행하면서, 학교가 마치 미로처럼 변해버리고 말았다. 공사 이전까지는 어떤 건물에서 출발하더라도 다른 모든 건물로 갈 수 있는 길이 있었으나, 공사가 진행되면서 어떻게 한 건진 알 수 없지만 일방통행만 가능한 길이 많이 늘고 말았다.
 * 컴퓨터과학과 학생 남규는 전공 수업을 듣고 교양 수업을 들으러 가던 중 길을 잃어 3일 밤낮을 헤매다가 공학관에서 종합관으로 가는 길은 존재하지 않는다는 결론을 내렸다.
 * 3일 사이에 과제도 내지 못하고 출석도 하지 못해 학사경고 위기에 처한 남규는 전공을 살려 현재 일방통행인 길들 중 반드시 양방향으로 바꿔야만 하는 길이 몇 개인지 조사해 학교에 건의하기로 마음을 먹었다.
 * 남규는 여러 건물들 사이를 직접 잇는 길들을 모두 조사했고, 그 중 어떤 길들이 일방통행인지, 또는 양방향 통행이 가능한지를 모두 체크했다.
 * 남규의 프로그램은 간단하다. 출발지와 도착지를 입력하면 도착지까지 가기 위해 최소 몇 개의 길을 양방향으로 바꿔야만 하는지를 출력해준다. 프로그램이 완성되었다는 소문이 퍼지자, 남규처럼 길을 잃고 헤맨 경험이 있는 학생들은 남규에게 묻기 시작했다.
 * "공학관에서 대강당 갈 수 있어?"
 * "상경대 별관에서 학관으로는?"
 * 남규는 매번 손으로 타이핑해 입력하고 결과를 보내주는 데에 지치고 말았다.
 * 결국 앓아누운 남규를 위해 학생들의 질문을 해결할 새로운 프로그램을 만들어보자.
 *
 * ? 입력
 * 첫 줄에 Y대학교 건물의 수 n과 길의 수 m이 주어진다. (n ≤ 250, m ≤ n * (n - 1) / 2 )
 * 다음 m줄에 걸쳐, u v b (1 ≤ u ≤ n, 1 ≤ v ≤ n, u != v, b = 0 또는 1) 의 형태로 길에 대한 정보가 주어진다.
 * b가 0일 경우 u에서 v로 가는 일방통행 길인 것이고, b가 1일 경우 u와 v를 잇는 양방향 길이다.
 * 어떤 두 건물 사이를 잇는 길은 최대 한 개이다.
 * 다음 줄에 학생들의 질문의 수 k가 주어진다. (1 ≤ k ≤ 30,000)
 * 다음 k줄에 걸쳐 s e (1 ≤ s ≤ n, 1 ≤ e ≤ n)의 형태로 학생들의 질문들이 주어진다.
 * 이는 질문한 학생이 건물 s에서 건물 e로 가고 싶다는 의미이다.
 *
 * 4 3      -> n m
 * 1 2 0    -> u v b
 * 2 3 1    -> u v b
 * 3 4 0    -> u v b
 * 7        -> k
 * 1 1      -> s e
 * 1 2      -> s e
 * 2 1      -> s e
 * 1 4      -> s e
 * 4 1      -> s e
 * 2 3      -> s e
 * 4 3      -> s e
 *
 * ? 출력
 * 출력은 k줄에 걸쳐 이루어진다.
 * 각 질문에 대해, 최소 몇 개의 일방통행인 길을 양방향 통행으로 바꿔야 출발지에서 도착지로 갈 수 있는지를 출력한다.
 * 모든 길을 양방향으로 바꾸더라도 서로 도달 불가능한 건물은 없다.
 *
 * 0
 * 0
 * 1
 * 0
 * 2
 * 0
 * 1
 *
 * ? 채점 결과
 * * 시간: 0.392초
 * * 메모리: 34MB
 * * 언어: JAVA8
 * * 시도: 3
 */
package Gold.III;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G3_11562 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int MAX = 100_000;

    static int n, m, k;
    static int[][] floyd;
    static ArrayList<int[]> questions;

    public static void main(String[] args) throws IOException {
        input();
        floydWarshall();

        StringBuffer sb = new StringBuffer();
        for (int[] question : questions) {
            sb.append(floyd[question[0]][question[1]] + "\n");
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    public static void floydWarshall() {
        for (int t = 1; t <= n; t++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (t == i || i == j) {
                        continue;
                    }

                    if (floyd[i][j] > floyd[i][t] + floyd[t][j]) {
                        floyd[i][j] = floyd[i][t] + floyd[t][j];
                    }
                }
            }
        }
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        floyd = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    continue;
                }

                floyd[i][j] = MAX;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            floyd[u][v] = 0;
            if (b == 0) {
                floyd[v][u] = 1;
            } else {
                floyd[v][u] = 0;
            }
        }

        questions = new ArrayList<>();
        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            questions.add(new int[] {s, e});
        }
    }
}
