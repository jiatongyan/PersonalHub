package com.dylan.personalhub.service;

import com.dylan.personalhub.entity.Article;
import com.dylan.personalhub.mapper.ArticleMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleService {


    private final ArticleMapper articleMapper;


    public ArticleService(
            ArticleMapper articleMapper
    ){

        this.articleMapper = articleMapper;

    }


    public List<Article> getAll(){

        return articleMapper.findAll();

    }


    public Article getById(Long id){

        return articleMapper.findById(id);

    }


}