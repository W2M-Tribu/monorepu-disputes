package com.libreriaTest.snowflakeclient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProbarRequestDTO {
    private String url;
    private Map<String, Object> json;
}
