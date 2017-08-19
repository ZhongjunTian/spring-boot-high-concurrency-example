const http = require('http')
      cluster = require('cluster'),
      numCPUs = require('os').cpus().length

const sort10000Double = () => {
	const arr = [];
	for (let i = 0; i < 10000; i++) {
		arr.push(Math.random());
	}
	arr.sort( (a, b) => a - b);
}

if (cluster.isMaster) {
    console.log(`Master ${process.pid} is running`);

      // Fork workers.
  	for (let i = 0; i < numCPUs; i++) {
    	cluster.fork();
  	}

  	cluster.on('exit', (worker, code, signal) => {
    	console.log(`worker ${worker.process.pid} died`);
  	});
} else {
	http.createServer((req, res) => {
		console.log('Example app listening on port 8000!')
	    new Promise( resolve => {
	    	setTimeout( () => {
	    		sort10000Double();
	    		sort10000Double();
	    		resolve();
	    	}, 10)
	    })
	    .then( result => {
	    	res.writeHead(200);
	    	res.end("Hello World");
	    })
	}).listen(8000);
	console.log(`Worker ${process.pid} started`);
}