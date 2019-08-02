package com.serverless.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import javax.xml.bind.annotation.XmlRootElement;

@DynamoDBTable(tableName = "Book")
@XmlRootElement
public class Book {

    private Long id;
    private String name;
    private Double value;
    private String description;
    private String author;

    public Book() {
    }

    public Book(Long id, String name, Double value, String description, String author) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.description = description;
        this.author = author;
    }

    @DynamoDBHashKey(attributeName = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "value")
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}