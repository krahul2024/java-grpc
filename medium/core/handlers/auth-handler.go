package handlers

import (
	"context"
	"core/protobuf"
	"core/utils"
	"encoding/json"
	"net/http"
	"time"

	"google.golang.org/grpc/codes"
	"google.golang.org/grpc/status"
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

func parseRequestBody(r *http.Request, reqBody any) error {
	decoder := json.NewDecoder(r.Body)
	return decoder.Decode(reqBody)
}

func login(w http.ResponseWriter, r *http.Request) {
	var body struct {
		UserName string `json:"userName"`
		Password string `json:"password"`
	}

	if err := parseRequestBody(r, &body); err != nil {
		http.Error (w, utils.HttpMsg.BadRequest, http.StatusBadRequest)
		return
	}

	ctx, cancel := context.WithTimeout(context.Background(), utils.TimeOutSeconds * time.Second)
	defer cancel()

	res, err := utils.GrpClient.Login(
		ctx,
		&protobuf.LoginRequest{
			UserName: body.UserName,
			Password: body.Password,
		},
		)

	if err != nil {
		if status.Code(err) == codes.Unauthenticated {
			http.Error (w, utils.HttpMsg.InvalidCredentials, http.StatusUnauthorized)
			return
		}

		http.Error(w, utils.HttpMsg.InternalServerError, http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(map[string]interface{}{
		"token": res.GetToken(),
		"validUpto": res.GetValidUpto().AsTime(),
	})
}

func register(w http.ResponseWriter, r *http.Request) {
}

func logout (w http.ResponseWriter, r *http.Request) {
}
