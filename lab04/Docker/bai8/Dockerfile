FROM postgres:15

# Thiết lập mật khẩu mặc định cho Postgres (Bắt buộc phải có)
ENV POSTGRES_PASSWORD=mysecretpassword

# Copy file SQL vào thư mục đặc biệt để tự động chạy khi khởi tạo
COPY init.sql /docker-entrypoint-initdb.d/