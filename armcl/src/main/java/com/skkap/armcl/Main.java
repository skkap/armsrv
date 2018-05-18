package com.skkap.armcl;
import org.apache.thrift.TException;

import com.linecorp.armeria.client.Clients;
import com.linecorp.armeria.common.thrift.ThriftCompletableFuture;

import com.skkap.armcl.thrift.UserService;

public class Main {
    public static void main(String[] args) {
        try {
            UserService.AsyncIface userService = Clients.newClient(
                    "tbinary+http://127.0.0.1:8080/user",
                    UserService.AsyncIface.class);

            ThriftCompletableFuture<String> future = new ThriftCompletableFuture<String>();
            userService.get("Slava", future);

            // You can also wait until the call is finished.
            String reply = future.get();

            System.out.println(reply);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
