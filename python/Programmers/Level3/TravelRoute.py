"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/43164

입력예시
tickets
[["ICN", "JFK"], ["HND", "IAD"], ["JFK", "HND"]]	
[["ICN", "SFO"], ["ICN", "ATL"], ["SFO", "ATL"], ["ATL", "ICN"], ["ATL","SFO"]]	

결과예시
return
["ICN", "JFK", "HND", "IAD"]
["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
"""

# 재귀함수를 이용한 풀이

from collections import defaultdict

def solution(tickets):
    answer = []
    total_routes_len = len(tickets) + 1
    ticket_graph = create_graph(tickets)

    for ticket in ticket_graph:
        ticket_graph[ticket].sort()

    answer = bfs("ICN", ["ICN"], ticket_graph, total_routes_len)

    return answer

def bfs(key, routes, ticket_graph, total_routes_len):
    if len(routes) == total_routes_len:
        return routes

    for i, airport in enumerate(ticket_graph[key]):
        ticket_graph[key].pop(i)

        r = routes[:]
        r.append(airport)

        result = bfs(airport, r, ticket_graph, total_routes_len)

        if result:
            return result

        ticket_graph[key].insert(i, airport)

def create_graph(tickets):
    ticket_graph = defaultdict(list)
    for key, value in tickets:
        ticket_graph[key].append(value)

    return ticket_graph

# 스택을 이용한 풀이
from collections import defaultdict

def solution(tickets):
    answer = []
    routes = defaultdict(list)

    for ticket in tickets:
        routes[ticket[0]].append(ticket[1])

    for key in routes.keys():
        routes[key].sort(reverse=True)

    stack = ['ICN']
    while stack:
        tmp = stack[-1]

        if not routes[tmp]:
            answer.append(stack.pop())
        else:
            stack.append(routes[tmp].pop())
    answer.reverse()

    return answer