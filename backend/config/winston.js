const winston = require('winston');

const timezone = () =>
  new Date().toLocaleString('en-US', {
    timeZone: 'Asia/Kolkata',
  });

const logger = winston.createLogger({
  transports: [
    new winston.transports.File({
      level: 'info',
      filename: './logs/server.log',
      handleExceptions: true,
      json: true,
      maxsize: 5242880, // 5MB
      maxFiles: 5,
      colorize: true,
    }),
    new winston.transports.Console({
      level: 'debug',
      handleExceptions: true,
      json: false,
      colorize: true,
    }),
  ],
  format: winston.format.combine(
    winston.format.simple(),
    winston.format.timestamp({
      format: timezone,
    }),
    winston.format.printf(
      (info) => `[${info.timestamp}] ${info.level}: ${info.message}`,
    ),
  ),
  exitOnError: false,
});

logger.stream = {
  write(message) {
    logger.info(message);
  },
};
module.exports = logger;