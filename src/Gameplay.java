import java.awt.*;
import java.util.Random;

public class Gameplay
{
    int numberOfColumns = 20;
    int numberOfRows = 20;
    int numberOfBombs = 7;
    int column, row, mines, pom1;

    Random random = new Random();
    boolean isWin, isLose;
    Field[][] field;

    Frame lose, win;

    public Gameplay()
    {
        field = new Field[numberOfColumns][numberOfRows];
        for(int i=0; i<numberOfColumns; i++)
        {
            for(int j=0; j<numberOfRows; j++)
            {
                field[i][j] = new Field(i, j); // Inicjalizacja całej tablicy
            }
        }
        arrangeBombs();
        writeNumbersOfBombs();
    }

    public void drawComponent(Graphics g)
    {
        for(int i=0; i<numberOfColumns; i++)
        {
            for(int j=0; j<numberOfRows; j++)
            {
                field[i][j].draw(g); // Rysowanie poletka z Field.java
            }
        }
        if(isWin == true)
        {
            new Frame(1, 2).setVisible(true);
        }
    }

    public void arrangeBombs()
    {
        for(int i=0; i<numberOfBombs; i++)
        {
            column = random.nextInt(numberOfColumns);
            row = random.nextInt(numberOfRows);

            if(field[column][row].bombED == false)
            {
                field[column][row].bombED = true;
               // System.out.println("Bomba na: " + column + ";" + row); // Cheat
            }
            else
            {
                i--;
            }
        }
    }

    public void writeNumbersOfBombs() {
        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                mines = 0;

                if (((i-1) >= 0) && ((j-1) >= 0) && (field[i-1][j-1].bombED == true))
                    mines++;
                if (((i-1) >= 0) && (field[i-1][j].bombED == true))
                    mines++;
                if (((i-1) >= 0) && ((j + 1) < numberOfRows) && (field[i-1][j+1].bombED == true))
                    mines++;
                if (((j-1) >= 0) && (field[i][j-1].bombED == true))
                    mines++;
                if (((j+1) < numberOfRows) && (field[i][j+1].bombED == true))
                    mines++;
                if (((i+1) < numberOfColumns) && ((j-1) >= 0) && (field[i+1][j-1].bombED == true))
                    mines++;
                if (((i+1) < numberOfColumns) && (field[i+1][j].bombED == true))
                    mines++;
                if (((i+1) < numberOfColumns) && ((j+1) < numberOfRows) && (field[i+1][j+1].bombED == true))
                    mines++;

                field[i][j].bombsInNeighbour = mines;
            }
        }
    }

    public void openEmpty(int x, int y)
    {
        field[x][y].openED = true;
        if(field[x][y].bombsInNeighbour == 0)
        {
            if (x-1>=0 && (field[x-1][y].bombED == false) && (field[x-1][y].openED == false) && (field[x-1][y].flagED == false) && (field[x-1][y].bombsInNeighbour >= 0))
                openEmpty(x-1, y);
            if (y-1>=0 && (field[x][y-1].bombED == false) && (field[x][y-1].openED == false) && (field[x][y-1].flagED == false) && (field[x][y-1].bombsInNeighbour >= 0))
                openEmpty(x, y-1);
            if (x+1 < numberOfColumns && (field[x+1][y].bombED == false) && (field[x+1][y].openED == false) && (field[x+1][y].flagED == false) && (field[x+1][y].bombsInNeighbour >= 0))
                openEmpty(x+1, y);
            if (y+1 < numberOfColumns && (field[x][y+1].bombED == false) && (field[x][y+1].openED == false) && (field[x][y+1].flagED == false) && (field[x][y+1].bombsInNeighbour >= 0))
                openEmpty(x, y+1);
        }
    }

    public void lpmClick(int xClick, int yClick)
    {
        if(field[xClick/numberOfColumns][yClick/numberOfRows].flagED == false)
        {
            field[xClick/numberOfColumns][yClick/numberOfRows].openED = true;
            if(field[xClick/numberOfColumns][yClick/numberOfRows].bombED == true)
            {
                System.out.println("Bomba!");
                lose = new Frame(1);
                lose.setVisible(true);
                lose.setDefaultCloseOperation(3);
            }
            else
            {
                if(field[xClick/numberOfColumns][yClick/numberOfRows].bombsInNeighbour == 0)
                {
                    openEmpty(xClick/numberOfColumns, yClick/numberOfRows);
                }
            }
        }
    }

    public void ppmClick(int xClick, int yClick)
    {
        field[xClick/numberOfColumns][yClick/numberOfRows].setFlagED();
        checkFinish();
    }

    public void checkFinish()
    {
        pom1 = 0;
        for(int i=0; i < numberOfColumns; i++)
        {
            for (int j = 0; j < numberOfRows; j++)
            {
                if(((field[i][j].bombED == true) && (field[i][j].flagED == true)) == true)
                {
                    if(((field[i][j].flagED == true) && (field[i][j].bombED == false)) == true)
                    {
                        pom1--;
                    }
                    else
                    {
                        pom1++;
                        if(pom1 == numberOfBombs)
                        {
                        isWin = true;
                        }
                    }
                }
            }
        }
    }
}

