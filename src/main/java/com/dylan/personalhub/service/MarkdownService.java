package com.dylan.personalhub.service;


import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;


@Service
public class MarkdownService {


    private final Parser parser;

    private final HtmlRenderer renderer;



    public MarkdownService(){


        parser = Parser.builder()
                .build();


        renderer = HtmlRenderer.builder()
                .build();

    }



    public String render(String markdown){


        if(markdown == null){

            return "";

        }


        Node document = parser.parse(markdown);


        return renderer.render(document);

    }

}