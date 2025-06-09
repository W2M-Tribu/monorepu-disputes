const express = require('express');
const app = express();
const port = 3002;

app.get('/categoria', (req, res) => {
  res.json({ categoria: 'número de días de vacaciones incorrecto' });
});

app.listen(port, () => {
  console.log(`IA Mock listening at http://localhost:${port}`);
});