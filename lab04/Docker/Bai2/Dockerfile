FROM python:3.9

WORKDIR /app

# Cài đặt thư viện Flask trực tiếp bằng lệnh RUN
RUN pip install flask

COPY app.py .

EXPOSE 5000

CMD ["python", "app.py"]