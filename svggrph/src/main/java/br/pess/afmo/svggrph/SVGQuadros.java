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
public class SVGQuadros extends GraficoSVGAbstrato {
    private double size = 700;

/**
     * 
     */
    public SVGQuadros() {
        super();
        nfi.setMinimumIntegerDigits(0);
        nfp.setMinimumFractionDigits(1);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    protected Element prepare() {
        double largura = (NSize > 0) ? NSize : size;
        double escala = largura / size;
        Element g = create("g", "id:fundo;transform: scale(" + escala + ")");
        setAttribute(g, "style", "stroke-width:1; font-family:Verdana;");
        g.appendChild(create("rect", "id:fundorect;width:" + size + ";height:" + size + ";fill:white;stroke:white;x:0;y:0"));
        setAttributes(svgRoot, "width:" + largura + ";height:" + largura);

        return g;
    }

    /* (non-Javadoc)
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
        setAttribute(egraf, "style", "stroke:none;stroke-width:.1;fill:black; font-family:Verdana;");

        Element epontos = create("g", "id:pontos");
        Element etext = create("g", "id:texto");

        if (efeito3D) {
            setAttribute(egraf, "filter", "url(#flt2)");
            setAttribute(egraf, "epontos", "url(#flt)");
        }

        egraf.appendChild(create("rect", "id=quadro1;x:90;y:300;width:310;height:315;fill:red"));
        egraf.appendChild(create("rect", "id=quadro2;x:400;y:300;width:215;height:315;fill:gold"));
        egraf.appendChild(create("rect", "id=quadro3;x:90;y:90;width:310;height:210;fill:gold"));
        egraf.appendChild(create("rect", "id=quadro4;x:400;y:90;width:215;height:210;fill:green"));

        double pos1 = 0;
        double pos2 = 0;
        double pos3 = 0;
        double pos4 = 0;

        for (int i = 0; i < series.length; i++) {
            epontos.appendChild(create("circle",
                    "id:ponto" + i + ";cy:" + (700 - series[i][0]) + ";cx:" + series[i][1] +
                    ";r:5;stroke:black;stroke-width:0.7;fill:burlywood"));

            if ((series[i][0] < 400) && (series[i][1] < 400)) {
                pos1 += 1;
            } else if ((series[i][0] < 400) && (series[i][1] >= 400)) {
                pos2 += 1;
            } else if ((series[i][0] >= 400) && (series[i][1] < 400)) {
                pos3 += 1;
            } else {
                pos4 += 1;
            }
        }

        etext.appendChild(text("text", "id:text1;x:250;y:645", "font-size:16px;text-anchor:middle;",
                nfi.format(pos1) + " agências (" + nfp.format(pos1 / series.length) + ")"));
        etext.appendChild(text("text", "id:text2;x:500;y:645", "font-size:16px;text-anchor:middle;",
                nfi.format(pos2) + " agências (" + nfp.format(pos2 / series.length) + ")"));
        etext.appendChild(text("text", "id:text3;x:250;y:80", "font-size:16px;text-anchor:middle;",
                nfi.format(pos3) + " agências (" + nfp.format(pos3 / series.length) + ")"));
        etext.appendChild(text("text", "id:text4;x:500;y:80", "font-size:16px;text-anchor:middle;",
                nfi.format(pos4) + " agências (" + nfp.format(pos4 / series.length) + ")"));
        etext.appendChild(text("text", "id:titulo;x:350;y:40", "font-size:24px;text-anchor:middle;", titulo));
        etext.appendChild(text("text", "id:y600;x:85;y:100", "font-size:14;text-anchor:end;", "600"));
        etext.appendChild(text("text", "id:y400;x:85;y:305", "font-size:14;text-anchor:end;", "400"));
        etext.appendChild(text("text", "id:y100;x:85;y:615", "font-size:14;text-anchor:end;", "100"));
        etext.appendChild(text("text", "id:x100;x:100;y:620;transform:rotate(-90,100,620)", "font-size:14;text-anchor:end;", "100"));
        etext.appendChild(text("text", "id:x400;x:405;y:620;transform:rotate(-90,405,620)", "font-size:14;text-anchor:end;", "400"));
        etext.appendChild(text("text", "id:x600;x:610;y:620;transform:rotate(-90,610,620)", "font-size:14;text-anchor:end;", "600"));
        etext.appendChild(text("text", "id:tituloy;x:50;y:350;transform:rotate(-90,50,350)", "font-size:20;text-anchor:middle;",
                yTituloLeft));
        etext.appendChild(text("text", "id:titulo;x:350;y:670", "font-size:20px;text-anchor:middle;", xTituloBottom));

        Element t = text("text", "id:base;x:" + (size - 10) + ";fill:black;y:" + (size - 10), "text-anchor:end;font-size:16", null);
        t.appendChild(text("tspan", "dx:-1em;dy:-1em", null, base));
        etext.appendChild(t);
        g.appendChild(egraf);
        g.appendChild(epontos);
        g.appendChild(etext);
        svgRoot.appendChild(g);

        return doc;
    }

    /**
     * DOCUMENT ME!
     */
    protected void setDefs() {
        Element e = create("defs", "id:defs");
        Element filter = create("filter", "id:flt");
        filter.appendChild(create("feGaussianBlur", "in:SourceAlpha;result:blur;stdDeviation:2"));
        filter.appendChild(create("feOffset", "in:blur;dx:2;dy:2;result:offsetBlur"));

        Element feSpecularLighting = create("feSpecularLighting",
                "specularConstant:1;specularExponent:10;surfaceScale:3;lighting-color:#aaaaaa;result:specOut;in:blur");
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
        filter = create("filter", "id:flt2");
        filter.appendChild(create("feGaussianBlur", "in:SourceAlpha;result:blur;stdDeviation:10"));
        filter.appendChild(create("feOffset", "in:blur;dx:4;dy:4;result:offsetBlur"));
        feSpecularLighting = create("feSpecularLighting",
                "specularConstant:1;specularExponent:10;surfaceScale:6;lighting-color:#aaaaaa;result:specOut;in:blur");
        feSpecularLighting.appendChild(create("fePointLight", "x:-5000;y:-10000;z:20000"));
        filter.appendChild(feSpecularLighting);
        filter.appendChild(create("feComposite", "in:specOut;in2:SourceAlpha;operator:in;result:specOut"));
        filter.appendChild(create("feComposite",
                "in:SourceGraphic;in2:specOut;operator:arithmetic;result:litPaint;k1:0;k2:1;k3:1;k4:0"));
        feMerge = create("feMerge", "");
        feMerge.appendChild(create("feMergeNode", "in:offsetBlur"));
        feMerge.appendChild(create("feMergeNode", "in:litPaint"));
        filter.appendChild(feMerge);
        e.appendChild(filter);
        svgRoot.appendChild(e);
    }
}
