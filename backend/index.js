const express = require("express");
const morgan = require('morgan');
const bodyParser = require('body-parser');
const helmet = require('helmet');
const dotenv = require('dotenv');


const logger = require("./config/winston");


const app = express();
dotenv.config();
const PORT = process.env.PORT || 3000;

app.use(morgan("common", {stream: logger.stream}));
app.use(bodyParser.urlencoded({extended: false}));
app.use(helmet());

const route = require('./routes/index');

app.use(route);

app.use((err, req, res, next) => {
    logger.error(`${err}`);
    res.status(500).send('Something went wrong');
    next();
  });

app.listen(PORT, () => {
    logger.log('info', `Server running on http://localhost:${PORT}`);
});