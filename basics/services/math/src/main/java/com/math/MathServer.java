package com.math;

import io.grpc.ServerBuilder;
import io.grpc.Server;
import io.grpc.stub.StreamObserver;

public class MathServer {
    int port = 50051;

    public void start() throws Exception {
        Server server = ServerBuilder.forPort(this.port).addService(new MathServiceImpl()).build();
        System.out.println("Math server started, listening on " + this.port);
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down math server as jvm is shutting down...");
            server.shutdown();
            System.err.println("Math server shut down...");
        }));

        server.awaitTermination();
    }
}

class MathServiceImpl extends MathServiceGrpc.MathServiceImplBase {
    @Override
    public void sqr(NumberRequest req, StreamObserver<NumberReply> res) {
        int number = req.getNumber();
        long result = MathMethods.square(number);
        NumberReply reply = NumberReply.newBuilder().setNumber(result).build();
        res.onNext(reply);
        res.onCompleted();
    }

    @Override
    public void fib(NumberRequest req, StreamObserver<NumberReply> res) {
        int number = req.getNumber();
        long result = MathMethods.fib(number);
        NumberReply reply = NumberReply.newBuilder().setNumber(result).build();
        res.onNext(reply);
        res.onCompleted();
    }

    @Override
    public void fact(NumberRequest req, StreamObserver<NumberReply> res) {
        int n = req.getNumber();
        long result = MathMethods.factorial(n);
        NumberReply nr = NumberReply.newBuilder().setNumber(result).build();
        res.onNext(nr);
        res.onCompleted();
    }

};

class MathMethods {
    static long factorial(int n) {
        long factorial = 1;

        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }

        return factorial;
    }

    static long square(int n) {
        return n * n;
    }

    static long fib(int n) {
        long[] mem = new long[n+1];
        mem[0] = 0;
        mem[1] = 1;

        for (int i = 2; i <= n; i++) {
            mem[i] = mem[i-1] + mem[i-2];
        }

        return mem[n];
    }
};
