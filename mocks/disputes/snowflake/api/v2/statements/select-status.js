module.exports = request.body && request.body.indexOf('SELECT') !== -1
    ? 'HTTP/1.1 200 OK'
    : 'HTTP/1.1 404 Not Found';
