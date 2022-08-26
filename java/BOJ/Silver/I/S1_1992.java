/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/1992
 *
 * ? 제목: 쿼드트리
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 흑백 영상을 압축하여 표현하는 데이터 구조로 쿼드 트리(Quad Tree)라는 방법이 있다. 흰 점을 나타내는 0과 검은 점을 나타내는 1로만 이루어진 영상(2차원 배열)에서 같은 숫자의 점들이 한 곳에 많이 몰려있으면, 쿼드 트리에서는 이를 압축하여 간단히 표현할 수 있다.
 * 주어진 영상이 모두 0으로만 되어 있으면 압축 결과는 "0"이 되고, 모두 1로만 되어 있으면 압축 결과는 "1"이 된다. 만약 0과 1이 섞여 있으면 전체를 한 번에 나타내지를 못하고, 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래, 이렇게 4개의 영상으로 나누어 압축하게 되며, 이 4개의 영역을 압축한 결과를 차례대로 괄호 안에 묶어서 표현한다
 * 위 그림에서 왼쪽의 영상은 오른쪽의 배열과 같이 숫자로 주어지며, 이 영상을 쿼드 트리 구조를 이용하여 압축하면 "(0(0011)(0(0111)01)1)"로 표현된다.  N ×N 크기의 영상이 주어질 때, 이 영상을 압축한 결과를 출력하는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에는 영상의 크기를 나타내는 숫자 N 이 주어진다. N 은 언제나 2의 제곱수로 주어지며, 1 ≤ N ≤ 64의 범위를 가진다. 두 번째 줄부터는 길이 N의 문자열이 N개 들어온다. 각 문자열은 0 또는 1의 숫자로 이루어져 있으며, 영상의 각 점들을 나타낸다.
 *
 * 8        -> n
 * 11110000 -> arr[0][0] ~ arr[0][n-1]
 * 11110000
 * 00011100
 * 00011100
 * 11110000
 * 11110000
 * 11110011
 * 11110011 -> arr[n-1][0] ~ arr[n-1][n-1]
 *
 * ? 출력
 * 영상을 압축한 결과를 출력한다.
 *
 * ((110(0101))(0010)1(0001))
 *
 * ? 채점 결과
 * * 시간: 84ms
 * * 메모리: 13MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Silver.I;

import java.io.*;

public class S1_1992 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // * 파싱
    static int n;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        bw.write(zip(0, 0, n)); // * 2. 분할 정복

        br.close();
        bw.flush();
        bw.close();
    }

    // ! 분할 정복
    public static String zip(int x, int y, int n) {
        if (n == 1) {
            return String.valueOf(arr[x][y]);
        }

        boolean isSame = true;
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n;j ++) {
                if (arr[x][y] != arr[i][j]) {
                    isSame = false;
                    break;
                }
            }
        }

        if (isSame) {
            return String.valueOf(arr[x][y]);
        }

        int side = n / 2;
        StringBuilder sb = new StringBuilder();
        sb.append("(" +
                zip(x, y, side) +
                zip(x, y+side, side) +
                zip(x+side, y, side) +
                zip(x+side, y+side, side) +
                ")");

        return sb.toString();
    }

    // ! 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new char[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }

}
