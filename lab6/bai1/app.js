const express = require('express');
const app= express();

app.get('/',(req, rep)=>
res.send('app running'));

app.listen(3000,()=>
console.log('server is running'));
