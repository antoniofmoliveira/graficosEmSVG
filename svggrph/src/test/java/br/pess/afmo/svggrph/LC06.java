package br.pess.afmo.svggrph;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;


/**
 * DOCUMENT ME!
 *
 * @author antoniofmoliveira@outlook.com
 */
public class LC06 {
/**
     * 
     */
    public LC06() {
        super();
    }

    /**
     * DOCUMENT ME!
     *
     * @param args
     */
    public static void main(String[] args) {
        SVGColuna graf = new SVGColuna();
        graf.setSeriesColuna(new Double[][] {
                { 4890.92, 1887.1, 14020.47 },
                { 205302.39, 150603.95, 11436.0 },
                { 19815.68, 6355.98, 27823.21 },
                { 0.0, 1364.38, 2170.81 },
                { 3353.31, 607.51, 10.75 },
                { 10406.85, 1064.1, 47.13 },
                { 4308.05, 3826.69, 3671.57 },
                { 93812.11, 1121.26, 8635.08 },
                { 20879.71, 10165.8, 65986.44 },
                { 19825.35, 95834.0, 10541.65 },
                { 9582.09, 6248.1, 14767.64 },
            });
        graf.setYTituloLeft("Valor (R$)");
        graf.setTags(new String[] { "maio/2006", "junho/2006", "julho/2006" });

        String d = "Ausência alvará...;CÂMBIO;Disparo de alarme;IR, ITR ou CPMF;ISSQN;Plano de segurança;Recol. de Tributos;Tempo de espera na fila;Trabalhista;Outros - BACEN;Outros;";
        graf.setSeriesNames(d.split(";"));
        // graf.setColors(new String[]{"blue", "red"});
        graf.setPlotar(new boolean[] {
                true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,
            });
        //        graf.setMaximoY(50);
        graf.setYTicsLeft(10);
        //        graf.setObservacoes(new String[] {
        //                "Ponderada: Índice calculado conforme pesos do Rating.",
        //                "Bruta: quantidade de fichas com não conformidade dividida pela quantidade verificada.",
        //            });
        graf.setCasasDecimais(new int[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, });
        graf.setTitulo("Multas Pagas nos últimos 3 meses - por Processo, Produto e Serviço");
        graf.setBase("fonte:Sistema CDA");
        // graf.setEfeito3D(false);
        graf.setSize(760);

        try {
            // graf.toSVG("multas6.svg");
            // graf.toXML("multas6.svg");
            // graf.rasterizeMe("multas6.jpg");
            graf.rasterizeMe("multas6.png");
            // graf.rasterizeMe("multas6.tif");
        } catch (TranscoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
