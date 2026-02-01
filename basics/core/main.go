package main

import (
	"context"
	"core/protobuf"
	"encoding/json"
	"fmt"
	"log"
	"net/http"

	"google.golang.org/grpc"
	"google.golang.org/grpc/codes"
	"google.golang.org/grpc/credentials/insecure"
	"google.golang.org/grpc/status"
)

const (
	serverAddr = "localhost:50051"
)

var HttpMsg = struct {
	InternalServerError string
	BadRequest          string
	NotFound            string
	Conflict            string
	PreconditionFailed  string
	Unauthorized        string
	Forbidden           string
	ServiceUnavailable  string
	GatewayTimeout      string
}{
		InternalServerError: "Internal server error",
		BadRequest:          "Bad request",
		NotFound:            "Not found",
		Conflict:            "Conflict",
		PreconditionFailed:  "Precondition failed",
		Unauthorized:        "Unauthorized",
		Forbidden:           "Forbidden",
		ServiceUnavailable:  "Service unavailable",
		GatewayTimeout:      "Service timeout",
	}

var client protobuf.MathServiceClient
var grpcToHTTP = map[codes.Code]int{
	codes.InvalidArgument:    http.StatusBadRequest,
	codes.NotFound:           http.StatusNotFound,
	codes.AlreadyExists:      http.StatusConflict,
	codes.FailedPrecondition: http.StatusPreconditionFailed,
	codes.DeadlineExceeded:   http.StatusGatewayTimeout,
	codes.Unavailable:        http.StatusServiceUnavailable,
	codes.Unauthenticated:    http.StatusUnauthorized,
	codes.PermissionDenied:   http.StatusForbidden,
}

func initGrpcClient() *grpc.ClientConn {
	conn, err := grpc.NewClient(serverAddr, grpc.WithTransportCredentials(insecure.NewCredentials()))
	if err != nil {
		log.Fatalf("Couldn't connect: %v", err)
	}
	client = protobuf.NewMathServiceClient(conn)
	return conn
}

func main() {
	PORT := 3000
	conn := initGrpcClient()
	defer conn.Close()

	http.HandleFunc("/sqr", sqrHandler)
	http.HandleFunc("/fact", factHandler)
	http.HandleFunc("/fib", fibHandler)

	log.Println("Server is running at localhost:", PORT)
	if err := http.ListenAndServe(fmt.Sprintf(":%v", PORT), nil); err != nil {
		log.Fatalf("Error starting the server, %v", err)
	}
}

func sqrHandler(w http.ResponseWriter, r *http.Request) {
	if methodCheck(w, r, "POST") {
		var reqBody struct {
			Number int32 `json:"number"`
		}

		if err := parseRequestBody(r, &reqBody); err != nil {
			http.Error(w, "Invalid input", http.StatusBadRequest)
			return
		}

		log.Printf("Got request: sqrHandler; number = %d\n", reqBody.Number);

		res, err := client.Sqr(context.Background(), &protobuf.NumberRequest{Number: reqBody.Number})
		if err != nil {
			http.Error(w, "Error calling gRPC service", http.StatusInternalServerError)
			return
		}
		fmt.Fprintf(w, "Square result: %d\n", res.GetNumber())
	}
}

func factHandler(w http.ResponseWriter, r *http.Request) {
	if methodCheck(w, r, "POST") {
		var reqBody struct {
			Number int32 `json:"number"`
		}
		if err := parseRequestBody(r, &reqBody); err != nil {
			http.Error(w, "Invalid input", http.StatusBadRequest)
			return
		}

		log.Printf("Got request: factHandler; number = %d\n", reqBody.Number);

		// TODO: use ctx = context.Background() and then defer, pass this ctx
		res, err := client.Fact(context.Background(), &protobuf.NumberRequest{
			Number: reqBody.Number,
		})

		if err != nil {
			st, ok := status.FromError(err)
			if !ok {
				http.Error(w, HttpMsg.InternalServerError, http.StatusInternalServerError)
				return
			}

			msg := st.Message()
			code, ok := grpcToHTTP[st.Code()]
			if !ok {
				msg, code = HttpMsg.InternalServerError, http.StatusInternalServerError
			}

			http.Error(w, msg, code)
			return
		}

		fmt.Fprintf(w, "Factorial result: %d\n", res.GetNumber())
	}
}

func fibHandler(w http.ResponseWriter, r *http.Request) {
	if methodCheck(w, r, "POST") {
		var reqBody struct {
			Number int32 `json:"number"`
		}
		if err := parseRequestBody(r, &reqBody); err != nil {
			http.Error(w, "Invalid input", http.StatusBadRequest)
			return
		}
		log.Printf("Got request: fibHandler; number = %d\n", reqBody.Number);

		res, err := client.Fib(context.Background(), &protobuf.NumberRequest{Number: reqBody.Number})
		if err != nil {
			st, ok := status.FromError(err)
			if !ok {
				http.Error (w, HttpMsg.InternalServerError, http.StatusInternalServerError)
				return
			}

			msg := st.Message()
			code, ok := grpcToHTTP[st.Code()]

			if !ok {
				msg, code = HttpMsg.InternalServerError, http.StatusInternalServerError
			}

			http.Error(w, msg, code)
			return
		}

		fmt.Fprintf(w, "Fib result: %d\n", res.GetNumber())
	}
}

func methodCheck(w http.ResponseWriter, r *http.Request, targetMethod string) bool {
	if r.Method != targetMethod {
		http.Error(w, "Method not allowed. Use POST.", http.StatusMethodNotAllowed)
		return false
	}
	return true
}

func parseRequestBody(r *http.Request, reqBody any) error {
	decoder := json.NewDecoder(r.Body)
	return decoder.Decode(reqBody)
}
