chess_piece = list(map(int, input().split()))
chess_piece_original = [1, 1, 2, 2, 2, 8] 
for i in range(6):
    chess_piece[i] = chess_piece_original[i] - chess_piece[i]
print(*chess_piece)