/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/21609
 *
 * ? 제목: 상어 중학교
 * ? 문제
 * 상어 중학교의 코딩 동아리에서 게임을 만들었다. 이 게임은 크기가 N×N인 격자에서 진행되고, 초기에 격자의 모든 칸에는 블록이 하나씩 들어있고, 블록은 검은색 블록, 무지개 블록, 일반 블록이 있다. 일반 블록은 M가지 색상이 있고, 색은 M이하의 자연수로 표현한다. 검은색 블록은 -1, 무지개 블록은 0으로 표현한다. (i, j)는 격자의 i번 행, j번 열을 의미하고, |r1 - r2| + |c1 - c2| = 1을 만족하는 두 칸 (r1, c1)과 (r2, c2)를 인접한 칸이라고 한다.
 * 블록 그룹은 연결된 블록의 집합이다. 그룹에는 일반 블록이 적어도 하나 있어야 하며, 일반 블록의 색은 모두 같아야 한다. 검은색 블록은 포함되면 안 되고, 무지개 블록은 얼마나 들어있든 상관없다. 그룹에 속한 블록의 개수는 2보다 크거나 같아야 하며, 임의의 한 블록에서 그룹에 속한 인접한 칸으로 이동해서 그룹에 속한 다른 모든 칸으로 이동할 수 있어야 한다. 블록 그룹의 기준 블록은 무지개 블록이 아닌 블록 중에서 행의 번호가 가장 작은 블록, 그러한 블록이 여러개면 열의 번호가 가장 작은 블록이다.
 * 오늘은 이 게임에 오토 플레이 기능을 만드려고 한다. 오토 플레이는 다음과 같은 과정이 블록 그룹이 존재하는 동안 계속해서 반복되어야 한다.
 * 크기가 가장 큰 블록 그룹을 찾는다. 그러한 블록 그룹이 여러 개라면 포함된 무지개 블록의 수가 가장 많은 블록 그룹, 그러한 블록도 여러개라면 기준 블록의 행이 가장 큰 것을, 그 것도 여러개이면 열이 가장 큰 것을 찾는다.
 * 1에서 찾은 블록 그룹의 모든 블록을 제거한다. 블록 그룹에 포함된 블록의 수를 B라고 했을 때, B2점을 획득한다.
 * 격자에 중력이 작용한다.
 * 격자가 90도 반시계 방향으로 회전한다.
 * 다시 격자에 중력이 작용한다.
 * 격자에 중력이 작용하면 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동한다. 이동은 다른 블록이나 격자의 경계를 만나기 전까지 계속 된다.
 *
 * 다음은 N = 5, M = 3인 경우의 예시이다.
 *
 * 2	2	-1	3	1
 * 3	3	2	0	-1
 * 0	0	0	1	2
 * -1	3	1	3	2
 * 0	3	2	2	1
 * 여기서 찾을 수 있는 크기가 가장 큰 블록 그룹을 다음과 같이 빨간색으로 표시했다.
 *
 * 2	2	-1	3	1
 * 3	3	2	0	-1
 * 0	0	0	1	2
 * -1	3	1	3	2
 * 0	3	2	2	1
 * 블록 그룹이 제거되면 다음과 같이 변하고, 점수 82점을 획득한다.
 *
 * 2	2	-1	3	1
 *  	 	2	0	-1
 *  	 	 	1	2
 * -1	 	1	3	2
 *  	 	2	2	1
 * 중력이 작용하면 다음과 같이 변한다.
 *
 *  	 	-1	3	1
 *  	 	 	0	-1
 * 2	 	2	1	2
 * -1	 	1	3	2
 *  	2	2	2	1
 * 90도 반시계방향으로 회전한 결과는 다음과 같다.
 *
 * 1	-1	2	2	1
 * 3	0	1	3	2
 * -1	 	2	1	2
 *  	 	 	 	2
 *  	 	2	-1
 * 다시 여기서 중력이 작용하면 다음과 같이 변한다.
 *
 * 1	-1
 * 3	 	2	2	1
 * -1	 	1	3	2
 *  	 	2	1	2
 *  	0	2	-1	2
 * 오토 플레이가 모두 끝났을 때 획득한 점수의 합을 구해보자.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 격자 한 변의 크기 N, 색상의 개수 M이 주어진다.
 * 둘째 줄부터 N개의 줄에 격자의 칸에 들어있는 블록의 정보가 1번 행부터 N번 행까지 순서대로 주어진다. 각 행에 대한 정보는 1열부터 N열까지 순서대로 주어진다. 입력으로 주어지는 칸의 정보는 -1, 0, M이하의 자연수로만 이루어져 있다.
 *
 * 5 3          -> n m
 * 2 2 -1 3 1   -> arr[0][0] ~ arr[n-1]
 * 3 3 2 0 -1
 * 0 0 0 1 2
 * -1 3 1 3 2
 * 0 3 2 2 1    -> arr[n-1][0] ~ arr[n-1][n-1]
 *
 * ? 출력
 * 첫째 줄에 획득한 점수의 합을 출력한다.
 *
 * 77
 *
 * ? 제한
 * 1 ≤ N ≤ 20
 * 1 ≤ M ≤ 5
 *
 * ? 채점 결과
 * * 시간: 272ms
 * * 메모리: 20MB
 * * 언어: JAVA8
 * * 시도: 4
 **/
