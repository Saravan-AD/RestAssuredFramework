package com.automation.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CreateResponsePojo {
    String id;
    String name;
    DataPojo data;
    String createdAt;
}
