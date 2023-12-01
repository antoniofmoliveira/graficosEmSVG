/* $Id: SVGCilindros.java 18 2006-09-14 17:11:42Z antoniofmoliveira@outlook.com $ */
package br.pess.afmo.svggrph;

import org.apache.batik.transcoder.TranscoderException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.UnsupportedEncodingException;

import java.text.DecimalFormat;


/**
 * DOCUMENT ME!
 *
 * @author antoniofmoliveira@outlook.com
 */
public class SVGCilindros extends GraficoSVGAbstrato {
    private double size = 1000;
    private int cor = -1;

/**
     * 
     */
    public SVGCilindros() {
        super();
        nfi.setMinimumIntegerDigits(0);
        nfd.setMaximumFractionDigits(1);
    }

    /*
     * (non-Javadoc)
     *
     * @see br.com.bb.controles.caderno.DOMgraf.DOMGrafico#getSVGDocument()
     */
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
        Element egraf = create("g", "id:grafico");
        setAttribute(egraf, "style", "stroke:black;stroke-width:1;fill:black; font-family:Verdana;");

        Element etext = create("g", "id:texto");
        Element g1 = create("g", "stroke:none;stroke-width:.2;stroke-linejoin:miter;opacity:.9");

        if (efeito3D) {
            setAttribute(g1, "filter", "url(#flt)");
        }

        Element g2 = (Element) g1.cloneNode(true);
        Element g3 = (Element) g1.cloneNode(true);
        DecimalFormat df = new DecimalFormat("#");

        if (yMaxLeft == 0) {
            for (int i = 0; i < serie.length; i++)
                yMaxLeft = (serie[i] > yMaxLeft) ? serie[i] : yMaxLeft;
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

        // ====================
        // ================
        double ax = size - 100;
        double n = ax / serie.length;
        double o = n / 2;
        double[] nx = new double[serie.length];
        double[] mx = new double[serie.length];
        double larguraColuna = (n) * .48;

        for (int i = 0; i < serie.length; i++) {
            nx[i] = 40 + (n * (i + 1));
            mx[i] = nx[i] - o;

            String lcor = colors[i];

            if (cor != -1) {
                lcor = colors[cor];
            }

            g1.appendChild(create("ellipse",
                    "opacity:.9;fill:url(#t" + lcor + ");cx:" + mx[i] + ";cy:" + (270 - (200 / d * serie[i])) + ";rx:" +
                    larguraColuna + ";ry:" + (larguraColuna / 2)));

            String pd = "M" + s(mx[i] - larguraColuna) + s(270 - (200 / d * serie[i])) + " L " + s(mx[i] - larguraColuna) +
                s(270) + "A 10,5 0 0,0 " + s(mx[i] + larguraColuna) + s(270) + " L " + s(mx[i] + larguraColuna) +
                s(270 - (200 / d * serie[i])) + " A 10,5 0 0,1 " + s(mx[i] - larguraColuna) + s(270 - (200 / d * serie[i]));
            g2.appendChild(create("path", "fill:url(#c" + lcor + ");d:" + pd));
            g3.appendChild(create("ellipse",
                    "opacity:.2;fill:url(#t" + lcor + ");cx:" + mx[i] + ";cy:270;rx:" + larguraColuna + ";ry:" +
                    (larguraColuna / 2)));
            etext.appendChild(text("text", "x:" + mx[i] + ";y:315;transform:rotate(0," + mx[i] + ",315)",
                    "font-size:9px;text-anchor:middle;", serieNames[i]));
            etext.appendChild(text("text",
                    "x:" + (mx[i]) + ";y:" + (250 - (200 / d * serie[i])) + ";transform:rotate(0," + (mx[i] - (o / 2)) + "," +
                    (250 - (200 / d * serie[i])) + ")", "font-size:9.5px;text-anchor:middle;", nfd.format(serie[i])));
        }

        Element l = text("text", "x:" + (size / 2) + ";y:20", "font-size:12px;text-anchor:middle;", null);
        l.appendChild(text("tspan", "", null, titulo));
        l.appendChild(text("tspan", "x:" + (size / 2) + ";dy:+1.5em", null, subtitulo));
        etext.appendChild(l);
        g.appendChild(create("path",
                "filter:url(#flt);opacity:.2;stroke:blue;stroke-width:4;d: M 30,300 L " + (size - 2) + ",300 L " + (size - 42) +
                ",240 L 0,240 z"));

        Element t = text("text", "id:base;x:" + size + ";fill:black;y:400", "text-anchor:end;font-size:6", null);
        t.appendChild(text("tspan", "dx:-1em;dy:-1em", null, base));
        etext.appendChild(t);

        // System.out.println(svgRoot.getAttribute("width")+"_"+svgRoot.getAttribute("height"));
        if (observacoes.length > 0) {
            t = text("text", "id:base;x:5;fill:black;y:" + (400 - (9 * observacoes.length)), "text-anchor:start;font-size:7", null);

            for (int i = 0; i < observacoes.length; i++) {
                t.appendChild(text("tspan", "x:5;dy:1em", null, observacoes[i]));
            }

            etext.appendChild(t);
        }

        g.appendChild(g1);
        g.appendChild(g2);
        g.appendChild(g3);
        g.appendChild(etext);
        svgRoot.appendChild(g);

        return doc;
    }

