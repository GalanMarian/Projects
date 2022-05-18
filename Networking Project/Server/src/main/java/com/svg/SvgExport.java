package com.svg;

import com.model.Person;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.*;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SvgExport {
    private final DOMImplementation domImplementation = GenericDOMImplementation.getDOMImplementation();
    private final String svgNS = "http://www.w3.org/2000/svg";
    private final Document document = domImplementation.createDocument(svgNS, "html", null);
    private final SVGGeneratorContext svgGeneratorContext;
    private final SVGGraphics2D svgGenerator;
    private Map<String, List<String>> socialNetwork= new HashMap<>();

    public SvgExport(List<Person> socialNetwork) {
        this.svgGeneratorContext = SVGGeneratorContext.createDefault(document);
        svgGeneratorContext.setComment("Lab 10 - Social Network");
        svgGeneratorContext.setEmbeddedFontsOn(true);
        this.svgGenerator = new SVGGraphics2D(svgGeneratorContext, true);
        for(Person person: socialNetwork)
        {
            List<Person> friends= new ArrayList<>();
            List<String> nameOfFriends= new ArrayList<>();
            friends=person.getFriends();
            for(Person friend: friends)
                nameOfFriends.add(friend.getName());
            this.socialNetwork.put(person.getName(), nameOfFriends);
        }
    }

    public void export() throws IOException {
        Graphics2D svg = svgGenerator;
        svg.setColor(Color.BLACK);
        String fontName = "Raleway";
        Font font = new Font(fontName, Font.PLAIN, 10);
        svg.setFont(font);
        svg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        String line;
        int textY = 7;
        for(Map.Entry<String,List<String>> entry : socialNetwork.entrySet()){
            line = getString(entry.getKey());
            svg.drawString(line, 0, textY);
            textY += 10;
        }

        OutputStream outputStream = new FileOutputStream("F:/PA_2022_2B5_GALAN_SILVIU_MARIAN/Lab 10 - Homework/Server/test.svg");
        Writer out = new OutputStreamWriter(outputStream, "UTF-8");
        svgGenerator.stream(out, true);
    }

    private String getString(String username){
        StringBuilder line = new StringBuilder();

        line.append("username: ").append(username).append(" -> friends: ");

        for(String friend : socialNetwork.get(username)){
            line.append(friend).append("; ");
        }

        return line.toString();
    }
}
