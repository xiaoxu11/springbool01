package com.qf.dto;

public class EITreeDTO {
    private Integer id;
    private String text;
    private String state;
    private Object children;

    public EITreeDTO() {
    }

    public EITreeDTO(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public EITreeDTO(Integer id, String text, String state) {
        this.id = id;
        this.text = text;
        this.state = state;
    }

    public EITreeDTO(Integer id, String text, String state, Object children) {
        this.id = id;
        this.text = text;
        this.state = state;
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getChildren() {
        return children;
    }

    public void setChildren(Object children) {
        this.children = children;
    }
}
