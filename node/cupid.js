const express = require('express')
const app = express()

const sortDouble = () => {
	const arr = [];
	for (let i = 0; i < 10000; i++) {
		arr.push(Math.random());
	}
	arr.sort( (a, b) => a - b);
}

app.get('/', (req, res) => {
	new Promise( resolve => {
		setTimeout( () => {
			sortDouble();
			resolve();
		}, 10)
	})
	.then( result => {
		res.send("Hello World");
	})
})

app.listen(8080, () => {
  console.log('Example app listening on port 8080!')
})