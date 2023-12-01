/* $Id: SVGColuna.java 24 2007-05-30 19:26:52Z antoniofmoliveira@outlook.com $ */
package br.pess.afmo.svggrph;

import org.apache.batik.transcoder.TranscoderException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.UnsupportedEncodingException;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.Locale;


/**
 * DOCUMENT ME!
 *
 * @author antoniofmoliveira@outlook.com
 */
public class SVGColuna extends GraficoSVGAbstrato {
    private double size = 500;
    private boolean[] plotar = { true, true, true, true, true };
    private int[] casasDecimais = { 1, 1, 1, 1, 1 };
    private NumberFormat[] nfs = {  };

/**
     * Creates a new DOMBarra object.
     */
    public SVGColuna() {
        super();
        nfi.setMinimumIntegerDigits(0);
        nfp.setMaximumFractionDigits(1);
        setCasasDecimais(this.casasDecimais);
    }

    /**
     * DOCUMENT ME!
     *
     * @param plotar The yTitulo to set.
     */
    /**
     * DOCUMENT ME!
     *
     * @param plotar The plotar to set.
     */
    public void setPlotar(boolean[] plotar) {
        this.plotar = plotar;
    }

    /**
     * DOCUMENT ME!
     *
     * @param casasDecimais The casasDecimais to set.
     */
    public void setCasasDecimais(int[] casasDecimais) {
        this.casasDecimais = casasDecimais;
        nfs = new NumberFormat[casasDecimais.length];

        for (int i = 0; i < casasDecimais.length; i++) {
            nfs[i] = NumberFormat.getNumberInstance(Locale.GERMAN);
            nfs[i].setMaximumFractionDigits(casasDecimais[i]);
            nfs[i].setMinimumFractionDigits(casasDecimais[i]);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws UnsupportedEncodingException DOCUMENT ME!
     * @throws TranscoderException DOCUMENT ME!
     */
    public Document getSVGDocument() throws UnsupportedEncodingException, TranscoderException {
        setDefs();

        Element g = prepare();
        Element egraf = create("g", "filter:url(#flt);id:grafico");
        setAttribute(egraf, "style", "stroke:black;stroke-width:1;fill:black; font-family:Verdana;");

        Element etext = create("g", "id:texto");
        Element ecolunas = (Element) egraf.cloneNode(true);

        if (efeito3D) {
            egraf.setAttributeNS(null, "filter", "url(#flt)");
            ecolunas.setAttributeNS(null, "filter", "url(#flt)");
        }

        DecimalFormat df = new DecimalFormat("#");

        if (yMaxLeft == 0) {
            for (int i = 0; i < seriesColuna.length; i++)
                if (plotar[i]) {
                    for (int j = 0; j < seriesColuna[i].length; j++)
                        yMaxLeft = (seriesColuna[i][j] > yMaxLeft) ? seriesColuna[i][j] : yMaxLeft;
                }
        }

        String m = df.format(yMaxLeft);
        double m1 = Double.parseDouble(m.substring(0, 1)) * Math.pow(10, (m.length() - 1));
        double m2 = Math.pow(10, (m.length() - 1));
        double d = m1;

        if (d < yMaxLeft) {
            m1 = (Double.parseDouble(m.substring(0, 1)) + 1) * Math.pow(10, (m.length() - 1));
            m2 = Math.pow(10, (m.length() - 1));
            d = m1;
        }

        if (d > (yMaxLeft * 1.2)) {
            m = df.format(yMaxLeft * 1.2);
            m1 = Double.parseDouble(m.substring(0, 2)) * Math.pow(10, (m.length() - 2));
            m2 = Math.pow(10, (m.length() - 1));
            d = m1;
        }

        double d2 = m2;

        if (d2 >= (d / 2)) {
            d2 = d2 / 10;

            if ((d / d2) > 10) {
                d2 = d2 * 2;
            }
        }

        yMaxLeft = d;

        double tics = 0;

        if (yTicsLeft == 0) {
            tics = d / d2;
        } else {
            tics = yTicsLeft;
            d2 = d / yTicsLeft;
        }

        double dist1 = (240 - 40) / tics;

        for (int i = 0; i < tics; i++) {
            double y = 40 + (i * dist1);
            egraf.appendChild(line("76;" + y + ";460;" + y));
            etext.appendChild(text("text", "x:72;y:" + (y + 3), "font-size:8px;text-anchor:end;", nfi.format((d - (d2 * i)))));
        }

        etext.appendChild(text("text", "x:72;y:240", "font-size:8px;text-anchor:end;", "0"));
        egraf.appendChild(line("76;240;80;240"));
        egraf.appendChild(line("80;40;80;240"));
        egraf.appendChild(line("80;240;460;240"));

        double ax = 460 - 80;
        double n = ax / seriesColuna[0].length;
        double o = n / 2;
        double[] nx = new double[seriesColuna[0].length];
        double[] mx = new double[seriesColuna[0].length];
        int naoPlotar = 0;

        for (int i = 0; i < plotar.length; i++) {
            if (!plotar[i]) {
                naoPlotar++;
            }
        }

        double larguraColuna = (n * .8) / (seriesColuna.length - naoPlotar);

        for (int i = 0; i < seriesColuna[0].length; i++) {
            nx[i] = 80 + (n * (i + 1));
            mx[i] = nx[i] - o;
            egraf.appendChild(line(nx[i] + ";240;" + nx[i] + ";260"));
            egraf.appendChild(line(mx[i] + ";238;" + mx[i] + ";242"));
        }

        egraf.appendChild(line("80;240;80;260"));

        // ==================
        for (int i = 0; i < seriesColuna[0].length; i++) {
            int contador = -1;

            for (int j = 0; j < seriesColuna.length; j++) {
                if (plotar[j]) {
                    contador++;
                    ecolunas.appendChild(create("rect",
                            "stroke-width:0.1;x:" + (nx[i] - (n * .9) + (contador * larguraColuna)) + ";y:" +
                            (240 - (200 / d * seriesColuna[j][i])) + ";width:" + (larguraColuna * .88) + ";height:" +
                            (200 / d * seriesColuna[j][i]) + ";fill:" + colors[j]));
                }
            }
        }

        for (int i = 0; i < tags.length; i++) {
            etext.appendChild(text("text", "x:" + mx[i] + ";y:255", "font-size:9px;text-anchor:middle;", tags[i]));
        }

        for (int i = 0; i < seriesColuna.length; i++) {
            etext.appendChild(text("text", "x:22;y:" + (270 + (10 * i)), "font-size:7px;text-anchor:start;", seriesNames[i]));

            for (int j = 0; j < seriesColuna[i].length; j++) {
//                etext.appendChild(text("text", "x:" + mx[j] + ";y:" + (270 + (10 * i)), "font-size:7px;text-anchor:middle;",
//                        nfs[i].format(seriesColuna[i][j])));
            }

            if (plotar[i]) {
                ecolunas.appendChild(create("circle",
                        "stroke-width:0.1;stroke:black;r:4;cx:10;cy:" + (267 + (10 * i)) + ";fill:" + colors[i]));
            }
        }

        etext.appendChild(text("text", "x:270;y:20", "font-size:12px;text-anchor:middle;", titulo));
        etext.appendChild(text("text", "id:tituloy;x:30;y:140;transform:rotate(-90,30,140)", "font-size:12;text-anchor:middle;",
                yTituloLeft));

        Element t = text("text",
                "id:base;x:" + (size - 10) + ";fill:black;y:" +
                ((size * .7) + ((seriesColuna.length > 8) ? ((seriesColuna.length - 8) * 20) : 0)),
                "text-anchor:end;font-size:7", null);
        t.appendChild(text("tspan", "dx:-1em;dy:-4em", null, base));
        etext.appendChild(t);

        if (observacoes.length > 0) {
            t = text("text",
                    "id:base;x:5;fill:black;y:" +
                    (((size * 0.7) + ((seriesColuna.length > 8) ? ((seriesColuna.length - 8) * 20) : 0)) -
                    (9 * observacoes.length)), "text-anchor:start;font-size:6", null);

            for (int i = 0; i < observacoes.length; i++) {
                t.appendChild(text("tspan", "x:5;dy:1em", null, observacoes[i]));
            }

            etext.appendChild(t);
        }

        g.appendChild(egraf);
        g.appendChild(ecolunas);
        g.appendChild(etext);
        svgRoot.appendChild(g);

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
    protected Element prepare() {
        double largura = (NSize > 0) ? NSize : size;
        double escala = largura / size;
        Element g = create("g", "id:fundo;transform:scale(" + escala + ")");
        setAttribute(g, "style", "stroke-width:1; font-family:Verdana;");
        g.appendChild(create("rect", "id:fundorect;width:100%;height:100%;fill:white;stroke:white;x:0;y:0"));
        setAttributes(svgRoot,
            "width:" + largura + ";height:" +
            ((largura * .7) + ((seriesColuna.length > 8) ? (((seriesColuna.length - 8) * 20) + 10) : 0)));

        // <g style="font-family:Verdana;" transform=" skewX(35) rotate(35)
        // scale(.9) translate(50,10)">\
        // <g style="font-family:Verdana;" transform=" skewX(-35) rotate(-35)
        // scale(.9) translate(50,400)">/
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
    protected void setDefs() {
        Element e = create("defs", "");
        Element filter = create("filter", "id:flt");
        filter.appendChild(create("feGaussianBlur", "in:SourceAlpha;result:blur;stdDeviation:2"));
        filter.appendChild(create("feOffset", "in:blur;dx:2;dy:2;result:offsetBlur"));

        Element feSpecularLighting = create("feSpecularLighting",
                "specularConstant:1;specularExponent:10;surfaceScale:3;lighting-color:white;result:specOut;in:blur");
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
