package game;

public class Board {
    private char[][] mat;

    int size;

    public Board(int size) {
        this.size = size;
        mat = new char[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                mat[row][col] = '-';
            }
        }
    }

    public void displayBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(mat[row][col] + " ");
            }
            System.out.println();
        }
    }

    public String getBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current board state:\n");
        sb.append("  ");
        for (int col = 0; col < size; col++) {
            sb.append(col).append(" ");
        }
        sb.append("\n");
        for (int row = 0; row < size; row++) {
            sb.append(row).append(" ");
            for (int col = 0; col < size; col++) {
                if (mat[row][col] == 'X')
                    sb.append("\u001B[36m").append(mat[row][col]).append("\u001B[0m ");
                else if (mat[row][col] == 'O')
                    sb.append("\u001B[35m").append(mat[row][col]).append("\u001B[0m ");
                else
                    sb.append(mat[row][col]).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public boolean isValid(int row, int col) {
        return mat[row][col] == '-';
    }

    public void placeMove(int row, int col, char symbol) {
        mat[row][col] = symbol;
    }

    public boolean isWinningMove(int row, int col, char symbol) {
        return checkRow(row, symbol) || checkCol(col, symbol) || checkDiag(row, col, symbol);
    }

    private boolean checkDiag(int row, int col, char symbol) {
        int k = 0;
        int row2 = row;
        int col2 = col;
        while (row < size && col < size && mat[row][col] == symbol) {
            k++;
            row++;
            col++;
            if (k == 5) {
                return true;
            }
        }
        row = row2;
        col = col2;
        while (row > 0 && col > 0 && mat[row][col] == symbol) {
            k++;
            row--;
            col--;
            if (k == 6) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCol(int col, char symbol) {
        for (int i = 0; i < size; i++) {
            int k = 0;
            while (i < size && mat[i][col] == symbol) {
                k++;
                i++;
                if (k == 5) {
                    return true;
                }
            }
        }
        return false;

    }

    private boolean checkRow(int row, char symbol) {
        for (int i = 0; i < size; i++) {
            int k = 0;
            while (i < size && mat[row][i] == symbol) {
                k++;
                i++;
                if (k == 5) {
                    return true;
                }
            }
        }
        return false;

    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