    /**
     * DOCUMENT ME!
     *
     * @param casasDecimais The casasDecimais to set.
     */
    public void setCasasDecimais(int casasDecimais) {
        nfd.setMaximumFractionDigits(casasDecimais);
    }

    /**
     * DOCUMENT ME!
     *
     * @param cor The cor to set.
     */
    public void setCor(int cor) {
        this.cor = cor;
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
        size = 100 + (70 * serie.length);

        double escala = (NSize > 0) ? (NSize / size) : 1;
        Element g = create("g", "id:fundo;transform:scale(" + escala + ")");
        setAttribute(g, "style", "stroke-width:1; font-family:Verdana;");
        g.appendChild(create("rect", "id:fundorect;width:100%;height:100%;fill:white;stroke:white;x:0;y:0"));
        setAttributes(svgRoot, "width:" + ((NSize > 0) ? NSize : size) + ";height:" +
            ((NSize > 0) ? ((400 * NSize) / size) : 400));

        // String script="\nSVGDocument = null\n"+
        // "function Initialize(LoadEvent){SVGDocument =
        // LoadEvent.getTarget().getOwnerDocument()}\n"+
        // "function highlight( id, h ){ SVGDocument.getElementById( id
        // ).getStyle().setProperty('stroke', '#000');\n"+
        // " SVGDocument.getElementById( id
        // ).getStyle().setProperty('stroke-width', '1.0');}\n"+
        // "function lowlight( id ){ SVGDocument.getElementById( id
        // ).getStyle().setProperty('stroke', 'gray');\n"+
        // " SVGDocument.getElementById( id
        // ).getStyle().setProperty('stroke-width', '0.5');}\n";
        // Element s= create("script","type:text/javascript");
        // s.appendChild(doc.createCDATASection(script));
        // svgRoot.appendChild(s);
        // setAttribute(svgRoot,"onload","Initialize(evt)");
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
        Element filter = create("filter", "id:flt;filterUnits:userSpaceOnUse;x:0;y:0;width:100%;height:100%");
        filter.appendChild(create("feGaussianBlur", "in:SourceAlpha;result:blur;stdDeviation:2"));
        filter.appendChild(create("feOffset", "in:blur;dx:2;dy:2;result:offsetBlur"));

        Element feSpecularLighting = create("feSpecularLighting",
                "specularConstant:.75;specularExponent:20;surfaceScale:10;lighting-color:#bbbbbb;result:specOut;in:blur");
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

        if (cor > -1) {
            e.appendChild(gradienteC(colors[cor], colorsTo[cor]));
            e.appendChild(gradienteR(colors[cor], colorsTo[cor]));
            e.appendChild(gradienteT(colors[cor], colorsTo[cor]));
        } else {
            for (int i = 0; i < serie.length; i++) {
                e.appendChild(gradienteC(colors[i], colorsTo[i]));
                e.appendChild(gradienteR(colors[i], colorsTo[i]));
                e.appendChild(gradienteT(colors[i], colorsTo[i]));
            }
        }

        svgRoot.appendChild(e);
    }
}
