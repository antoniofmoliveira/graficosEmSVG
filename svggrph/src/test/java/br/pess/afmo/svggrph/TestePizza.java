package br.pess.afmo.svggrph;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Id: TestePizza.java 18 2006-09-14 17:11:42Z antoniofmoliveira@outlook.com $
 */
public class TestePizza {
    /**
     * DOCUMENT ME!
     *
     * @param args
     */
    public static void main(String[] args) {
        SVGPizza graf = new SVGPizza();
        graf.setSerie(new Double[] { 20.0, 40.0, 60.0, 70.0, 100.0 });
        graf.setTags(new String[] { "serie1", "serie2", "serie3", "serie4", "serie5" });
        // graf.setExplode(new boolean[] { false, false, false, false, false });
        // graf.setColors(new String[]{"red","blue"});
        graf.setTitulo("título");
        // graf.setRaio(50);
        graf.setBase("bas/00-bas/00");
        graf.setSize(1000);

        try {
            // graf.toSVG("pizza.svg");
            // graf.toXML("pizza.svg");
            // graf.rasterizeMe("pizza.jpg");
            graf.rasterizeMe("pizza.png");
            // graf.rasterizeMe("pizza.tif");
        } catch (TranscoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
