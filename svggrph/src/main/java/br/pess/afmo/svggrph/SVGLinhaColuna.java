/* $Id: SVGLinhaColuna.java 18 2006-09-14 17:11:42Z antoniofmoliveira@outlook.com $ */
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
public class SVGLinhaColuna extends GraficoSVGAbstrato {
    private double size = 500;

    //    private String titulo = "titulo";
    private boolean[] plotar = { true, true, true, true, true };
    private int[] casasDecimaisColuna = { 1, 1, 1, 1, 1 };
    private int[] casasDecimaisLinha = { 1, 1, 1, 1, 1 };
    private NumberFormat[] nfsColuna = {  };
    private NumberFormat[] nfsLinha = {  };
    private double yMaxLeft = 0;

    //    private double yTicsLeft = 0;
    private String[] colorsLinha = {  };
    private String[] colorsColuna = {  };

/**
     * Creates a new DOMBarra object.
     */
    public SVGLinhaColuna() {
        super();
        nfi.setMinimumIntegerDigits(0);
        nfd.setMaximumFractionDigits(1);
        setCasasDecimaisColuna(this.casasDecimaisColuna);
        setCasasDecimaisLinha(this.casasDecimaisLinha);
        colorsColuna = colors;
        colorsLinha = colorsTo;
    }

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
    public void setCasasDecimaisColuna(int[] casasDecimais) {
        this.casasDecimaisColuna = casasDecimais;
        nfsColuna = new NumberFormat[casasDecimais.length];

        for (int i = 0; i < casasDecimais.length; i++) {
            nfsColuna[i] = NumberFormat.getNumberInstance(Locale.GERMAN);
            nfsColuna[i].setMaximumFractionDigits(casasDecimais[i]);
            nfsColuna[i].setMinimumFractionDigits(casasDecimais[i]);
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
        Element elinhas = (Element) egraf.cloneNode(true);
        Element ecolunas = (Element) egraf.cloneNode(true);

        if (efeito3D) {
            egraf.setAttributeNS(null, "filter", "url(#flt)");
            elinhas.setAttributeNS(null, "filter", "url(#flt)");
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
            etext.appendChild(text("text", "x:72;y:" + (y + 3), "font-size:7px;text-anchor:end;",
                    nfi.format((yMaxLeft - (d2 * i)))));
        }

        etext.appendChild(text("text", "x:72;y:243", "font-size:8px;text-anchor:end;", "0"));
        egraf.appendChild(line("76;240;80;240"));
        egraf.appendChild(line("80;40;80;240"));
        egraf.appendChild(line("80;240;460;240"));
        egraf.appendChild(line("460;40;460;240"));

        if (yMaxRight == 0) {
            for (int i = 0; i < seriesLinha.length; i++)
                if (plotar[i]) {
                    for (int j = 0; j < seriesLinha[i].length; j++)
                        yMaxRight = (seriesLinha[i][j] > yMaxRight) ? seriesLinha[i][j] : yMaxRight;
                }
        }

        m = df.format(yMaxRight);
        m1 = Double.parseDouble(m.substring(0, 1)) * Math.pow(10, (m.length() - 1));
        m2 = Math.pow(10, (m.length() - 1));
        d = m1;

        if (d < yMaxRight) {
            m1 = (Double.parseDouble(m.substring(0, 1)) + 1) * Math.pow(10, (m.length() - 1));
            m2 = Math.pow(10, (m.length() - 1));
            d = m1;
        }

        if (d > (yMaxRight * 1.2)) {
            m = df.format(yMaxRight * 1.2);
            m1 = Double.parseDouble(m.substring(0, 2)) * Math.pow(10, (m.length() - 2));
            m2 = Math.pow(10, (m.length() - 1));
            d = m1;
        }

        yMaxRight = d;
        tics = 0;

        if (yTicsRight == 0) {
            tics = d / d2;
        } else {
            tics = yTicsRight;
            d2 = d / yTicsRight;
        }

        dist1 = (240 - 40) / tics;

        for (int i = 0; i < tics; i++) {
            double y = 40 + (i * dist1);
            egraf.appendChild(line("460;" + y + ";464;" + y));
            etext.appendChild(text("text", "x:475;y:" + (y + 3), "font-size:7px;text-anchor:end;",
                    nfi.format((yMaxRight - (d2 * i)))));
        }

        etext.appendChild(text("text", "x:475;y:243", "font-size:8px;text-anchor:end;", "0"));

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
                //                if (plotar[j]) {
                contador++;
                ecolunas.appendChild(create("rect",
                        "stroke-width:0.1;x:" + (nx[i] - (n * .9) + (contador * larguraColuna)) + ";y:" +
                        (240 - (200 / yMaxLeft * seriesColuna[j][i])) + ";width:" + (larguraColuna * .88) + ";height:" +
                        (200 / yMaxLeft * seriesColuna[j][i]) + ";fill:" + colorsColuna[j]));

                //                }
            }
        }

