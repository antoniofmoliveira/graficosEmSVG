/* $Id: GraficoSVGAbstrato.java 24 2007-05-30 19:26:52Z antoniofmoliveira@outlook.com $ */
package br.pess.afmo.svggrph;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.dom.GenericDOMImplementation;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.transcoder.image.TIFFTranscoder;
import org.apache.batik.transcoder.svg2svg.SVGTranscoder;
import org.apache.batik.util.XMLResourceDescriptor;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import java.text.NumberFormat;

import java.util.Locale;
import java.util.zip.GZIPOutputStream;

/**
 * DOCUMENT ME!
 *
 * @author antoniofmoliveira@outlook.com
 */
public abstract class GraficoSVGAbstrato {
    protected String svgNS = null;
    protected DOMImplementation impl = null;
    protected Document doc = null;
    protected Element svgRoot = null;
    protected double size = 1000;
    protected String titulo = null;
    protected String subtitulo = null;
    protected NumberFormat nfi = null;
    protected NumberFormat nfd = null;
    protected NumberFormat nfp = null;
    protected double NSize = 0;
    protected String base = null;
    protected boolean efeito3D = true;
    protected String[] observacoes = {};
    protected String xTituloBottom = null;
    protected String xTituloTop = null;
    protected String yTituloLeft = null;
    protected String yTituloRight = null;
    protected String[] xTags = {};
    protected String[] yTags = {};
    protected String[] tags = {};
    protected String[] serieNames = {};
    protected String[] seriesNames = null;
    protected Double[] serie = {};
    protected double[][] series = {};
    protected Double[][] seriesLinha = {};
    protected Double[][] seriesColuna = {};
    protected String[] seriesNamesColuna = null;
    protected String[] seriesNamesLinha = null;
    protected double yMaxLeft = 0;
    protected double yTicsLeft = 0;
    protected double yMaxRight = 0;
    protected double yTicsRight = 0;
    protected String[] colors = {
            "violet", "blue", "green", "orange", "red", "lightpink", "gray", "lightskyblue", "lightcyan", "salmon",
            "turquoise",
            "khaki", "lightgoldenrodyellow", "lightcoral", "lightyellow"
    };
    protected String[] colorsTo = {
            "darkviolet", "darkblue", "darkgreen", "darkorange", "darkred", "pink", "darkgray", "skyblue", "cyan",
            "darksalmon",
            "darkturquoise", "darkkhaki", "goldenrod", "coral", "yellow"
    };
    protected boolean calculated = false;

