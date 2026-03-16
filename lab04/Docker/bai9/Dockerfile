FROM redis:latest

# Copy file cấu hình vào container
COPY redis.conf /usr/local/etc/redis/redis.conf

# Chỉ định Redis chạy kèm với file cấu hình vừa copy
CMD [ "redis-server", "/usr/local/etc/redis/redis.conf" ]