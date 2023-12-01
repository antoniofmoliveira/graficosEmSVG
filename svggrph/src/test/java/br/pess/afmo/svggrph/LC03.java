package br.pess.afmo.svggrph;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;


/**
 * DOCUMENT ME!
 *
 * @author antoniofmoliveira@outlook.com
 */
public class LC03 {
/**
     * 
     */
    public LC03() {
        super();
    }

    /**
     * DOCUMENT ME!
     *
     * @param args
     */
    public static void main(String[] args) {
        SVGLinhaPonto graf = new SVGLinhaPonto();
        String d = "43656,05 398420,88   104797,55   4218,52 27541,36    12667,15    13785,89    221402,72   113010,51   177608,44   188124,86";
        d = d.replace(",", ".");

        String[] ds = d.split("\\s+");
        Double[] da = new Double[ds.length];

        for (int i = 0; i < ds.length; i++) {
            da[i] = Double.parseDouble(ds[i]);
        }

        graf.setSeriesColuna(new Double[][] { da });
        d = "51    319 115 34  27  16  75  72  66  390 62";
        d = d.replace(",", ".");
        ds = d.split("\\s+");

        Double[] de = new Double[ds.length];

        for (int i = 0; i < ds.length; i++) {
            de[i] = Double.parseDouble(ds[i]);
        }

        graf.setSeriesLinha(new Double[][] { de });
        graf.setYTituloLeft("Valor (R$)");
        graf.setYTituloRight("Quantidade");
        d = "Ausência alvará, licenças, taxas;CÂMBIO;Disparo de alarme;IR, ITR ou CPMF;ISSQN;Plano de segurança;Recolhimento de Tributos;Tempo de espera na fila;Trabalhista;Outros - BACEN;Outros;";
        graf.setTags(d.split(";"));
        graf.setSeriesNamesColuna(new String[] { "Valor (R$)" });
        graf.setSeriesNamesLinha(new String[] { "Quantidade" });
        graf.setColors(new String[] { "red", "green", "blue", "violet" });
        graf.setPlotar(new boolean[] {
                true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true
            });
        graf.setYTicsLeft(10);
        graf.setYTicsRight(10);
        graf.setObservacoes(new String[] { "Total de R$ 1.305.233,93 em 1.227 eventos.", });
        graf.setBase("fonte:Sistema CDA - jan-jul/2006");
        graf.setCasasDecimaisColuna(new int[] { 0, 0, 0, 0, 1 });
        graf.setCasasDecimaisLinha(new int[] { 0, 1, 2, 2, 1 });
        graf.setTitulo("Multas Pagas - por Produto, Processo e Serviço");
        // graf(false);
        graf.setSize(860);
        graf.setYTicsLeft(10);
        graf.setYTicsRight(10);
        graf.setAnguloTag(-45);

        try {
            // graf.toSVG("multas3.svg");
            // graf.toXML("multas3.svg");
            // graf.rasterizeMe("multas3.jpg");
            graf.rasterizeMe("multas3.png");
            // graf.rasterizeMe("multas3.tif");
        } catch (TranscoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
