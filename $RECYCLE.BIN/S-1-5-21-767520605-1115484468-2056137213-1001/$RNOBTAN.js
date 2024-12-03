const express = require("express")
const router = express.Router()
const bcrypt = require("bcryptjs")
const { User } = require("../models") // Adjust if necessary

// Register a new user
router.post("/register", async (req, res) => {
  try {
    const { username, email, password } = req.body

    if (!username || !email || !password) {
      return res.status(400).json({ error: "All fields are required" })
    }

    const hashedPassword = await bcrypt.hash(password, 10)

    const newUser = await User.create({
      username,
      email,
      password: hashedPassword,
    })

    res
      .status(201)
      .json({ message: "User registered successfully", user: newUser })
  } catch (error) {
    console.error(error)
    res.status(500).json({ error: "User registration failed" })
  }
})

module.exports = router
