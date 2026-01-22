package main

import (
	"log"
	"net/http"
	"fmt"
)

func main() {
	PORT := 3000;

	http.HandleFunc("/sqr", sqrHandler)
	http.HandleFunc("/fact", factHandler)
	http.HandleFunc("/fib", fibHandler)

	log.Println("Server is running at localhost:", PORT);

	if err := http.ListenAndServe(fmt.Sprintf(":%v", PORT), nil); err != nil {
		log.Fatalf("Error starting the server, %v", err);
	}
}

func sqrHandler(w http.ResponseWriter, r *http.Request) {
	if (methodCheck(w, r, "POST")) {
		log.Println("square handler")
	}
}

func factHandler(w http.ResponseWriter, r *http.Request) {
	if (methodCheck(w, r, "POST")) {
		log.Println("fact handler")
	}

}
func fibHandler(w http.ResponseWriter, r *http.Request) {
	if (methodCheck(w, r, "POST")) {
		log.Println("fib handler")
	}
}

func methodCheck(w http.ResponseWriter, r *http.Request, targetMethod string) bool {
	if r.Method != targetMethod {
		http.Error(w, "Method not allowed. Use POST.", http.StatusMethodNotAllowed)
		return false
	}

	return true
}
