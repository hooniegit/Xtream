package com.hooniegit.Xtream.Modules.Stream.Sample;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SampleClass implements Serializable {
    private String name;
    private String email;
}
