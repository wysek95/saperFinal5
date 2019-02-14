import java.awt.*;
import java.awt.image.BufferedImage;

public class Field
{
    static int widthOfField = 20;
    static int heightOfField = 20;

    static BufferedImage bombImage = Frame.scale(Frame.loadImage("bomba.png"), widthOfField, heightOfField);
    static BufferedImage wyciImage = Frame.scale(Frame.loadImage("wyci.png"), widthOfField, heightOfField);
    static BufferedImage wciImage = Frame.scale(Frame.loadImage("wci.png"), widthOfField, heightOfField);
    static BufferedImage flagImage = Frame.scale(Frame.loadImage("flaga.png"), widthOfField, heightOfField);

    //Po wskazówkach Pana Doktora, zrobiłem powyższe zmienne statyczne, co poskutkowało zmienjszeniem potrzebnej pamięci aż o połowę

    boolean bombED, openED, flagED;
    int bombsInNeighbour;
    int x;
    int y;

    public Field(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g)
    {
        if(openED == false)
        {
            if(flagED == false)
            {
                g.drawImage(wyciImage, x * widthOfField, y * heightOfField, null);
            }
            else
            {
                g.drawImage(flagImage, x * widthOfField, y * heightOfField, null);
            }
        }
        else
        {
            if(bombED == true)
            {
                g.drawImage(bombImage, x * widthOfField, y * heightOfField, null);
            }
            else
            {
                g.drawImage(wciImage, x * widthOfField, y * heightOfField, null);
                if(bombsInNeighbour > 0)
                {
                    g.drawString("" + bombsInNeighbour, x * widthOfField + 7, y * heightOfField + heightOfField - 3);
                }
            }
        }
    }

    public void setFlagED()
    {
        if(flagED == true)
        {
            flagED = false;
        }
            else
        {
            flagED = true;
        }
    }
}
