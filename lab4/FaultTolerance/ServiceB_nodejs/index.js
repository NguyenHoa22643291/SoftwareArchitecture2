const express = require('express');
const app = express();
const PORT = 3000;

// API để Spring Boot gọi sang
app.get('/target', (req, res) => {
    const chance = Math.random();

    // Giả lập lỗi: 50% tỉ lệ trả về lỗi 500 để test Circuit Breaker/Retry
    if (chance < 0.5) {
        console.log("=> Giả lập lỗi 500!");
        return res.status(500).send("Node.js bị lỗi rồi!");
    }

    console.log("=> Trả về dữ liệu thành công!");
    res.json({ message: "Hello từ Node.js!" });
});

app.listen(PORT, () => console.log(`Node.js đang chạy tại port ${PORT}`));