        double vant = -1;

        for (int i = 0; i < seriesLinha.length; i++) {
            //            if (plotar[i]) {
            StringBuffer sbl = new StringBuffer();

            for (int j = 0; j < seriesLinha[i].length; j++) {
                if (seriesLinha[i][j] > 0) {
                    if ((j != 0) && (vant != 0)) {
                        sbl.append(" L ");
                    } else if (vant == 0) {
                        sbl.append(" M ");
                    }

                    sbl.append(mx[j]).append(",").append(240 - ((240 - 40) / yMaxRight * seriesLinha[i][j]));
                }

                vant = seriesLinha[i][j];
            }

            if (sbl.toString().length() > 0) {
                String dd = sbl.toString();

                if (!dd.trim().substring(0, 1).equalsIgnoreCase("M")) {
                    dd = "M " + dd;
                }

                elinhas.appendChild(create("path", "fill:none;stroke-width:3;stroke:" + colorsLinha[i] + ";d:" + dd));
            }

            //            }
        }

        for (int i = 0; i < seriesLinha.length; i++)

            //            if (plotar[i]) {
            for (int j = 0; j < seriesLinha[i].length; j++) {
                if (seriesLinha[i][j] > 0) {
                    elinhas.appendChild(create("circle",
                            "r:4;stroke:black;cx:" + mx[j] + ";cy:" + (240 - (200 / yMaxRight * seriesLinha[i][j])) +
                            ";stroke-width:" + 0.1 + ";fill:" + colorsLinha[i]));
                }
            }

        //            }
        for (int i = 0; i < tags.length; i++) {
            //            etext.appendChild(text("text", "x:" + mx[i] + ";y:255;transform:rotate(-45," + mx[i] + ",255)", "font-size:8px;text-anchor:end;", tags[i]));
            etext.appendChild(text("text", "x:" + mx[i] + ";y:255;transform:rotate(0," + mx[i] + ",255)",
                    "font-size:8px;text-anchor:middle;", tags[i]));
        }

        double fimAnt = 0;

        for (int i = 0; i < seriesColuna.length; i++) {
            fimAnt = i;
            etext.appendChild(text("text", "x:22;y:" + (270 + (10 * i)), "font-size:8px;text-anchor:start;", seriesNamesColuna[i]));

            for (int j = 0; j < seriesColuna[i].length; j++) {
                etext.appendChild(text("text", "x:" + mx[j] + ";y:" + (270 + (10 * i)), "font-size:8px;text-anchor:middle;",
                        nfsColuna[i].format(seriesColuna[i][j])));
            }

            //            if (plotar[i]) {
            ecolunas.appendChild(create("rect",
                    "stroke-width:0.1;stroke:black;width:8;height:8;x:6;y:" + (263 + (10 * i)) + ";fill:" + colorsColuna[i]));

            //            }
        }

        for (int i = 0; i < seriesLinha.length; i++) {
            etext.appendChild(text("text", "x:22;y:" + (270 + (10 * (i + fimAnt + 1))), "font-size:8px;text-anchor:start;",
                    seriesNamesLinha[i]));

            for (int j = 0; j < seriesLinha[i].length; j++) {
                etext.appendChild(text("text", "x:" + mx[j] + ";y:" + (270 + (10 * (i + fimAnt + 1))),
                        "font-size:8px;text-anchor:middle;", nfsLinha[i].format(seriesLinha[i][j])));
            }

            //            if (plotar[i]) {
            elinhas.appendChild(create("path",
                    "stroke:" + colorsLinha[i] + ";stroke-width:3;fill:" + colorsLinha[i] + ";d:" + "M 2" +
                    s(267 + (10 * (i + fimAnt + 1))) + " L 18" + s(267 + (10 * (i + fimAnt + 1)))));
            elinhas.appendChild(create("circle",
                    "stroke-width:0.1;stroke:black;r:4;cx:10;cy:" + (267 + (10 * (i + fimAnt + 1))) + ";fill:" + colorsLinha[i]));

            //            }
        }

