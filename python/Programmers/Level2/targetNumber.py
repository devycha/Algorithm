"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/43165?language=python3

입력예시
numbers	target
[1, 1, 1, 1, 1]
[4, 1, 2, 1]

target
3
4

출력예시
5
3
"""

answer = 0
def solution(numbers, target):
    return dfs(numbers, target, 0, 0)

def dfs(numbers, target, index, num):
    global answer
    if index == len(numbers):
        if num == target:
            answer += 1
        return

    dfs(numbers, target, index + 1, num + numbers[index])
    dfs(numbers, target, index + 1, num - numbers[index])
    
    return answer