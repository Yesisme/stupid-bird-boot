package com.epsoft.demo.pattern.factory.factroyMethod;

public class NoteVideo implements Video{
    @Override
    public void produce() {
        System.out.println("开始录制笔记视频");
    }

    @Override
    public String trade(String id) {
        return null;
    }
}
