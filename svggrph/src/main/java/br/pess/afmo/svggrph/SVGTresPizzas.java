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
public class SVGTresPizzas extends GraficoSVGAbstrato {
    private double[] serieEsquerda = null;
    private double[] serieDireita = null;
    private double[] serieCentro = null;
    private double size = 800;
    private String[] subMAlta = {  };
    private String[] subAlta = {  };
    private String[] subMedia = {  };

/**
     *
     */
    public SVGTresPizzas() {
        super();
    }

    /**
     * DOCUMENT ME!
     *
     * @param subAlta The subAlta to set.
     */
    public void setSubAlta(String[] subAlta) {
        this.subAlta = subAlta;
    }

    /**
     * DOCUMENT ME!
     *
     * @param subMAlta The subMAlta to set.
     */
    public void setSubMAlta(String[] subMAlta) {
        this.subMAlta = subMAlta;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the serie1.
     */
    public double[] getSerieEsquerda() {
        return this.serieEsquerda;
    }

    /**
     * DOCUMENT ME!
     *
     * @param serie1 The serie1 to set.
     */
    public void setSerieEsquerda(double[] serie1) {
        this.serieEsquerda = serie1;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the serie2.
     */
    public double[] getSerieDireita() {
        return this.serieDireita;
    }

    /**
     * DOCUMENT ME!
     *
     * @param serie2 The serie2 to set.
     */
    public void setSerieDireita(double[] serie2) {
        this.serieDireita = serie2;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the serie3.
     */
    public double[] getSerieCentro() {
        return this.serieCentro;
    }

    /**
     * DOCUMENT ME!
     *
     * @param serie3 The serie3 to set.
     */
    public void setSerieCentro(double[] serie3) {
        this.serieCentro = serie3;
    }

    /**
     * DOCUMENT ME!
     *
     * @param subMedia The subMedia to set.
     */
    public void setSubMedia(String[] subMedia) {
        this.subMedia = subMedia;
    }

    private Element[] getGraficoCentro() {
        Element egraf = create("g", "id:graficoc;transform:translate(400,150)");
        Element etext = create("g", "id:textoc;transform:translate(400,150)");
        String[] colors = { "green", "blue", "yellow", "red", "violet", "orange", "" };
        String[] tags = { "baixa", "média", "alta", "muito alta", "outra" };
        double[] serie = serieCentro;
        double raio = (size * 70) / 800;
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
                    double anguloTexto = ((anguloFinal - anguloInicial) / 2) + anguloInicial;
                    double pYtexto = -1 * (raio * 1.35) * Math.sin(anguloTexto);
                    double pXtexto = (raio * 1.35) * Math.cos(anguloTexto);
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
                    // t.appendChild(text("tspan", "id:val" + i + ";x:" +
                    // pXtexto + ";dy:1em", null, nfd.format(serie[i])));
                    t.appendChild(text("tspan", "id:perc" + i + ";x:" + pXtexto + ";dy:1em", null,
                            nfp.format(serie[i] / valorTotal)));
                    etext.appendChild(t);
                    offset = anguloFinal;
                }
            }
        } else {
            egraf.appendChild(create("circle", "id:fatia0;cx:0;cy:0;stroke:black;r:" + raio + ";fill:" + colors[serieNZero]));

            Element t = text("text", "id:texto0;x:0;y:0;fill:black", "text-anchor:middle;font-size:12", null);
            t.appendChild(text("tspan", "id:tag0;x:0;dy:-.5em", null, tags[serieNZero]));
            //            t.appendChild(text("tspan", "id:val0;x:0;dy:1em", null, nfd.format(serie[serieNZero])));
            t.appendChild(text("tspan", "id:perc0;x:0;dy:1em", null, "100%"));
            etext.appendChild(t);
        }

        return new Element[] { egraf, etext };
    }

