package com.math;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: math.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MathServiceGrpc {

  private MathServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "math.MathService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.math.NumberRequest,
      com.math.NumberReply> getSqrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Sqr",
      requestType = com.math.NumberRequest.class,
      responseType = com.math.NumberReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.math.NumberRequest,
      com.math.NumberReply> getSqrMethod() {
    io.grpc.MethodDescriptor<com.math.NumberRequest, com.math.NumberReply> getSqrMethod;
    if ((getSqrMethod = MathServiceGrpc.getSqrMethod) == null) {
      synchronized (MathServiceGrpc.class) {
        if ((getSqrMethod = MathServiceGrpc.getSqrMethod) == null) {
          MathServiceGrpc.getSqrMethod = getSqrMethod =
              io.grpc.MethodDescriptor.<com.math.NumberRequest, com.math.NumberReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Sqr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.math.NumberRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.math.NumberReply.getDefaultInstance()))
              .setSchemaDescriptor(new MathServiceMethodDescriptorSupplier("Sqr"))
              .build();
        }
      }
    }
    return getSqrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.math.NumberRequest,
      com.math.NumberReply> getFactMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Fact",
      requestType = com.math.NumberRequest.class,
      responseType = com.math.NumberReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.math.NumberRequest,
      com.math.NumberReply> getFactMethod() {
    io.grpc.MethodDescriptor<com.math.NumberRequest, com.math.NumberReply> getFactMethod;
    if ((getFactMethod = MathServiceGrpc.getFactMethod) == null) {
      synchronized (MathServiceGrpc.class) {
        if ((getFactMethod = MathServiceGrpc.getFactMethod) == null) {
          MathServiceGrpc.getFactMethod = getFactMethod =
              io.grpc.MethodDescriptor.<com.math.NumberRequest, com.math.NumberReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Fact"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.math.NumberRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.math.NumberReply.getDefaultInstance()))
              .setSchemaDescriptor(new MathServiceMethodDescriptorSupplier("Fact"))
              .build();
        }
      }
    }
    return getFactMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.math.NumberRequest,
      com.math.NumberReply> getFibMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Fib",
      requestType = com.math.NumberRequest.class,
      responseType = com.math.NumberReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.math.NumberRequest,
      com.math.NumberReply> getFibMethod() {
    io.grpc.MethodDescriptor<com.math.NumberRequest, com.math.NumberReply> getFibMethod;
    if ((getFibMethod = MathServiceGrpc.getFibMethod) == null) {
      synchronized (MathServiceGrpc.class) {
        if ((getFibMethod = MathServiceGrpc.getFibMethod) == null) {
          MathServiceGrpc.getFibMethod = getFibMethod =
              io.grpc.MethodDescriptor.<com.math.NumberRequest, com.math.NumberReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Fib"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.math.NumberRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.math.NumberReply.getDefaultInstance()))
              .setSchemaDescriptor(new MathServiceMethodDescriptorSupplier("Fib"))
              .build();
        }
      }
    }
    return getFibMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MathServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MathServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MathServiceStub>() {
        @java.lang.Override
        public MathServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MathServiceStub(channel, callOptions);
        }
      };
    return MathServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MathServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MathServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MathServiceBlockingStub>() {
        @java.lang.Override
        public MathServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MathServiceBlockingStub(channel, callOptions);
        }
      };
    return MathServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MathServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MathServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MathServiceFutureStub>() {
        @java.lang.Override
        public MathServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MathServiceFutureStub(channel, callOptions);
        }
      };
    return MathServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void sqr(com.math.NumberRequest request,
        io.grpc.stub.StreamObserver<com.math.NumberReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSqrMethod(), responseObserver);
    }

    /**
     */
    default void fact(com.math.NumberRequest request,
        io.grpc.stub.StreamObserver<com.math.NumberReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFactMethod(), responseObserver);
    }

    /**
     */
    default void fib(com.math.NumberRequest request,
        io.grpc.stub.StreamObserver<com.math.NumberReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFibMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service MathService.
   */
  public static abstract class MathServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MathServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service MathService.
   */
  public static final class MathServiceStub
      extends io.grpc.stub.AbstractAsyncStub<MathServiceStub> {
    private MathServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MathServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MathServiceStub(channel, callOptions);
    }

    /**
     */
    public void sqr(com.math.NumberRequest request,
        io.grpc.stub.StreamObserver<com.math.NumberReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSqrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void fact(com.math.NumberRequest request,
        io.grpc.stub.StreamObserver<com.math.NumberReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFactMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void fib(com.math.NumberRequest request,
        io.grpc.stub.StreamObserver<com.math.NumberReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFibMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service MathService.
   */
  public static final class MathServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MathServiceBlockingStub> {
    private MathServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MathServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MathServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.math.NumberReply sqr(com.math.NumberRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSqrMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.math.NumberReply fact(com.math.NumberRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFactMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.math.NumberReply fib(com.math.NumberRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFibMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service MathService.
   */
  public static final class MathServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<MathServiceFutureStub> {
    private MathServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MathServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MathServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.math.NumberReply> sqr(
        com.math.NumberRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSqrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.math.NumberReply> fact(
        com.math.NumberRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFactMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.math.NumberReply> fib(
        com.math.NumberRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFibMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SQR = 0;
  private static final int METHODID_FACT = 1;
  private static final int METHODID_FIB = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SQR:
          serviceImpl.sqr((com.math.NumberRequest) request,
              (io.grpc.stub.StreamObserver<com.math.NumberReply>) responseObserver);
          break;
        case METHODID_FACT:
          serviceImpl.fact((com.math.NumberRequest) request,
              (io.grpc.stub.StreamObserver<com.math.NumberReply>) responseObserver);
          break;
        case METHODID_FIB:
          serviceImpl.fib((com.math.NumberRequest) request,
              (io.grpc.stub.StreamObserver<com.math.NumberReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSqrMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.math.NumberRequest,
              com.math.NumberReply>(
                service, METHODID_SQR)))
        .addMethod(
          getFactMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.math.NumberRequest,
              com.math.NumberReply>(
                service, METHODID_FACT)))
        .addMethod(
          getFibMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.math.NumberRequest,
              com.math.NumberReply>(
                service, METHODID_FIB)))
        .build();
  }

  private static abstract class MathServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MathServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.math.Math.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MathService");
    }
  }

  private static final class MathServiceFileDescriptorSupplier
      extends MathServiceBaseDescriptorSupplier {
    MathServiceFileDescriptorSupplier() {}
  }

  private static final class MathServiceMethodDescriptorSupplier
      extends MathServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    MathServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MathServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MathServiceFileDescriptorSupplier())
              .addMethod(getSqrMethod())
              .addMethod(getFactMethod())
              .addMethod(getFibMethod())
              .build();
        }
      }
    }
    return result;
  }
}
