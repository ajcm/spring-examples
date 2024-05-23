package com.example;

public class MyWriter implements Writer {

    private MessageBean messageBean;

    public MyWriter(MessageBean messageBean) {
        this.messageBean = messageBean;
    }

    @Override
    public String write() {
        return this.messageBean.getMessage();
    }
}
