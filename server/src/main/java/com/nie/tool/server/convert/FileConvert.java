package com.nie.tool.server.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author njy
 * @since 2024/7/31 09:19
 */
@Mapper
public interface FileConvert {
    FileConvert INSTANCE = Mappers.getMapper(FileConvert.class);




}
