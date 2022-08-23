/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/14939
 *
 * ? 제목: 불 끄기
 * ? 시간 제한: 1초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 전구 100개가 10×10 정사각형 모양으로 늘어서 있다. 전구에 달린 스위치를 누르면 그 전구와 위, 아래, 왼쪽, 오른쪽에 있는 전구의 상태도 바뀐다. 전구 100개의 상태가 주어지면 모든 전구를 끄기 위해 최소한으로 눌러야 하는 스위치의 개수를 출력하라
 *
 * ? 입력 & 파싱
 * 10줄에 10글자씩 입력이 주어진다. #은 꺼진 전구고 O(대문자 알파벳 o)는 켜진 전구다. #과 O외에는 입력으로 주어지지 않는다.
 *
 * #O########   -> arr[0][0] ~ arr[0][9]
 * OOO#######
 * #O########
 * ####OO####
 * ###O##O###
 * ####OO####
 * ##########
 * ########O#
 * #######OOO
 * ########O#   -> arr[9][0] ~ arr[9][9]
 *
 * ? 출력
 * 모든 전구를 끄기 위해 최소한으로 눌러야 하는 스위치의 개수를 출력하라. 불가능하면 -1를 출력하라.
 *
 * 4
 *
 * ? 채점 결과
 * * 시간: 104ms
 * * 메모리: 13MB
 * * 언어: JAVA8
 * * 시도: 3
 */
package Platinum.V;

import java.io.*;

public class P5_14939 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    // * 파싱
    static boolean[][] arr = new boolean[10][10];

    // * 초기 설정
    static int count = 0; // 첫번째 줄의 스위치를 켜는 경우의 수마다 모든 불을 껐을 때 스위치를 누른 횟수
    static int min = 101; // 최솟값
    static boolean[] lightCase = new boolean[10]; // 초기 불켜짐 상태 배열
    static boolean[][] light = new boolean[10][10]; // 첫번째 줄의 스위치를 켜는 경우의 수마다 초기 불켜짐 상태 배열을 깊은복사 배열

    // * 5방(상하좌우 + 현재위치)
    static int[] dx = {0, 1, -1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        
        /*
         * 2. 첫번째 줄의 스위치를 켜는 방법의 모든 경우의 수를 구하고 
         * 모든 불을 끄기 위해 그리디한 방법으로
         * 현재 스위치를 기준으로 위의 불이 켜져있을때 해당 스위치를 눌러서
         * 현재 줄의 바로 윗줄을 확실하게 끄는 방법을 선택
         * 10번째 줄의 스위치까지 그리디한 방법으로 누르고 나면 마지막 줄의 불의 상태를 보고 모두 꺼져있을 때
         * count와 최솟값을 비교하여 업데이트
         */
        findCase(0); 
        
        // * 3. 출력
        System.out.println(min == 101 ? -1 : min);
    }

    // ! 입력 받기
    public static void input() throws IOException {
        for (int i = 0; i < 10; i++) {
            int p = 0;
            for (char c : br.readLine().toCharArray()) {
                arr[i][p++] = c == 'O';
            }
        }
    }

    // ! 2차원 배열 깊은 복사: light <- arr
    public static void copy() {
        for (int i = 0; i < 10; i++) {
            light[i] = arr[i].clone();
        }
    }

    // ! 첫번째 줄의 스위치를 누르는 모든 경우의 수 구하기 2^10 = 1024
    public static void findCase(int len) {
        if (len == 10) {
            turnOff();
            return;
        }

        lightCase[len] = true;
        findCase(len+1);

        lightCase[len] = false;
        findCase(len+1);
    }

    // ! 모든 불을 끄기 위한 그리디한 방법 중 실제로 모든 불이 꺼졌을 때 count값과 최솟값을 비교하여 업데이트하는 함수
    public static void turnOff() {
        count = 0; // 스위치 작동 횟수 초기화
        copy(); // 초기 불켜짐 상태를 깊은복사해서 새롭게 초기화

        // * 첫번째 줄 스위치 작동 (lightCase)
        for (int i = 0; i < 10; i++) {
            if (lightCase[i]) {
                count++;
                for (int j = 0; j < 5; j++) {
                    int ny = dy[j];
                    int nx = i + dx[j];

                    if (ny < 0 || nx < 0 || ny >= 10 || nx >= 10) {
                        continue;
                    }

                    if (light[ny][nx]) {
                        light[ny][nx] = false;
                    } else {
                        light[ny][nx] = true;
                    }
                }
            }
        }

        // * 첫번째 줄 스위치 작동 후의 결과를 가지고 스위치 작동
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (light[i-1][j]) {
                    count++;
                    for (int k = 0; k < 5; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];

                        if (ny < 0 || nx < 0 || ny >= 10 || nx >= 10) {
                            continue;
                        }

                        if (light[ny][nx]) {
                            light[ny][nx] = false;
                        } else {
                            light[ny][nx] = true;
                        }
                    }
                }
            }
        }

        // * 마지막 줄이 모두 꺼져있을 때 최솟값 업데이트
        if (lastCheck() && count < min) {
            min = count;
        }
    }

    // ! 마지막 줄의 불이 모두 꺼졌는지 체크하는 함수
    public static boolean lastCheck() {
        for (int i = 0; i < 10; i++) {
            if (light[9][i]) {
                return false;
            }
        }
        return true;
    }
}
