const express = require('express')
const app = express()

const sort10000Double = () => {
	const arr = [];
	for (let i = 0; i < 10000; i++) {
		const posOrNeg = Math.round(Math.random()) * 2 - 1;
		arr.push(Math.random() * posOrNeg * i * 2);
	}
	arr.sort( (a, b) => a - b);
}

app.get('/', (req, res) => {
	new Promise( resolve => {
		setTimeout( () => {
			sort10000Double();
			sort10000Double();
			resolve();
		}, 10)
	})
	.then( result => {
		res.send("Hello World");
	})
})

app.listen(666, () => {
  console.log('Example app listening on port 666!')
})