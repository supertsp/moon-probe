package br.com.tiagopedroso.moonprobe.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MoonArea {

    private String name;

    private Integer
            width = 0,
            height = 0;

    public MoonArea(Integer width, Integer height) {
        setDimensions(width, height);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(Integer width) {
        if (width >= 0) {
            this.width = width;
        } else {
            this.width = 0;
        }
    }

    public void setHeight(Integer height) {
        if (height >= 0) {
            this.height = height;
        } else {
            this.height = 0;
        }
    }

    public void setDimensions(Integer width, Integer height) {
        setWidth(width);
        setHeight(height);
    }

}
