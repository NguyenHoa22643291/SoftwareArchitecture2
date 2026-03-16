import os

# Hàm os.getenv sẽ lấy giá trị của biến APP_ENV từ hệ thống
# Nếu không tìm thấy, nó sẽ lấy giá trị mặc định là 'Not Set'
app_env = os.getenv('APP_ENV', 'Not Set')

print("--------------------------------")
print(f"Giá trị biến môi trường APP_ENV là: {app_env}")
print("--------------------------------")