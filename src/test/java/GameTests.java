import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spw4.game2048.Direction;
import spw4.game2048.Game;

import static org.mockito.Mockito.when;

//pipelinereset
public class GameTests {
    Game game;
    @BeforeEach
    void createNewGame(){
        game = new Game();
    }

    @Test
    public void whenGameIsCreated_shouldHaveABoardWhichHas16Tiles(){
        int tiles = 0;
        for (int i = 0; i < game.getBoard().length; i++) {
            tiles += game.getBoard()[i].length;
        }
        Assertions.assertEquals(16, tiles);
    }

    @Test
    public void whenGameIsCreated_shouldHaveABoardWithExactly2TilesThatAreNotZero(){
        game.initialize();
        int sum = 0;
        for (int i = 0; i < game.getBoard().length; i++) {
            for (int j = 0; j < game.getBoard()[i].length; j++) {
                if(game.getBoard()[i][j] != 0){
                    sum++;
                }
            }
        }
        Assertions.assertEquals(2, sum);
    }

    @Test
    public void whenMovingUp_shouldDoCorrectMovement(){
        int[][] arr = new int[][]{
                {0, 2, 2, 2},
                {0, 2, 4, 2},
                {0, 2, 8, 8},
                {0, 0, 0, 8}};
        game.setBoard(arr);
        game.move(Direction.up);

        Assertions.assertEquals(4, game.getBoard()[0][1]);
        Assertions.assertEquals(2, game.getBoard()[1][1]);

        Assertions.assertEquals(2, game.getBoard()[0][2]);
        Assertions.assertEquals(4, game.getBoard()[1][2]);
        Assertions.assertEquals(8, game.getBoard()[2][2]);

        Assertions.assertEquals(4, game.getBoard()[0][3]);
        Assertions.assertEquals(16, game.getBoard()[1][3]);
    }

    @Test
    public void whenMovingDown_shouldDoCorrectMovement(){
        int[][] arr = new int[][]{
                {0, 2, 2, 2},
                {0, 2, 4, 2},
                {0, 2, 8, 8},
                {0, 0, 0, 8}};
        game.setBoard(arr);
        game.move(Direction.down);

        Assertions.assertEquals(2, game.getBoard()[2][1]);
        Assertions.assertEquals(4, game.getBoard()[3][1]);

        Assertions.assertEquals(2, game.getBoard()[1][2]);
        Assertions.assertEquals(4, game.getBoard()[2][2]);
        Assertions.assertEquals(8, game.getBoard()[3][2]);

        Assertions.assertEquals(4, game.getBoard()[2][3]);
        Assertions.assertEquals(16, game.getBoard()[3][3]);
    }

    @Test
    public void whenMovingRight_shouldDoCorrectMovement(){
        int[][] arr = new int[][]{
                {0, 2, 2, 2},
                {0, 2, 4, 2},
                {0, 2, 8, 8},
                {0, 0, 0, 8}};
        game.setBoard(arr);
        game.move(Direction.right);

        Assertions.assertEquals(2, game.getBoard()[0][2]);
        Assertions.assertEquals(4, game.getBoard()[0][3]);

        Assertions.assertEquals(2, game.getBoard()[1][1]);
        Assertions.assertEquals(4, game.getBoard()[1][2]);
        Assertions.assertEquals(2, game.getBoard()[1][3]);

        Assertions.assertEquals(2, game.getBoard()[2][2]);
        Assertions.assertEquals(16, game.getBoard()[2][3]);

        Assertions.assertEquals(8, game.getBoard()[3][3]);
    }

    @Test
    public void whenMovingLeft_shouldDoCorrectMovement(){
        int[][] arr = new int[][]{
                {0, 2, 2, 2},
                {0, 2, 4, 2},
                {0, 2, 8, 8},
                {0, 0, 0, 8}};
        game.setBoard(arr);
        game.move(Direction.left);

        Assertions.assertEquals(4, game.getBoard()[0][0]);
        Assertions.assertEquals(2, game.getBoard()[0][1]);

        Assertions.assertEquals(2, game.getBoard()[1][0]);
        Assertions.assertEquals(4, game.getBoard()[1][1]);
        Assertions.assertEquals(2, game.getBoard()[1][2]);

        Assertions.assertEquals(2, game.getBoard()[2][0]);
        Assertions.assertEquals(16, game.getBoard()[2][1]);

        Assertions.assertEquals(8, game.getBoard()[3][0]);
    }

    @Test
    public void whenBoardIsFull_isOverShouldReturnTrue(){
        int[][] arr = new int[][]{
                {2, 4, 8, 64},
                {4, 2, 4, 2},
                {16, 128, 16, 8},
                {8, 2, 4, 8}};
        game.setBoard(arr);

        Assertions.assertEquals(true, game.isOver());
    }

    @Test
    public void whenBoardContains2048_isWonShouldReturnTrue(){
        int[][] arr = new int[][]{
                {2048, 0, 8, 64},
                {4, 2, 4, 2},
                {16, 128, 16, 8},
                {8, 2, 4, 8}};
        game.setBoard(arr);

        Assertions.assertEquals(true, game.isWon());
    }

    @Test
    public void whenBoardDoesNotContain2048_isWonShouldReturnFalse(){
        int[][] arr = new int[][]{
                {16, 0, 8, 64},
                {4, 2, 4, 2},
                {16, 128, 16, 8},
                {8, 2, 4, 8}};
        game.setBoard(arr);

        Assertions.assertEquals(false, game.isWon());
    }

    @Test
    public void whenBoardMoves5Times_getMovesShouldReturn5(){
        game.initialize();

        game.move(Direction.up);
        game.move(Direction.left);
        game.move(Direction.right);
        game.move(Direction.down);
        game.move(Direction.up);

        Assertions.assertEquals(5, game.getMoves());
    }

    @Test
    public void gameShouldReturnCorrectScore(){
        int[][] arr = new int[][]{
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        game.setBoard(arr);
        game.move(Direction.up);

        Assertions.assertEquals(4, game.getScore());
    }
}
