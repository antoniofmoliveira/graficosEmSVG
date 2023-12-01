package br.pess.afmo.svggrph;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;


/**
 * DOCUMENT ME!
 *
 * @author antoniofmoliveira@outlook.com
 */
public class LC04 {
/**
     * 
     */
    public LC04() {
        super();
    }

    /**
     * DOCUMENT ME!
     *
     * @param args
     */
    public static void main(String[] args) {
        SVGLinhaPonto graf = new SVGLinhaPonto();
        String d = "576029,32   2371,19 113146,86   3993,12 28887,09    406454,02   165019,86   9332,47";
        d = d.replace(",", ".");

        String[] ds = d.split("\\s+");
        Double[] da = new Double[ds.length];

        for (int i = 0; i < ds.length; i++) {
            da[i] = Double.parseDouble(ds[i]);
        }

        graf.setSeriesColuna(new Double[][] { da });
        d = "709   10  67  9   38  250 62  82";
        d = d.replace(",", ".");
        ds = d.split("\\s+");

        Double[] de = new Double[ds.length];

        for (int i = 0; i < ds.length; i++) {
            de[i] = Double.parseDouble(ds[i]);
        }

        graf.setSeriesLinha(new Double[][] { de });
        graf.setYTituloLeft("Valor (R$)");
        graf.setYTituloRight("Quantidade");
        d = "Bacen;Detran;DRT;Outros;Polícia Federal;Prefeitura;Procon;SRF;";
        graf.setTags(d.split(";"));
        graf.setSeriesNamesColuna(new String[] { "Valor (R$)" });
        graf.setSeriesNamesLinha(new String[] { "Quantidade" });
        graf.setColors(new String[] { "red", "green", "blue", "violet" });
        graf.setPlotar(new boolean[] {
                true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
                true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
                true, true
            });
        graf.setYTicsLeft(10);
        graf.setYTicsRight(10);
        graf.setObservacoes(new String[] { "Total de R$ 1.305.233,93 em 1.227 eventos.", });
        graf.setBase("fonte:Sistema CDA - jan-jul/2006");
        graf.setCasasDecimaisColuna(new int[] { 0, 0, 0, 0, 1 });
        graf.setCasasDecimaisLinha(new int[] { 0, 1, 2, 2, 1 });
        graf.setTitulo("Multas Pagas - por Ógão Imputante");
        // graf(false);
        graf.setSize(860);
        graf.setYTicsLeft(10);
        graf.setYTicsRight(10);

        try {
            // graf.toSVG("multas4.svg");
            // graf.toXML("multas4.svg");
            // graf.rasterizeMe("multas4.jpg");
            graf.rasterizeMe("multas4.png");
            // graf.rasterizeMe("multas4.tif");
        } catch (TranscoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
