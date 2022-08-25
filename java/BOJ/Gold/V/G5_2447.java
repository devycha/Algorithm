/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2447
 *
 * ? 제목: 별 찍기 - 10
 * ? 시간 제한: 1초
 * ? 메모리 제한: 256MB
 *
 * ? 문제
 * 재귀적인 패턴으로 별을 찍어 보자. N이 3의 거듭제곱(3, 9, 27, ...)이라고 할 때, 크기 N의 패턴은 N×N 정사각형 모양이다.
 * 크기 3의 패턴은 가운데에 공백이 있고, 가운데를 제외한 모든 칸에 별이 하나씩 있는 패턴이다.
 *
 * ***
 * * *
 * ***
 *
 * N이 3보다 클 경우, 크기 N의 패턴은 공백으로 채워진 가운데의 (N/3)×(N/3) 정사각형을 크기 N/3의 패턴으로 둘러싼 형태이다. 예를 들어 크기 27의 패턴은 예제 출력 1과 같다.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 N이 주어진다. N은 3의 거듭제곱이다. 즉 어떤 정수 k에 대해 N=3k이며, 이때 1 ≤ k < 8이다.
 *
 * 27
 *
 * ? 출력
 * 첫째 줄부터 N번째 줄까지 별을 출력한다.
 *
 * ***************************
 * * ** ** ** ** ** ** ** ** *
 * ***************************
 * ***   ******   ******   ***
 * * *   * ** *   * ** *   * *
 * ***   ******   ******   ***
 * ***************************
 * * ** ** ** ** ** ** ** ** *
 * ***************************
 * *********         *********
 * * ** ** *         * ** ** *
 * *********         *********
 * ***   ***         ***   ***
 * * *   * *         * *   * *
 * ***   ***         ***   ***
 * *********         *********
 * * ** ** *         * ** ** *
 * *********         *********
 * ***************************
 * * ** ** ** ** ** ** ** ** *
 * ***************************
 * ***   ******   ******   ***
 * * *   * ** *   * ** *   * *
 * ***   ******   ******   ***
 * ***************************
 * * ** ** ** ** ** ** ** ** *
 * ***************************
 *
 * ? 채점 결과
 * * 시간: 196ms
 * * 메모리: 22MB
 * * 언어: JAVA8
 * * 시도: 3
 */
package Gold.V;

import java.io.*;

public class G5_2447 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // * 파싱
    static int n;
    
    // * 초기 설정
    static char[][] arr;
    
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];

        find(0, 0, n, false);

        for (int i = 0; i < n; i++) {
            bw.write(arr[i]);
            bw.write("\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void find(int x, int y, int n, boolean isBlank) {
        // 빈공간일 때
        if (isBlank) {
            for (int i = x; i < x + n; i++) {
                for (int j = y; j < y + n; j++) {
                    arr[i][j] = ' ';
                }
            }
            return;
        }

        // 더이상 쪼갤 수 없을 때
        if (n == 1) {
            arr[x][y] = '*';
            return;
        }


        // 9블럭으로 나누었을 때 한변의 길이 
        int side = n / 3;
        int cnt = 0;
        for (int i = x; i < x + n; i += side) {
            for (int j = y; j < y + n; j += side) {
                cnt++;
                if (cnt == 5) {
                    find(i, j, side, true);
                } else {
                    find(i, j, side, false);
                }
            }
        }
    }
}
