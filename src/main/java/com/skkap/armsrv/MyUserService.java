package com.skkap.armsrv;

import org.apache.thrift.async.AsyncMethodCallback;

import com.skkap.armsrv.thrift.UserService;

public class MyUserService implements UserService.AsyncIface {
    @Override
    public void get(String name, AsyncMethodCallback<String> resultHandler) {
        resultHandler.onComplete("Hello, " + name + '!');
    }
}
