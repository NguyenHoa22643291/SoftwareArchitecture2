const mysql = require('mysql2');

const connection = mysql.createConnection({
  host: 'db', // Tên service trong docker-compose
  user: 'user',
  password: 'password',
  database: 'mydb'
});

connection.connect((err) => {
  if (err) {
    console.error('Kết nối thất bại: ' + err.stack);
    return;
  }
  console.log('Chúc mừng Hoa! Kết nối MySQL thành công với ID: ' + connection.threadId);
});