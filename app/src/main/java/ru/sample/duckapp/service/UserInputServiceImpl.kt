package ru.sample.duckapp.service

import ru.sample.duckapp.domain.DuckContainer

class UserInputServiceImpl : UserInputService {
    override fun parseInput(code: String): DuckContainer {
        return try {
            val httpCode = code.toInt()
            DuckContainer(httpCode)
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            DuckContainer(-1)
        }
    }
}