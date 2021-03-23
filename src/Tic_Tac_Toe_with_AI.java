import java.util.Random;
import java.util.Scanner;


class Tic_Tac_Toe_with_AI {
    public static void main(String[] args) {
        String cells = "         ";

        paint(cells, -1, -1);//打印空棋盘

        play(cells);
    }

    /**
     * 游戏主菜单
     *
     * @param cells 棋盘
     */
    static void play(String cells) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        String coordinate;
        int coordinateX;
        int coordinateY;
        int marks;
        int turns = 1;//奇X偶O


        while (true) {
            if ((turns & 1) == 1) {
                System.out.print("Enter the coordinates: ");
                if (scan.hasNextInt()) {
                    coordinate = scan.nextLine();
                    coordinateX = (coordinate.charAt(0) - '0');
                    coordinateY = (coordinate.charAt(2) - '0');
                    marks = (coordinateX - 1) * 3 + (coordinateY - 1);

                    if (coordinateX > 3 || coordinateX < 0 || coordinateY > 3 || coordinateY < 0) {
                        System.out.println("Coordinates should be form 1 to 3!");
                        continue;
                    }
                    if (cells.charAt(marks) != ' ') {
                        System.out.println("This cell is occupied! Choose another one!");
                        continue;
                    }

                    cells = paint(cells, marks, turns);
                    turns++;

                } else {
                    System.out.println("You should enter numbers!");
                    scan.nextLine();
                }
            } else {
                marks = random.nextInt(9) ;
                System.out.println("Making move level \"easy\"");
                while (cells.charAt(marks) != ' ') {
                    marks = random.nextInt(9);
                }
                cells = paint(cells, marks, turns);
                turns++;
            }
            boolean draw;
            boolean impossible;
            boolean xWin;
            boolean oWin;

            xWin = check(cells, 'X');
            oWin = check(cells, 'O');
            impossible = (xWin && oWin) || count(cells);
            draw = (isMax(cells)) && !xWin && !oWin;

            if (impossible) {
                System.out.println("Impossible");
                return;
            }

            if (xWin) {
                System.out.println("X wins");
                return;
            }
            if (oWin) {
                System.out.println("O wins");
                return;
            }
            if (draw) {
                System.out.println("Draw");
                return;
            }
        }
    }

    /**
     * @param cells 棋盘
     * @param marks 下棋点
     * @param turns 计算此轮谁下棋
     */
    static String paint(String cells, int marks, int turns) {
        System.out.println("---------");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.print("| ");
            }
            if (i == marks) {
                StringBuilder c = new StringBuilder(cells);
                if (turns % 2 == 0) {
                    c.setCharAt(marks, 'O');
                    cells = c.toString();
                    System.out.print("O ");
                } else {
                    c.setCharAt(marks, 'X');
                    cells = c.toString();
                    System.out.print("X ");
                }
            } else {
                System.out.print(cells.charAt(i) + " ");
            }

            if (i % 3 == 2) {
                System.out.println("|");
            }
        }
        System.out.println("---------");
        return cells;
    }

    /**
     * @param cells 棋盘
     * @param a     取X或者O
     * @return 检查字符a是否胜利
     */
    static boolean check(String cells, char a) {
        if (cells.charAt(0) == a && cells.charAt(0) == cells.charAt(1) && cells.charAt(1) == cells.charAt(2)) {
            return true;
        } else if (cells.charAt(3) == a && cells.charAt(3) == cells.charAt(4) && cells.charAt(4) == cells.charAt(5)) {
            return true;
        } else if (cells.charAt(6) == a && cells.charAt(6) == cells.charAt(7) && cells.charAt(7) == cells.charAt(8)) {
            return true;
        } else if (cells.charAt(0) == a && cells.charAt(0) == cells.charAt(3) && cells.charAt(3) == cells.charAt(6)) {
            return true;
        } else if (cells.charAt(1) == a && cells.charAt(1) == cells.charAt(4) && cells.charAt(4) == cells.charAt(7)) {
            return true;
        } else if (cells.charAt(2) == a && cells.charAt(2) == cells.charAt(5) && cells.charAt(5) == cells.charAt(8)) {
            return true;
        } else if (cells.charAt(0) == a && cells.charAt(0) == cells.charAt(4) && cells.charAt(4) == cells.charAt(8)) {
            return true;
        } else return cells.charAt(2) == a && cells.charAt(2) == cells.charAt(4) && cells.charAt(4) == cells.charAt(6);
    }

    /**
     * @param cells 棋盘
     * @return 检查棋盘中X和O的数量，若其中一个比另外一数量多出1以上返回true
     */
    static boolean count(String cells) {
        int x = 0;
        int o = 0;
        for (int i = 0; i < cells.length(); i++) {
            if (cells.charAt(i) == 'X') {
                x++;
            }
            if (cells.charAt(i) == 'O') {
                o++;
            }
        }
        return Math.abs(x - o) > 1;
    }

    /**
     * @param cells 棋盘
     * @return 如果棋盘中没有可以下子的地方，返回true
     */
    static boolean isMax(String cells) {
        int num = 0;
        for (int i = 0; i < cells.length(); i++) {
            if (cells.charAt(i) == ' ') {
                num++;
            }
        }
        return num <= 0;
    }
}