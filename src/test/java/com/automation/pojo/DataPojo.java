package com.automation.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DataPojo {
    int year;
    double price;
    @JsonProperty("CPU model")
    String CPU_model;
    @JsonProperty("Hard_disk_size")
    String harddiskize;
}
