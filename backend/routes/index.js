const router = require("express").Router();
const projectRouteController = require('./project_controller');

router.use("/projects", projectRouteController);


module.exports = router;