package Gold.II;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G2_21609 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static int[][] arr;

    // * 초기 설정
    static int point = 0; // 최종 점수
    static boolean isFinished = false; // 끝났는지 여부
    static boolean[][] visited; // 방문리스트
    static ArrayList<ArrayList<int[]>> groups; // 매 단계마다 가능한 모든 블록 그룹 리스트

    // * 상하좌우
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기

        // * 2. 2개 이상의 블록을 갖는 그룹이 있을 때까지
        while (true) {
            groups = new ArrayList<>(); // 그룹 리스트 초기화
            visited = new boolean[n][n]; // 방문 리스트 초기화

            // * 2-1. 그룹 찾기
            findGroup();

            // 존재하는 그룹이 없다면 종료
            if (isFinished) {
                break;
            }

            // * 2-2. 문제 조건을 만족하는 최대 길이의 블록 찾기
            ArrayList<int[]> maxBlock = findMaxBlock();

            // 문제 조건을 만족하는 블록의 최대 길이가 2보다 작을 때 종료
            if (maxBlock.size() < 2) {
                break;
            }

            // * 2-3. 해당 블록의 길이의 제곱만큼 점수 증가
            point += (int) Math.pow(maxBlock.size(), 2);

            // * 2-4. 해당 그룹의 블록들을 빈칸(-2) 처리
            for (int[] a: maxBlock) {
                arr[a[0]][a[1]] = -2;
            }

            // * 2-5. 중력 -> 반시계 회전 -> 중력
            down();
            rotateRCW();
            down();
        }

        // * 3. 점수 출력
        bw.write(String.valueOf(point));

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! 기존 블록 찾기 함수
    public static int[] findBase(ArrayList<int[]> list) {
        for (int[] b : list) {
            if (arr[b[0]][b[1]] != 0) {
                return b;
            }
        }
        return null;
    }

    // ! 2차원 배열 반시계 회전 함수
    public static void rotateRCW() {
        // X, Y -> N-Y-1, X
        int[][] newArr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newArr[n-j-1][i] = arr[i][j];
            }
        }

        arr = newArr;
    }

    // ! 중력 작용 함수
    public static void down() {
        for (int j = 0; j < n; j++) {
            for (int i = n-1; i >= 0; i--) {
                if (arr[i][j] >= 0) {
                    for (int k = i+1; k < n; k++) {
                        if (arr[k][j] == -2) {
                            arr[k][j] = arr[k-1][j];
                            arr[k-1][j] = -2;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    // ! 조건을 만족하는 길이가 가장 큰 블록 그룹 리스트를 찾는 함수
    public static ArrayList<int[]> findMaxBlock() {
        int maxLength = 0;
        for (int i = 0; i < groups.size(); i++) {
            if (maxLength < groups.get(i).size()) {
                maxLength = groups.get(i).size();
            }
        }

        ArrayList<int[]> maxBlock = null;
        int rainbow = 0;
        int baseX = 0;
        int baseY = 0;

        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).size() == maxLength) {
                blockSort(groups.get(i));
                int[] base = findBase(groups.get(i));

                int count = 0;
                for (int[] g: groups.get(i)) {
                    if (arr[g[0]][g[1]] == 0) {
                        count++;
                    }
                }

                if (maxBlock == null) {
                    maxBlock = groups.get(i);
                    baseX = base[0];
                    baseY = base[1];
                    rainbow = count;
                    continue;
                }

                if (rainbow < count) {
                    maxBlock = groups.get(i);
                    rainbow = count;
                    baseX = base[0];
                    baseY = base[1];
                } else if (rainbow == count) {
                    if (baseX < base[0]) {
                        maxBlock = groups.get(i);
                        rainbow = count;
                        baseX = base[0];
                        baseY = base[1];
                    } else if (baseX == base[0]) {
                        if (baseY < base[1]) {
                            maxBlock = groups.get(i);
                            rainbow = count;
                            baseX = base[0];
                            baseY = base[1];
                        }
                    }
                }
            }
        }

        return maxBlock;
    }

    // ! 각 그룹의 블록의 위치를 행, 열에 대해 오름차순
    public static void blockSort(ArrayList<int[]> list) {
        list.sort((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
    }

    // ! DFS를 이용하여 가능한 모든 블록 그룹 리스트를 찾는 함수
    public static void findGroup() {
        isFinished = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] > 0 && !visited[i][j]) {
                    isFinished = false;
                    groups.add(new ArrayList<>());
                    visited[i][j] = true;
                    dfs(i, j, arr[i][j]);

                    for (int[] group: groups.get(groups.size()-1)) {
                        if (arr[group[0]][group[1]] == 0) {
                            visited[group[0]][group[1]] = false;
                        }
                    }
                }
            }
        }
    }

    // ! DFS 함수
    public static void dfs(int x, int y, int gNum) {
        groups.get(groups.size()-1).add(new int[] {x, y});

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny] && (arr[nx][ny] == gNum || arr[nx][ny] == 0)) {
                visited[nx][ny] = true;
                dfs(nx, ny, gNum);
            }
        }
    }

    // ! 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
