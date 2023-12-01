package br.pess.afmo.svggrph;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;


/**
 * DOCUMENT ME!
 *
 * @author antoniofmoliveira@outlook.com
 */
public class TesteColuna {
/**
     * 
     */
    public TesteColuna() {
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
                { 10.1, 10.7, 10.6, 11.5 },
                { 32.4, 31.3, 30.2, 30.9 },
                { 37084.0, 38617.0, 38705.0, 34927.0 }
            });
        graf.setYTituloLeft("% Não Conformidade");
        graf.setTags(new String[] { "jul-set/2005", "ago-out/2005", "set-nov/2005", "out/05-dez/05" });
        graf.setSeriesNames(new String[] { "Ponderado", "Bruta", "Verificações", "rating 4", "rating 5", });
        // graf.setColors(new String[]{"blue", "red"});
        graf.setPlotar(new boolean[] { true, true, false });
        graf.setMaximoY(50);
        graf.setYTicsLeft(10);
        graf.setObservacoes(new String[] {
                "Ponderada: índice calculado conforme pesos do Rating.",
                "Bruta: quantidade de fichas com não conformidade dividida pela quantidade verificada.",
            });
        graf.setCasasDecimais(new int[] { 1, 1, 0, 1, 1 });
        graf.setTitulo("Operações de Crédito");
        graf.setBase("bas/00-bas/00");
        // graf.setEfeito3D(false);
        graf.setSize(1000);

        try {
            // graf.toSVG("coluna.svg");
            // graf.toXML("coluna.svg");
            // graf.rasterizeMe("coluna.jpg");
            graf.rasterizeMe("coluna.png");
            // graf.rasterizeMe("coluna.tif");
        } catch (TranscoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
