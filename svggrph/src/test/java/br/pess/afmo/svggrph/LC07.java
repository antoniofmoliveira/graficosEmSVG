package br.pess.afmo.svggrph;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;


/**
 * DOCUMENT ME!
 *
 * @author antoniofmoliveira@outlook.com
 */
public class LC07 {
/**
     * 
     */
    public LC07() {
        super();
    }

    /**
     * DOCUMENT ME!
     *
     * @param args
     */
    public static void main(String[] args) {
        SVGLinhaPonto2 graf = new SVGLinhaPonto2();
        //        String d = "43656,05 398420,88   104797,55   4218,52 27541,36    12667,15    13785,89    221402,72   113010,51   177608,44   188124,86";
        //        d = d.replace(",", ".");
        //        String[] ds = d.split("\\s+");
        //        double[] da = new double[ds.length];
        //        for (int i = 0; i < ds.length; i++) {
        //            da[i] = Double.parseDouble(ds[i]);
        //        }
        //        graf.setSeriesColuna(new double[][] {{0,6000,3500,900,0,750,914.09},
        //                {27704.45,0,0,450,57240.08,857.65,222},
        //                {0,1271.58,9337.8,3000,1835.4,2436,3894.69},
        //                {118.76,0,0,1050,1771.14,18,197.13},
        //                {0,0,1182.67,150,1236.3,300,128.34},
        //                {0,1363.5,0,5886,3903.52,6180,15311.65},                  });
        graf.setSeriesColuna(new Double[][] {
                { 0.0, 27704.45, 0.0, 118.76, 0.0, 0.0 },
                { 6000.0, 0.0, 1271.58, 0.0, 0.0, 1363.5 },
                     // graf.toSVG("trespizzas.svg");
            // graf.toXML("trespizzas.svg");
            // graf.rasterizeMe("trespizzas.jpg");       { 750.0, 857.65, 2436.0, 18.0, 300.0, 6180.0 },
                { 914.09, 222.0, 3894.69, 197.13, 128.34, 15311.65 },
            });
        //         d = "51    319 115 34  27  16  75  72  66  390 62";
        //        d = d.replace(",", ".");
        //         ds = d.split("\\s+");
        //        double[] de = new double[ds.length];
        //        for (int i = 0; i < ds.length; i++) {
        //            de[i] = Double.parseDouble(ds[i]);
        //        }
        //        
        graf.setSeriesLinha(new Double[][] {
                { 0.0, 21.0, 0.0, 1.0, 0.0, 0.0 },
                { 1.0, 0.0, 2.0, 0.0, 0.0, 3.0 },
                { 1.0, 0.0, 5.0, 0.0, 16.0, 0.0 },
                { 5.0, 3.0, 18.0, 7.0, 1.0, 33.0 },
                { 0.0, 13.0, 1.0, 2.0, 2.0, 2.0 },
                { 5.0, 8.0, 16.0, 1.0, 2.0, 32.0 },
                { 4.0, 4.0, 7.0, 3.0, 1.0, 14.0 },
            });
        graf.setYTituloLeft("Valor (R$)");
        graf.setYTituloRight("Quantidade");

        String d = "Disparo de alarme ;Tempo de fila ;Ausência de alvará;Câmbio;Trabalhista;Outros - Bacen;Outros";
        graf.setTags("DF;RS;SP;MG;GO;Outros".split(";"));
        graf.setSeriesNamesColuna(d.split(";"));
        graf.setSeriesNamesLinha(d.split(";"));
        // graf.setColors(new String[]{ "green", "red", "blue", "violet" });
        graf.setPlotar(new boolean[] {
                true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true
            });
        graf.setYTicsLeft(10);
        graf.setYTicsRight(10);
        //        graf.setObservacoes(new String[] {
        //                "Ponderada: índice calculado conforme pesos do Rating.",
        //                "Bruta: quantidade de fichas com não conformidade dividida pela quantidade verificada.",
        //            });
        graf.setBase("jan-jul/2006");
        graf.setCasasDecimaisColuna(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        graf.setCasasDecimaisLinha(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        graf.setTitulo("Multas Pagas - por Produto, Processo, Serviço e UF");
        graf.setObservacoes(new String[] { "Total de R$ 1.305.233,93 em 1.227 eventos.", });
        graf.setBase("fonte:Sistema CDA - jul/2006");
        // graf(false);
        graf.setSize(860);
        graf.setYTicsLeft(10);
        graf.setYTicsRight(10);

        try {
            // graf.toSVG("multas7.svg");
            // graf.toXML("multas7.svg");
            // graf.rasterizeMe("multas7.jpg");
            graf.rasterizeMe("multas7.png");
            // graf.rasterizeMe("multas7.tif");
        } catch (TranscoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
