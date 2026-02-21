package handlers

import (
	"context"
	"core/protobuf"
	"core/utils"
	"encoding/json"
	"net/http"
	"time"
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
		utils.HandleGrpcError(w, err)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)

	utils.WriteRespFromMap(w, map[string]interface{}{
		"token": res.GetToken(),
		"validUpto": res.GetValidUpto().AsTime(),
	})
}

func register(w http.ResponseWriter, r *http.Request) {
	var body struct {
		UserName string `json:"userName"`
		Name string `json:"name"`
		Password string `json:"password"`
	}

	if err := parseRequestBody(r, &body); err != nil {
		utils.WriteErrorJSON(w, err, http.StatusBadRequest)
		return
	}

	ctx, cancel := context.WithTimeout(context.Background(), utils.TimeOutSeconds * time.Second)
	defer cancel()

	res, err := utils.GrpClient.Register(
		ctx,
		&protobuf.RegisterRequest{
			UserName: body.UserName,
			Password: body.Password,
			Name: body.Name,
		},
		)

	if err != nil {
		utils.HandleGrpcError(w, err)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusCreated)

	utils.WriteRespFromMap (w, map[string]interface{}{
		"success": res.GetSuccess(),
	})
}

func logout (w http.ResponseWriter, r *http.Request) {
}