        etext.appendChild(text("text", "x:270;y:20", "font-size:12px;text-anchor:middle;", titulo));
        etext.appendChild(text("text", "id:tituloyleft;x:30;y:140;transform:rotate(-90,30,140)",
                "font-size:12;text-anchor:middle;", yTituloLeft));
        etext.appendChild(text("text", "id:tituloyright;x:490;y:140;transform:rotate(90,490,140)",
                "font-size:12;text-anchor:middle;", yTituloRight));
        etext.appendChild(text("text", "id:tituloxbot;x:270;y:" + ((size * .7) - 20), "font-size:12;text-anchor:middle;",
                xTituloBottom));

        Element t = text("text", "id:base;x:" + ((size * 1.03) - 10) + ";fill:black;y:" + (size * .7),
                "text-anchor:end;font-size:8", null);
        t.appendChild(text("tspan", "dx:-1em;dy:-1em", null, base));
        etext.appendChild(t);

        if (observacoes.length > 0) {
            t = text("text", "id:base;x:5;fill:black;y:" + ((size * 0.7) - (9 * observacoes.length)),
                    "text-anchor:start;font-size:6", null);

            for (int i = 0; i < observacoes.length; i++) {
                t.appendChild(text("tspan", "x:5;dy:1em", null, observacoes[i]));
            }

            etext.appendChild(t);
        }

        g.appendChild(egraf);
        g.appendChild(ecolunas);
        g.appendChild(elinhas);
        g.appendChild(etext);
        svgRoot.appendChild(g);

        return doc;
    }

    /**
     * DOCUMENT ME!
     *
     * @param casasDecimais The casasDecimaisLinha to set.
     */
    public void setCasasDecimaisLinha(int[] casasDecimais) {
        this.casasDecimaisLinha = casasDecimais;
        nfsLinha = new NumberFormat[casasDecimaisLinha.length];

        for (int i = 0; i < casasDecimais.length; i++) {
            nfsLinha[i] = NumberFormat.getNumberInstance(Locale.GERMAN);
            nfsLinha[i].setMaximumFractionDigits(casasDecimaisLinha[i]);
            nfsLinha[i].setMinimumFractionDigits(casasDecimaisLinha[i]);
        }
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
        setAttributes(svgRoot, "width:" + (largura * 1.03) + ";height:" + (largura * .7));

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

    /**
     * DOCUMENT ME!
     *
     * @return Returns the nfsColuna.
     */
    public NumberFormat[] getNfsColuna() {
        return this.nfsColuna;
    }

    /**
     * DOCUMENT ME!
     *
     * @param nfsColuna The nfsColuna to set.
     */
    public void setNfsColuna(NumberFormat[] nfsColuna) {
        this.nfsColuna = nfsColuna;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the nfsLinha.
     */
    public NumberFormat[] getNfsLinha() {
        return this.nfsLinha;
    }

    /**
     * DOCUMENT ME!
     *
     * @param nfsLinha The nfsLinha to set.
     */
    public void setNfsLinha(NumberFormat[] nfsLinha) {
        this.nfsLinha = nfsLinha;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String[] getColorsColuna() {
        return colorsColuna;
    }

    /**
     * DOCUMENT ME!
     *
     * @param colorsColuna DOCUMENT ME!
     */
    public void setColorsColuna(String[] colorsColuna) {
        this.colorsColuna = colorsColuna;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String[] getColorsLinha() {
        return colorsLinha;
    }

    /**
     * DOCUMENT ME!
     *
     * @param colorsLinha DOCUMENT ME!
     */
    public void setColorsLinha(String[] colorsLinha) {
        this.colorsLinha = colorsLinha;
    }
}
