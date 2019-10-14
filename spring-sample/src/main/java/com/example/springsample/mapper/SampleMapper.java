package com.example.springsample.mapper;

import com.example.springsample.annotation.Sample;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
@Sample
public interface SampleMapper {
    default Integer select(){return  1;}
}
