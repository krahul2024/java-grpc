package main

import (
	"core/handlers"
	"core/protobuf"
	"core/utils"
	"fmt"
	"log"
	"net/http"

	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
)

const (
	PORT           = 4000
	grpcServerAddr = 50051
)

func main() {

	conn := initGrpClient()
	defer conn.Close()

	// auth routes
	http.HandleFunc("/auth/login",    utils.WithMethod(http.MethodPost, handlers.Auth.Login))
	http.HandleFunc("/auth/register", utils.WithMethod(http.MethodPost, handlers.Auth.Register))
	http.HandleFunc("/auth/logout",   utils.WithMethod(http.MethodGet, handlers.Auth.Logout))

	// not found
	http.HandleFunc("/", notFound)

	log.Printf("Starting the server, at localhost:%v", PORT)

	if err := http.ListenAndServe(fmt.Sprintf(":%v", PORT), utils.WithLogger(http.DefaultServeMux)); err != nil {
		log.Fatalf("Error starting the server, %v\n", err)
	}
}

func initGrpClient () *grpc.ClientConn {
	conn, err := grpc.NewClient(fmt.Sprintf ("localhost:%v", grpcServerAddr), grpc.WithTransportCredentials(insecure.NewCredentials()))
	if err != nil {
		log.Fatalf ("couldn't connect: %v\n", err)
	}

	utils.GrpClient = protobuf.NewAuthServiceClient(conn)
	return conn
}

func notFound(w http.ResponseWriter, r *http.Request) {
	http.Error (w, utils.HttpMsg.NotFound, http.StatusNotFound)
}
