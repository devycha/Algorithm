# 문제링크
# https://programmers.co.kr/learn/courses/30/lessons/42748?language=python3

def solution(array, commands):
    answer = []
    
    for command in commands:
        startNumber = command[0]
        endNumber = command[1]
        selectNumber = command[2] - 1
        
        if startNumber == endNumber:
            answer.append(array[startNumber - 1])
            continue
            
        tempArray = array[startNumber - 1 : endNumber]
        tempArray.sort()
        
        answer.append(tempArray[selectNumber])
    
    return answer