    /**
     * 
     */
    public GraficoSVGAbstrato() {
        super();
        impl = GenericDOMImplementation.getDOMImplementation();
        svgNS = "http://www.w3.org/2000/svg";
        doc = impl.createDocument(svgNS, "svg", null);
        svgRoot = doc.getDocumentElement();
        nfi = NumberFormat.getIntegerInstance(Locale.GERMAN);
        nfi.setMinimumIntegerDigits(0);
        nfd = NumberFormat.getNumberInstance(Locale.GERMAN);
        nfd.setMaximumFractionDigits(2);
        nfp = NumberFormat.getPercentInstance(Locale.GERMAN);
        nfp.setMaximumFractionDigits(1);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws UnsupportedEncodingException DOCUMENT ME!
     * @throws TranscoderException          DOCUMENT ME!
     */
    public abstract Document getSVGDocument() throws UnsupportedEncodingException, TranscoderException;

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    protected abstract Element prepare();

    /**
     * DOCUMENT ME!
     */
    protected abstract void setDefs();

    /**
     * DOCUMENT ME!
     *
     * @param d DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    protected String s(double d) {
        return " " + ds(d);
    }

    /**
     * DOCUMENT ME!
     *
     * @param d DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    protected String ds(double d) {
        try {
            return Double.toString(d);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param fileName DOCUMENT ME!
     *
     * @throws TranscoderException DOCUMENT ME!
     * @throws IOException
     */
    public void toXML(String fileName) throws TranscoderException, IOException {
        if (!calculated) {
            doc = getSVGDocument();
        }

        if (fileName.endsWith(".svg")) {
            fileName += "z";
        }

        Writer out = new OutputStreamWriter(new GZIPOutputStream(
                new BufferedOutputStream(new FileOutputStream(new File(fileName)))), "UTF-8");
        TranscoderInput ti = new TranscoderInput(doc);
        TranscoderOutput to = new TranscoderOutput(out);
        SVGTranscoder st = new SVGTranscoder();
        st.addTranscodingHint(SVGTranscoder.KEY_XML_DECLARATION,
                "<?xml version='1.0' encoding='UTF-8' standalone='no' ?>");
        // st.addTranscodingHint(SVGTranscoder.KEY_NEWLINE, SVGTranscoder.VALUE_NEWLINE_CR_LF);
        st.transcode(ti, to);
        out.flush();
        out.close();
    }

    public void toSVG(String fileName) throws TranscoderException, IOException {
        if (!calculated) {
            doc = getSVGDocument();
        }

        // if (fileName.endsWith(".svg")) {
        //     fileName += "z";
        // }

        Writer out = new OutputStreamWriter(
                new BufferedOutputStream(new FileOutputStream(new File(fileName))), "UTF-8");
        TranscoderInput ti = new TranscoderInput(doc);
        TranscoderOutput to = new TranscoderOutput(out);
        SVGTranscoder st = new SVGTranscoder();
        st.addTranscodingHint(SVGTranscoder.KEY_XML_DECLARATION,
                "<?xml version='1.0' encoding='UTF-8' standalone='no' ?>");
        // st.addTranscodingHint(SVGTranscoder.KEY_NEWLINE, SVGTranscoder.VALUE_NEWLINE_CR_LF);
        st.transcode(ti, to);
        out.flush();
        out.close();
    }

    /**
     * DOCUMENT ME!
     *
     * @param doc      DOCUMENT ME!
     * @param fileName DOCUMENT ME!
     *
     * @throws TranscoderException DOCUMENT ME!
     * @throws IOException
     */
    public static void toXML(Document doc, String fileName)
            throws TranscoderException, IOException {
        if (fileName.endsWith(".svg")) {
            fileName += "z";
        }

        Writer out = new OutputStreamWriter(new GZIPOutputStream(
                new BufferedOutputStream(new FileOutputStream(new File(fileName)))), "UTF-8");
        TranscoderInput ti = new TranscoderInput(doc);
        TranscoderOutput to = new TranscoderOutput(out);
        SVGTranscoder st = new SVGTranscoder();
        st.addTranscodingHint(SVGTranscoder.KEY_XML_DECLARATION,
                "<?xml version='1.0' encoding='UTF-8' standalone='no' ?>");
        // st.addTranscodingHint(SVGTranscoder.KEY_NEWLINE, SVGTranscoder.VALUE_NEWLINE_CR_LF);
        st.transcode(ti, to);
        out.flush();
        out.close();
    }

    /**
     * DOCUMENT ME!
     *
     * @param fileName DOCUMENT ME!
     *
     * @throws TranscoderException DOCUMENT ME!
     * @throws IOException         DOCUMENT ME!
     */
    public void rasterizeMe(String fileName) throws TranscoderException, IOException {
        if (!calculated) {
            doc = getSVGDocument();
        }

        GraficoSVGAbstrato.rasterize(doc, fileName);
    }

    /**
     * DOCUMENT ME!
     *
     * @param document DOCUMENT ME!
     * @param fileName DOCUMENT ME!
     *
     * @throws TranscoderException DOCUMENT ME!
     * @throws IOException         DOCUMENT ME!
     */
    public static void rasterize(Document document, String fileName)
            throws TranscoderException, IOException {
        TranscoderInput ti = new TranscoderInput(document);
        OutputStream ostream = new FileOutputStream(fileName);
        TranscoderOutput to = new TranscoderOutput(ostream);
        Transcoder st = null;

        if (fileName.toUpperCase().endsWith("PNG")) {
            st = new PNGTranscoder();
        } else if (fileName.toUpperCase().endsWith("JPG")) {
            st = new JPEGTranscoder();
        } else if (fileName.toUpperCase().endsWith("TIF")) {
            st = new TIFFTranscoder();
        }

        if (null != st) {
            st.transcode(ti, to);
            ostream.flush();
            ostream.close();
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param fileSVG  DOCUMENT ME!
     * @param fileName DOCUMENT ME!
     *
     * @throws TranscoderException DOCUMENT ME!
     * @throws IOException         DOCUMENT ME!
     */
    protected static void rasterize(String fileSVG, String fileName)
            throws TranscoderException, IOException {
        String svgURI = new File(fileSVG).toURI().toURL().toString();
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        Document doc = f.createDocument(svgURI);
        rasterize(doc, fileName);
    }

    /**
     * DOCUMENT ME!
     *
     * @param fileSVG DOCUMENT ME!
     *
     * @throws TranscoderException DOCUMENT ME!
     * @throws IOException         DOCUMENT ME!
     */
    public static void rasterize(String fileSVG) throws TranscoderException, IOException {
        String file = fileSVG;

        if (!fileSVG.toUpperCase().endsWith(".SVGZ")) {
            file += ".svgz";
        }

        rasterize(fileSVG, file.replace(".svgz", ".png"));
    }

    /**
     * DOCUMENT ME!
     *
     * @param element    DOCUMENT ME!
     * @param attributes DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    protected Element create(String element, String attributes) {
        Element e = doc.createElementNS(svgNS, element);
        setAttributes(e, attributes);

        return e;
    }

    /**
     * DOCUMENT ME!
     *
     * @param e          DOCUMENT ME!
     * @param attributes DOCUMENT ME!
     */
    protected void setAttributes(Element e, String attributes) {
        String[] args = attributes.split(";");

        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                setAttribute(e, args[i]);
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param e  DOCUMENT ME!
     * @param kv DOCUMENT ME!
     */
    protected void setAttribute(Element e, String kv) {
        String[] args = kv.split(":");

        if (args.length == 2) {
            setAttribute(e, args[0], args[1]);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param e DOCUMENT ME!
     * @param k DOCUMENT ME!
     * @param v DOCUMENT ME!
     */
    protected void setAttribute(Element e, String k, String v) {
        if ((null != k) && (null != v)) {
            e.setAttributeNS(null, k, v);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param from DOCUMENT ME!
     * @param to   DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    protected Element gradienteC(String from, String to) {
        Element e = create("linearGradient", "id:c" + from);
        e.appendChild(create("stop", "offset:20%;stop-color:" + from));
        e.appendChild(create("stop", "offset:80%;stop-color:white"));
        e.appendChild(create("stop", "offset:90%;stop-color:" + to));

        return e;
    }

    /**
     * DOCUMENT ME!
     *
     * @param from DOCUMENT ME!
     * @param to   DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    protected Element gradienteT(String from, String to) {
        Element e = create("linearGradient", "id:t" + from);
        e.appendChild(create("stop", "offset:20%;stop-color:" + from));
        e.appendChild(create("stop", "offset:90%;stop-color:" + to));

        return e;
    }

    /**
     * DOCUMENT ME!
     *
     * @param from DOCUMENT ME!
     * @param to   DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    protected Element gradienteR(String from, String to) {
        Element e = create("radialGradient",
                "id:r" + from + ";gradientUnits:objectBoundingBox;cx:0.5;cy:0.5;r:1;fx:.7;fy:.4");
        e.appendChild(create("stop", "offset:0%;stop-color:white"));
        e.appendChild(create("stop", "offset:50%;stop-color:" + from));
        e.appendChild(create("stop", "offset:50%;stop-color:" + to));

        return e;
    }

    /**
     * DOCUMENT ME!
     *
     * @param positions DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    protected Element line(String positions) {
        String[] args = positions.split(";");
        Element l = doc.createElementNS(svgNS, "line");
        l.setAttributeNS(null, "x1", args[0]);
        l.setAttributeNS(null, "y1", args[1]);
        l.setAttributeNS(null, "x2", args[2]);
        l.setAttributeNS(null, "y2", args[3]);

        return l;
    }

    /**
     * DOCUMENT ME!
     *
     * @param e          DOCUMENT ME!
     * @param attributes DOCUMENT ME!
     * @param estilo     DOCUMENT ME!
     * @param texto      DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    protected Element text(String e, String attributes, String estilo, String texto) {
        Element l = create(e, attributes);

        if (null != estilo) {
            setAttribute(l, "style", estilo);
        }

        if (null != texto) {
            l.appendChild(doc.createTextNode(texto));
        }

        return l;
    }

    /**
     * DOCUMENT ME!
     *
     * @param arquivo DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String getArquivo(String arquivo) {
        try {
            File f = new File(arquivo);
            BufferedReader fr = new BufferedReader(new FileReader(f));
            String buf = "";
            boolean reading = true;

            while (reading) {
                String tx;
                tx = fr.readLine();

                if (null == tx) {
                    reading = false;
                } else {
                    buf += tx;
                }

                ;
            }

            fr.close();

            return buf;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public double getSize() {
        return NSize;
    }

    /**
     * DOCUMENT ME!
     *
     * @param size DOCUMENT ME!
     */
    public void setSize(double size) {
        NSize = size;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Double[] getSerie() {
        return serie;
    }

    /**
     * DOCUMENT ME!
     *
     * @param serie DOCUMENT ME!
     */
    public void setSerie(Double[] serie) {
        this.serie = serie;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String[] getTags() {
        return tags;
    }

    /**
     * DOCUMENT ME!
     *
     * @param tags DOCUMENT ME!
     */
    public void setTags(String[] tags) {
        this.tags = tags;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String[] getColors() {
        return colors;
    }

    /**
     * DOCUMENT ME!
     *
     * @param colors DOCUMENT ME!
     */
    public void setColors(String[] colors) {
        this.colors = colors;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * DOCUMENT ME!
     *
     * @param titulo DOCUMENT ME!
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getBase() {
        return base;
    }

    /**
     * DOCUMENT ME!
     *
     * @param base DOCUMENT ME!
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the series.
     */
    public double[][] getSeries() {
        return this.series;
    }

    /**
     * DOCUMENT ME!
     *
     * @param series The series to set.
     */
    public void setSeries(double[][] series) {
        this.series = series;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the xTituloBottom.
     */
    public String getXTituloBottom() {
        return this.xTituloBottom;
    }

    /**
     * DOCUMENT ME!
     *
     * @param tituloBottom The xTituloBottom to set.
     */
    public void setXTituloBottom(String tituloBottom) {
        this.xTituloBottom = tituloBottom;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the xTituloTop.
     */
    public String getXTituloTop() {
        return this.xTituloTop;
    }

    /**
     * DOCUMENT ME!
     *
     * @param tituloTop The xTituloTop to set.
     */
    public void setXTituloTop(String tituloTop) {
        this.xTituloTop = tituloTop;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the yTituloLeft.
     */
    public String getYTituloLeft() {
        return this.yTituloLeft;
    }

    /**
     * DOCUMENT ME!
     *
     * @param tituloLeft The yTituloLeft to set.
     */
    public void setYTituloLeft(String tituloLeft) {
        this.yTituloLeft = tituloLeft;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the yTituloRight.
     */
    public String getYTituloRight() {
        return this.yTituloRight;
    }

    /**
     * DOCUMENT ME!
     *
     * @param tituloRight The yTituloRight to set.
     */
    public void setYTituloRight(String tituloRight) {
        this.yTituloRight = tituloRight;
    }

    /**
     * DOCUMENT ME!
     *
     * @param ytics DOCUMENT ME!
     */
    public void setYTicsLeft(double ytics) {
        this.yTicsLeft = ytics;
    }

    /**
     * DOCUMENT ME!
     *
     * @param observacoes DOCUMENT ME!
     */
    public void setObservacoes(String[] observacoes) {
        this.observacoes = observacoes;
    }

    /**
     * DOCUMENT ME!
     *
     * @param max DOCUMENT ME!
     */
    public void setMaximoY(double max) {
        this.yMaxLeft = max;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the seriesNames.
     */
    public String[] getSeriesNames() {
        return this.seriesNames;
    }

    /**
     * DOCUMENT ME!
     *
     * @param seriesNames The seriesNames to set.
     */
    public void setSeriesNames(String[] seriesNames) {
        this.seriesNames = seriesNames;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the seriesLinha.
     */
    public Double[][] getSeriesLinha() {
        return this.seriesLinha;
    }

    /**
     * DOCUMENT ME!
     *
     * @param seriesLinha The seriesLinha to set.
     */
    public void setSeriesLinha(Double[][] seriesLinha) {
        this.seriesLinha = seriesLinha;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the seriesColuna.
     */
    public Double[][] getSeriesColuna() {
        return this.seriesColuna;
    }

    /**
     * DOCUMENT ME!
     *
     * @param seriesColuna The seriesColuna to set.
     */
    public void setSeriesColuna(Double[][] seriesColuna) {
        this.seriesColuna = seriesColuna;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the seriesNamesColuna.
     */
    public String[] getSeriesNamesColuna() {
        return this.seriesNamesColuna;
    }

    /**
     * DOCUMENT ME!
     *
     * @param seriesNamesColuna The seriesNamesColuna to set.
     */
    public void setSeriesNamesColuna(String[] seriesNamesColuna) {
        this.seriesNamesColuna = seriesNamesColuna;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the seriesNamesLinha.
     */
    public String[] getSeriesNamesLinha() {
        return this.seriesNamesLinha;
    }

    /**
     * DOCUMENT ME!
     *
     * @param seriesNamesLinha The seriesNamesLinha to set.
     */
    public void setSeriesNamesLinha(String[] seriesNamesLinha) {
        this.seriesNamesLinha = seriesNamesLinha;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the yMaxLeft.
     */
    public double getYMaxLeft() {
        return this.yMaxLeft;
    }

    /**
     * DOCUMENT ME!
     *
     * @param maxLeft The yMaxLeft to set.
     */
    public void setYMaxLeft(double maxLeft) {
        this.yMaxLeft = maxLeft;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the yMaxRight.
     */
    public double getYMaxRight() {
        return this.yMaxRight;
    }

    /**
     * DOCUMENT ME!
     *
     * @param maxRight The yMaxRight to set.
     */
    public void setYMaxRight(double maxRight) {
        this.yMaxRight = maxRight;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the yTicsRight.
     */
    public double getYTicsRight() {
        return this.yTicsRight;
    }

    /**
     * DOCUMENT ME!
     *
     * @param ticsRight The yTicsRight to set.
     */
    public void setYTicsRight(double ticsRight) {
        this.yTicsRight = ticsRight;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the yTicsLeft.
     */
    public double getYTicsLeft() {
        return this.yTicsLeft;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the subtitulo.
     */
    public String getSubtitulo() {
        return this.subtitulo;
    }

    /**
     * DOCUMENT ME!
     *
     * @param subtitulo The subtitulo to set.
     */
    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the serieNames.
     */
    public String[] getSerieNames() {
        return this.serieNames;
    }

    /**
     * DOCUMENT ME!
     *
     * @param serieNames The serieNames to set.
     */
    public void setSerieNames(String[] serieNames) {
        this.serieNames = serieNames;
    }

    /**
     * DOCUMENT ME!
     *
     * @return Returns the efeito3D.
     */
    public boolean isEfeito3D() {
        return this.efeito3D;
    }

    /**
     * DOCUMENT ME!
     *
     * @param efeito3D The efeito3D to set.
     */
    public void setEfeito3D(boolean efeito3D) {
        this.efeito3D = efeito3D;
    }
}
