package com.example.p7mvp.threadFactory;

public interface ThreadFactoryInterface {
    void createThread(Runnable runnable);
    void removeThread();
}
