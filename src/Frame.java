import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Frame extends JFrame implements MouseListener
{
    static int frameWidth = 406;
    static int frameHeight = 436;

    Window window;
    Gameplay gameplay;
    JLabel lose, win;

    public Frame()
    {
        initialization();
        propertiesOfFrame();

        add(window);
        addMouseListener(this);

        setVisible(true);
    }

    public Frame(int x)
    {
        lose = new JLabel("Przegrana");
        setSize(300,100);
        setLocationRelativeTo(null);
        setTitle("Przegrana");
        add(lose);
    }

    public Frame(int x, int y)
    {
        win = new JLabel("Wygrana");
        setSize(300,100);
        setLocationRelativeTo(null);
        setTitle("Wygrana");
        setDefaultCloseOperation(3);
        add(win);
    }

    public class Window extends JPanel
    {
        public void paintComponent(Graphics g)
        {
            gameplay.drawComponent(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(e.getButton() == 1)
        {
            gameplay.lpmClick(e.getX() - 3, e.getY() - 32);
            System.out.println(e.getX() + ";" + e.getY());
        }
        else if(e.getButton() == 3)
        {
            gameplay.ppmClick(e.getX() - 3, e.getY() - 32);
        }
        window.repaint();
    }

    public void initialization()
    {
        window = new Window();
        gameplay = new Gameplay();
    }

    public void propertiesOfFrame()
    {
        setTitle("Saper, Adrian Wysocki");
        setResizable(false);
        setDefaultCloseOperation(3); // 3 oznacza zamknięcie
        setLocationRelativeTo(null); // Ustawia ramkę na środku monitora
        setSize(frameWidth, frameHeight);
    }
/*

Sekcja ładowania i skalowania grafiki

 */
    public static BufferedImage loadImage(String path)
    {
        try
        {
            return ImageIO.read(Frame.class.getClassLoader().getResourceAsStream(path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage scale(BufferedImage source, int width, int height)
    {
        BufferedImage scaledImage = new BufferedImage(width, height, source.getType());
        Graphics g = scaledImage.getGraphics();
        g.drawImage(source, 0, 0, width, height, null);
        g.dispose();
        return scaledImage;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
