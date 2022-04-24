"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/43162?language=python3

입력예시
n
3
3
computers
[[1, 1, 0], [1, 1, 0], [0, 0, 1]]
[[1, 1, 0], [1, 1, 1], [0, 1, 1]]

출력예시
return
2
1

하루 넘게 고민하다 결국 다른사람 풀이 참고해서 풀었다..
"""

def soluction(n, computers):
    answer = 0
    visted = [False] * n

    for node in range(n):
        if (visted[node] == False):
            dfs(n, computers, node, visted)
            answer += 1

    return answer

def dfs(n, computers, node, visted):
    visted[node] = True

    for childNode in range(n):
        # 아직 방문하지 않은 노드이며 연결이 되어 있다면
        if (visted[childNode] == False and computers[node][childNode] == 1):
            # 새로운 노드 방문해서 그 노드와 연결되어 있는 다른 노드 탐색
            dfs(n, computers, childNode, visted)

    return visted