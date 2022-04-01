"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/82612

입력예시
price = 3
money = 20 
count = 4

출력예시
10
"""
def solution(price, money, count):
    answer = -1
    
    total = 0
    for i in range(1,count+1):
        total += (i * price)
        
    if (money > total):
        answer = 0
    else:
        answer = total - money

    return answer