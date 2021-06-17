package spw4.game2048;

import java.util.Random;

public class Game {
    private class Index {
        private int x;
        private int y;

        public Index() {
            if(!isOver()){
                calculateIndex();
            }
            else{
                System.out.println("Game Over");
                System.exit(1);
            }
        }

        private void calculateIndex(){
            Random r = new Random();
            while(true) {
                int x = r.nextInt(3-0) + 0;
                int y = r.nextInt(3-0) + 0;
                if(isValidIndex(x, y)){
                    this.x = x;
                    this.y = y;
                    return;
                }
            }
        }

        private boolean isValidIndex(int x, int y){
            return board[x][y] == 0;
        }
    }
    private int score;
    private int moves;
    private static final int length = 4;
    private static final int width = 4;
    private int[][] board = new int[length][width];

    public Game() {
        score = 0;
        moves = 0;
    }

    public boolean isOver() {
        int sum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] > 0){
                    sum++;
                }
            }
        }

        return sum == 16;
    }

    public boolean isWon() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 2048){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Moves: ").append(moves).append("\tScore: ").append(score).append("\n");

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 0){
                    builder.append("-\t");
                }
                else{
                    builder.append(board[i][j]).append("\t");
                }
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public void initialize() {
        Index one = new Index();
        board[one.x][one.y] = this.generateValueForTile();
        Index two = new Index();
        board[two.x][two.y] = this.generateValueForTile();
    }

    public int generateValueForTile(){
        Random r = new Random();
        int prob = r.nextInt(10 - 1) + 1;
        return prob == 10 ? 4 : 2;
    }

    public void move(Direction direction) {
        switch (direction){
            case left: {
                this.moveLeft();
                break;
            }
            case up: {
                this.moveUp();
                break;
            }
            case right: {
                this.moveRight();
                break;
            }
            case down: {
                this.moveDown();
                break;
            }
        }
        moves++;
        spawnNewTile();
    }

    private void spawnNewTile() {
        Index index = new Index();
        board[index.x][index.y] = generateValueForTile();
    }

    private void moveLeft() {
        int curr = 0;
        int index = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 0){
                    continue;
                }
                else {
                    if(curr == 0 || (curr != 0 && curr != board[i][j])){
                        curr = board[i][j];
                        index = j;
                    }
                    else {
                        board[i][j] = 0;
                        board[i][index] = board[i][index] * 2;
                        score += board[i][index];
                        curr = 0;
                    }
                }
            }
            curr = 0;
            index = 0;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                for (int x = j + 1; x < board[j].length; x++) {
                    if(board[i][j] != 0){
                        continue;
                    }
                    else {
                        board[i][j] = board[i][x];
                        board[i][x] = 0;
                    }
                }
            }
        }
    }

    private void moveRight() {
        int curr = 0;
        int index = width - 1;

        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = board[i].length - 1; j >= 0; j--) {
                if(board[i][j] == 0){
                    continue;
                }
                else {
                    if(curr == 0 || (curr != 0 && curr != board[i][j])){
                        curr = board[i][j];
                        index = j;
                    }
                    else {
                        board[i][j] = 0;
                        board[i][index] = board[i][index] * 2;
                        score += board[i][index];
                        curr = 0;
                    }
                }
            }
            curr = 0;
            index = width - 1;
        }

        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = board[i].length - 1; j >= 0; j--) {
                for (int x = j - 1; x >= 0; x--) {
                    if(board[i][j] != 0){
                        continue;
                    }
                    else {
                        board[i][j] = board[i][x];
                        board[i][x] = 0;
                    }
                }
            }
        }
    }

    private void moveUp() {
        int curr = 0;
        int index = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[j][i] == 0){
                    continue;
                }
                else {
                    if(curr == 0 || (curr != 0 && curr != board[j][i])){
                        curr = board[j][i];
                        index = j;
                    }
                    else {
                        board[j][i] = 0;
                        board[index][i] = board[index][i] * 2;
                        score += board[index][i];
                        curr = 0;
                    }
                }
            }
            curr = 0;
            index = 0;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                for (int x = j + 1; x < board[j].length; x++) {
                    if(board[j][i] != 0){
                        continue;
                    }
                    else {
                        board[j][i] = board[x][i];
                        board[x][i] = 0;
                    }
                }
            }
        }
    }

    private void moveDown() {
        int curr = 0;
        int index = 3;

        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = board[i].length - 1; j >= 0; j--) {
                if(board[j][i] == 0){
                    continue;
                }
                else {
                    if(curr == 0 || (curr != 0 && curr != board[j][i])){
                        curr = board[j][i];
                        index = j;
                    }
                    else {
                        board[j][i] = 0;
                        board[index][i] = board[index][i] * 2;
                        score += board[index][i];
                        curr = 0;
                    }
                }
            }
            curr = 0;
            index = 3;
        }

        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = board[i].length - 1; j >= 0; j--) {
                for (int x = j - 1; x >= 0; x--) {
                    if(board[j][i] != 0){
                        continue;
                    }
                    else {
                        board[j][i] = board[x][i];
                        board[x][i] = 0;
                    }
                }
            }
        }
    }

    public int getMoves() {
        return moves;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board){
        this.board = board;
    }

    public int getScore() {
        return score;
    }

    public int getValueAt(int x, int y) { return board[x][y]; }
}
