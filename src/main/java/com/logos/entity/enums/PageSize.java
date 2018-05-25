package com.logos.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PageSize {

    ROW5(5), ROW10(10), ROW15(15), ROW20(20), ROW50(50);

    private int pageSize;

}