package handlers

import (
	"fmt"
	"net/http"
)

type AuthHandler struct {
	Login http.HandlerFunc
	Register http.HandlerFunc
	Logout http.HandlerFunc
}

var Auth = AuthHandler {
	Login: login,
	Register: register,
	Logout: logout,
}

func login(w http.ResponseWriter, r *http.Request) {
	fmt.Println("from login")
}

func register(w http.ResponseWriter, r *http.Request) {
}

func logout (w http.ResponseWriter, r *http.Request) {
}
