package SudokuGame;

public class Map{

    public static int n = 3;
    public static int[][] map = new int[n*n][n*n];

    public static void GenerateMap(){
        for(int i = 0; i < n * n; i++){
            for(int j = 0; j < n * n; j++){
                map[i][j] = (i * n + i / n + j) % (n * n) + 1;
            }
        }
        for(int i = 0; i < 100; i++){
            ShuffleMap((int)(Math.random() * 5));
        }
    }

    static void Transposing(){
        for(int i = 0; i < n * n; i++){
            for(int j = i + 1; j < n * n; j++){
                int temp = map[i][j];
                map[i][j] = map[j][i];
                map[j][i] = temp;
            }
        }
    }

    static void SwapRowsInBlock(){
        int block = (int)(Math.random() * n);
        int row1 = (int)(Math.random() * n);
        int row2 = (int)(Math.random() * n);
        int line1 = block * n + row1;
        while(row1 == row2){
            row2 = (int)(Math.random() * n);
        }
        int line2 = block * n + row2;
        for(int i = 0; i < n * n; i++){
            int temp = map[line1][i];
            map[line1][i] = map[line2][i];
            map[line2][i] = temp;
        }
    }

    static void SwapColumnsInBlock(){
        int block = (int)(Math.random() * n);
        int row1 = (int)(Math.random() * n);
        int row2 = (int)(Math.random() * n);
        int line1 = block * n + row1;
        while(row1 == row2){
            row2 = (int)(Math.random() * n);
        }
        int line2 = block * n + row2;
        for(int i = 0; i < n * n; i++){
            int temp = map[i][line1];
            map[i][line1] = map[i][line2];
            map[i][line2] = temp;
        }
    }

    static void SwapBlocksInRow(){
        int block1 = (int)(Math.random() * n);
        int block2 = (int)(Math.random() * n);
        while (block1 == block2){
            block2 = (int)(Math.random() * n);
        }
        block1 = block1 * n;
        block2 = block2 * n;
        for(int i = 0; i < n * n; i++){
            int k = block2;
            for(int j = block1; j < block1 + n; j++){
                int temp = map[j][i];
                map[j][i] = map[k][i];
                map[k][i] = temp;
                k++;
            }
        }
    }

    static void SwapBlocksInColumn(){
        int block1 = (int)(Math.random() * n);
        int block2 = (int)(Math.random() * n);
        while (block1 == block2){
            block2 = (int)(Math.random() * n);
        }
        block1 = block1 * n;
        block2 = block2 * n;
        for(int i = 0; i < n * n; i++){
            int k = block2;
            for(int j = block1; j < block1 + n; j++){
                int temp = map[i][j];
                map[i][j] = map[i][k];
                map[i][k] = temp;
                k++;
            }
        }
    }

    public static void ShuffleMap(int i) {
        if(i == 0){
            Transposing();
        }
        if(i == 1){
            SwapRowsInBlock();
        }
        if(i == 2){
            SwapColumnsInBlock();
        }
        if(i == 3){
            SwapBlocksInRow();
        }
        if(i == 4){
            SwapBlocksInColumn();
        }
    }
}
