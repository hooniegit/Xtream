package com.hooniegit.Xtream.Modules.Stream.Sample;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class One implements Serializable {
    private String name;
    private Integer age;
}
