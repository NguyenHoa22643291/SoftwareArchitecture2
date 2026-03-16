FROM golang:1.21-alpine

WORKDIR /app

# Copy code vào
COPY . .

# Biên dịch ứng dụng Go thành file tên là 'main'
RUN go mod init my-go-app && go build -o main .

# Mở cổng 8080
EXPOSE 8080

# Chạy file binary đã build
CMD ["./main"]