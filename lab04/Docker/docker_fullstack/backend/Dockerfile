# Bước 1: Dùng image Node.js bản nhẹ
FROM node:18-alpine

# Bước 2: Tạo thư mục làm việc trong container
WORKDIR /app

# Bước 3: Copy file định nghĩa thư viện vào trước
COPY package.json ./

# Bước 4: Cài đặt các thư viện (express, pg)
RUN npm install

# Bước 5: Copy toàn bộ code (file app.js) vào
COPY . .

# Bước 6: Back-end chạy cổng 5000
EXPOSE 5000

# Bước 7: Lệnh để khởi động server
CMD ["node", "app.js"]