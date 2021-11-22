public class GameOfLife
{
    public static void main (String[] args)
    {
        if (args.length != 2)
        {
            System.err.println("Please enter only two arguments (both arguments should be positive integers)");
            System.exit(1);
        }

        int numCells = Integer.parseInt(args[0]);
        int numCopies = Integer.parseInt(args[1]);

        if((numCells <= 0) || (numCopies <= 0))
        {
            System.err.println("The arguments must be positive integers");
            System.exit(1);
        }

        int[][] state = new int[numCells][numCells];

        for(int i = 0; i < numCells; i++)
        {
            for(int j = 0; j < numCells; j++)
            {
                state[i][j] = 0;
            }
        }

        int[] randomRPent = new int[numCopies];
        int[] randomCPent = new int[numCopies];
        int[] randomRHWS = new int[numCopies];
        int[] randomCHWS = new int[numCopies];
        int[] randomRGlider = new int[numCopies];
        int[] randomCGlider = new int[numCopies];

        for (int k = 0; k < numCopies; k++)
        {
            randomRPent[k] = (int)(Math.random() * numCells);
            randomCPent[k] = (int)(Math.random() * numCells);
            state = Pentadecathlon(state, randomRPent[k], randomCPent[k], numCells);

            randomRHWS[k] = (int)(Math.random() * numCells);
            randomCHWS[k] = (int)(Math.random() * numCells);
            state = HeavyWeightSpaceship(state, randomRHWS[k], randomCHWS[k], numCells);

            randomRGlider[k] = (int)(Math.random() * numCells);
            randomCGlider[k] = (int)(Math.random() * numCells);
            state = Glider(state, randomRGlider[k], randomCGlider[k], numCells);
        }

        double doubleCells = (double)numCells;
        Draw(state, doubleCells);
        int[][] temp;

        StdDraw.enableDoubleBuffering();
        while (true)
        {
            temp = Rule(state, numCells);
            StdDraw.clear();
            for (int a = 0; a < numCells; a++)
            {
                for (int b = 0; b < numCells; b++)
                {
                    state[a][b] = temp[a][b];
                }
            }
            Draw(state, doubleCells);
            StdDraw.show();
            StdDraw.pause(20);
        }
    }

    public static int[][] Pentadecathlon (int[][] a, int r, int c, int cells)
    {
        for (int i = r - 4; i < r + 4; i++)
        {
            for (int j = c - 1; j < c + 2; j++)
            {
                if (i < 0)
                {
                    i = cells + i;
                }
                if (i >= cells)
                {
                    i = i % cells;
                }
                if (j < 0)
                {
                    j = cells + j;
                }
                if (j >= cells)
                {
                    j = j % cells;
                }
                a[i][j] = 1;
            }
        }

        if(r - 3 < 0)
        {
            a[cells + (r-3)][c] = 0;
        }
        else
        {
            a[r-3][c] = 0;
        }

        if(r + 2 >= cells)
        {
            a[(r+2) % cells][c] = 0;
        }
        else
        {
            a[r+2][c] = 0;
        }

        return a;
    }

    public static int[][] HeavyWeightSpaceship (int[][] a, int r, int c, int cells)
    {
        for (int i = c - 2; i < c + 4; i++)
        {
            if (i < 0)
            {
                i = cells + i;
            }
            if (i >= cells)
            {
                i = i % cells;
            }
            a[r][i] = 1;
        }

        if (r - 1 < 0 && c + 3 >= cells)
        {
            a[cells + (r-1)][(c+3) % cells] = 1;
        }
        else if (r - 1 < 0)
        {
            a[cells + (r-1)][c+3] = 1;
        }
        else if (c + 3 >= cells)
        {
            a[r-1][(c+3) % cells] = 1;
        }
        else
        {
            a[r-1][c+3] = 1;
        }

        if (r - 2 < 0 && c + 3 >= cells)
        {
            a[cells + (r-2)][(c+3) % cells] = 1;
        }
        else if (r - 2 < 0)
        {
            a[cells + (r-2)][c+3] = 1;
        }
        else if (c + 3 >= cells)
        {
            a[r-2][(c+3) % cells] = 1;
        }
        else
        {
            a[r-2][c+3] = 1;
        }

        if (r - 3 < 0 && c + 2 >= cells)
        {
            a[cells + (r-3)][(c+2) % cells] = 1;
        }
        else if (r - 3 < 0)
        {
            a[cells + (r-3)][c+2] = 1;
        }
        else if (c + 2 >= cells)
        {
            a[r-3][(c+2) % cells] = 1;
        }
        else
        {
            a[r-3][c+2] = 1;
        }

        if (r - 4 < 0)
        {
            a[cells + (r-4)][c] = 1;
        }
        else
        {
            a[r-4][c] = 1;
        }

        if (r - 4 < 0 && c - 1 < 0)
        {
            a[cells + (r-4)][cells + (c-1)] = 1;
        }
        else if (r - 4 < 0)
        {
            a[cells + (r-4)][c-1] = 1;
        }
        else if (c - 1 < 0)
        {
            a[r-4][cells + (c-1)] = 1;
        }
        else
        {
            a[r-4][c-1] = 1;
        }

        if (r - 3 < 0 && c - 3 < 0)
        {
            a[cells + (r-3)][cells + (c-3)] = 1;
        }
        else if (r - 3 < 0)
        {
            a[cells + (r-3)][c-3] = 1;
        }
        else if (c - 3 < 0)
        {
            a[r-3][cells + (c-3)] = 1;
        }
        else
        {
            a[r-3][c-3] = 1;
        }

        if (r - 1 < 0 && c - 3 < 0)
        {
            a[cells + (r-1)][cells + (c-3)] = 1;
        }
        else if (r - 1 < 0)
        {
            a[cells + (r-1)][c-3] = 1;
        }
        else if (c - 3 < 0)
        {
            a[r-1][cells + (c-3)] = 1;
        }
        else
        {
            a[r-1][c-3] = 1;
        }

        return a;
    }

    public static int[][] Glider (int[][] a, int r, int c, int cells)
    {
        a[r][c] = 1;

        if (c - 1 < 0)
        {
            a[r][cells + (c-1)] = 1;
        }
        else
        {
            a[r][c-1] = 1;
        }

        if (c + 1 >= cells)
        {
            a[r][(c+1) % cells] = 1;
        }
        else
        {
            a[r][c+1] = 1;
        }

        if (r - 1 < 0 && c + 1 >= cells)
        {
            a[cells + (r-1)][(c+1) % cells] = 1;
        }
        else if (r - 1 < 0)
        {
            a[cells + (r-1)][c+1] = 1;
        }
        else if (c + 1 >= cells)
        {
            a[r-1][(c+1) % cells] = 1;
        }
        else
        {
            a[r-1][c+1] = 1;
        }

        if (r - 2 < 0)
        {
            a[cells + (r-2)][c] = 1;
        }
        else
        {
            a[r-2][c] = 1;
        }

        return a;
    }

    public static void Draw (int[][] a, double cells)
    {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        double XPosition = (1.0/cells)/2.0;

        for (int i = 0; i < (int)cells; i++)
        {
            double YPosition = 1.0 - (1.0/cells)/2.0;
            for (int j = 0; j < (int)cells; j++)
            {
                if (a[j][i] == 0)
                {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.filledSquare(XPosition, YPosition, (1.0 / cells) / 2.0);
                }
                if (a[j][i] == 1)
                {
                    StdDraw.setPenColor(StdDraw.ORANGE);
                    StdDraw.filledSquare(XPosition, YPosition, (1.0 / cells) / 2.0);
                }
                YPosition -= 1.0 / cells;
            }
            XPosition += 1.0 / cells;
        }

        StdDraw.show();
    }

    public static int[][] Rule (int[][] a, int cells)
    {
        int[][] b = new int[cells][cells];

        for (int i = 0; i < cells; i++)
        {
            for (int j = 0; j < cells; j++)
            {
                b[i][j] = a[i][j];
            }
        }

        for (int i = 0; i < cells; i++)
        {
            for (int j = 0; j < cells; j++)
            {
                int liveNeighbors = 0;
                if (i - 1 < 0 && j - 1 < 0)
                {
                    if (a[cells + (i-1)][cells + (j-1)] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }
                else if (i - 1 < 0)
                {
                    if (a[cells + (i-1)][j-1] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }
                else if (j - 1 < 0)
                {
                    if (a[i-1][cells + (j-1)] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }
                else
                {
                    if (a[i-1][j-1] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }

                if (i - 1 < 0 && j + 1 >= cells)
                {
                    if (a[cells + (i-1)][(j+1) % cells] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }
                else if (i - 1 < 0)
                {
                    if (a[cells + (i-1)][j+1] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }
                else if (j + 1 >= cells)
                {
                    if (a[i-1][(j+1) % cells] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }
                else
                {
                    if (a[i-1][j+1] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }

                if (i + 1 >= cells && j - 1 < 0)
                {
                    if (a[(i+1) % cells][cells + (j-1)] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }
                else if (i + 1 >= cells)
                {
                    if (a[(i+1) % cells][j-1] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }
                else if (j - 1 < 0)
                {
                    if (a[i+1][cells + (j-1)] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }
                else
                {
                    if (a[i+1][j-1] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }

                if (i + 1 >= cells && j + 1 >= cells)
                {
                    if (a[(i+1) % cells][(j+1) % cells] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }
                else if (i + 1 >= cells)
                {
                    if (a[(i+1) % cells][j+1] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }
                else if (j + 1 >= cells)
                {
                    if (a[i+1][(j+1) % cells] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }
                else
                {
                    if (a[i+1][j+1] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }

                if (i - 1 < 0)
                {
                    if (a[cells + (i-1)][j] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }
                else
                {
                    if (a[i-1][j] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }

                if (i + 1 >= cells)
                {
                    if(a[(i+1) % cells][j] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }
                else
                {
                    if(a[i+1][j] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }

                if (j - 1 < 0)
                {
                    if (a[i][cells + (j-1)] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }
                else
                {
                    if (a[i][j-1] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }

                if (j + 1 >= cells)
                {
                    if(a[i][(j+1) % cells] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }
                else
                {
                    if(a[i][j+1] == 1)
                    {
                        liveNeighbors += 1;
                    }
                }

                if (a[i][j] == 1)
                {
                    if (liveNeighbors < 2 || liveNeighbors > 3)
                    {
                        b[i][j] = 0;
                    }
                }
                else
                {
                    if (liveNeighbors == 3)
                    {
                        b[i][j] = 1;
                    }
                }
            }
        }

        return b;
    }
}
