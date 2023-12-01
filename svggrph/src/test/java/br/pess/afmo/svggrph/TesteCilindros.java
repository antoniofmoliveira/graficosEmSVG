package br.pess.afmo.svggrph;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;


/**
 * DOCUMENT ME!
 *
 * @author antoniofmoliveira@outlook.com
 */
public class TesteCilindros {
/**
     * 
     */
    public TesteCilindros() {
        super();
    }

    /**
     * DOCUMENT ME!
     *
     * @param args
     */
    public static void main(String[] args) {
        SVGCilindros graf = new SVGCilindros();
        graf.setSerie(new Double[] { 107083.56, 71886.21, 111827.28, 228484.09, 392326.46 });
        graf.setSerieNames(new String[] {
                "jan/2006", "fev/2006", "mar/2006", "abr/2006", "mai/2006", "jan/2006", "fev/2006", "mar/2006", "abr/2006",
                "mai/2006"
            });
        graf.setTitulo("Montante de Multas por Mês");
        graf.setSubtitulo("Valores mensais");
        graf.setCasasDecimais(2);
        graf.setSize(1000);
        // graf.setEfeito3D(false);
        graf.setCor(4);

        try {
            // graf.toSVG("cilindros.svg");
            // graf.toXML("cilindros.svg");
            // graf.rasterizeMe("cilindros.jpg");
            graf.rasterizeMe("cilindros.png");
            // graf.rasterizeMe("cilindros.tif");
        } catch (TranscoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
