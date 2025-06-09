const express = require('express');
const app = express();
const port = 3001;

app.get('/estado', (req, res) => {
  res.json({ estado: 'pausado' });
});

app.listen(port, () => {
  console.log(`SAP Mock listening at http://localhost:${port}`);
});