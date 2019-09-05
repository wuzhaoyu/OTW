package com.wzy.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wzy.domain.File;
import com.wzy.mapper.FileMapper;
import com.wzy.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author zzw
 * @since 2018-08-01
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Autowired
    private FileMapper fileMapper;
}
