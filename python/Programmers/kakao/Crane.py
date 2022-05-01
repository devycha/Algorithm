"""
문제링크 : https://programmers.co.kr/learn/courses/30/lessons/64061?language=python3

입력예시
board
[[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]]
moves	
[1,5,3,5,1,2,1,4]

결과예시
4
"""

def solution(board, moves):
    answer = 0
    basket = []

    for move in moves:
        doll = selectDoll(move, board)
        
        if (doll != 0):
            basket.append(doll)
            
            basket_size = len(basket)
            
            if basket_size > 1 and basket[basket_size - 1] == basket[basket_size - 2]:
                basket.pop()
                basket.pop()
                answer += 2
                
    return answer

def selectDoll(move, board):
    for line in range(len(board)):
        if 0 != board[line][move - 1]:
            doll = board[line][move - 1]
            board[line][move - 1] = 0
            return doll
        
    return 0