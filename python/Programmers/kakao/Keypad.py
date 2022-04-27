"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/67256?language=python3

입력예시
numbers	
[1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5]
[7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2]
[1, 2, 3, 4, 5, 6, 7, 8, 9, 0]

hand
"right"	
"left"	
"right"

결과예시
result
"LRLLLRLLRRL"
"LRLLRRLLLRR"
"LLRLLRLLRL"
"""

def solution(numbers, hand):
    answer = ''
    
    # *, 0, # -> 10, 11, 12로 변환해서 계산
    current_left = 10
    current_right = 12
    
    for index in range(len(numbers)):
        if numbers[index] == 0:
            numbers[index] = 11
    
    for number in numbers:
        # n % 3을 통해 어느 라인에 있는 숫자인지 확인
        line = number % 3
        
        # 왼손 라인 -> 현재 왼손 값 변경, answer에 L 추가
        if line == 1:
            current_left = number
            answer += 'L'
    
        # 오른손 라인 ->  현재 오른손 값 변경, answer에 R 추가
        if line == 0:
            current_right = number
            answer += 'R'
    
        # 가운데 라인 -> 왼손, 오른손 거리 계산
        if line == 2:
            distance_left = int((abs(current_left - number) / 3) + (abs(current_left - number) % 3))
            distance_right = int((abs(current_right - number) / 3) + (abs(current_right - number) % 3))
            
            # 가까운 손에 현재 누른 번호값으로 변경, answer L or R 추가
            if distance_left > distance_right:
                current_right = number
                answer += 'R'

            elif distance_left < distance_right:
                current_left = number
                answer += 'L'

            # 거리가 같다면 hand을 확인하여 그 손에 현재 누른 번호값 변경, answer L or R 추가
            else:
                if hand == "right":
                    current_right = number
                    answer += 'R'
                if hand == "left":
                    current_left = number
                    answer += 'L'
        
    return answer