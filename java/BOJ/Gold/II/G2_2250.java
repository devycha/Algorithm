/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/2250
 *
 * ! 시간 제한: 2초
 * ! 메모리 제한: 128MB
 *
 * ! 문제
 * 이진트리를 다음의 규칙에 따라 행과 열에 번호가 붙어있는 격자 모양의 틀 속에 그리려고 한다. 이때 다음의 규칙에 따라 그리려고 한다.
 * 이진트리에서 같은 레벨(level)에 있는 노드는 같은 행에 위치한다.
 * 한 열에는 한 노드만 존재한다.
 * 임의의 노드의 왼쪽 부트리(left subtree)에 있는 노드들은 해당 노드보다 왼쪽의 열에 위치하고, 오른쪽 부트리(right subtree)에 있는 노드들은 해당 노드보다 오른쪽의 열에 위치한다.
 * 노드가 배치된 가장 왼쪽 열과 오른쪽 열 사이엔 아무 노드도 없이 비어있는 열은 없다.
 * 이와 같은 규칙에 따라 이진트리를 그릴 때 각 레벨의 너비는 그 레벨에 할당된 노드 중 가장 오른쪽에 위치한 노드의 열 번호에서 가장 왼쪽에 위치한 노드의 열 번호를 뺀 값 더하기 1로 정의한다. 트리의 레벨은 가장 위쪽에 있는 루트 노드가 1이고 아래로 1씩 증가한다.
 * 아래 그림은 어떤 이진트리를 위의 규칙에 따라 그려 본 것이다. 첫 번째 레벨의 너비는 1, 두 번째 레벨의 너비는 13, 3번째, 4번째 레벨의 너비는 각각 18이고, 5번째 레벨의 너비는 13이며, 그리고 6번째 레벨의 너비는 12이다.
 * 우리는 주어진 이진트리를 위의 규칙에 따라 그릴 때에 너비가 가장 넓은 레벨과 그 레벨의 너비를 계산하려고 한다. 위의 그림의 예에서 너비가 가장 넓은 레벨은 3번째와 4번째로 그 너비는 18이다. 너비가 가장 넓은 레벨이 두 개 이상 있을 때는 번호가 작은 레벨을 답으로 한다. 그러므로 이 예에 대한 답은 레벨은 3이고, 너비는 18이다.
 * 임의의 이진트리가 입력으로 주어질 때 너비가 가장 넓은 레벨과 그 레벨의 너비를 출력하는 프로그램을 작성하시오
 *
 * ! 입력 & 파싱
 * 첫째 줄에 노드의 개수를 나타내는 정수 N(1 ≤ N ≤ 10,000)이 주어진다. 다음 N개의 줄에는 각 줄마다 노드 번호와 해당 노드의 왼쪽 자식 노드와 오른쪽 자식 노드의 번호가 순서대로 주어진다. 노드들의 번호는 1부터 N까지이며, 자식이 없는 경우에는 자식 노드의 번호에 -1이 주어진다.
 *
 * 19       -> n
 * 1 2 3
 * 2 4 5
 * 3 6 7
 * 4 8 -1
 * 5 9 10
 * 6 11 12
 * 7 13 -1
 * 8 -1 -1
 * 9 14 15
 * 10 -1 -1
 * 11 16 -1
 * 12 -1 -1
 * 13 17 -1
 * 14 -1 -1
 * 15 18 -1
 * 16 -1 -1
 * 17 -1 19
 * 18 -1 -1
 * 19 -1 -1
 *
 * ! 출력
 * 첫째 줄에 너비가 가장 넓은 레벨과 그 레벨의 너비를 순서대로 출력한다. 너비가 가장 넓은 레벨이 두 개 이상 있을 때에는 번호가 작은 레벨을 출력한다.
 *
 * 3 18
 *
 * ? 채점 결과
 * 메모리: 18676KB
 * 시간: 192ms
 * 언어: JAVA
 */
package Gold.II;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G2_2250 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n; // 노드의 갯수
    static int[][] graph; // 이진트리(1차 idx: 부모노드번호, 2차 0번째 인덱스: 왼쪽자식, 2차 1번째 인덱스: 오른쪽 자식) -> graph[1][0]: 1번 노드의 왼쪽자식
    static int[] parents; // 인덱스(노드)의 값(부모노드)를 나타내는 배열
    static int[] visited; // 해당 노드의 열 번호가 저장되는 배열
    static ArrayList<Integer>[] height; // 각 층에 속하는 노드들이 저장되어 있는 배열
    static int count = 1; // 열 번호(노드가 하나씩 저장될 때마다 1씩 증가시켜서 사용 예정)
    static int maxGap = 1; // 너비의 최댓값
    static int maxLevel = 1; // 너비가 최댓값일 때 층

    /* 메인 함수 */
    public static void main(String[] args) throws IOException {
        input(); // 입력값 받아서 n, height, visited, graph, parents,
        dfs(findRoot(), 1); // DFS 수행

        // 각 층마다 너비를 구해서 최댓값을 찾는 로직
        for (int i = 1; i < height.length; i++) {
            if (height[i].size() > 1) {
                int width = findWidth(height[i]);
                if (width > maxGap) {
                    maxLevel = i;
                    maxGap = width;
                }
            }
        }

        // 최대너비를 가진 층과 너비를 출력
        System.out.println(maxLevel + " " + maxGap);
    }

    // 전위순회 (왼쪽노드 => 자기자신 => 오른쪽노드)
    public static void dfs(int node, int h) {
         height[h].add(node);
         int leftChild = graph[node][0];
         int rightChild = graph[node][1];

         // 왼쪽 노드 DFS 수행
         if (leftChild != 0) {
             dfs(leftChild, h + 1);
         }

         // 자기 자신 노드에 대한 열번호 저장 -> 카운트 증가
         visited[node] = count++;

         // 오른쪽 자식 노드 DFS 수행
         if (rightChild != 0) {
             dfs(rightChild, h + 1);
         }
    }

    // 노드들이 들어있는 리스트 안에서 각 노드들의 위치의 최솟값과 최댓값을 구해서 그 너비를 구하는 함수
    public static int findWidth(ArrayList<Integer> childList) {
        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int node: childList) {
            int position = visited[node];
            min = Math.min(min, position);
            max = Math.max(max, position);
        }

        return max - min + 1;
    }

    // Root 노드를 찾는 함수
    public static int findRoot(){
        // parents 배열에서 값이 0인(부모노드가 없는) 노드를 찾아서 리턴
        for (int i = 1; i < parents.length; i++) {
            if (parents[i] == 0) return i;
        }
        return -1;
    }

    // 입력값 처리 함수
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        height = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            height[i] = new ArrayList<Integer>();
        }

        visited = new int[n+1];
        Arrays.fill(visited, -1);

        parents = new int[n+1];
        graph = new int[n+1][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int leftChild = Integer.parseInt(st.nextToken());
            int rightChild = Integer.parseInt(st.nextToken());

            if (leftChild != -1) {
                graph[parent][0] = leftChild;
                parents[leftChild] = parent;
            } else {
                graph[parent][0] = 0;
            }

            if (rightChild != -1) {
                graph[parent][1] = rightChild;
                parents[rightChild] = parent;
            } else {
                graph[parent][1] = 0;
            }
        }
    }
}
