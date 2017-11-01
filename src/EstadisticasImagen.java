
import boofcv.alg.misc.ImageStatistics;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayU8;
import boofcv.struct.image.Planar;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.JViewport;

public class EstadisticasImagen {

    
    private static final EstadisticasImagen instance = new EstadisticasImagen();
    public final int ROJO = 0;
    public final int VERDE = 1;
    public final int AZUL = 2;

    public final int[] Componentes = {ROJO, VERDE, AZUL};

    public int maximo[] = new int[3];
    public int minimo[] = new int[3];
    public int promedio[] = new int[3];

    private EstadisticasImagen() {
    }

    public static EstadisticasImagen getInstance(){
        return instance;
    }

    public void calculaEstadisticas(BufferedImage imagen, JViewport vP) {
        Point esqSupIzda = vP.getViewPosition();
        Point esqInfDcha = new Point(esqSupIzda.x + vP.getWidth(), esqSupIzda.y + vP.getHeight());
        // convertir BufferedImage a imagen formato BoofCV
        Planar<GrayU8> imagenColor = ConvertBufferedImage.convertFromPlanar(imagen, null, true, GrayU8.class);
        // estadisticas  
        //System.out.println("Imagen -> Height: " + imagen.getHeight() + " Width: " + imagen.getWidth());
        //System.out.println("Esquina -> Height: " + esqInfDcha.y + " Width: " + esqInfDcha.x);
        for (int c : Componentes) {
            maximo[c] = ImageStatistics.max(imagenColor.getBand(c).subimage(esqSupIzda.x, esqSupIzda.y, esqInfDcha.x, esqInfDcha.y));
            minimo[c] = ImageStatistics.min(imagenColor.getBand(c).subimage(esqSupIzda.x, esqSupIzda.y, esqInfDcha.x, esqInfDcha.y));
            promedio[c] = (int) ImageStatistics.mean(imagenColor.getBand(c).subimage(esqSupIzda.x, esqSupIzda.y, esqInfDcha.x, esqInfDcha.y));
        }
    }
}