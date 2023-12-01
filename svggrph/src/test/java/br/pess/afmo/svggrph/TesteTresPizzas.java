package br.pess.afmo.svggrph;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;


/**
 * DOCUMENT ME!
 *
 * @author antoniofmoliveira@outlook.com
 */
public class TesteTresPizzas {
/**
     * 
     */
    public TesteTresPizzas() {
        super();
    }

    /**
     * DOCUMENT ME!
     *
     * @param args
     */
    public static void main(String[] args) {
        SVGTresPizzas graf = new SVGTresPizzas();
        graf.setBase("bas-00/bas/00");
        graf.setSerieEsquerda(new double[] { 31.8, 68.2 });
        graf.setSerieDireita(new double[] { 11.1, 88.9 });
        graf.setSerieCentro(new double[] { 17.5, 46.3, 27.3, 9.0 });
        graf.setTitulo("Operações de Crédito");

        String[] sma = {
                "Garantia constituída de acordo com os parâmetros de comprometimento estabelecidos",
                "Instrumento de crédito assinado pelos intervenientes/coobrigados."
            };
        String[] sa = {
                "Fiscalização realizada ou cumpridos os procedimentos necessários para realização pelo Nucac",
                "Apresentada carta de anuência/contrato de arrendamento registrado em cartório."
            };
        graf.setSubAlta(sa);
        graf.setSubMAlta(sma);
        graf.setSize(2000);

        try {
            // graf.toSVG("trespizzas.svg");
            // graf.toXML("trespizzas.svg");
            // graf.rasterizeMe("trespizzas.jpg");
            graf.rasterizeMe("trespizzas.png");
            // graf.rasterizeMe("trespizzas.tif");
        } catch (TranscoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
