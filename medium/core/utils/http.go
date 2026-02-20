package utils

import (
	"bytes"
	"encoding/json"
	"io"
	"log"
	"net/http"
	"time"
)

const (
	TimeOutSeconds = 2
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
	MethodNotAllowed    string
	InvalidCredentials  string
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
		MethodNotAllowed:    "Method Not Allowed",
		InvalidCredentials:  "Invalid Credentials",
	}

func WithMethod(method string, h http.HandlerFunc) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		if r.Method != method {
			http.Error(w, HttpMsg.MethodNotAllowed, http.StatusMethodNotAllowed)
			return
		}

		h(w, r)
	}
}

type loggingResponseWriter struct {
	http.ResponseWriter
	statusCode int
}

func (lrw *loggingResponseWriter) WriteHeader(code int) {
	lrw.statusCode = code
	lrw.ResponseWriter.WriteHeader(code)
}

func WithLogger(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		start := time.Now()

		var bodyBytes []byte
		if r.Body != nil {
			bodyBytes, _ = io.ReadAll(r.Body)
			r.Body = io.NopCloser(bytes.NewBuffer(bodyBytes))
		}

		lrw := &loggingResponseWriter{
			ResponseWriter: w,
			statusCode:     http.StatusOK,
		}

		next.ServeHTTP(lrw, r)

		log.Printf(
			"time=%s method=%s path=%s status=%d duration=%s ip=%s referrer=%q body=%s",
			time.Now().Format(time.RFC3339),
			r.Method,
			r.URL.Path,
			lrw.statusCode,
			time.Since(start),
			r.RemoteAddr,
			r.Referer(),
			formatJSON(bodyBytes),
		)
	})
}

func formatJSON(b []byte) string {
	if len(b) == 0 {
		return ""
	}

	var out bytes.Buffer
	if err := json.Indent(&out, b, "", "  "); err != nil {
		return string(b)
	}

	return out.String()
}

func getStatusColor(status int) string {
	switch {
	case status >= 200 && status < 300:
		return "\033[32m" // Green
	case status >= 300 && status < 400:
		return "\033[36m" // Cyan
	case status >= 400 && status < 500:
		return "\033[33m" // Yellow
	default:
		return "\033[31m" // Red
	}
}

func truncate(s string, maxLen int) string {
	if len(s) <= maxLen {
		return s
	}
	return s[:maxLen] + "..."
}

// Response methods
func WriteRespFromMap (w http.ResponseWriter, values map[string]interface{}) {
	json.NewEncoder(w).Encode(values)
}
