"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/42583?language=python3

입력예시
bridge_length
2			
100			
100

weight
10
100
100

truck_weights
[7,4,5,6]
[10]
[10,10,10,10,10,10,10,10,10,10]

출력예시
8
101
110
"""

def solution(bridge_length, weight, truck_weights):
    time = 0
    truck = [0] * bridge_length
    
    while truck:
        time += 1
        truck.pop(0)
        
        if truck_weights:
            if sum(truck) + truck_weights[0] <= weight:
                truck.append(truck_weights.pop(0))
            else:
                truck.append(0)
    
    return time