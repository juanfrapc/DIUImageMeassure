
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(File file) {
        try {
            this.image = ImageIO.read(file);
            this.setSize(image.getWidth(), image.getHeight());
            this.repaint();
        } catch (IOException ex) {
            System.out.println("El fichero no se ha leido correctamente");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public Dimension getPreferredSize() {
        return image == null? new Dimension(437,124) :
                new Dimension(image.getWidth(), image.getHeight());
    }

    
    
}
