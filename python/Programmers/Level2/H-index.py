"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/42747?language=python3

입력예시
[3, 0, 6, 1, 5]

결과예시
3
"""

def solution(citations):
    h_index = 0
    
    citations.sort(reverse=True)
    
    while True:
        count = 0
        
        for num in citations:
            if num >= h_index:
                count += 1
                
        if h_index > count:
            break
        else:
            h_index += 1
    
    return h_index - 1


"""
위에 짠 코드는 너무 비효율적이라고 생각해 다른 사람들의 코드를 보면서 다른 풀이를 학습
"""

def solution2(citations):
	answer = 0
	citations.sort()

  for i in range(1, len(citations)+1):
		num = citations[-i]
			if num >= i:
				answer = i

  return answer

"""
i = 1, num = 6
6 >= 1
anwer = 1

i = 2, num = 5
5 >= 2
anwer = 2

i = 3, num = 3
3 >= 3
anwer = 3

i = 4, num = 1
1 >= 4
anwer = 3

i = 5, num = 0
0 >= 5
anwer = 3
"""

def solution3(citations):
    sorted_citations = sorted(citations, reverse=True)

    for i in range(len(sorted_citations)):
        if sorted_citations[i] <= i: 
            return i

return len(sorted_citations)

"""
6 <= 0
5 <= 1
3 <= 2
1 <= 3 --> return
"""