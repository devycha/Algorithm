/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/3584
 *
 * ! 제목: 가장 가까운 공통 조상
 * ! 시간 제한: 1초
 * ! 메모리 제한: 128MB
 *
 * ! 문제
 * 루트가 있는 트리(rooted tree)가 주어지고, 그 트리 상의 두 정점이 주어질 때 그들의 가장 가까운 공통 조상(Nearest Common Anscestor)은 다음과 같이 정의됩니다.
 * 두 노드의 가장 가까운 공통 조상은, 두 노드를 모두 자손으로 가지면서 깊이가 가장 깊은(즉 두 노드에 가장 가까운) 노드를 말합니다.
 * 예를 들어  15와 11를 모두 자손으로 갖는 노드는 4와 8이 있지만, 그 중 깊이가 가장 깊은(15와 11에 가장 가까운) 노드는 4 이므로 가장 가까운 공통 조상은 4가 됩니다.
 * 루트가 있는 트리가 주어지고, 두 노드가 주어질 때 그 두 노드의 가장 가까운 공통 조상을 찾는 프로그램을 작성하세요
 *
 * ! 입력 & 파싱
 * 첫 줄에 테스트 케이스의 개수 T가 주어집니다.
 * 각 테스트 케이스마다, 첫째 줄에 트리를 구성하는 노드의 수 N이 주어집니다. (2 ≤ N ≤ 10,000)
 * 그리고 그 다음 N-1개의 줄에 트리를 구성하는 간선 정보가 주어집니다. 한 간선 당 한 줄에 두 개의 숫자 A B 가 순서대로 주어지는데, 이는 A가 B의 부모라는 뜻입니다. (당연히 정점이 N개인 트리는 항상 N-1개의 간선으로 이루어집니다!) A와 B는 1 이상 N 이하의 정수로 이름 붙여집니다.
 * 테스트 케이스의 마지막 줄에 가장 가까운 공통 조상을 구할 두 노드가 주어집니다.
 *
 * 2        -> t
 * 16       -> n
 * 1 14     -> a b
 * 8 5      -> a b
 * 10 16    -> a b
 * 5 9      -> a b
 * 4 6      -> a b
 * 8 4      -> a b
 * 4 10     -> a b
 * 1 13     -> a b
 * 6 15     -> a b
 * 10 11    -> a b
 * 6 7      -> a b
 * 10 2     -> a b
 * 16 3     -> a b
 * 8 1      -> a b
 * 16 12    -> a b
 * 16 7     -> node1 node2
 * 5        -> n
 * 2 3      -> a b
 * 3 4      -> a b
 * 3 1      -> a b
 * 1 5      -> a b
 * 3 5      -> node1 node2
 *
 * ! 출력
 * 각 테스트 케이스 별로, 첫 줄에 입력에서 주어진 두 노드의 가장 가까운 공통 조상을 출력합니다.
 *
 * 4
 * 3
 *
 * ? 채점 결과
 * 시간: 200ms
 * 메모리: 22544KB
 * 언어: JAVA8
 */
package Gold.IV;

import java.io.*;
import java.util.StringTokenizer;

public class G4_3584 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    
    // * 초기 설정
    static int n, arr[], node1, node2;

    // * 메인 함수
    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 갯수
        
        // 각 테스트케이스만큼 반복
        for (int i = 0; i < t; i++) {
            input(); // 입력 받기
            int result = findParent(node1, node2); // * 가장 가까운 공통조상 찾기
            bw.write(String.valueOf(result + "\n")); // 출력
        }
        
        // * 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // * 가장 가까운 공통조상 찾기
    public static int findParent(int a, int b) {
        boolean[] visited = new boolean[n+1]; // * 방문 리스트
        
        // * node1의 부모들을 모두 방문표시(루트노드까지)
        while (a != 0) {
            visited[a] = true;
            a = arr[a];
        }

        // * node2의 부모를 거슬러 올라가다가 이미 방문한 위치가 있다면 해당 노드 리턴
        while (b != 0) {
            if (visited[b]) {
                return b;
            }
            b = arr[b];
        }
        return -1;
    }

    // * 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine()); // * 노드 개수
        arr = new int[n+1]; // * 부모 배열
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[b] = a; // ! b의 부모가 a임을 표시
        }
        
        // * 공통조상 찾아야 하는 두 노드
        st = new StringTokenizer(br.readLine());
        node1 = Integer.parseInt(st.nextToken());
        node2 = Integer.parseInt(st.nextToken());
    }
}
