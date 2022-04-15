"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/42584?language=python3

입력예시
[1, 2, 3, 2, 3]

출력예시
[4, 3, 1, 1, 0]
"""

def solution(prices):
    answer = []

    for i in range(len(prices)):
        count = 0

        if i == len(prices) - 1:
            answer.append(0)
        else:
            for j in range(i+1,len(prices)):
                if prices[i] <= prices[j]:
                    count += 1
                else:
                    count += 1
                    break
            answer.append(count)

    return answer