package br.pess.afmo.svggrph;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;


/**
 * DOCUMENT ME!
 *
 * @author antoniofmoliveira@outlook.com
 */
public class TesteLinhaColuna {
/**
     * 
     */
    public TesteLinhaColuna() {
        super();
    }

    /**
     * DOCUMENT ME!
     *
     * @param args
     */
    public static void main(String[] args) {
        SVGLinhaColuna graf = new SVGLinhaColuna();
        graf.setSeriesColuna(new Double[][] {
                { 326654.0, 778827.0, 790083.0, 332082.0 },
                { 32654.0, 77827.0, 79083.0, 33082.0 },
                { 32664.0, 77887.0, 79003.0, 33202.0 },
                { 6292.0, 18102.0, 11117.0, 2885.0 },
            });
        graf.setSeriesLinha(new Double[][] {
                { 10.1, 10.7, 10.6, 11.5 },
                { 32.4, 31.3, 30.2, 30.9 },
                { 42.4, 41.3, 40.2, 40.9 },
                { 52.4, 51.3, 50.2, 50.9 },
            });
        graf.setYTituloLeft("Quantidade de Verificações");
        graf.setYTituloRight("Percentual");
        graf.setTags(new String[] { "jul-set/2005", "ago-out/2005", "set-nov/2005", "out/05-dez/05" });
        graf.setSeriesNamesColuna(new String[] { "Ponderado(V)", "Bruta(V)", "Verificações(V)", "Verificações(V)", "rating 5", });
        graf.setSeriesNamesLinha(new String[] { "Ponderado(Q)", "Bruta(Q)", "Verificações(Q)", "Verificações(Q)" });
        // graf.setColors(new String[]{ "green", "red", "blue", "violet" });
        graf.setPlotar(new boolean[] { true, true, true, true });
        graf.setYTicsLeft(10);
        graf.setYTicsRight(10);
        graf.setObservacoes(new String[] {
                "Ponderada: índice calculado conforme pesos do Rating.", //                "Bruta: quantidade de fichas com não conformidade dividida pela quantidade verificada.",
            });
        graf.setBase("bas/00-bas/00");
        graf.setCasasDecimaisColuna(new int[] { 0, 0, 0, 0, 1 });
        graf.setCasasDecimaisLinha(new int[] { 1, 1, 2, 2, 1 });
        graf.setTitulo("Operações de Crédito");
        // graf(false);
        graf.setSize(1000);

        try {
            // graf.toSVG("linhacoluna.svg");
            // graf.toXML("linhacoluna.svg");
            // graf.rasterizeMe("linhacoluna.jpg");
            graf.rasterizeMe("linhacoluna.png");
            // graf.rasterizeMe("linhacoluna.tif");
        } catch (TranscoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
