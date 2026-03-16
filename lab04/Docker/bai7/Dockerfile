# Sử dụng image python bản nhẹ (slim)
FROM python:3.9-slim

WORKDIR /app

# YÊU CẦU: Sử dụng ENV để thiết lập biến môi trường mặc định
ENV APP_ENV=development

COPY app.py .

# Chạy ứng dụng (Dùng -u để Python in log ra terminal ngay lập tức)
CMD ["python", "-u", "app.py"]