/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/1432
 *
 * ! 문제: 그래프 수정
 * ! 시간 제한: 2초
 * ! 메모리 제한: 128MB
 *
 * ! 문제
 * N개의 정점이 있는 그래프가 주어지면, 다음과 같은 방법에 의해서 정점의 번호를 다시 매기고 싶다.
 * 모든 그래프의 번호는 1보다 크거나 같고 N보다 작거나 같은 번호를 가져야 한다.
 * 만약 V1에서 V2로 연결된 간선이 있다면, V2의 번호는 V1보다 커야 한다.
 * 위와 같은 조건을 이용해서 그래프의 번호를 다시 매긴 후에, 1번 정점의 새로 고친 번호를 M1, 2번 정점의 새로 고친 번호를 M2, ..., N번 정점의 새로 고친 번호를 MN이라고 하면, N개의 수열이 만들어진다.
 * 이 수열을 출력하는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 정점의 개수 N이 주어진다. 둘째 줄부터 N개의 줄에는 인접행렬 형식으로 입력이 주어진다. 0은 연결되지 않았음을 의미하고, 1은 연결되었다는 것을 의미한다. N은 50보다 작거나 같은 자연수이다.
 *
 * 5        -> n
 * 00001    -> arr[0][0] ~ arr[0][n-1]
 * 00010
 * 00000
 * 00001
 * 00100    -> arr[n-1][0] ~ arr[n-1][n-1]
 *
 * ! 출력
 * 첫째 줄에 수열의 각 원소를 차례대로 공백을 사이에 두고 출력한다. 만약 그래프의 번호를 수정할 수 없다면 -1을 출력한다. 답이 여러 개일 경우에는 사전 순으로 제일 앞서는 것을 출력한다.
 *
 * 1 2 5 3 4
 *
 * ? 채점 결과
 * 메모리: 18364KB
 * 시간: 228ms
 * 언어: JAVA8
 */
package Platinum.IV;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P4_1432 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // 파싱
    static int n;
    static char[][] arr;

    // 초기 설정
    static int count, degree[], result[];

    // 메인
    public static void main(String[] args) throws IOException {
        input(); // 입력값 받기
        degree(); // 진출차수(out-degree) 기준으로 생성
        count = n; // 그래프 번호 N부터 시작
        if (degreeSort()) { // 그래프 수정이 가능하다면 결과 출력
            for (int i = 0; i < result.length; i++) {
                bw.write(result[i] + " ");
            }
        } else { // 그렇지 않다면 -1 출력
            bw.write(String.valueOf(-1));
        }
        br.close();
        bw.flush();
        bw.close();
    }

    // 위상정렬(out-degree기준)
    public static boolean degreeSort() {
        int c = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b-a);
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()) {
            c++;
            int cur = queue.poll();
            result[cur] = count--;

            for (int i = 0; i < n; i++) {
                if (arr[i][cur] == '1') {
                    if (--degree[i] == 0) {
                        queue.add(i);
                    }
                }
            }
        }

        return c == n;
    }

    // 진출차수(out-degree) 채우기
    public static void degree() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == '1') {
                    degree[i]++;
                }
            }
        }
    }

    // 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        degree = new int[n];
        result = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }
}