    private Element[] getGraficoDireita() {
        Element egraf = create("g", "id:graficod;transform:translate(650,150)");
        Element etext = create("g", "id:textod;transform:translate(650,150)");
        String[] colors = { "red", "blue" };
        String[] tags = { "Não Conforme", "Conforme" };
        double[] serie = serieDireita;
        double raio = (size * 100) / 800;
        double valorTotal = 0;
        double offset = 0;

        for (int i = 0; i < serie.length; i++)
            valorTotal += serie[i];

        for (int i = 0; i < serie.length; i++) {
            double angulo = serie[i] / valorTotal * Math.PI * 2;
            boolean largeArc = angulo >= Math.PI;
            angulo += offset;

            double eangulo = 0;
            double anguloFinal = 0;
            double anguloInicial = 0;
            double anguloTexto = 0;

            if (i == 0) {
                anguloFinal = (angulo / 2) - Math.PI;
                offset = anguloFinal;
                anguloInicial = Math.PI - (angulo / 2);
                eangulo = Math.PI;
            } else {
                anguloFinal = angulo;
                anguloInicial = -anguloFinal;
            }

            if (i == 0) {
                anguloTexto = Math.PI;
            } else {
                anguloTexto = 0;
            }

            double eY = -1 * raio * 0.01 * Math.sin(eangulo);
            double eX = raio * 0.01 * Math.cos(eangulo);
            double pYtexto = -1 * raio * 0.5 * Math.sin(anguloTexto);
            double pXtexto = raio * 0.5 * Math.cos(anguloTexto);
            double pYinicial = (-1 * raio * Math.sin(anguloInicial)) + eY;
            double pXinicial = (raio * Math.cos(anguloInicial)) + eX;
            double pYfinal = (-1 * raio * Math.sin(anguloFinal)) + eY;
            double pXfinal = (raio * Math.cos(anguloFinal)) + eX;
            String d = "M 0 0 M" + s(eX) + s(eY) + " L" + s(pXinicial) + s(pYinicial) + " A" + s(raio) + s(raio);
            d += ((largeArc ? " 0 1 0 " : " 0 0 0 ") + s(pXfinal) + s(pYfinal) + " z ");
            egraf.appendChild(create("path", "id:fatia" + i + ";fill:" + colors[i] + ";d:" + d));
            d = "M " + s(pXfinal) + s(pYfinal) + " L -250 " + ((i == 0) ? (75) : (-75));
            egraf.appendChild(create("path", "id:linha" + i + ";stroke:gray;stroke-width:.5;d:" + d));

            Element e = text("text", "x:" + pXtexto + ";y:" + pYtexto, "text-anchor:middle;font-size:12", null);
            e.appendChild(text("tspan", "x:" + pXtexto + ";dy:-.5em", null, tags[i]));
            e.appendChild(text("tspan", "x:" + pXtexto + ";dy:1em", null, nfp.format(serie[i])));
            etext.appendChild(e);
        }

        return new Element[] { egraf, etext };
    }

    private Element[] getGraficoEsquerda() {
        Element egraf = create("g", "id:graficoe;transform:translate(150,150)");
        Element etext = create("g", "id:textoe;transform:translate(150,150)");
        String[] colors = { "red", "blue" };
        String[] tags = { "Não Conforme", "Conforme" };
        double[] serie = serieEsquerda;
        double raio = (size * 100) / 800;
        double valorTotal = 0;
        double offset = 0;

        for (int i = 0; i < serie.length; i++)
            valorTotal += serie[i];

        for (int i = 0; i < serie.length; i++) {
            double anguloTexto = 0;
            double angulo = serie[i] / valorTotal * Math.PI * 2;
            boolean largeArc = angulo >= Math.PI;
            angulo += offset;

            double eangulo = 0;
            double anguloFinal = 0;

            if (i == 0) {
                anguloFinal = angulo / 2;
                offset = anguloFinal;
                eangulo = anguloFinal;
            } else {
                anguloFinal = angulo;
                eangulo = angulo / 2;
            }

            double anguloInicial = -anguloFinal;

            if (i == 0) {
                anguloTexto = 0;
            } else {
                anguloTexto = Math.PI;
            }

            double eY = -1 * raio * 0.01 * Math.sin(eangulo);
            double eX = raio * 0.01 * Math.cos(eangulo);
            double pYtexto = -1 * raio * 0.5 * Math.sin(anguloTexto);
            double pXtexto = raio * 0.5 * Math.cos(anguloTexto);
            double pYinicial = (-1 * raio * Math.sin(anguloInicial)) + eY;
            double pXinicial = (raio * Math.cos(anguloInicial)) + eX;
            double pYfinal = (-1 * raio * Math.sin(anguloFinal)) + eY;
            double pXfinal = (raio * Math.cos(anguloFinal)) + eX;
            String d = "M 0 0 M" + s(eX) + s(eY) + " L" + s(pXinicial) + s(pYinicial) + " A" + s(raio) + s(raio);
            d += ((largeArc ? " 0 1 0 " : " 0 0 0 ") + s(pXfinal) + s(pYfinal) + " z ");
            egraf.appendChild(create("path", "id:fatia" + i + ";fill:" + colors[i] + ";d:" + d));
            d = "M " + s(pXfinal) + s(pYfinal) + " L 250 " + ((i == 0) ? (-75) : 75);
            egraf.appendChild(create("path", "id:linha" + i + ";stroke:gray;stroke-width:.5;d:" + d));

            Element e = text("text", "x:" + pXtexto + ";y:" + pYtexto, "text-anchor:middle;font-size:12", null);
            e.appendChild(text("tspan", "x:" + pXtexto + ";dy:-.5em", null, tags[i]));
            e.appendChild(text("tspan", "x:" + pXtexto + ";dy:1em", null, nfp.format(serie[i])));
            etext.appendChild(e);
        }

        return new Element[] { egraf, etext };
    }

