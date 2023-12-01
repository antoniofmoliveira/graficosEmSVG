package br.pess.afmo.svggrph;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;

/**
 * DOCUMENT ME!
 *
 * @author antoniofmoliveira@outlook.com
 */
public class LC01 {
    /**
     * 
     */
    public LC01() {
        super();
    }

    /**
     * DOCUMENT ME!
     *
     * @param args
     */
    public static void main(String[] args) {
        SVGCilindros graf = new SVGCilindros();
        String d = "79221,89    58376,98    108215,89   229053,09   392326,46   278928,87   159110,75";
        d = d.replace(",", ".");

        String[] ds = d.split("\\s+");
        Double[] da = new Double[ds.length];

        for (int i = 0; i < ds.length; i++) {
            da[i] = Double.parseDouble(ds[i]);
        }

        graf.setSerie(da);
        graf.setSerieNames(
                new String[] { "jan/2006", "fev/2006", "mar/2006", "abr/2006", "mai/2006", "jun/2006", "jul/2006" });
        graf.setTitulo("Montante de Multas Pagas por Mês");
        graf.setObservacoes(new String[] { "Total de R$ 1.305.233,93 em 1.227 eventos.", });
        graf.setBase("fonte:Sistema CDA - jan-jul/2006");
        graf.setSubtitulo("");
        graf.setCasasDecimais(2);
        graf.setSize(885);
        graf.setEfeito3D(false);
        graf.setCor(4);

        try {
            // graf.toSVG("multas1.svg");
            // graf.toXML("multas1.svg");
            // graf.rasterizeMe("multas1.jpg");
            graf.rasterizeMe("multas1.png");
            // graf.rasterizeMe("multas1.tif");
        } catch (TranscoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
