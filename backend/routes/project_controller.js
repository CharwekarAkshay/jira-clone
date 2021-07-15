const router = require("express").Router();
const {
    getProjectHandle,
} = require("../services/project_service");

router.get("/", getProjectHandle);


module.exports = router;