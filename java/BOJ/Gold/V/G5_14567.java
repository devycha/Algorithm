/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/14567
 *
 * ? 제목: 선수과목(Prerequisite)
 * ? 시간 제한: 5초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 올해 Z대학 컴퓨터공학부에 새로 입학한 민욱이는 학부에 개설된 모든 전공과목을 듣고 졸업하려는 원대한 목표를 세웠다. 어떤 과목들은 선수과목이 있어 해당되는 모든 과목을 먼저 이수해야만 해당 과목을 이수할 수 있게 되어 있다. 공학인증을 포기할 수 없는 불쌍한 민욱이는 선수과목 조건을 반드시 지켜야만 한다. 민욱이는 선수과목 조건을 지킬 경우 각각의 전공과목을 언제 이수할 수 있는지 궁금해졌다. 계산을 편리하게 하기 위해 아래와 같이 조건을 간소화하여 계산하기로 하였다.
 * 한 학기에 들을 수 있는 과목 수에는 제한이 없다.
 * 모든 과목은 매 학기 항상 개설된다.
 * 모든 과목에 대해 각 과목을 이수하려면 최소 몇 학기가 걸리는지 계산하는 프로그램을 작성하여라.
 *
 * ? 입력 & 파싱
 * 첫 번째 줄에 과목의 수 N(1 ≤ N ≤ 1000)과 선수 조건의 수 M(0 ≤ M ≤ 500000)이 주어진다. 선수과목 조건은 M개의 줄에 걸쳐 한 줄에 정수 A B 형태로 주어진다. A번 과목이 B번 과목의 선수과목이다. A < B인 입력만 주어진다. (1 ≤ A < B ≤ N)
 *
 * 3 2  -> n m
 * 2 3  -> a b
 * 1 2  -> a b
 *
 * ? 출력
 * 1번 과목부터 N번 과목까지 차례대로 최소 몇 학기에 이수할 수 있는지를 한 줄에 공백으로 구분하여 출력한다.
 *
 * 1 2 3
 *
 * ? 채점 결과
 * * 시간: 0.584초
 * * 메모리: 130MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.V;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_14567 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static ArrayList<Integer>[] adj;

    // * 초기 설정
    static boolean[] visit;
    static int[] degree, sequence;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. 위상 정렬(BFS)
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                queue.add(new int[] {i, 1});
                visit[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            sequence[cur[0]] = cur[1];

            for (int next: adj[cur[0]]) {
                if (visit[next]) {
                    continue;
                }

                if (--degree[next] == 0) {
                    queue.add(new int[] {next, cur[1]+1});
                    visit[next] = true;
                }
            }
        }

        // * 3. 출력
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= n; i++) {
            sb.append(sequence[i] + " ");
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

        degree = new int[n+1];
        sequence = new int[n+1];
        visit = new boolean[n+1];
        adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            degree[b]++;
            adj[a].add(b);
        }
    }
}
