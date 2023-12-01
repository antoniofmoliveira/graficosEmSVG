package br.pess.afmo.svggrph;
import org.apache.batik.transcoder.TranscoderException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.UnsupportedEncodingException;


/**
 * DOCUMENT ME!
 *
 * @author antoniofmoliveira@outlook.com
 */
public class SVGPizza extends GraficoSVGAbstrato {
    private double raio = 0;
    private boolean[] explode = {  };
    private boolean showPercent = true;
    private boolean showValue = false;

/**
     *
     */
    public SVGPizza() {
        super();
        nfi.setMinimumIntegerDigits(0);
        nfp.setMaximumFractionDigits(2);
        size = 400;
        raio = size * 0.35;
    }

    /**
     * DOCUMENT ME!
     */
    protected void setDefs() {
        Element e = create("defs", "");
        Element filter = create("filter", "id:flt");
        filter.appendChild(create("feGaussianBlur", "in:SourceAlpha;result:blur;stdDeviation:" + (size * 0.005)));
        filter.appendChild(create("feOffset", "in:blur;dx:" + (size * 0.005) + ";dy:" + (size * 0.005) + ";result:offsetBlur"));

        Element feSpecularLighting = create("feSpecularLighting",
                "specularConstant:1;specularExponent:10;surfaceScale:" + (size * 0.00625) +
                ";lighting-color:white;result:specOut;in:blur");
        feSpecularLighting.appendChild(create("fePointLight", "x:-5000;y:-10000;z:20000"));
        filter.appendChild(feSpecularLighting);
        filter.appendChild(create("feComposite", "in:specOut;in2:SourceAlpha;operator:in;result:specOut"));
        filter.appendChild(create("feComposite",
                "in:SourceGraphic;in2:specOut;operator:arithmetic;result:litPaint;k1:0;k2:1;k3:1;k4:0"));

        Element feMerge = create("feMerge", "");
        feMerge.appendChild(create("feMergeNode", "in:offsetBlur"));
        feMerge.appendChild(create("feMergeNode", "in:litPaint"));
        filter.appendChild(feMerge);
        e.appendChild(filter);
        svgRoot.appendChild(e);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    protected Element prepare() {
        double largura = (NSize > 0) ? NSize : size;
        double meio = largura / 2;
        double escala = largura / size;
        Element g = create("g", "id:fundo;transform:translate(" + meio + "," + meio + ")" + " scale(" + escala + ")");
        setAttribute(g, "style", "stroke-width:1; font-family:Verdana;");
        g.appendChild(create("rect",
                "id:fundorect;width:" + (largura * 2) + ";height:" + (largura * 2) + ";fill:white;stroke:white;x:" + -largura +
                ";y:" + -largura));
        setAttributes(svgRoot, "width:" + largura + ";height:" + largura);

        return g;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws UnsupportedEncodingException
     * @throws TranscoderException
     */
    public Document getSVGDocument() throws UnsupportedEncodingException, TranscoderException {
        setDefs();

        Element g = prepare();
        Element egraf = create("g", "filter:url(#flt);id:grafico");
        Element etext = create("g", "id:texto");
        int nzeros = 0;
        int serieNZero = 0;
        double valorTotal = 0;

        for (int i = 0; i < serie.length; i++) {
            valorTotal += serie[i];

            if (serie[i] > 0) {
                nzeros++;
                serieNZero = i;
            }
        }

        if (nzeros != 1) {
            double valAcum = 0;
            double offset = 0;

            for (int i = 0; i < serie.length; i++) {
                if (serie[i] > 0) {
                    valAcum += serie[i];

                    double angulo = valAcum / valorTotal * Math.PI * 2;
                    double anguloFinal = angulo;
                    double anguloInicial = offset;
                    double eangulo = ((anguloFinal - anguloInicial) / 2) + anguloInicial;
                    double eY = -1 * raio * 0.01 * Math.sin(eangulo);
                    double eX = raio * 0.01 * Math.cos(eangulo);

                    if (explode[i]) {
                        eY = -1 * raio * 0.1 * Math.sin(eangulo);
                        eX = raio * 0.1 * Math.cos(eangulo);
                    }

                    double anguloTexto = ((anguloFinal - anguloInicial) / 2) + anguloInicial;
                    double pYtexto = -1 * (raio * 1.15) * Math.sin(anguloTexto);
                    double pXtexto = (raio * 1.15) * Math.cos(anguloTexto);
                    boolean largeArc = (anguloFinal - anguloInicial) >= Math.PI;
                    double pYinicial = (-1 * raio * Math.sin(anguloInicial)) + eY;
                    double pXinicial = (raio * Math.cos(anguloInicial)) + eX;
                    double pYfinal = (-1 * raio * Math.sin(anguloFinal)) + eY;
                    double pXfinal = (raio * Math.cos(anguloFinal)) + eX;
                    String d = "M 0 0 M" + s(eX) + s(eY) + " L" + s(pXinicial) + s(pYinicial) + " A" + s(raio) + s(raio);
                    d += ((largeArc ? " 0 1 0 " : " 0 0 0 ") + s(pXfinal) + s(pYfinal) + " z");
                    egraf.appendChild(create("path", "id:fatia" + i + ";fill:" + colors[i] + ";d:" + d));

                    Element t = text("text", "id:texto" + i + ";x:" + pXtexto + ";y:" + pYtexto + ";fill:black",
                            "text-anchor:middle;font-size:12", null);
                    t.appendChild(text("tspan", "id:tag" + i + ";x:" + pXtexto + ";dy:-.5em", null, tags[i]));

                    if (showValue) {
                        t.appendChild(text("tspan", "id:val" + i + ";x:" + pXtexto + ";dy:1em", null, nfd.format(serie[i])));
                    }

                    if (showPercent) {
                        t.appendChild(text("tspan", "id:perc" + i + ";x:" + pXtexto + ";dy:1em", null,
                                nfp.format(serie[i] / valorTotal)));
                    }

                    etext.appendChild(t);
                    offset = anguloFinal;
                }
            }
        } else {
            egraf.appendChild(create("circle", "id:fatia0;cx:0;cy:0;stroke:black;r:" + raio + ";fill:" + colors[serieNZero]));

            Element t = text("text", "id:texto0;x:0;y:0;fill:black", "text-anchor:middle;font-size:12", null);
            t.appendChild(text("tspan", "id:tag0;x:0;dy:-.5em", null, tags[serieNZero]));

            if (showValue) {
                t.appendChild(text("tspan", "id:val0;x:0;dy:1em", null, nfd.format(serie[serieNZero])));
            }

            if (showPercent) {
                t.appendChild(text("tspan", "id:perc0;x:0;dy:1em", null, "100%"));
            }

            etext.appendChild(t);
        }

        etext.appendChild(text("text", "id:titulo;x:0;fill:black;y:" + ((-size / 2) + (size / 2 * 0.09)),
                "text-anchor:middle;font-size:16", titulo));

        Element t = text("text", "id:base;x:" + (size / 2) + ";fill:black;y:" + ((size / 2) - 9), "text-anchor:end;font-size:8",
                null);
        t.appendChild(text("tspan", "dx:-1em;dy:1em", null, base));
        etext.appendChild(t);

        if (observacoes.length > 0) {
            t = text("text", "id:base;x:" + (-size / 2) + ";fill:black;y:" + ((size / 2) - (9 * observacoes.length)),
                    "text-anchor:start;font-size:8", null);

            for (int i = 0; i < observacoes.length; i++) {
                t.appendChild(text("tspan", "x:" + (-size / 2) + ";dy:1em", null, observacoes[i]));
            }

            etext.appendChild(t);
        }

        etext.appendChild(t);
        g.appendChild(egraf);
        g.appendChild(etext);
        svgRoot.appendChild(g);
        calculated = true;

        return doc;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean[] getExplode() {
        return explode;
    }

    /**
     * DOCUMENT ME!
     *
     * @param explode DOCUMENT ME!
     */
    public void setExplode(boolean[] explode) {
        this.explode = explode;

        if (this.explode.length < serie.length) {
            boolean[] temp = new boolean[serie.length];

            for (int i = 0; i < temp.length; i++) {
                temp[i] = false;
            }

            for (int i = 0; i < explode.length; i++) {
                temp[i] = explode[i];
            }

            this.explode = temp;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param tags DOCUMENT ME!
     */
    public void setTags(String[] tags) {
        super.setTags(tags);

        if (tags.length < serie.length) {
            String[] temp = new String[serie.length];

            for (int i = 0; i < temp.length; i++) {
                temp[i] = "ND " + i;
            }

            for (int i = 0; i < tags.length; i++) {
                temp[i] = tags[i];
            }

            this.tags = temp;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param serie DOCUMENT ME!
     */
    public void setSerie(Double[] serie) {
        super.setSerie(serie);

        if (this.explode.length < serie.length) {
            boolean[] temp = new boolean[serie.length];

            for (int i = 0; i < temp.length; i++) {
                temp[i] = false;
            }

            for (int i = 0; i < explode.length; i++) {
                temp[i] = explode[i];
            }

            this.explode = temp;
        }

        if (tags.length < serie.length) {
            String[] temp = new String[serie.length];

            for (int i = 0; i < temp.length; i++) {
                temp[i] = "ND " + i;
            }

            for (int i = 0; i < tags.length; i++) {
                temp[i] = tags[i];
            }

            this.tags = temp;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param colors DOCUMENT ME!
     */
    public void setColors(String[] colors) {
        String[] localColors = colors;

        if (localColors.length < serie.length) {
            String[] temp = new String[serie.length];

            for (int i = 0; i < temp.length; i++) {
                temp[i] = colors[i];
            }

            for (int i = 0; i < localColors.length; i++) {
                temp[i] = localColors[i];
            }

            this.colors = temp;
        } else {
            this.colors = localColors;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public double getRaio() {
        return raio;
    }

    /**
     * DOCUMENT ME!
     *
     * @param raio DOCUMENT ME!
     */
    public void setRaio(double raio) {
        if (raio > (size * 0.35)) {
            this.raio = raio * 0.35;
        } else {
            this.raio = raio;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean isShowPercent() {
        return showPercent;
    }

    /**
     * DOCUMENT ME!
     *
     * @param showPercent DOCUMENT ME!
     */
    public void setShowPercent(boolean showPercent) {
        this.showPercent = showPercent;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean isShowValue() {
        return showValue;
    }

    /**
     * DOCUMENT ME!
     *
     * @param showValue DOCUMENT ME!
     */
    public void setShowValue(boolean showValue) {
        this.showValue = showValue;
    }
}
