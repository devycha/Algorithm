/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/5214
 *
 * ? 제목: 환승
 * ? 시간 제한: 2초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 아주 먼 미래에 사람들이 가장 많이 사용하는 대중교통은 하이퍼튜브이다. 하이퍼튜브 하나는 역 K개를 서로 연결한다. 1번역에서 N번역으로 가는데 방문하는 최소 역의 수는 몇 개일까?
 *
 * ? 입력 & 파싱
 * 첫째 줄에 역의 수 N과 한 하이퍼튜브가 서로 연결하는 역의 개수 K, 하이퍼튜브의 개수 M이 주어진다. (1 ≤ N ≤ 100,000, 1 ≤ K, M ≤ 1000)
 * 다음 M개 줄에는 하이퍼튜브의 정보가 한 줄에 하나씩 주어진다. 총 K개 숫자가 주어지며, 이 숫자는 그 하이퍼튜브가 서로 연결하는 역의 번호이다.
 *
 * 9 3 5    -> n, k, m
 * 1 2 3    s
 * 1 4 5
 * 3 6 7
 * 5 6 7
 * 6 8 9
 *
 * ? 출력
 * 첫째 줄에 1번역에서 N번역으로 가는데 방문하는 역의 개수의 최솟값을 출력한다. 만약, 갈 수 없다면 -1을 출력한다.
 *
 * 4
 *
 * ? 채점 결과
 * * 시간: 470ms
 * * 메모리: 77MB
 * * 언어: JAVA8
 */
package Gold.II;

import java.io.*;
import java.util.*;

public class G2_5214 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // * 파싱
    static int n, k, m;
    static ArrayList<Integer>[] station;
    static ArrayList<Integer>[] tube;

    // * 초기 설정
    static int[] stationVisited;
    static int[] tubeVisited;

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        bfs(); // * 2. 역과 튜브를 모두 정점으로 간주하고 BFS

        // * 3. 정답 출력
        bw.write(String.valueOf(stationVisited[n] == 0 ? -1 : stationVisited[n]));

        br.close();
        bw.flush();
        bw.close();
    }

    // ! BFS
    public static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {1, 1});
        stationVisited[1] = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll(); // cur[0]: number, cur[1] : isStation

            if (cur[1] == 1) { // station
                for (int nextTube : station[cur[0]]) {
                    if (tubeVisited[nextTube] == 0) {
                        tubeVisited[nextTube] = stationVisited[cur[0]];
                        queue.add(new int[] {nextTube, 0});
                    }
                }
            } else { // tube
                for (int nextStation : tube[cur[0]]) {
                    if (stationVisited[nextStation] == 0) {
                        stationVisited[nextStation] = tubeVisited[cur[0]] + 1;
                        queue.add(new int[] {nextStation, 1});

                        if (nextStation == n) {
                            return;
                        }
                    }
                }
            }
        }
    }

    // ! 입력 받기
    public static void input() throws IOException {
        InputReader in = new InputReader(System.in);

        n = in.readInt();
        k = in.readInt();
        m = in.readInt();

        station = new ArrayList[n+1];
        tube = new ArrayList[m+1];
        stationVisited = new int[n+1];
        tubeVisited= new int[m+1];

        for (int i = 1; i <= n; i++) {
            station[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            tube[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < k; j++) {
                int s = in.readInt();
                tube[i].add(s);
                station[s].add(i);
            }
        }
    }

    // ! INPUT 속도 증가
    private static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
}