console.log(`
****************************************************
                  You Will Win!
****************************************************`);

const fs                   = require("fs");
const async                = require("async");
const path                 = require("path");

const BALL_ROLLING_SPEED   = 2;
const BALL_PICKING_TIME    = 100;

const MAX_RUNNING_TIME     = 1000;

const GRAND_PRIZE_ODDS     = 1 / 292201338;
const WIN_1_MILLION_ODDS   = 1 / 11688053.52;
const WIN_50_THOUSAND_ODDS = 1 / 913129.18;
const WIN_1_HUNDRED_ODDS   = 1 / 36525.17 + 1 / 14494.11;

const getRedBall           = () => Math.round(Math.random() * 25) + 1;

const getWhiteBall         = () => Math.round(Math.random() * 68) + 1;

const stopAndPickBall      = interval => clearInterval(interval);

const am_i_gonna_win       = () => {
  const chance = Math.random();
  if (chance <= GRAND_PRIZE_ODDS) return "GRAND PRIZE"
  else if (chance <= WIN_1_MILLION_ODDS) return "ONE MILLION"
  else if (chance <= WIN_50_THOUSAND_ODDS) return "FIFTY THOUSAND"
  else if (chance <= WIN_1_HUNDRED_ODDS) return "ONE HUNDRED"
  else return ""
}

const WIN_WIN_WIN = require("./YOU_ARE_THE_NEXT_MILLIONAIRE.json");

const getPowerball = (callback) => {
  const red_ball_list = [];
  const white_ball_list = [];
  Array(5).fill(0).reduce((accum, nextBall) => {
    return accum.then( () => {
      const getLucky = new Promise( resolve => {
        let white_ball = 0;
        let get_white_ball_rolling = setInterval( () => {
          white_ball = getWhiteBall();
        }, Math.random() * BALL_ROLLING_SPEED);
        setTimeout(() => {
          console.log("\u25CB --> \u25CB --> \u25CB --> \u25CB --> \u25CB --> \u25CB");
          stopAndPickBall(get_white_ball_rolling);
          while (white_ball_list.indexOf(white_ball) !== -1) white_ball = getWhiteBall();
          white_ball_list.push(white_ball);
          resolve();
        }, Math.random() * BALL_PICKING_TIME);
      })
      return getLucky
    })
  }, new Promise( resolve => resolve()))
  .then( () => {
    return new Promise( resolve => {
       let red_ball = 0;
       let get_red_ball_rolling = setInterval( () => {
         red_ball = getRedBall();
       }, Math.random() * BALL_ROLLING_SPEED);
       setTimeout(() => {
          console.log("\uD83D\uDD34 --> \uD83D\uDD34 --> \uD83D\uDD34 --> \uD83D\uDD34 --> \uD83D\uDD34 --> \uD83D\uDD34");
         stopAndPickBall(get_red_ball_rolling);
         red_ball_list.push(red_ball);
         resolve();
       }, Math.random() * BALL_PICKING_TIME);
    })
  })
  .then( () => {
    const $MONEY$ = am_i_gonna_win();
    if ($MONEY$) {
      console.log(`
        $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

            You WILL WIN ${$MONEY$} $$$$$$$$$!!! Congrats!!!

        $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
      `)
      WIN_WIN_WIN[$MONEY$].push({ white_ball_list, red_ball_list });
    } 
    callback();
  })
  .catch( err => {
    console.log("Never make mistake on getting big money!!!");
    console.log(err);
    callback(err);
  });
}

let count = 0;
const powerballCB = (err) => {
  if (err) {
    console.log(err);
  } else if (count === MAX_RUNNING_TIME) {
    console.log("WIN WIN WIN");
    fs.writeFile(path.join(__dirname, "YOU_ARE_THE_NEXT_MILLIONAIRE.json"), JSON.stringify(WIN_WIN_WIN), err => {
      if (err) throw err;
      process.exit();
    })
  } else {
    console.log(count++);
    getPowerball(powerballCB);
  }
}

getPowerball(powerballCB);