package com.abdo.learn.utils;

import org.springframework.stereotype.Component;



@Component
public class PhotosUtils {
    public String getExtensionByStringHandling(String filename) {
    return filename.substring(filename.lastIndexOf(".") + 1);
}
}
