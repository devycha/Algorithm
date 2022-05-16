"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/43163

입력예시
begin
"hit"
"hit"

target
"cog"
"cog"

words
["hot", "dot", "dog", "lot", "log", "cog"]
["hot", "dot", "dog", "lot", "log"]

결과예시
4
0
"""

def solution(begin, target, words):
    answer = 0
    if target not in words:
        return 0

    # 그래프 생성
    visited_list = []
    graph = dict()
    create_graph(begin, words, graph, visited_list)

    # 단어를 변환시키는 데까지 걸린 횟수 찾기
    dfs_visited = []
    depth_list = []
    depth = 0
    answer = min(dfs(begin, target, graph, dfs_visited, depth, depth_list))

    return answer

# DFS로 target 단어 찾기
def dfs(node, target, graph, dfs_visited, depth, depth_list):
    dfs_visited.append(node)

    for word in graph[node]:
        if word == target:
            depth_list.append(depth + 1)
        if word not in dfs_visited:
            dfs(word, target, graph, dfs_visited, depth + 1, depth_list)
    return depth_list

# words 리스트를 그래프 구조로 변경
def create_graph(node, words, graph, visited_list):
    graph[node] = create_leaf(node, words, visited_list)
    visited_list.append(node)

    for word in words:
        if word not in visited_list:
            create_graph(word, words, graph, visited_list)
    return graph

# 자식노드 생성
def create_leaf(node, words, visited_list):
    leaf_list = []
    for word in words:
        if compare(node, word):
            leaf_list.append(word)
    return leaf_list

# 두 문자에서 틀린 부분이 한개인 문자 찾기
def compare(word1, word2):
    count = 0
    for i in range(len(word1)):
        if word1[i] != word2[i]:
            count += 1
    if count == 1:
        return True
    else:
        return False

"""
다른 사람의 풀이 참고

그래프를 만들지 않고 푸는 풀이
"""

def solution(begin, target, words):
    answer = 0
    visited = [False] * len(words)
    
    if target not in words:
        return 0
    
    answer = bfs(begin,target, words, visited)
                    
    return answer

def bfs(begin, target, words, visited):
    stack = [(begin, 0)]
    
    while stack:
        node, depth = stack.pop()
        if node == target:
            return depth
        
        for i in range(len(words)):
            if visited[i] == True:
                continue 
            
            count = 0
            for a,b in zip(node, words[i]):
                if a != b:
                    count += 1
            if count == 1:
                stack.append((words[i], depth+1))