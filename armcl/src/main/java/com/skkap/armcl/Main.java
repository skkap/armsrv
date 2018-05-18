package com.skkap.armcl;
import com.linecorp.armeria.client.Clients;
import com.linecorp.armeria.common.thrift.ThriftCompletableFuture;

import com.skkap.armcl.thrift.UserService;

public class Main {
    public static void main(String[] args) throws Exception{
       UserService.AsyncIface userService = Clients.newClient(
                "tbinary+http://127.0.0.1:8080/user",
                UserService.AsyncIface.class);

        ThriftCompletableFuture<String> future = new ThriftCompletableFuture<String>();
        userService.get("Slava", future);

        future.exceptionally(cause -> {
            cause.printStackTrace();
            return null;
        });

        // You can also wait until the call is finished.
        String reply = future.get();

        System.out.println(reply);
    }
}
