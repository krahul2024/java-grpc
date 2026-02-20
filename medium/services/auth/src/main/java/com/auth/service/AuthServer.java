package com.auth.service;

import java.time.Instant;

import com.auth.grpc.AuthServiceGrpc;
import com.auth.grpc.LoginReply;
import com.auth.grpc.LoginRequest;
import com.google.protobuf.Timestamp;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class AuthServer {
    int port = 50051;

    public void start() throws Exception {
        Server server = ServerBuilder.forPort (this.port).addService(new AuthServiceImpl()).build();
        System.out.println("Auth server started, listening on " + this.port);
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down Auth server as jvm is shutting down...");
            server.shutdown();
            System.err.println("Auth server shut down...");
        }));

        server.awaitTermination();
    }
}

class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {
    @Override
    public void login(LoginRequest loginReq, StreamObserver<LoginReply> loginRes) {
        String userName = loginReq.getUserName();
        String password = loginReq.getPassword();
        if (password.length() < 8) {
            loginRes.onError(
                Status.UNAUTHENTICATED
                .withDescription("Password too short, length should be atleast 8")
                .asRuntimeException()
            );

            return;
        }

        Instant now     = Instant.now();

        //NOTE: java follows this builder pattern apparently
        LoginReply logRep = LoginReply.newBuilder()
        .setToken("sometoken")
        .setValidUpto(Timestamp.newBuilder().setSeconds(now.getEpochSecond()).setNanos(0).build())
        .build();

        loginRes.onNext(logRep);
        loginRes.onCompleted();
    }
}
