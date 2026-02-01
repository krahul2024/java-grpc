package main

import (
	"core/handlers"
	"core/utils"
	"fmt"
	"log"
	"net/http"
)

const (
	PORT = 4000
)

func main() {

	// auth routes
	http.HandleFunc("/auth/login",    utils.WithMethod(http.MethodPost, handlers.Auth.Login))
	http.HandleFunc("/auth/register", utils.WithMethod(http.MethodPost, handlers.Auth.Register))
	http.HandleFunc("/auth/logout",   utils.WithMethod(http.MethodGet, handlers.Auth.Logout))

	http.HandleFunc("/",              notFound)

	log.Printf("Starting the server, at localhost:%v", PORT)

	if err := http.ListenAndServe(fmt.Sprintf(":%v", PORT), utils.WithLogger(http.DefaultServeMux)); err != nil {
		log.Fatalf("Error starting the server, %v\n", err)
	}
}

func notFound(w http.ResponseWriter, r *http.Request) {
	http.Error (w, utils.HttpMsg.NotFound, http.StatusNotFound)
}
