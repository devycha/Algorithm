def solution(numbers):
    answer = ''
    
    changeStr = list(map(str, numbers))
    
    changeStr.sort(key=lambda x : x*3, reverse=True)
    
    for strNum in changeStr:
        answer += strNum
    
    return str(int(answer))