package br.pess.afmo.svggrph;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;


/**
 * DOCUMENT ME!
 *
 * @author antoniofmoliveira@outlook.com
 */
public class LC02 {
/**
     * 
     */
    public LC02() {
        super();
    }

    /**
     * DOCUMENT ME!
     *
     * @param args
     */
    public static void main(String[] args) {
        SVGLinhaPonto graf = new SVGLinhaPonto();
        String d = "15858,63    5267,13 32013,77    9042,40 445701,76   6341,72 24347,48    150,00  14938,48    33826,22    4438,08 24182,97    7966,03 5386,27 11944,85    2634,24 274430,46   17494,39    791,35  197341,46   47419,37    574,60  67856,45    23902,69    13705,58    450,00  14840,32    2387,23";
        d = d.replace(",", ".");

        String[] ds = d.split("\\s+");
        Double[] da = new Double[ds.length];

        for (int i = 0; i < ds.length; i++) {
            da[i] = Double.parseDouble(ds[i]);
        }

        graf.setSeriesColuna(new Double[][] { da });
        d = "15    10  78  20  164 24  49  1   16  122 11  52  36  8   35  3   45  112 4   193 24  1   119 34  34  1   14  2";
        d = d.replace(",", ".");
        ds = d.split("\\s+");

        Double[] de = new Double[ds.length];

        for (int i = 0; i < ds.length; i++) {
            de[i] = Double.parseDouble(ds[i]);
        }

        graf.setSeriesLinha(new Double[][] { de });
        graf.setYTituloLeft("Valor (R$)");
        graf.setYTituloRight("Quantidade");
        d = "AL;AM;BA;CE;DF;ES;GO;GOV I;MA;MG;MS;MT;PA;PB;PE;PI;PR;RJ;RN;RS;SC;SE;SP;SP I;SP II;SP III;TO;COM II;";
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
        graf.setTitulo("Multas Pagas - por Superintendência");
        // graf(false);
        graf.setSize(860);
        graf.setYTicsLeft(10);
        graf.setYTicsRight(10);
        graf.setAnguloTag(-90);
        graf.setAnguloValorTabela(new double[] { -90, -90 });

        try {
            // graf.toSVG("multas2.svg");
            // graf.toXML("multas2.svg");
            // graf.rasterizeMe("multas2.jpg");
            graf.rasterizeMe("multas2.png");
            // graf.rasterizeMe("multas2.tif");
        } catch (TranscoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