    /*
     * (non-Javadoc)
     *
     * @see br.com.bb.controles.caderno.svg.GraficoSVGAbstrato#getSVGDocument()
     */
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws UnsupportedEncodingException DOCUMENT ME!
     * @throws TranscoderException DOCUMENT ME!
     */
    @Override
    public Document getSVGDocument() throws UnsupportedEncodingException, TranscoderException {
        setDefs();

        Element g = prepare();
        Element egraf = create("g", "filter:url(#flt);id:grafico");
        Element etext = create("g", "id:texto");
        setAttribute(etext, "style", "font-family:Verdana;font-size:8");

        Element[] g1 = getGraficoEsquerda();
        egraf.appendChild(g1[0]);
        etext.appendChild(g1[1]);

        Element[] g2 = getGraficoDireita();
        egraf.appendChild(g2[0]);
        etext.appendChild(g2[1]);

        Element[] g3 = getGraficoCentro();
        egraf.appendChild(g3[0]);
        etext.appendChild(g3[1]);

        Element t = text("text", "x:10;y:310;", "font-family:Bitstream Vera Serif;font-size:9", "Principais Falhas:");

        if (subMAlta.length > 0) {
            t.appendChild(text("tspan", "x:20;dy:+1em;", null, "Criticidade Muito Alta:"));

            for (int i = 0; i < subMAlta.length; i++) {
                t.appendChild(text("tspan", "x:50;dy:+1em;", null, subMAlta[i]));
            }
        }

        if (subAlta.length > 0) {
            t.appendChild(text("tspan", "x:20;dy:+1em;", null, "Criticidade Alta:"));

            for (int i = 0; i < subAlta.length; i++) {
                t.appendChild(text("tspan", "x:50;dy:+1em;", null, subAlta[i]));
            }
        }

        if (subMedia.length > 0) {
            t.appendChild(text("tspan", "x:20;dy:+1em;", null, "Criticidade Média:"));

            for (int i = 0; i < subMedia.length; i++) {
                t.appendChild(text("tspan", "x:50;dy:+1em;", null, subMedia[i]));
            }
        }

        etext.appendChild(t);
        etext.appendChild(text("text", "x:400;y:30", "text-anchor:middle;font-size:16", titulo));
        t = text("text", "x:150;y:265", "text-anchor:middle;font-size:14", null);
        t.appendChild(text("tspan", "x:150;dy:+0em;", null, "Nível de Conformidade"));
        t.appendChild(text("tspan", "x:150;dy:+1em;", null, "Absoluto"));
        t.appendChild(text("tspan", "x:150;dy:+1em;", null, "(por dossiê)"));
        etext.appendChild(t);
        t = text("text", "x:650;y:265", "text-anchor:middle;font-size:14", null);
        t.appendChild(text("tspan", "x:650;dy:+0em;", null, "Nível de Conformidade"));
        t.appendChild(text("tspan", "x:650;dy:+1em;", null, "ajustado ao Risco"));
        t.appendChild(text("tspan", "x:650;dy:+1em;", null, "(por dossiê)"));
        etext.appendChild(t);
        t = text("text", "x:400;y:260", "text-anchor:middle;font-size:14", null);
        t.appendChild(text("tspan", "x:400;dy:+0em;", null, "Não Conformidade"));
        t.appendChild(text("tspan", "x:400;dy:+1em;", null, "por Criticidade"));
        etext.appendChild(t);
        t = text("text",
                "id:base;x:" + ((NSize > 0) ? NSize : size) + ";fill:black;y:" + ((NSize > 0) ? ((NSize * 380) / 800) : 380),
                "text-anchor:end;font-size:8", null);
        t.appendChild(text("tspan", "dx:-1em;dy:-1em", null, base));
        etext.appendChild(t);
        g.appendChild(egraf);
        g.appendChild(etext);
        svgRoot.appendChild(g);
        calculated = true;

        return doc;
    }

    /*
     * (non-Javadoc)
     *
     * @see br.com.bb.controles.caderno.svg.GraficoSVGAbstrato#prepare()
     */
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    protected Element prepare() {
        double largura = (NSize > 0) ? NSize : size;
        double altura = (NSize > 0) ? ((NSize * 380) / 800) : 380;
        double escala = largura / size;
        Element g = create("g", "id:fundo;transform: scale(" + escala + ")");
        setAttribute(g, "style", "stroke-width:1; font-family:Verdana;");
        g.appendChild(create("rect", "id:fundorect;width:100%;height:100%;fill:white;stroke:white;x:0;y:0"));
        setAttributes(svgRoot, "width:" + largura + ";height:" + altura);

        return g;
    }

    /*
     * (non-Javadoc)
     *
     * @see br.com.bb.controles.caderno.svg.GraficoSVGAbstrato#setDefs()
     */
    /**
     * DOCUMENT ME!
     */
    @Override
    protected void setDefs() {
        Element e = create("defs", "");
        Element filter = create("filter", "id:flt");
        filter.appendChild(create("feGaussianBlur", "in:SourceAlpha;result:blur;stdDeviation:4"));
        filter.appendChild(create("feOffset", "in:blur;dx:4;dy:4;result:offsetBlur"));

        Element feSpecularLighting = create("feSpecularLighting",
                "specularConstant:1;specularExponent:10;surfaceScale:5;lighting-color:white;result:specOut;in:blur");
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
}
