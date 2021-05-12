package com.micro.Faresearch.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fare {
    @Id
    private ObjectId _id;
    @NotNull(message = "flightno is mandatory")
    private String flightno;
    @Min(value=1, message="must be equal or greater than 1")
    private int fprice;
}
