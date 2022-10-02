/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2239
 *
 * ? 제목: 스도쿠
 * ? 시간 제한: 2초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 스도쿠는 매우 간단한 숫자 퍼즐이다. 9×9 크기의 보드가 있을 때, 각 행과 각 열, 그리고 9개의 3×3 크기의 보드에 1부터 9까지의 숫자가 중복 없이 나타나도록 보드를 채우면 된다. 예를 들어 다음을 보자.
 * 위 그림은 참 잘도 스도쿠 퍼즐을 푼 경우이다. 각 행에 1부터 9까지의 숫자가 중복 없이 나오고, 각 열에 1부터 9까지의 숫자가 중복 없이 나오고, 각 3×3짜리 사각형(9개이며, 위에서 색깔로 표시되었다)에 1부터 9까지의 숫자가 중복 없이 나오기 때문이다.
 * 하다 만 스도쿠 퍼즐이 주어졌을 때, 마저 끝내는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 9개의 줄에 9개의 숫자로 보드가 입력된다. 아직 숫자가 채워지지 않은 칸에는 0이 주어진다.
 *
 * 103000509    -> arr[0][0] ~ arr[0][8]
 * 002109400
 * 000704000
 * 300502006
 * 060000050
 * 700803004
 * 000401000
 * 009205800
 * 804000107    -> arr[8][0] ~ arr[8][8]
 *
 * ? 출력
 * 9개의 줄에 9개의 숫자로 답을 출력한다. 답이 여러 개 있다면 그 중 사전식으로 앞서는 것을 출력한다. 즉, 81자리의 수가 제일 작은 경우를 출력한다.
 *
 * 143628579
 * 572139468
 * 986754231
 * 391542786
 * 468917352
 * 725863914
 * 237481695
 * 619275843
 * 854396127
 *
 * ? 채점 결과
 * * 시간: 0.384초
 * * 메모리: 133MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.IV;

import java.io.*;
import java.util.ArrayList;

public class G4_2239 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // * 파싱
    static int[][] arr = new int[9][9];

    // * 초기 설정
    static ArrayList<int[]> blanks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        backTracking(0); // * 2. 백트래킹 후 성공사례 나오자마자 출력
    }

    // 백트래킹
    public static void backTracking(int len) throws IOException {
        // * 빈칸인 곳을 모두 채우기 성공한 경우
        if (len == blanks.size()) {
            print();
            System.exit(0);
        }

        boolean[] nums = new boolean[10];
        int curX = blanks.get(len)[0];
        int curY = blanks.get(len)[1];

        // * 가로 & 세로에 있는 숫자들 제거
        for (int i = 0; i < 9; i++) {
            if (arr[curX][i] != 0) {
                nums[arr[curX][i]] = true;
            }

            if (arr[i][curY] != 0) {
                nums[arr[i][curY]] = true;
            }
        }

        // * 같은 3x3 네모칸에 있는 숫자들 제거
        int startX = (curX / 3) * 3;
        int startY = (curY / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[startX+i][startY+j] != 0) {
                    nums[arr[startX+i][startY+j]] = true;
                }
            }
        }

        // * 제거되고 남아있는 숫자들을 for 문을 돌려서 백트래킹
        for (int i = 1; i < 10; i++) {
            if (!nums[i]) {
                arr[curX][curY] = i;
                backTracking(len+1);
                arr[curX][curY] = 0;
            }
        }

    }

    // 스도쿠 배열 출력
    public static void print() throws IOException {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    // 입력 받기
    public static void input() throws IOException {
        for (int i = 0; i < 9; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < 9; j++) {
                arr[i][j] = line[j] - '0';

                if (arr[i][j] == 0) {
                    blanks.add(new int[] {i, j});
                }
            }
        }

    }

}
