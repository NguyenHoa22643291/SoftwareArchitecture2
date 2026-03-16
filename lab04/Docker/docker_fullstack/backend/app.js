const express = require('express');
const { Pool } = require('pg');
const app = express();

// Kết nối DB (Dùng tên service 'db' trong docker-compose)
const pool = new Pool({
    user: 'postgres',
    host: 'db',
    database: 'postgres',
    password: 'password123',
    port: 5432,
});

app.get('/api/data', async (req, res) => {
    res.json({ message: "Dữ liệu đã đi qua Lá chắn và Back-end thành công!" });
});

app.listen(5000, () => console.log('BE running on 5000'));