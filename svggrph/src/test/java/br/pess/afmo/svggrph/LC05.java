package br.pess.afmo.svggrph;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Id: LC05.java 18 2006-09-14 17:11:42Z antoniofmoliveira@outlook.com $
 */
public class LC05 {
    /**
     * DOCUMENT ME!
     *
     * @param args
     */
    public static void main(String[] args) {
        SVGPizza graf = new SVGPizza();
        String d = "483147,32   107283,97   714802,64";
        d = d.replace(",", ".");

        String[] ds = d.split("\\s+");

        //        System.out.print(ds.length);
        Double[] da = new Double[ds.length];

        for (int i = 0; i < ds.length; i++) {
            da[i] = Double.parseDouble(ds[i]);
        }

        //        System.out.print(da.length);
        graf.setSerie(da);
        d = "Municipal;Estadual;Federal";
        graf.setTags(d.split(";"));
        // graf.setExplode(new boolean[] { false, false, false, false, false });
        // graf.setColors(new String[]{"red","blue"});
        graf.setTitulo("Multas Pagas - por Âmbito do Órgão Imputante");
        // graf.setRaio(50);
        graf.setObservacoes(new String[] { "Total de R$ 1.305.233,93 em 1.227 eventos.", });
        graf.setBase("fonte:Sistema CDA - jan-jul/2006");
        graf.setSize(600);

        try {
            // graf.toSVG("multas5.svg");
            // graf.toXML("multas5.svg");
            // graf.rasterizeMe("multas5.jpg");
            graf.rasterizeMe("multas5.png");
            // graf.rasterizeMe("multas5.tif");
        } catch (TranscoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
