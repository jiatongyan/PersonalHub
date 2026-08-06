package com.dylan.personalhub.mapper;

import com.dylan.personalhub.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ArticleMapper {

    List<Article> findAll();

    Article findById(Long id);

}