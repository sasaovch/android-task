package ru.sample.duckapp.service

import ru.sample.duckapp.domain.DuckContainer

interface UserInputService {
    fun parseInput(code: String): DuckContainer
}